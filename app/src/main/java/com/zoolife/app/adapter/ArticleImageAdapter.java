package com.zoolife.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.zoolife.app.R;
import com.zoolife.app.activity.ArticleDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArticleImageAdapter extends PagerAdapter {


    List<String> imageList;
    ArticleDetailActivity activity;
    LayoutInflater mLayoutInflater;

    public ArticleImageAdapter(ArticleDetailActivity activity, List<String> imageList ) {
        this.imageList = imageList;
        this.activity = activity;
        mLayoutInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.article_imageslider_item, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = itemView.findViewById(R.id.imageViewMain);

        // setting the image in the imageView

        Picasso.get().load(imageList.get(position)).into(imageView);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
