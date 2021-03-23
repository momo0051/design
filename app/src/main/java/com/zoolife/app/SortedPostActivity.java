package com.zoolife.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zoolife.app.ResponseModel.AllPost.AllPostResponseModel;
import com.zoolife.app.ResponseModel.AllPost.DataItem;
import com.zoolife.app.activity.AppBaseActivity;
import com.zoolife.app.adapter.HomeAdapter;
import com.zoolife.app.models.HomeModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SortedPostActivity extends AppBaseActivity {

    HomeAdapter homeAdapter;
    RecyclerView recyclerView;
    ImageView btnSearchBack;
    FrameLayout searchingLayout;
    EditText searchbarEdittext;
    ImageView searchImageBtn;
    ProgressBar progress_circular;
    ArrayList<HomeModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_post);
        searchbarEdittext = findViewById(R.id.searchbar_edittext);
        searchImageBtn = findViewById(R.id.search_image_btn);
        progress_circular = findViewById(R.id.progress_circular_search);
        searchingLayout = findViewById(R.id.searching_layout);
        recyclerView = findViewById(R.id.search_recyclerview);
        btnSearchBack = (ImageView) findViewById(R.id.btnSearchBack);
        btnSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortedPostActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        Bundle extra = intent.getExtras();
        String activity = extra.getString("activity");
        if (activity.equals("search"))
            arrayList = (ArrayList<HomeModel>) args.getSerializable("ARRAYLIST");
        else
            searchingLayout.setVisibility(View.VISIBLE);

        if (searchingLayout.getVisibility() == View.VISIBLE) {
            searchImageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (searchbarEdittext.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Enter a Keyword to search", Toast.LENGTH_SHORT).show();
                    } else {
                        getAllPost(searchbarEdittext.getText().toString());
                    }
                }
            });


        }


        homeAdapter = new HomeAdapter(getApplicationContext(), arrayList,session);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void getAllPost(String name) {

        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<AllPostResponseModel> call = apiService.getAllPost();
        call.enqueue(new Callback<AllPostResponseModel>() {
            @Override
            public void onResponse(Call<AllPostResponseModel> call, Response<AllPostResponseModel> response) {
                AllPostResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {


                    ArrayList<HomeModel> arrayList = new ArrayList<>();


                    progress_circular.setVisibility(View.GONE);


                    for (int i = 0; i < responseModel.getData().size(); i++) {
                        if (responseModel.getData().get(i).getItemTitle().toLowerCase().contains(name.toLowerCase())) {
                            DataItem dataItem = responseModel.getData().get(i);
                            arrayList.add(new HomeModel(dataItem.getItemTitle(), dataItem.getCreatedAt(), dataItem.getCity(), dataItem.getUsername(), dataItem.getImgUrl(), String.valueOf(dataItem.getId()), dataItem.getPriority()));

                        }
                    }


                    if (arrayList.size() > 0) {
                        homeAdapter = new HomeAdapter(getApplicationContext(), arrayList,session);
                        recyclerView.setAdapter(homeAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    } else {
                        Toast.makeText(getApplicationContext(), "No Post found with the keyword!", Toast.LENGTH_SHORT).show();
//                        homeAdapter.notifyDataSetChanged();
                    }


                } else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<AllPostResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

}