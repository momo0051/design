package com.zoolife.app.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.zoolife.app.R;
import com.zoolife.app.utility.LocaleHelper;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class SplashActivity extends AppBaseActivity {

    Handler handler = new Handler();
    String  TAG = "SplashActivityyyy";
    private static final int PERMISSIONS_REQUEST = 211;
    boolean allPermission = true;
    AlertDialog dialog;
    LinearLayout btnPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        FirebaseApp.initializeApp(this);

        try {
//            String token = FirebaseInstanceId.getInstance().getToken();
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    String token = instanceIdResult.getToken();
                    // send it to server
                }
            });
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        try {

            View decorView = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window window = getWindow();
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        checkGoHome();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLightStatusBar();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

//            if(!session.isLogin()) {
//                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                finish();
//            }
//            else{
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
          //  }
        }
    };

    public void checkGoHome(){
        handler.postDelayed(runnable,3000);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = LocaleHelper.onAttach(newBase);
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

}