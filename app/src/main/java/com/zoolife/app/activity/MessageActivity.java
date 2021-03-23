package com.zoolife.app.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zoolife.app.R;
import com.zoolife.app.adapter.MessageAdapter;
import com.zoolife.app.models.MessageModels;
import com.zoolife.app.utility.LocaleHelper;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MessageActivity extends AppCompatActivity {

    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    List<MessageModels> messageModelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

//        recyclerView = findViewById(R.id.message_rv);
//        messageAdapter = new MessageAdapter(MessageActivity.this,populateData());
//        recyclerView.setAdapter(messageAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
//        messageAdapter.notifyDataSetChanged();

    }

    public List<MessageModels> populateData(){
        messageModelsList = new ArrayList<>();

        MessageModels messageModels = new MessageModels("sk","sk_urtys","THis is test message","2020-10-20","1",getApplicationContext().getResources().getColor(R.color.orchid));
        MessageModels messageModels1 = new MessageModels("ty","tyreklasdf","THis is test message","2020-10-20","2",getApplicationContext().getResources().getColor(R.color.orchid));
        MessageModels messageModels2 = new MessageModels("re","retrhf","THis is test message","2020-10-20","3",getApplicationContext().getResources().getColor(R.color.orchid));
        MessageModels messageModels3 = new MessageModels("kj","kjasrta","THis is test message","2020-10-20","4",getApplicationContext().getResources().getColor(R.color.orchid));
        MessageModels messageModels4 = new MessageModels("lk","lkasdfhja","THis is test message","2020-10-20","5",getApplicationContext().getResources().getColor(R.color.orchid));

        messageModelsList.add(messageModels);
        messageModelsList.add(messageModels1);
        messageModelsList.add(messageModels2);
        messageModelsList.add(messageModels3);
        messageModelsList.add(messageModels4);

        return messageModelsList;

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = LocaleHelper.onAttach(newBase);
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

}