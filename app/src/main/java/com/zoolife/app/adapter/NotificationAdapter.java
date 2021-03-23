package com.zoolife.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zoolife.app.R;
import com.zoolife.app.activity.AddDetailsActivity;
import com.zoolife.app.models.NotificationModel;
import com.zoolife.app.utility.TimeShow;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    Context context;
    List<NotificationModel> data;
    NotificationModel current;

    public NotificationAdapter(Context context, List<NotificationModel> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_notification, parent, false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        current = data.get(position);
        holder.title.setText(current.notif_title);
//        holder.postedBy.setText(current.notif_posted);
        TimeShow timeShow = new TimeShow();
        holder.postedBy.setText(timeShow.covertTimeToText(context, current.notif_posted));

        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HomeModel model = data.get(getAdapterPosition());
                Intent intent = new Intent(context, AddDetailsActivity.class);
                intent.putExtra("id", current.adId);
                intent.putExtra("from", "Home");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

//        holder.postedBy.setText(getDate(current.notif_posted));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public String getDate(String dateTime) {
        Locale loc = new Locale("ar");
        Locale.setDefault(loc);


        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = utcFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat pstFormat = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
//        pstFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        pstFormat.setTimeZone(TimeZone.getTimeZone("AST"));

//        System.out.println(pstFormat.format(date));
        return pstFormat.format(date);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, postedBy;
        FrameLayout frameLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notif_title);
            postedBy = itemView.findViewById(R.id.notif_posted);
            frameLayout = itemView.findViewById(R.id.frame);
        }
    }


}
