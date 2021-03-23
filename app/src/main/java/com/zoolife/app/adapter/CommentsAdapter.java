package com.zoolife.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zoolife.app.R;
import com.zoolife.app.models.CommentModel;
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

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<CommentModel> data;
    CommentModel current;

    public CommentsAdapter(Context context, List<CommentModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_comment, parent, false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        current = data.get(position);
        holder.setIsRecyclable(true);
        /*if(position % 2 == 0){
            holder.singleCLick.setBackgroundColor(context.getResources().getColor(R.color.white_smoke));

        }else{
            holder.singleCLick.setBackgroundColor(context.getResources().getColor(R.color.white));
        }*/
        holder.idTV.setText("" + current.username);
        holder.commentTv.setText(current.title);

        TimeShow timeShow = new TimeShow();
        if (!current.co.contains("T")) {
            holder.co.setText(timeShow.covertTimeToText(context, current.co));
        }

//        holder.co.setText(getDate(current.co));

//        Glide
//        .with(context)
//        .load(current.image)
//        .centerCrop()
//        .placeholder(R.drawable.placeholder)
//        .into(holder.itemImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context != null) {
//                    Intent intent = new Intent(context, AdInfoActivity.class);
//                    context.startActivity(intent);
                }

            }
        });

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
        TextView idTV, commentTv, co;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idTV = itemView.findViewById(R.id.idTV);
            commentTv = itemView.findViewById(R.id.commentTv);
            co = itemView.findViewById(R.id.time_stamp);


        }
    }
}
