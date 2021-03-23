package com.zoolife.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.zoolife.app.R;
import com.zoolife.app.models.ImageData;

import java.util.List;

public class AdSliderAdapter  extends PagerAdapter {

    Context context;
    List<ImageData> adSlideData;
    LayoutInflater layoutInflater;


    public AdSliderAdapter(Context context,  List<ImageData> adSlideData) {
        this.context = context;
        this.adSlideData = adSlideData;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return adSlideData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.layout_adslider_adapter, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.adSliderImageview);

        container.addView(itemView);
        imageView.setImageResource(adSlideData.get(position).getIcon());

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
