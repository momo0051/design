package com.zoolife.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zoolife.app.activity.AdsTermsCondition;
import com.zoolife.app.R;
import com.zoolife.app.models.CategoryModel;

import java.util.List;

public class CategoryAdsAdapter extends RecyclerView.Adapter<CategoryAdsAdapter.MyViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<CategoryModel> data;
    CategoryModel current;

    public CategoryAdsAdapter(Context context, List<CategoryModel> data){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data=data;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_single_add_ads,parent,false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        current = data.get(position);
        holder.title.setText(current.name);

        holder.btnCLick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,AdsTermsCondition.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView icon;
        LinearLayout btnCLick;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cat_name);
            icon = itemView.findViewById(R.id.icon_cat_ads);
            btnCLick = itemView.findViewById(R.id.single_click_cat);
        }
    }

}
