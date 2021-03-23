package com.zoolife.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.ShowComment.DataItem;
import com.zoolife.app.ResponseModel.ShowComment.ViewCommentsResponseModel;
import com.zoolife.app.adapter.CommentsAdapter;
import com.zoolife.app.models.CommentModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullCommentActivity extends AppBaseActivity {
    private static final String TAG = "FullCommentActivity";

    ImageView btnSearchBack;
    ProgressBar progress_circular;
    CommentsAdapter commentsAdapter;
    RecyclerView fullCommentRecyclerview;
    String add_id, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_comment);
        progress_circular = findViewById(R.id.progress_circular_comments);
        btnSearchBack = findViewById(R.id.btn_back_fullcomment);
        fullCommentRecyclerview = findViewById(R.id.full_comment_rv);
        btnSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FullCommentActivity.this.finish();
            }
        });

        add_id = getIntent().getStringExtra("id");
        try {
            username = getIntent().getStringExtra("username");
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewComments();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLightStatusBar();
    }

    private void viewComments() {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<ViewCommentsResponseModel> call = apiService.listCommentByItem(add_id);
        call.enqueue(new Callback<ViewCommentsResponseModel>() {
            @Override
            public void onResponse(Call<ViewCommentsResponseModel> call, Response<ViewCommentsResponseModel> response) {
                ViewCommentsResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {
                    progress_circular.setVisibility(View.GONE);

                    if (responseModel != null && !responseModel.isError()) {
                        progress_circular.setVisibility(View.GONE);

                        ArrayList<CommentModel> arrayList = new ArrayList<>();

                        for (int i = 0; i < responseModel.getData().size(); i++) {
                            DataItem dataItem = responseModel.getData().get(i);
                            arrayList.add(new CommentModel(dataItem.getMessage(), String.valueOf(dataItem.getId()), username, dataItem.getCo()));
                        }
                        if (arrayList.size() > 0) {
                            commentsAdapter = new CommentsAdapter(getApplicationContext(), arrayList);
                            fullCommentRecyclerview.setAdapter(commentsAdapter);
                            fullCommentRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    } else {
                        // infoDialog("Server Error.");
                        progress_circular.setVisibility(View.GONE);
                    }
                } else {
                    progress_circular.setVisibility(View.GONE);
                    Toast.makeText(FullCommentActivity.this, "No Comments", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ViewCommentsResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }
}