package com.zoolife.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.GetUserProfile.GetUserProfileResponseModel;
import com.zoolife.app.Session;
import com.zoolife.app.activity.ChatActivity;
import com.zoolife.app.firebase.models.Group;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    Context context;
    List<Group> data;
    Session session;

    public MessageAdapter(Context context, List<Group> data, Session session) {
        this.context = context;
        this.data = data;
        this.session = session;
    }

    public void sort() {
        Collections.sort(data, new Comparator<Group>() {
            @Override
            public int compare(Group first, Group second) {
                return -first.getLastMessageDate().compareTo(second.getLastMessageDate());
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_message_layout_new, parent, false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Group group = data.get(position);
        holder.bind(group);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView image;
        public TextView adName, userName, message, time, badge;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.image = itemView.findViewById(R.id.image);
            this.adName = itemView.findViewById(R.id.adName);
            this.userName = itemView.findViewById(R.id.userName);
            this.message = itemView.findViewById(R.id.message);
            this.time = itemView.findViewById(R.id.time);
            this.badge = itemView.findViewById(R.id.badge);
        }

        public void bind(Group group) {
            adName.setText(group.getAdTitle());
            userName.setText(group.getAdCreatedUser());
            message.setText(group.getLastMessage());
            setDate(time, group.getLastMessageDate());
            if (group.getBadges() != null)
                badge.setText(group.getBadges().get(session.getUserId()) + "");
            try {
                if (group.getBadges().get(session.getUserId()) > 0L) {
                    badge.setVisibility(View.VISIBLE);
                    message.setTypeface(null, Typeface.BOLD);
                } else {
                    badge.setVisibility(View.GONE);
                    message.setTypeface(null, Typeface.NORMAL);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            itemView.setOnClickListener(v -> {
                getUserProfileApi(group);
            });
        }

        private void getUserProfileApi(Group group) {
            String username = group.getSenderPhone().equals(session.getPhone()) ? group.getRecipientPhone() : group.getSenderPhone();
            //String ID = group.getse().equals(session.getPhone()) ? group.getRecipientPhone() : group.getSenderPhone();

            ApiService apiService = ApiClient.getClient(context).create(ApiService.class);
            Call<GetUserProfileResponseModel> call = apiService.getUserProfile(session.getUserId());//session.getUserId()
            call.enqueue(new Callback<GetUserProfileResponseModel>() {
                @Override
                public void onResponse(Call<GetUserProfileResponseModel> call, Response<GetUserProfileResponseModel> response) {
                    GetUserProfileResponseModel responseModel = response.body();
                    if (response.isSuccessful() && responseModel.getData() != null) {
                        Intent chatIntent = new Intent(context, ChatActivity.class);
                        chatIntent.putExtra(ChatActivity.AD_ID, group.getAdId());
                        chatIntent.putExtra(ChatActivity.AD_TITLE, group.getAdTitle());
                        chatIntent.putExtra(ChatActivity.AD_CREATED_USER, group.getAdCreatedUser());
                        chatIntent.putExtra(ChatActivity.USER_OBJ, responseModel);
                        chatIntent.putExtra(ChatActivity.GROUP_ID, group.getGroupId());
                        context.startActivity(chatIntent);

                    } else {
                        Log.d("AddDetailsActivity", "Server Error.");
                    }
                }

                @Override
                public void onFailure(Call<GetUserProfileResponseModel> call, Throwable t) {
                    t.printStackTrace();

                    Log.i("asjdvgwrbv", "qq:" + t.getMessage());
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        private void setDate(TextView view, Timestamp timestamp) {
            if (new SimpleDateFormat("yyyyMMdd").format(new Date()).equals(new SimpleDateFormat("yyyyMMdd").format(timestamp.toDate()))) {
                DateFormat df = new DateFormat();
                String dateStr = df.format("hh:mm a", timestamp.toDate()) + "";
                view.setText(dateStr + "");
            } else {
                DateFormat df = new DateFormat();
                String dateStr = df.format("MMM dd", timestamp.toDate()) + "";
                view.setText(dateStr + "");
            }
        }
    }
}
