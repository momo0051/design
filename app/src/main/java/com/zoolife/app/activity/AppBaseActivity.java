package com.zoolife.app.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.Category.CategoryResponseModel;
import com.zoolife.app.Session;

import androidx.appcompat.app.AppCompatActivity;

public class AppBaseActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public SharedPreferences sharedpreferences;
    public SharedPreferences.Editor editor;
    static public Session session;//global variable
    static public CategoryResponseModel categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        session = new Session(this); //in oncreate

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void forceRTLIfSupported() {
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }

    public void setLightStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = getWindow().getDecorView();
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }

        View toolbar = findViewById(R.id.toolbar_2);
        if (toolbar != null) toolbar.setBackgroundColor(Color.WHITE);
        getWindow().setStatusBarColor(Color.WHITE);
    }

    void setDarkStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = getWindow().getDecorView();
            int flags = view.getSystemUiVisibility();
            flags ^= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }

        findViewById(R.id.toolbar_2).setBackgroundColor(Color.parseColor("#3280b1"));
        getWindow().setStatusBarColor(Color.parseColor("#3280b1"));
    }
}