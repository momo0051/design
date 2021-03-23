package com.zoolife.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zoolife.app.R;
import com.zoolife.app.models.related_ad_home.Datum;

public class ImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);


        Toolbar toolbar=findViewById(R.id.toolbarImageDetail);

        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }




        Intent intent=getIntent();


        Datum datum=intent.getParcelableExtra("image_detail");


        TextView imgTitle=findViewById(R.id.imgTitle);

        imgTitle.setText(datum.getTitle());

        TextView imgDescription=findViewById(R.id.imgDescription);

        imgDescription.setText(datum.getDescription());

        ImageView imgImage=findViewById(R.id.adImageDetail);

        Picasso.get().load(datum.getImage1()).into(imgImage);



        Log.i("aehbfweg","wd:"+datum.toString());




    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}