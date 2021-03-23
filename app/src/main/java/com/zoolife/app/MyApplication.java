package com.zoolife.app;

import android.app.Application;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;

public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();
    public static MyApplication instance;
    public SharedPreferences userLocaleHolder;

    public MyApplication() {
        instance = this;
    }

    public static MyApplication getAppInstance() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
//        FirebaseApp.initializeApp(this);

    }


    public void saveHiddenModeSetting(String which) {
        this.userLocaleHolder = getSharedPreferences("locale", 0);
        SharedPreferences.Editor editor = this.userLocaleHolder.edit();
        editor.putString("lang", which);
        editor.commit();
    }

    public String getHiddenModeSettings() {
        this.userLocaleHolder = getSharedPreferences("locale", 0);
        if (this.userLocaleHolder != null) {
            return this.userLocaleHolder.getString("lang", "ar");
        }
        return "ar";
    }
}