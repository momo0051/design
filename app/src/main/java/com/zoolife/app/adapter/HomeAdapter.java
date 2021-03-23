package com.zoolife.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zoolife.app.R;
import com.zoolife.app.Session;
import com.zoolife.app.activity.AddDetailsActivity;
import com.zoolife.app.activity.LoginActivity;
import com.zoolife.app.models.HomeModel;
import com.zoolife.app.utility.TimeShow;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    Context context;
    List<HomeModel> data;
    HomeModel current;
    private final Session session;

    public HomeAdapter(Context context, List<HomeModel> data, Session session) {
        this.context = context;
        this.data = data;
        this.session = session;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_home, parent, false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        current = data.get(position);
//        holder.setIsRecyclable(true);
        if (current.priority.equals("0") || current.priority == null) {
            holder.itemImage.setBorderColor(context.getResources().getColor(R.color.transparent));
//            holder.singleCLick.setBackground(context.getResources().getDrawable(R.color.white_smoke));
        } else {
            holder.itemImage.setBorderColor(context.getResources().getColor(R.color.yellow));
//            holder.singleCLick.setBackground(context.getResources().getDrawable(R.drawable.border_yellow));
        }
        holder.itemTitle.setText(current.title);
//        holder.itemPOstedDate.setText(parseDate(current.postedDate));
        TimeShow timeShow = new TimeShow();
        if (current.postedDate != null) {
            holder.itemPOstedDate.setText(timeShow.covertTimeToText(context, current.postedDate));
        }
//        holder.itemPOstedDate.setText(parseDate(current.postedDate));

//        holder.itemPostedBy.setText(timeShow.covertTimeToText(current.postedDate));

        Log.i("sadhjbvwve", "m:" + current.username);
        holder.itemPostedBy.setText(current.username);
        holder.itemLocation.setText(current.location);

        holder.item_user_postedby_layout.setVisibility(View.VISIBLE);

        final RequestOptions requestOptions = new RequestOptions()
                .transforms(new CenterCrop(), new RoundedCorners(16));


        Glide.with(context)
                .load(R.drawable.placeholder)
                .centerCrop()
                .apply(requestOptions)
                .into(holder.itemImage);
        if (!current.image.equals(""))
            Glide.with(context)
                    .load(current.image)
                    .centerCrop()
                    .apply(requestOptions)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.itemImage.post(() -> Glide.with(context)
                                    .load(R.drawable.placeholder)
                                    .centerCrop()
                                    .apply(requestOptions)
                                    .into(holder.itemImage));
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setEvenData() {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RoundedImageView itemImage;
        ImageView itemFavImage;
        TextView itemTitle, itemPOstedDate, itemPostedBy, itemLocation;
        LinearLayout singleCLick;
        LinearLayout item_user_postedby_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            singleCLick = itemView.findViewById(R.id.single_click);
            itemImage = itemView.findViewById(R.id.item_image);
            itemFavImage = itemView.findViewById(R.id.item_fav_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemPOstedDate = itemView.findViewById(R.id.date);
            itemPostedBy = itemView.findViewById(R.id.item_user_postedby);
            item_user_postedby_layout = itemView.findViewById(R.id.item_user_postedby_layout);
            itemLocation = itemView.findViewById(R.id.item_location);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (context != null) {
                HomeModel model = data.get(getAdapterPosition());
                Intent intent = new Intent(context, AddDetailsActivity.class);
                intent.putExtra("id", String.valueOf(model.id));
                intent.putExtra("from", "Home");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
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
