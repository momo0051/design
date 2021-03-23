package com.zoolife.app.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.GetUserProfile.GetUserProfileResponseModel;
import com.zoolife.app.adapter.ChatAdapter;
import com.zoolife.app.firebase.Collections;
import com.zoolife.app.firebase.FirebaseUtils;
import com.zoolife.app.firebase.models.Group;
import com.zoolife.app.firebase.models.Thread;
import com.zoolife.app.firebase.models.User;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppBaseActivity {

    public static final String TAG = ChatAdapter.class.getSimpleName();

    public static final String FROM_NOTIFICATION = "fromNotification";
    public static final String GROUP_ID = "groupId";
    public static final String USER_EMAIL = "userEmail";
    public static final String AD_ID = "adId";
    public static final String AD_TITLE = "adTitle";
    public static final String AD_CREATED_USER = "adCreatedUser";
    public static final String USER_OBJ = "user_obj";

    private static final int CAMERA_PIC = 1;
    private static final int GALLERY_PIC = 2;

    private RelativeLayout backBtn;
    private RecyclerView chatView;
    private ImageView messageBox_imageBtn;
    private EditText messageBox_textET;
    private FrameLayout messageBox_sendBtn;

    private ChatAdapter chatAdapter;
    private LinearLayoutManager layoutManager;

    private List<Thread> messagesList;

    private boolean fromNotification = false;
    private String userEmail;
    private String adId;
    private String adTitle;
    private String adCreatedUser;
    private GetUserProfileResponseModel userObj;

    private Uri cameraImageUri;

    private String groupId;
    private String otherUserFirebaseId;

    private Group currentGroup;

    private boolean otherUserOnline = false;
    static ProgressBar dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //forceRTLIfSupported();
        setContentView(R.layout.activity_chat);


        if (getIntent() != null) {
            if (getIntent().getBooleanExtra(FROM_NOTIFICATION, false)) {
                fromNotification = true;
                if (getIntent().getStringExtra(GROUP_ID) != null && getIntent().getStringExtra(USER_EMAIL) != null) {
                    groupId = getIntent().getStringExtra(GROUP_ID);
                    userEmail = getIntent().getStringExtra(USER_EMAIL);
                } else
                    finish();
            } else {
                if (getIntent().getStringExtra(AD_ID) != null && getIntent().getStringExtra(AD_TITLE) != null && getIntent().getParcelableExtra(USER_OBJ) != null) {
                    adId = getIntent().getStringExtra(AD_ID);
                    adTitle = getIntent().getStringExtra(AD_TITLE);
                    adCreatedUser = getIntent().getStringExtra(AD_CREATED_USER);
                    userObj = getIntent().getParcelableExtra(USER_OBJ);
                    groupId = getIntent().getStringExtra(GROUP_ID);
                } else
                    finish();
            }
        } else
            finish();

        backBtn = findViewById(R.id.backBtn);
        chatView = findViewById(R.id.chatView);
        messageBox_imageBtn = findViewById(R.id.messageBox_imageBtn);
        messageBox_textET = findViewById(R.id.messageBox_textET);
        messageBox_sendBtn = findViewById(R.id.messageBox_sendBtn);

        backBtn.setOnClickListener(v -> onBackPressed());

        messageBox_imageBtn.setEnabled(false);
        messageBox_sendBtn.setEnabled(false);

        messagesList = new ArrayList<>();
        chatAdapter = new ChatAdapter(ChatActivity.this, session, messagesList, null);
        layoutManager = new LinearLayoutManager(ChatActivity.this);
        layoutManager.setStackFromEnd(true);
        chatView.setLayoutManager(layoutManager);
        chatView.setAdapter(chatAdapter);
        dialog = findViewById(R.id.progressBar);
        dialog.setVisibility(View.GONE);

        if (fromNotification) {
            getUserProfileApi(() -> FirebaseUtils.getGroupId(groupId, s -> {
                groupId = s;
                addSnapshotListener();

                messageBox_sendBtn.setEnabled(true);
                messageBox_imageBtn.setEnabled(true);
            }));
        } else {
            if (groupId.equalsIgnoreCase("")) {
                try {
                    FirebaseUtils.getGroupId(this, session, adId, adTitle, adCreatedUser, userObj, s -> {
                        groupId = s;
                        addSnapshotListener();

                        if (!this.isFinishing())
                            session.setChatGroupId(groupId);

                        messageBox_sendBtn.setEnabled(true);
                        messageBox_imageBtn.setEnabled(true);
                    });
                } catch (Exception ex) {
                    Toast.makeText(this, "Make sure you are signed in", Toast.LENGTH_LONG).show();
                }
            } else {
                addSnapshotListener();
                if (!this.isFinishing())
                    session.setChatGroupId(groupId);

                messageBox_sendBtn.setEnabled(true);
                messageBox_imageBtn.setEnabled(true);
            }


        }

        messageBox_imageBtn.setOnClickListener(imageClickListener);

        messageBox_sendBtn.setOnClickListener(sendClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        session.setOnChat(true);
        if (groupId != null)
            session.setChatGroupId(groupId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        session.setOnChat(false);
        session.setChatGroupId(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        addSnapshotListener();
        if (resultCode == RESULT_OK && requestCode == CAMERA_PIC) {
            Long prevBadge = currentGroup.getBadges() != null ? currentGroup.getBadges().get(userObj.getData().getId()) : null;
            dialog.setVisibility(View.VISIBLE);
            FirebaseUtils.saveImageInStorage(this, groupId, session, userObj, cameraImageUri, otherUserOnline, prevBadge);
        } else if (resultCode == RESULT_OK && requestCode == GALLERY_PIC) {
            Long prevBadge = currentGroup.getBadges() != null ? currentGroup.getBadges().get(userObj.getData().getId()) : null;
            dialog.setVisibility(View.VISIBLE);
            FirebaseUtils.saveImageInStorage(this, groupId, session, userObj, data.getData(), otherUserOnline, prevBadge);
        }

    }

    private void addSnapshotListener() {
        Collections.groupsRef
                .document(groupId)
                .collection(Thread.CLASS_NAME)
                .orderBy(Thread.CREATED)
                .addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e(TAG, e.getMessage());
                            return;
                        }

                        List<DocumentChange> documentChanges = value.getDocumentChanges();

                        for (DocumentChange documentChange : documentChanges) {
                            QueryDocumentSnapshot document = documentChange.getDocument();
                            messagesList.add(new Thread(document));
                        }
                        try {
                            if (!messagesList.get(messagesList.size() - 1).getUrl().isEmpty()) {

                                dialog.setVisibility(View.GONE);

                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        chatAdapter.notifyDataSetChanged();
                        chatView.scrollToPosition(messagesList.size() - 1);
                    }
                });

        Collections.groupsRef.document(groupId).addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                currentGroup = new Group(value);
                seeMessages();
            }
        });

        getOtherUserId();
    }

    private void seeMessages() {
        Map<String, Long> badges = currentGroup.getBadges();
        try {
            if (badges != null) {
                Long badge = badges.get(session.getUserId());
                if (badge > 0) {
                    badges.put(session.getUserId(), 0L);
                    Collections.groupsRef.document(groupId).update(
                            Group.BADGES, badges
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getOtherUserId() {
        FirebaseUtils.getOtherUserId(userObj.getData().getId(), new FirebaseUtils.OnCompleteWithDataCallback<String>() {
            @Override
            public void onComplete(String s) {
                otherUserFirebaseId = s;
                addUserSnapshotListener(s);
            }
        });
    }

    private void addUserSnapshotListener(String userId) {
        Collections.usersRef.document(userId).addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Boolean online = value.getBoolean(User.ONLINE);
                Timestamp lastActive = value.getTimestamp(User.LAST_ACTIVE);

                otherUserOnline = online;

                if (online) {
//                    toolbar_status.setText("Online");
                } else {
//                    toolbar_status.setText("Last Active: " + setDate(lastActive));
                }
            }
        });
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery"};
        AlertDialog dialog = new AlertDialog.Builder(ChatActivity.this)
                .setTitle("Send Image")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            if (ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(ChatActivity.this, "Please enable Camera Permission in Settings", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            ContentValues values = new ContentValues();
                            values.put(MediaStore.Images.Media.TITLE, "New Picture");
                            values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                            cameraImageUri = ChatActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri);
                            startActivityForResult(intent, CAMERA_PIC);
                        } else if (options[item].equals("Choose from Gallery")) {
                            Intent profilePicIntent = new Intent(Intent.ACTION_GET_CONTENT);
                            profilePicIntent.setType("image/*");
                            startActivityForResult(profilePicIntent, GALLERY_PIC);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setBackgroundColor(Color.WHITE);
    }

    private void getUserProfileApi(final FirebaseUtils.OnCompleteCallback onCompleteCallback) {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<GetUserProfileResponseModel> call = apiService.getUserProfile(adCreatedUser);
        call.enqueue(new Callback<GetUserProfileResponseModel>() {
            @Override
            public void onResponse(Call<GetUserProfileResponseModel> call, Response<GetUserProfileResponseModel> response) {
                GetUserProfileResponseModel responseModel = response.body();
                if (response.isSuccessful() && responseModel.getData() != null) {
                    userObj = responseModel;
                    onCompleteCallback.onComplete();
                } else {
                    Log.d("AddDetailsActivity", "Server Error.");
                }
            }

            @Override
            public void onFailure(Call<GetUserProfileResponseModel> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ChatActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Click Listeners

    private View.OnClickListener imageClickListener = v -> {
        if (ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ChatActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
            return;
        } else {
            selectImage();
        }
    };

    private View.OnClickListener sendClickListener = v -> {
        String messageText = messageBox_textET.getText().toString();

        if (TextUtils.isEmpty(messageText))
            return;

        messageBox_textET.setText("");


        Log.i("askhbwr", "ss:" + messageText);

        Thread thread = new Thread(
                messageText,
                new Timestamp(new Date()),
                userObj.getData().getFullname(),
                "",
                userObj.getData().getId(),
                false,
                session.getFullName(),
                // TODO: Handle Profile Pic
                "",
                session.getUserId(),
                session.getUserId(),
                ""
        );
        Long prevBadge = currentGroup.getBadges() != null ? currentGroup.getBadges().get(userObj.getData().getId()) : null;
            /*if (otherUserOnline)
                FirebaseUtils.sendThread(groupId, thread, false, null, prevBadge);
            else*/

        FirebaseUtils.sendThread(session, groupId, thread, false, userObj.getData().getId(), prevBadge);

        // TODO: Handle Notifications
        /*if (otherUserFirebaseId != null)*/


        Log.i("asdkhbvwev", "ss:" + userObj.getData().getDevice_token());
        FirebaseUtils.sendNotification(session.getFullName(), messageText, userObj.getData().getDevice_token(), "NC", session.getEmail(), groupId, getString(R.string.firebase_server_key), request -> Volley.newRequestQueue(getApplicationContext()).add(request));
    };
}