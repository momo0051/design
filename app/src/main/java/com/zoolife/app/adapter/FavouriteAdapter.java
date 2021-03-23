package com.zoolife.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.GetFavourites.Datum;
import com.zoolife.app.activity.AddDetailsActivity;
import com.zoolife.app.utility.TimeShow;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {
    Context context;
    List<Datum> data;
    Datum current;

    public FavouriteAdapter(Context context, List<Datum> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_favourite, parent, false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Datum current = data.get(position);
//        holder.setIsRecyclable(true);
        /*if(position % 2 == 0){
            holder.singleCLick.setBackgroundColor(context.getResources().getColor(R.color.white_smoke));

        }else{
            holder.singleCLick.setBackgroundColor(context.getResources().getColor(R.color.white));
        }*/
        holder.itemTitle.setText(current.getItemTitle());
        // holder.itemPOstedDate.setText(parseDate(current.getCreatedAt()));
//        holder.itemPOstedDate.setText(parseDate(current.getCo()));
        holder.itemLocation.setText(current.getCity());
        holder.itemPostedBy.setText(current.getUsername());
        TimeShow timeShow = new TimeShow();
        holder.itemPOstedDate.setText(timeShow.covertTimeToText(context, current.getCreatedAt()));

        RequestOptions requestOptions = new RequestOptions();

        Resources r = context.getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                18,
                r.getDisplayMetrics()
        );
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners((int) px));
        Glide
                .with(context)
                .load(current.getImgUrl())
                .centerCrop()
                .apply(requestOptions)
                .placeholder(R.drawable.placeholder)
                .into(holder.itemImage);

        holder.singleCLick.setOnClickListener(v -> {
            if (context != null) {
                Intent addDetailsIntent = new Intent(context, AddDetailsActivity.class);
                addDetailsIntent.putExtra("from", "Home");
                addDetailsIntent.putExtra("id", String.valueOf(current.getId()));
                context.startActivity(addDetailsIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setEvenData() {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle, itemPOstedDate, itemPostedBy, itemLocation;
        LinearLayout singleCLick;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            singleCLick = itemView.findViewById(R.id.single_click);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemPOstedDate = itemView.findViewById(R.id.item_posted_on);
            itemPostedBy = itemView.findViewById(R.id.item_user_postedby);
            itemLocation = itemView.findViewById(R.id.item_location);

        }

    }

    private String parseDate(String createdAt) {
        String date = createdAt.split(" ")[0];
        String time = createdAt.split(" ")[1];

        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]) - 1;
        int day = Integer.parseInt(date.split("-")[2]);

        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);
        int second = Integer.parseInt(time.split(":")[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute, second);

        Date createdAtDate = cal.getTime();

        return new SimpleDateFormat("EEEE, MMM d, yyyy").format(createdAtDate);
    }
}
