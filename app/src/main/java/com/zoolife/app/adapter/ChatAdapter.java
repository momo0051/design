package com.zoolife.app.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.signature.ObjectKey;
import com.google.firebase.Timestamp;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.GetPost.GetPostResponseModel;
import com.zoolife.app.Session;
import com.zoolife.app.firebase.models.Thread;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private static final int VIEW_TYPE_IMAGE_SENT = 3;
    private static final int VIEW_TYPE_IMAGE_RECEIVED = 4;

    private Context context;
    private Session session;
    private List<Thread> threadsList;
    private GetPostResponseModel responseModel;

    private ObjectKey glideSignature = new ObjectKey(System.currentTimeMillis());

    public ChatAdapter(Context context, Session session, List<Thread> threadsList, GetPostResponseModel responseModel) {
        this.context = context;
        this.session = session;
        this.threadsList = threadsList;
        this.responseModel = responseModel;
    }

    @Override
    public int getItemCount() {
        return threadsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Thread thread = threadsList.get(position);

        if (thread.getSenderDatabaseId().equals(session.getUserId())) {
            if (!thread.getUrl().isEmpty())
                return VIEW_TYPE_IMAGE_SENT;
            else
                return VIEW_TYPE_MESSAGE_SENT;
        }
        else {
            if (!thread.getUrl().isEmpty())
                return VIEW_TYPE_IMAGE_RECEIVED;
            else
                return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(context).inflate(R.layout.chat_message_sent, parent, false);
            return new SentMessageHolder(view);
        }
        else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(context).inflate(R.layout.chat_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }
        else if (viewType == VIEW_TYPE_IMAGE_SENT) {
            view = LayoutInflater.from(context).inflate(R.layout.chat_message_image_sent, parent, false);
            return new SentImageHolder(view);
        }
        else if (viewType == VIEW_TYPE_IMAGE_RECEIVED) {
            view = LayoutInflater.from(context).inflate(R.layout.chat_message_image_received, parent, false);
            return new ReceivedImageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Thread thread = threadsList.get(position);

        boolean displayDate = false;

        if (position != 0) {
            if (!(new SimpleDateFormat("yyyyMMdd").format(threadsList.get(position - 1).getCreated().toDate()).equals(new SimpleDateFormat("yyyyMMdd").format(thread.getCreated().toDate())))) {
                displayDate = true;
            }
        }
        else if (position == 0) {
            displayDate = true;
        }

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(thread, displayDate);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(thread, displayDate);
                break;
            case VIEW_TYPE_IMAGE_SENT:
                ((SentImageHolder) holder).bind(thread, displayDate);
                break;
            case VIEW_TYPE_IMAGE_RECEIVED:
                ((ReceivedImageHolder) holder).bind(thread, displayDate);
                break;
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView text, time, dateIndicator;

        public SentMessageHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            time = itemView.findViewById(R.id.time);
            dateIndicator = itemView.findViewById(R.id.dateIndicator);
        }

        public void bind(Thread thread, boolean displayDate) {
            // TODO: Profile Picture Handle
            /*if (Data.getInstance().getCurrentUserLoginRes().profilePicture != null && !Data.getInstance().getCurrentUserLoginRes().profilePicture.isEmpty())
                Glide.with(context)
                        .load(Data.getInstance().getCurrentUserLoginRes().profilePicture)
                        .placeholder(R.drawable.empty_profile_image)
                        .signature(glideSignature)
                        .into(image);*/
            text.setText(thread.getContent());
            setDate(time, thread.getCreated(), true);

            if (displayDate) {
                dateIndicator.setVisibility(View.VISIBLE);
                setDate(dateIndicator, thread.getCreated(), false);
            }
            else {
                dateIndicator.setVisibility(View.GONE);
            }
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView text, time, dateIndicator;

        public ReceivedMessageHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            time = itemView.findViewById(R.id.time);
            dateIndicator = itemView.findViewById(R.id.dateIndicator);
        }

        public void bind(Thread thread, boolean displayDate) {
            // TODO: Profile Picture Handle
            /*if (friend.senderUser.profilePicture != null && !friend.senderUser.profilePicture.isEmpty())
                Glide.with(context)
                        .load(friend.senderUser.profilePicture)
                        .placeholder(R.drawable.empty_profile_image)
                        .signature(glideSignature)
                        .into(image);*/
            text.setText(thread.getContent());
            setDate(time, thread.getCreated(), true);

            if (displayDate) {
                dateIndicator.setVisibility(View.VISIBLE);
                setDate(dateIndicator, thread.getCreated(), false);
            }
            else {
                dateIndicator.setVisibility(View.GONE);
            }
        }
    }

    private class SentImageHolder extends RecyclerView.ViewHolder {
        CircleImageView userImage;
        ImageView image;
        TextView time, dateIndicator;

        public SentImageHolder(View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.userImage);
            image = itemView.findViewById(R.id.image);
            time = itemView.findViewById(R.id.time);
            dateIndicator = itemView.findViewById(R.id.dateIndicator);
        }

        public void bind(final Thread thread, boolean displayDate) {
            // TODO: Profile Picture Handle
            /*if (Data.getInstance().getCurrentUserLoginRes().profilePicture != null && !Data.getInstance().getCurrentUserLoginRes().profilePicture.isEmpty())
                Glide.with(context)
                        .load(Data.getInstance().getCurrentUserLoginRes().profilePicture)
                        .placeholder(R.drawable.empty_profile_image)
                        .signature(glideSignature)
                        .into(userImage);*/
            Picasso.get()
                    .load(thread.getUrl())
                    .placeholder(new ColorDrawable(Color.LTGRAY))
                    .into(image);
            setDate(time, thread.getCreated(), true);

            if (displayDate) {
                dateIndicator.setVisibility(View.VISIBLE);
                setDate(dateIndicator, thread.getCreated(), false);
            }
            else {
                dateIndicator.setVisibility(View.GONE);
            }

            // TODO: Picture Click Handle
            /*image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog builder = new Dialog(context);
                    builder.setContentView(R.layout.photo_layout);
                    builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    builder.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    ImageView imageView = builder.findViewById(R.id.NGNA_PL_image);
                    Picasso.get()
                            .load(thread.getUrl())
                            .into(imageView);
                    builder.show();
                }
            });*/
        }
    }

    private class ReceivedImageHolder extends RecyclerView.ViewHolder {
        CircleImageView userImage;
        ImageView image;
        TextView time, dateIndicator;

        public ReceivedImageHolder(View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.userImage);
            image = itemView.findViewById(R.id.image);
            time = itemView.findViewById(R.id.time);
            dateIndicator = itemView.findViewById(R.id.dateIndicator);
        }

        public void bind(final Thread thread, boolean displayDate) {
            // TODO: Profile Picture Handle
            /*if (friend.senderUser.profilePicture != null && !friend.senderUser.profilePicture.isEmpty())
                Glide.with(context)
                        .load(friend.senderUser.profilePicture)
                        .placeholder(R.drawable.empty_profile_image)
                        .signature(glideSignature)
                        .into(userImage);*/
            Picasso.get()
                    .load(thread.getUrl())
                    .placeholder(new ColorDrawable(Color.LTGRAY))
                    .into(image);
            setDate(time, thread.getCreated(), true);

            if (displayDate) {
                dateIndicator.setVisibility(View.VISIBLE);
                setDate(dateIndicator, thread.getCreated(), false);
            }
            else {
                dateIndicator.setVisibility(View.GONE);
            }

            // TODO: Picture Click Handle
            /*image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog builder = new Dialog(context);
                    builder.setContentView(R.layout.photo_layout);
                    builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    builder.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    ImageView imageView = builder.findViewById(R.id.NGNA_PL_image);
                    Picasso.get()
                            .load(thread.getUrl())
                            .into(imageView);
                    builder.show();
                }
            });*/
        }
    }

    private static void setDate(TextView view, Timestamp timestamp, boolean isTime) {
        if (isTime) {
            DateFormat df = new DateFormat();
            String dateStr = df.format("hh:mm a", timestamp.toDate())+"";
            view.setText(dateStr+"");
        }
        else {
            if (new SimpleDateFormat("yyyyMMdd").format(new Date()).equals(new SimpleDateFormat("yyyyMMdd").format(timestamp.toDate()))) {
                view.setText("Today");
            }
            else {
                DateFormat df = new DateFormat();
                String dateStr = df.format("MMM dd", timestamp.toDate())+"";
                view.setText(dateStr+"");
            }
        }
        /*if (new SimpleDateFormat("yyyyMMdd").format(new Date()).equals(new SimpleDateFormat("yyyyMMdd").format(timestamp.toDate()))) {
            DateFormat df = new DateFormat();
            String dateStr = df.format("hh:mm a", timestamp.toDate())+"";
            view.setText(dateStr+"");
        }
        else {
            DateFormat df = new DateFormat();
            String dateStr = df.format("MMM dd", timestamp.toDate())+"";
            view.setText(dateStr+"");
        }*/
    }
}
