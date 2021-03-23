package com.zoolife.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.zoolife.app.R;
import com.zoolife.app.activity.ArticleDetailActivity;
import com.zoolife.app.models.ArticlesModel;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    Context context;
    List<ArticlesModel> data;

    public ArticlesAdapter(Context context, List<ArticlesModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ArticlesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View articleResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_article_grid, parent, false);
        return new ArticlesAdapter.MyViewHolder(articleResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapter.MyViewHolder holder, int position) {
        ArticlesModel model = data.get(position);


        holder.image1.setClipToOutline(true);
        Picasso.get().load(model.image1).into(holder.image1);
        holder.title.setText(model.title);
        holder.date.setText(model.date);

        holder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ArticleDetailActivity.class);
                intent.putExtra("image1", model.image1);
                intent.putExtra("image2", model.image2);

                intent.putExtra("image3", model.image3);
                intent.putExtra("id", model.id);
                intent.putExtra("title", model.title);
                intent.putExtra("description", model.description);
                intent.putExtra("date", model.date);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView image1;
        TextView title;
        TextView date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.article_image1);
            title = itemView.findViewById(R.id.article_title);
            date = itemView.findViewById(R.id.article_date);

        }
    }
}
