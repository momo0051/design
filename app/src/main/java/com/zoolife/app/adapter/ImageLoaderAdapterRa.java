package com.zoolife.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.zoolife.app.R;
import com.zoolife.app.activity.AddDetailsActivity;
import com.zoolife.app.models.RelatedAdModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageLoaderAdapterRa extends RecyclerView.Adapter<ImageLoaderAdapterRa.MyViewHolder> {
    Context context;
    List<RelatedAdModel> data;

    public ImageLoaderAdapterRa(Context context, List<RelatedAdModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image, parent, false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        RelatedAdModel current = data.get(position);
        holder.setIsRecyclable(true);
        /*if(position % 2 == 0){
            holder.singleCLick.setBackgroundColor(context.getResources().getColor(R.color.white_smoke));

        }else{
            holder.singleCLick.setBackgroundColor(context.getResources().getColor(R.color.white));
        }*/

        Glide.with(context)
                .load(current.getImgUrl())
                .fitCenter()
                .transform(new CenterCrop(), new RoundedCorners(10))
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
//            Dialog builder = new Dialog(context);
//            builder.setContentView(R.layout.photo_layout);
//            builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//            builder.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            ImageView imageView = builder.findViewById(R.id.image);
//            ProgressBar progressBar = builder.findViewById(R.id.progressBar);
//            progressBar.setVisibility(View.VISIBLE);
//            try {
//                Picasso.get()
//                        .load(current.getIcon())
//                        .into(imageView, new Callback() {
//                            @Override
//                            public void onSuccess() {
//                                progressBar.setVisibility(View.GONE);
//                            }
//
//                            @Override
//                            public void onError(Exception e) {
//                                progressBar.setVisibility(View.GONE);
//                            }
//                        });
//            }
//            catch (Exception ex ){
//                ex.printStackTrace();
//            }
//            builder.show();

            Intent intent = new Intent(context, AddDetailsActivity.class);
            intent.putExtra("id", current.getId());
            intent.putExtra("from", "Home");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setEvenData() {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);

        }
    }
}
