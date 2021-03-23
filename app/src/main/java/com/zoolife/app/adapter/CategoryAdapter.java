package com.zoolife.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zoolife.app.R;
import com.zoolife.app.fragments.HomeFragment;
import com.zoolife.app.models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    List<CategoryModel> data;
    CategoryModel current;
    int row_index = -1;
    HomeFragment homeFragment;

    public CategoryAdapter(Context context, List<CategoryModel> data, HomeFragment homeFragment) {
        this.context = context;
        this.data = data;
        this.homeFragment = homeFragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        current = data.get(position);
        holder.itemTitle.setText(data.get(position).getName());
        Glide
                .with(context)
                .load(current.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.itemImage);


        holder.categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                homeFragment.getSubCategory(Integer.parseInt(data.get(position).getId()));
                homeFragment.getAllPostByCategory(Integer.parseInt(data.get(position).getId()));
                notifyDataSetChanged();
            }
        });

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) holder.cardView.getLayoutParams();

        if (row_index == position) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#3280B1"));
            holder.cardView.setAlpha(0.61f);
            holder.cardView.setMaxCardElevation(0);
            holder.itemTitle.setTextColor(Color.parseColor("#EBF2EE"));
            lp.topMargin = 0;
//            holder.categoryLayout.setBackground(context.getResources().getDrawable(R.drawable.border_blue_yellow));
//            holder.itemTitle.setTextColor(context.getResources().getColor(R.color.appColor));
//            holder.itemImage.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.appColor)));

        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#EBF2EE"));
            holder.cardView.setAlpha(1f);
            holder.cardView.setMaxCardElevation(8);
            holder.itemTitle.setTextColor(Color.parseColor("#9F9F9F"));
            lp.topMargin = 20;
//            holder.categoryLayout.setBackground(context.getResources().getDrawable(R.drawable.border_blue));
//            holder.itemTitle.setTextColor(context.getResources().getColor(R.color.white));
//            holder.itemImage.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.white)));
        }
        holder.cardView.setLayoutParams(lp);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setEvenData() {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView itemImage, itemFavImage;
        TextView itemTitle, itemPOstedDate, itemPostedBy, itemLocation;
        LinearLayout categoryLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            itemImage = itemView.findViewById(R.id.image);
            itemTitle = itemView.findViewById(R.id.title);
            categoryLayout = itemView.findViewById(R.id.categoryLayout);

        }
    }
}
