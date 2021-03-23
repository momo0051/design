package com.zoolife.app.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.GetUserProfile.GetUserProfileResponseModel;
import com.zoolife.app.Session;
import com.zoolife.app.activity.ChatActivity;
import com.zoolife.app.activity.DeliveryOrderActivity;
import com.zoolife.app.activity.LoginActivity;
import com.zoolife.app.firebase.Collections;
import com.zoolife.app.firebase.models.Group;
import com.zoolife.app.models.DeliveryModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.MyViewHolder> {

    public DeliveryOrderActivity activity;
    List<DeliveryModel> data;
    Session session;

    List<Group> groupsList;


    public DeliveryAdapter(DeliveryOrderActivity activity, List<DeliveryModel> data, Session session) {
        this.activity = activity;
        this.data = data;
        this.session = session;
    }

    @NonNull
    @Override
    public DeliveryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View deliveryResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item, parent, false);
        return new MyViewHolder(deliveryResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DeliveryModel current = data.get(position);

        holder.itemTitle.setText(current.itemTitle);
        holder.itemDescription.setText(current.itemDescription);


        Log.i("ajhsbvwv","w:"+current.username);

        holder.username.setText(current.username);
        holder.city.setText(current.city);
        if (current.email != null) {
            if (current.email.equals(session.getEmail())) {
                holder.deleteIcon.setVisibility(View.VISIBLE);
            } else
                holder.deleteIcon.setVisibility(View.GONE);
        } else {
            holder.deleteIcon.setVisibility(View.GONE);
        }


        holder.callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + current.phone));
                startActivity(intent);
            }
        });
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (session.isLogin()) {
                    activity.deleteDelivery(current.id);
                } else {
                    startActivity(new Intent(activity, LoginActivity.class));
                }
            }
        });

        holder.whatsappBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String url = "https://api.whatsapp.com/send?phone="+current.phone;
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                activity.startActivity(i);

//                openWhatsAppConversation(activity.getApplicationContext(), current.phone, "");
                openWhatsApp(current.phone, "");


            }
        });

        holder.chatIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                current.email;
//                current.id;
//                current.itemTitle;
//                current.username;
//                Group group = new Group();
                if (session.isLogin()) {

                    getUserProfileApi(current);
                } else {
                    startActivity(new Intent(activity, LoginActivity.class));

                }
            }
        });

    }

    //    private void getUserProfileApi(DeliveryModel group) {
    private void getUserProfileApi(DeliveryModel group) {

//        String username = group.email.equals(session.getEmail()) ? session.getEmail() : group.email;
        String username = group.phone.equals(session.getPhone()) ? session.getPhone() : group.phone;
        String ID = group.id.equals(session.getUserId()) ? session.getUserId() : group.id;
//        String username = group.getSenderEmail().equals(session.getEmail()) ? group.getRecipientEmail() : group.getSenderEmail();


        ApiService apiService = ApiClient.getClient(activity).create(ApiService.class);
        Call<GetUserProfileResponseModel> call = apiService.getUserProfile(ID);
        call.enqueue(new Callback<GetUserProfileResponseModel>() {
            @Override
            public void onResponse(Call<GetUserProfileResponseModel> call, Response<GetUserProfileResponseModel> response) {
                GetUserProfileResponseModel responseModel = response.body();
                if (response.isSuccessful() && responseModel.getData() != null) {
                    Intent chatIntent = new Intent(activity, ChatActivity.class);
//                    chatIntent.putExtra(ChatActivity.AD_ID, group.getAdId());
//                    chatIntent.putExtra(ChatActivity.AD_TITLE, group.getAdTitle());
//                    chatIntent.putExtra(ChatActivity.AD_CREATED_USER, group.getAdCreatedUser());

                    chatIntent.putExtra(ChatActivity.AD_ID, group.id);
                    chatIntent.putExtra(ChatActivity.AD_TITLE, group.itemTitle);
                    chatIntent.putExtra(ChatActivity.AD_CREATED_USER, group.username);
                    chatIntent.putExtra(ChatActivity.USER_OBJ, responseModel);
                    activity.startActivity(chatIntent);

                } else {
                    Log.d("AddDetailsActivity", "Server Error.");
                }
            }

            @Override
            public void onFailure(Call<GetUserProfileResponseModel> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public static void openWhatsAppConversation(Context context, String number, String message) {

        number = number.replace(" ", "").replace("+", "");

        Intent sendIntent = new Intent("android.intent.action.MAIN");

        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number) + "@s.whatsapp.net");

        context.startActivity(sendIntent);
    }


    public void openWhatsApp(String number, String message) {
        try {
            String text = message;// Replace with your message.

            String toNumber = PhoneNumberUtils.stripSeparators(number); // Replace with mobile phone number without +Sign or leading zeros, but with country code
            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(activity, "Whatsapp app not found on your device", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView city, itemTitle, itemDescription, username;
        ImageView callIcon, deleteIcon, whatsappBtn, chatIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.delivery_city);
            itemTitle = itemView.findViewById(R.id.delivery_item_title);
            itemDescription = itemView.findViewById(R.id.delivery_item_desc);
            username = itemView.findViewById(R.id.item_username);
            callIcon = itemView.findViewById(R.id.call_icon);
            chatIcon = itemView.findViewById(R.id.chat_box);
            deleteIcon = itemView.findViewById(R.id.delivery_delete_icon);
            whatsappBtn = itemView.findViewById(R.id.whatsapp_icon);

        }
    }

    private void fetchGroups() {
        if (activity == null) return;
        Collections.groupsRef.whereArrayContains(Group.FRIEND_MODEL, session.getUserId()).get()
                .addOnCompleteListener(activity, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documents = task.getResult().getDocuments();

                            groupsList = new ArrayList<>();
                            for (DocumentSnapshot document : documents)
                                groupsList.add(new Group(document));

                            addGroupSnapshotListener();
                        }
                    }
                });
    }

    private void addGroupSnapshotListener() {
        if (activity == null) return;
        Collections.groupsRef.whereArrayContains(Group.FRIEND_MODEL, session.getUserId()).addSnapshotListener(activity, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("MessageFragment", error.getMessage());
                    return;
                }

                List<DocumentChange> documentChanges = value.getDocumentChanges();

                for (DocumentChange documentChange : documentChanges) {
                    QueryDocumentSnapshot document = documentChange.getDocument();

                    for (int i = 0; i < groupsList.size(); i++) {
                        Group group = groupsList.get(i);
                        if (group.getGroupId().equals(document.getString(Group.GROUP_ID))) {
                            groupsList.set(i, new Group(document));
                        }
                    }
                }
            }
        });
    }
}
