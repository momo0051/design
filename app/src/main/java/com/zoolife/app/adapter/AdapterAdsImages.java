package com.zoolife.app.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.zoolife.app.R;
import com.zoolife.app.interfaces.ImageListener;
import com.zoolife.app.view.SquareImageView;

import java.util.List;

/**
 * Created by Rana on 8/12/2016.
 */
public class AdapterAdsImages extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Uri> adsImagesList;
    private Context _cntx;
    private ImageListener listener;
    private View.OnClickListener uploadClickListener;
    private View.OnClickListener coverImageClickListener;
    int selectedPosition = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ad_img;

        public MyViewHolder(View view) {
            super(view);
            ad_img = (ImageView) view.findViewById(R.id.ad_img);
        }
    }

    public class UploadViewHolder extends RecyclerView.ViewHolder {
        public SquareImageView coverImage;
        public LinearLayout addImagesLyt;

        public UploadViewHolder(View view) {
            super(view);
            coverImage = (SquareImageView) view.findViewById(R.id.coverImg);
            addImagesLyt = (LinearLayout) view.findViewById(R.id.adImagesContainer);
        }
    }

    public AdapterAdsImages(List<Uri> adsImagesList, Context _cntx, ImageListener listener, View.OnClickListener uploadClickListener, View.OnClickListener coverImageClickListener) {
        this.adsImagesList = adsImagesList;
        this._cntx = _cntx;
        this.listener = listener;
        this.uploadClickListener = uploadClickListener;
        this.coverImageClickListener = coverImageClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0: {
                View uploadView;
                uploadView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_upload_image, parent, false);
                return new UploadViewHolder(uploadView);
            }
            default: {
                View itemView;
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_adimage, parent, false);
                return new MyViewHolder(itemView);
            }
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {
            case 0: {
                ((UploadViewHolder) holder).addImagesLyt.setOnClickListener(uploadClickListener);
                ((UploadViewHolder) holder).coverImage.setOnClickListener(coverImageClickListener);
                break;
            }
            default:
                final Uri imageuri = adsImagesList.get(position - 1);
                if (imageuri.toString().startsWith("http")) {
                    Picasso.get()
                            .load(imageuri)
                            .into(((MyViewHolder) holder).ad_img);
                } else {
                    ((MyViewHolder) holder).ad_img.setImageURI(imageuri);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                selectedPosition=position;
                        listener.getSelectedImage(position - 1);
                        notifyDataSetChanged();
                    }
                });
        }
    }

    @Override
    public int getItemCount() {
        return adsImagesList.size() + 1;
    }

}
