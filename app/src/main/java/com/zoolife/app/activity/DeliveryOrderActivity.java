package com.zoolife.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.AddDelivery.AddDeliveryResponseModel;
import com.zoolife.app.ResponseModel.CityNameResponseModel.CityNameResponseModel;
import com.zoolife.app.ResponseModel.UserPost.DataItem;
import com.zoolife.app.ResponseModel.UserPost.UserAllPostResponseModel;
import com.zoolife.app.adapter.DeliveryAdapter;
import com.zoolife.app.firebase.models.Group;
import com.zoolife.app.models.DeliveryModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeliveryOrderActivity extends AppBaseActivity {
    private static final String TAG = "DeliveryOrderActivity";

    ImageView btnSearchBack;
    RecyclerView deliveryRecyclerview;
    ProgressBar progress_circular;
    List<DeliveryModel> dataList;
    TextView addDelivery;
    DeliveryAdapter homeAdapter;


    List<Group> groupsList;


    EditText deliveryDesc;
    Spinner deliveryCitySpinner;
    ArrayAdapter aa;
    String itemTitle, city;
    Button addDeliveryBtn;
    String[] cities = new String[]{};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_order);

        AlertDialog.Builder builder = new AlertDialog.Builder(DeliveryOrderActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);

        deliveryRecyclerview = findViewById(R.id.delivery_recyclerview);
        btnSearchBack = findViewById(R.id.btn_back);
        progress_circular = findViewById(R.id.delivery_pbar);
        addDelivery = findViewById(R.id.add_delivery);
        addDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), AddDeliveryActivity.class));
                if (!session.isLogin()) {
                    startActivity(new Intent(DeliveryOrderActivity.this, LoginActivity.class));
                    return;
                }

                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.add_delivery_item, viewGroup, false);
                deliveryCitySpinner = dialogView.findViewById(R.id.add_delivery_city_spinner);
                deliveryDesc = dialogView.findViewById(R.id.add_delivery_desc);
                ImageView ivClose = dialogView.findViewById(R.id.close);


//                progress_circular = findViewById(R.id.add_delivery_pbar);

                aa = new ArrayAdapter(DeliveryOrderActivity.this, android.R.layout.simple_spinner_item, cities);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                deliveryCitySpinner.setAdapter(aa);
                addDeliveryBtn = dialogView.findViewById(R.id.add_delivery_btn);


                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                ivClose.setOnClickListener(view1 -> {
                    alertDialog.dismiss();
                });

                addDeliveryBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itemTitle = deliveryDesc.getText().toString();
                        city = deliveryCitySpinner.getSelectedItem().toString();
                        if (itemTitle.isEmpty()) {
                            Toast.makeText(DeliveryOrderActivity.this, "Enter Title " + itemTitle, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (city.isEmpty()) {
                            Toast.makeText(DeliveryOrderActivity.this, "Enter city " + city, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        addDelivery(itemTitle, city);
                        alertDialog.dismiss();
                        getAllDelivery();
                        homeAdapter.notifyDataSetChanged();


                    }
                });


            }
        });
        btnSearchBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DeliveryOrderActivity.this.finish();
            }
        });
        getAllCityNames();
        getAllDelivery();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLightStatusBar();
    }

    public void addDelivery(String itemTitle, String city) {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<AddDeliveryResponseModel> call = apiService.addDelivery(session.getUserId(), "", itemTitle, "4000", "4", "1", "1", "1", city, "المملكة العربية السعودية", session.getPhone());
        call.enqueue(new Callback<AddDeliveryResponseModel>() {
            @Override
            public void onResponse(Call<AddDeliveryResponseModel> call, Response<AddDeliveryResponseModel> response) {
                AddDeliveryResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {
                    progress_circular.setVisibility(View.GONE);
                    getAllDelivery();

                    // Toast.makeText(DeliveryOrderActivity.this, "" + responseModel.getMessage(), Toast.LENGTH_SHORT).show();


                } else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                    Log.d(TAG, "onResponse: " + responseModel.toString());
                }

            }

            @Override
            public void onFailure(Call<AddDeliveryResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";

                Log.i("asjdhvfwve","ss"+t.getMessage());

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    public void deleteDelivery(String id) {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<UserAllPostResponseModel> call = apiService.deleteDelivery(session.getUserId(), id);
        call.enqueue(new Callback<UserAllPostResponseModel>() {
            @Override
            public void onResponse(Call<UserAllPostResponseModel> call, Response<UserAllPostResponseModel> response) {
                UserAllPostResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {
                    progress_circular.setVisibility(View.GONE);

                    // Toast.makeText(DeliveryOrderActivity.this, "" + responseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    getAllDelivery();
                    homeAdapter.notifyDataSetChanged();


                } else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                    Log.d(TAG, "onResponse: " + responseModel.toString());
                }

            }

            @Override
            public void onFailure(Call<UserAllPostResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    private void getAllDelivery() {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);

        Log.i("asehvbwev","e"+session.getUserId());

        Call<UserAllPostResponseModel> call = apiService.getAllDelivery(Integer.parseInt(session.getUserId()));
        call.enqueue(new Callback<UserAllPostResponseModel>() {
            @Override
            public void onResponse(Call<UserAllPostResponseModel> call, Response<UserAllPostResponseModel> response) {
                UserAllPostResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {
                    progress_circular.setVisibility(View.GONE);

                    dataList = new ArrayList<>();
                    Log.d(TAG, "onResponse: true");

                    for (int i = 0; i < responseModel.getData().size(); i++) {
//                        if (responseModel.getData().get(i).getEmail().equals(session.getEmail())){
                        DataItem HomeModel = responseModel.getData().get(i);
                        dataList.add(new DeliveryModel(HomeModel.getItemTitle(), HomeModel.getItemDesc(), HomeModel.getCity(), HomeModel.getUsername(), HomeModel.getPhone(), HomeModel.getEmail(), String.valueOf(HomeModel.getId())));
//                        }

                    }
//                    for(int i=0 ; i<responseModel.getData().size() ; i++)
//                    {
//                        if (!responseModel.getData().get(i).getEmail().equals(session.getEmail())){
//                            DataItem HomeModel = responseModel.getData().get(i);
//                            dataList.add(new DeliveryModel(HomeModel.getItemTitle(),HomeModel.getCity(),HomeModel.getUsername(),HomeModel.getPhone(), HomeModel.getEmail(), HomeModel.getId()));
//                        }
//
//                    }

                    if (dataList.size() > 0) {
                        homeAdapter = new DeliveryAdapter(DeliveryOrderActivity.this, dataList, session);
                        deliveryRecyclerview.setAdapter(homeAdapter);
                        deliveryRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        homeAdapter.notifyDataSetChanged();
                    }

                } else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<UserAllPostResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();


                Log.i("wefwef","e:"+t.getMessage());

                Log.d(TAG, "onFailure: true" + t.getMessage());
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    private void getAllCityNames() {

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<CityNameResponseModel> call = apiService.getAllCityNames();
        call.enqueue(new Callback<CityNameResponseModel>() {
            @Override
            public void onResponse(Call<CityNameResponseModel> call, Response<CityNameResponseModel> response) {
                CityNameResponseModel responseModel = response.body();
                cities = new String[responseModel.getData().size()];
                for (int i = 0; i < responseModel.getData().size(); i++) {
                    cities[i] = responseModel.getData().get(i).getName();
                }

            }

            @Override
            public void onFailure(Call<CityNameResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}