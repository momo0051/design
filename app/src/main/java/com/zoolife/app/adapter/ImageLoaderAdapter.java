package com.zoolife.app.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.zoolife.app.R;
import com.zoolife.app.models.ImageModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageLoaderAdapter extends RecyclerView.Adapter<ImageLoaderAdapter.MyViewHolder> {
    Context context;
    List<ImageModel> data;

    public ImageLoaderAdapter(Context context, List<ImageModel> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image,parent,false);
        return new MyViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        ImageModel current = data.get(position);
        holder.setIsRecyclable(true);
        /*if(position % 2 == 0){
            holder.singleCLick.setBackgroundColor(context.getResources().getColor(R.color.white_smoke));

        }else{
            holder.singleCLick.setBackgroundColor(context.getResources().getColor(R.color.white));
        }*/

        Glide.with(context)
                .load(current.getIcon())
                .fitCenter()
                .transform(new CenterCrop(),new RoundedCorners(10))
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {

            Log.i("asdkhbjwev","ksadhbvwv");

            Dialog builder = new Dialog(context);
            builder.setContentView(R.layout.photo_layout);
            builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            builder.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ImageView imageView = builder.findViewById(R.id.image);
            ProgressBar progressBar = builder.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            try {
                Picasso.get()
                        .load(current.getIcon())
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
            catch (Exception ex ){
                ex.printStackTrace();
            }
            builder.show();
        });

    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setEvenData(){

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);

        }
    }
}
