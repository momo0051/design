package com.zoolife.app.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zoolife.app.R;

public class AboutUsActivity extends AppBaseActivity {

    ImageView llFacebook, llInsta, llTiktok, llSnap, llTwitter, llTelegram;
    LinearLayout llWhatsapp1, llWhatsapp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        llFacebook = findViewById(R.id.ll_facebook);
        llInsta = findViewById(R.id.ll_insta);
        llTiktok = findViewById(R.id.ll_tiktok);
        llSnap = findViewById(R.id.ll_snap);
        llTwitter = findViewById(R.id.ll_twitter);
        llTelegram = findViewById(R.id.ll_twitter);

        findViewById(R.id.back).setOnClickListener(v -> onBackPressed());

        llWhatsapp1 = findViewById(R.id.ll_whatsapp_1);
        llWhatsapp2 = findViewById(R.id.ll_whatsapp_2);

        llFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("https://www.facebook.com/zoolife.mooh");
            }
        });
        llInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("https://www.instagram.com/zoolife2030/");
            }
        });
        llTiktok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("https://www.tiktok.com/@zoolife2030?lang=en");
            }
        });
        llSnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("https://www.snapchat.com/add/zoolife.sa");
            }
        });
        llTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("https://twitter.com/zoolife2030/status/1336141977218379777?s=24");
            }
        });

        llWhatsapp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp("+966591114156", "");
            }
        });


        llWhatsapp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp("+966551180030", "");
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        setLightStatusBar();
    }

    public void openLink(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    public void openWhatsApp(String number, String message) {
        try {
            String text = message;// Replace with your message.

            String toNumber = PhoneNumberUtils.stripSeparators(number); // Replace with mobile phone number without +Sign or leading zeros, but with country code
            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Whatsapp app not found on your device", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}