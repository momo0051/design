package com.zoolife.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zoolife.app.R;
import com.zoolife.app.utility.LocaleHelper;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class AdsTermsCondition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_terms_condition);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        findViewById(R.id.terms_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdsTermsCondition.this, MessageActivity.class));
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = LocaleHelper.onAttach(newBase);
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

}