package com.zoolife.app.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.zoolife.app.LinkUtils;
import com.zoolife.app.R;

import java.util.ArrayList;
import java.util.List;

public class ArticleDetailActivity extends AppBaseActivity {

    String image1, image2, image3, title, description, date;
    TextView articleTitle, articleDescription, articleDate;
    ImageView backBtn;
    ViewPager articleViewPager;

    List<SlideModel> imageList;

    ImageSlider articleSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            image1 = extras.getString("image1");
            image2 = extras.getString("image2");
            image3 = extras.getString("image3");
            title = extras.getString("title");
            description = extras.getString("description");
            date = extras.getString("date");



            Log.i("askjhbvwrv","ss"+description);

        }
        imageList = new ArrayList<>();
        if (!image1.isEmpty())
            imageList.add(new SlideModel(image1));
        if (!image2.isEmpty())
            imageList.add(new SlideModel(image2));
        if (!image3.isEmpty())
            imageList.add(new SlideModel(image3));

        articleDate = findViewById(R.id.article_detail_date);
        articleTitle = findViewById(R.id.article_detail_title);
        articleDescription = findViewById(R.id.article_detail_description);
        articleDate.setText(date);
        articleTitle.setText(title);



        articleDescription.setText(description);




        articleSlider = findViewById(R.id.article_viewpager);
        articleSlider.startSliding(1000);
        articleSlider.setImageList(imageList, true);

        backBtn = findViewById(R.id.article_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArticleDetailActivity.this.finish();
            }
        });

//        ArticleImageAdapter articleImageAdapter = new ArticleImageAdapter(ArticleDetailActivity.this, imageList);
//        articleViewPager.setAdapter(articleImageAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setLightStatusBar();


    }
}