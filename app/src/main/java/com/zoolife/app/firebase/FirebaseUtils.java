package com.zoolife.app.firebase;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.zoolife.app.ResponseModel.GetPost.GetPostResponseModel;
import com.zoolife.app.ResponseModel.GetUserProfile.GetUserProfileResponseModel;
import com.zoolife.app.Session;
import com.zoolife.app.firebase.Collections;

import org.json.JSONException;
import org.json.JSONObject;

import com.zoolife.app.firebase.models.Group;
import com.zoolife.app.firebase.models.User;
import com.zoolife.app.firebase.models.Thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseUtils {

    private static String TAG = FirebaseUtils.class.getSimpleName();

    public static String FCM_URL = "https://fcm.googleapis.com/fcm/send";

    // User Presence

    /*public static void makeUserOnline(String databaseId) {
        if (FirebaseData.currentUser == null) {
            getCurrentUser(databaseId, new OnCompleteCallback() {
                @Override
                public void onComplete() {
                    Collections.usersRef.document(FirebaseData.currentUser.getUserId()).update(
                            User.ONLINE, true
                    );
                    Collections.statusRef.child(FirebaseData.currentUser.getUserId()).setValue("online");

                    Collections.statusRef.child(FirebaseData.currentUser.getUserId())
                            .onDisconnect()
                            .setValue("offline");
                }
            });
        }
        else {
            Collections.usersRef.document(FirebaseData.currentUser.getUserId()).update(
                    User.ONLINE, true
            );
            Collections.statusRef.child(FirebaseData.currentUser.getUserId()).setValue("online");

            Collections.statusRef.child(FirebaseData.currentUser.getUserId())
                    .onDisconnect()
                    .setValue("offline");
        }
    }*/

    // Manipulating User Object

    public static void saveCurrentUser(final String databaseId, final String email, final String name, final String profilePictureUrl, final OnCompleteWithDataCallback<String> onCompleteWithDataCallback) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            String token = task.getResult().getToken();

                            User user = new User(databaseId, email, name, profilePictureUrl, "", false, new Timestamp(new Date()), token);
                            Collections.usersRef.add(user)
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            if (task.isSuccessful()) {
                                                task.getResult().update(User.USER_ID, task.getResult().getId());

                                                if (onCompleteWithDataCallback != null)
                                                    onCompleteWithDataCallback.onComplete(task.getResult().getId());
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    public static void updateUserToken(final String databaseId) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            final String token = task.getResult().getToken();

                            Collections.usersRef.whereEqualTo(User.DATABASE_ID, databaseId).get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful() && task.getResult().getDocuments().size() != 0) {
                                                DocumentSnapshot document = task.getResult().getDocuments().get(0);

                                                document.getReference().update(User.TOKEN, token);
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    // Creating Chat Groups

    public static void createGroup(Session session, final String adId, final String adTitle, final String adCreatedUser, final GetUserProfileResponseModel responseModel, final OnCompleteWithDataCallback<String> onCompleteWithDataCallback) {
        final List<String> array = new ArrayList<>();

        String databaseId = session.getUserId();
        String otherDatabaseId = responseModel.getData().getId();

        if (Integer.parseInt(databaseId) > Integer.parseInt(otherDatabaseId))
            array.addAll(Arrays.asList(otherDatabaseId, databaseId));
        else
            array.addAll(Arrays.asList(databaseId, otherDatabaseId));

        Collections.groupsRef.whereEqualTo(Group.FRIEND_MODEL, array).whereEqualTo(Group.AD_ID, responseModel.getData().getId()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().getDocuments().size() > 0) {
                                DocumentSnapshot document = task.getResult().getDocuments().get(0);
                                document.getReference().update(
                                        Group.AD_ID, responseModel.getData().getId()
                                );
                            }
                            else {
                                Map<String, Long> badges = new HashMap<>();
                                badges.put(session.getUserId(), 1L);
                                badges.put(responseModel.getData().getId(), 1L);
                                Log.e("receiver_phone_number",responseModel.getData().getPhone());
                                Log.e("sender_phone_number",session.getPhone());
                                Group group = new Group("", "", "",
                                        new Timestamp(new Date()),
                                        session.getFullName(),
                                        session.getEmail(),
                                        responseModel.getData().getFullname(),
                                        responseModel.getData().getEmail(),
                                        adId,
                                        adTitle,
                                        // TODO: Handle Ad Image
                                        "",
                                        adCreatedUser,
                                        array, badges,
                                        responseModel.getData().getPhone(),
                                        session.getPhone()
                                );
                                Collections.groupsRef.add(group)
                                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                if (task.isSuccessful()) {
                                                    final String groupId = task.getResult().getId();

                                                    task.getResult().update(Group.GROUP_ID, groupId);

                                                    getUserId(session.getUserId(), session.getFullName(), session.getEmail(), null);

                                                    getUserId(responseModel.getData().getId(), responseModel.getData().getFullname(), responseModel.getData().getEmail(), null);

                                                    if (onCompleteWithDataCallback != null)
                                                        onCompleteWithDataCallback.onComplete(groupId);
                                                }
                                            }
                                        });
                            }
                        }
                    }
                });
    }

    // Getting User Objects

    public static void getCurrentUser(String databaseId, final OnCompleteCallback onCompleteCallback) {
        Collections.usersRef.whereEqualTo(User.DATABASE_ID, databaseId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && task.getResult().getDocuments().size() != 0) {
                            DocumentSnapshot document = task.getResult().getDocuments().get(0);

                            FirebaseData.currentUser = new User(document);

                            if (onCompleteCallback != null)
                                onCompleteCallback.onComplete();
                        }
                    }
                });
    }

    public static void getOtherUserId(String otherUserId, final OnCompleteWithDataCallback<String> onCompleteWithDataCallback) {
        Collections.usersRef.whereEqualTo(User.DATABASE_ID, otherUserId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().getDocuments().size() == 0)
                                return;
                            DocumentSnapshot document = task.getResult().getDocuments().get(0);

                            if (onCompleteWithDataCallback != null)
                                onCompleteWithDataCallback.onComplete(document.getString(User.USER_ID));
                        }
                    }
                });


        /*Collections.groupParticipationRef.whereEqualTo(GroupParticipation.GROUP, groupId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documents = task.getResult().getDocuments();

                            for (DocumentSnapshot document : documents)
                                if (!document.getString(GroupParticipation.USER_DATABASE_ID).equals(currentDatabaseId))
                                    if (onCompleteWithDataCallback != null)
                                        onCompleteWithDataCallback.onComplete(document.getString(GroupParticipation.USER));
                        }
                    }
                });*/
    }

    public static void getUserId(final String userId, final String name, final String email, final OnCompleteWithDataCallback<String> onCompleteWithDataCallback) {
        Collections.usersRef.whereEqualTo(User.DATABASE_ID, userId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().getDocuments().size() == 0) {
                                // TODO: Profile Pic
                                saveCurrentUser(userId, email, name, "", new OnCompleteWithDataCallback<String>() {
                                    @Override
                                    public void onComplete(String s) {
                                        if (onCompleteWithDataCallback != null)
                                            onCompleteWithDataCallback.onComplete(s);
                                    }
                                });
                            }
                            else {
                                DocumentSnapshot document = task.getResult().getDocuments().get(0);

                                if (onCompleteWithDataCallback != null)
                                    onCompleteWithDataCallback.onComplete(document.getString(User.USER_ID));
                            }
                        }

                        /*if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult().getDocuments().get(0);

                            if (onCompleteWithDataCallback != null)
                                onCompleteWithDataCallback.onComplete(document.getString(User.USER_ID));
                        }*/
                    }
                });
    }

    // Chatting

    public static void getGroupId(Context context, final Session session, final String adId, final String adTitle, final String adCreatedUser, final GetUserProfileResponseModel responseModel, final OnCompleteWithDataCallback<String> onCompleteWithDataCallback) {
        List<String> array = new ArrayList<>();

        String databaseId = session.getUserId();
        String otherDatabaseId = responseModel.getData().getId();

        if (otherDatabaseId.equals("")) {
            Toast.makeText(context, "Get Group Id Toast", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Integer.parseInt(databaseId) > Integer.parseInt(otherDatabaseId))
            array.addAll(Arrays.asList(otherDatabaseId, databaseId));
        else
            array.addAll(Arrays.asList(databaseId, otherDatabaseId));

        Collections.groupsRef.whereEqualTo(Group.FRIEND_MODEL, array).whereEqualTo(Group.AD_ID, adId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().getDocuments().size() != 0) {
                                DocumentSnapshot document = task.getResult().getDocuments().get(0);

                                if (onCompleteWithDataCallback != null)
                                    onCompleteWithDataCallback.onComplete(document.getString(Group.GROUP_ID));
                            }
                            else {
                                createGroup(session, adId, adTitle, adCreatedUser, responseModel, new OnCompleteWithDataCallback<String>() {
                                    @Override
                                    public void onComplete(String s) {
                                        if (onCompleteWithDataCallback != null)
                                            onCompleteWithDataCallback.onComplete(s);
                                    }
                                });
                            }
                        }
                    }
                });
    }

    public static void getGroupId(final String groupId, final OnCompleteWithDataCallback<String> onCompleteWithDataCallback) {
        Collections.groupsRef.whereEqualTo(Group.GROUP_ID, groupId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().getDocuments().size() != 0) {
                                DocumentSnapshot document = task.getResult().getDocuments().get(0);

                                if (onCompleteWithDataCallback != null)
                                    onCompleteWithDataCallback.onComplete(document.getString(Group.GROUP_ID));
                            }
                        }
                    }
                });
    }

    /*public static void getGroupsParticipations(String userId, final OnCompleteCallback onCompleteCallback) {
        Collections.groupParticipationRef.whereEqualTo(GroupParticipation.USER_DATABASE_ID, userId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documents = task.getResult().getDocuments();

                            List<GroupParticipation> groupParticipations = new ArrayList<>();
                            for (DocumentSnapshot document : documents)
                                groupParticipations.add(new GroupParticipation(document));

                            org.worldguide.worldguide.firebase.FirebaseData.usersParticipations = groupParticipations;

                            if (onCompleteCallback != null)
                                onCompleteCallback.onComplete();
                        }
                    }
                });
    }*/

    /*public static void getGroups(final OnCompleteCallback onCompleteCallback) {
        if (FirebaseData.usersParticipations.size() == 0) return;
        Collections.groupsRef.whereIn(Group.GROUP_ID, getGroupsInParticipations(org.worldguide.worldguide.firebase.FirebaseData.usersParticipations)).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documents = task.getResult().getDocuments();

                            List<Group> groups = new ArrayList<>();
                            for (DocumentSnapshot document : documents)
                                groups.add(new Group(document));

                            org.worldguide.worldguide.firebase.FirebaseData.userGroups = groups;

                            if (onCompleteCallback != null)
                                onCompleteCallback.onComplete();
                        }
                    }
                });
    }*/

    public static void sendThread(final Session session, String groupId, Thread thread, boolean isImage, String otherUserId, Long prevBadge) {
        Collections.groupsRef.document(groupId).collection(Thread.CLASS_NAME).add(thread);


        Log.i("sakjdbverwbv",":"+groupId);

        if (isImage){
            Collections.groupsRef.document(groupId).update(
                    Group.LAST_MESSAGE, "Photo",
                    Group.LAST_MESSAGE_DATE, thread.getCreated()
            );
        }
        else{

            Collections.groupsRef.document(groupId).update(
                    Group.LAST_MESSAGE, thread.getContent(),
                    Group.LAST_MESSAGE_DATE, thread.getCreated()
            ).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    Log.i("khjdbsvwevg","success");

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.i("khjdbsvwevg","fail:"+e.getMessage());

                }
            });

        }

        if (otherUserId != null && prevBadge != null) {
            Log.i("khjdbsvwevg","hey");
            Map<String, Long> badges = new HashMap<>();
            badges.put(session.getUserId(),  0L);
            badges.put(otherUserId, prevBadge + 1L);
            Collections.groupsRef.document(groupId).update(
                    Group.BADGES, badges
            ).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    Log.i("khjdbsvwevg","success1");

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.i("khjdbsvwevg","fail1:"+e.getMessage());

                }
            });;
        }
    }

    public static void saveImageInStorage(Context context, final String groupId, final Session session, final GetUserProfileResponseModel responseModel, Uri uri, final boolean otherUserOnline, final Long prevBadge) {
        final StorageReference fileRef = Collections.storageRef.child(groupId).child(System.currentTimeMillis() + "." + getFileExtension(context, uri));
        fileRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        fileRef.getDownloadUrl()
                                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            String url = task.getResult().toString();
                                            Thread thread = new Thread(
                                                    "",
                                                    new Timestamp(new Date()),
                                                    responseModel.getData().getFullname(),
                                                    "",
                                                    responseModel.getData().getId(),
                                                    false,
                                                    session.getFullName(),
                                                    // TODO: Profile Pic
                                                    /*Data.getInstance().getCurrentUserLoginRes().profilePicture,*/ "",
                                                    session.getUserId(),
                                                    session.getUserId(),
                                                    url
                                            );
                                            /*if (otherUserOnline)
                                                FirebaseUtils.sendThread(groupId, thread, true, null, prevBadge);
                                            else*/

                                            FirebaseUtils.sendThread(session, groupId, thread, true, responseModel.getData().getId(), prevBadge);
                                        }
                                    }
                                });
                    }
                });
    }

    public static void sendNotification(final String title, final String message, String token, final String identifier, final String userEmail, final String groupId, final String serverKey, final OnCompleteWithDataCallback<JsonObjectRequest> callback) {
        /*Collections.usersRef.document(firebaseUserId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();

                            String token = document.getString(User.TOKEN);


                        }
                    }
                });*/

        JSONObject params = new JSONObject();

        try {
            params.put("to", token);
            params.put("notification", new JSONObject().put("title", title).put("body", message).put("sound", "default"));
            params.put("data", new JSONObject().put("data", new JSONObject().put("identifier", identifier).put("user_email", userEmail).put("group_id", groupId).toString()));

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, FCM_URL, params,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i(TAG, response.toString());




                            Log.i("kasjebqwrg","hey:"+response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Log.i("kasjebqwrg","pey");
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "key=" + serverKey);
                    headers.put("Content-Type", "application/json");

                    return headers;
                }
            };

            callback.onComplete(request);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // General Utils

    private static String getFileExtension(Context context, Uri uri) {
        ContentResolver cr = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    // Callbacks

    public interface OnCompleteCallback {
        void onComplete();
    }

    public interface OnCompleteWithDataCallback<T> {
        void onComplete(T t);
    }
}
