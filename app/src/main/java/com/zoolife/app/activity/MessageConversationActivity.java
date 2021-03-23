package com.zoolife.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zoolife.app.R;

import androidx.appcompat.app.AppCompatActivity;

public class MessageConversationActivity extends AppCompatActivity {

    RelativeLayout backMessageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_conversation);

        backMessageBtn = (RelativeLayout) findViewById(R.id.backMessageBtn);

        backMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MessageConversationActivity.this, MessageActivity.class);
                startActivity(intent);

            }
        });
    }
}