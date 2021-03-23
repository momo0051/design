package com.zoolife.app.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.AddDelivery.AddDeliveryResponseModel;
import com.zoolife.app.ResponseModel.CityNameResponseModel.CityNameResponseModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zoolife.app.activity.AppBaseActivity.session;

public class AddDeliveryActivity extends AppCompatActivity {

    private static final String TAG = "AddDeliveryActivity";


    EditText deliveryDesc;
    Spinner deliveryCitySpinner;
    ArrayAdapter aa;
    String itemTitle, city;
    Button addDeliveryBtn;
    ProgressBar progress_circular;
    String[] cities = new String[]{};
    List<String> cityList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery);

        deliveryCitySpinner = findViewById(R.id.add_delivery_city_spinner);
        deliveryDesc = findViewById(R.id.add_delivery_desc);


        progress_circular = findViewById(R.id.add_delivery_pbar);

        aa = new ArrayAdapter(AddDeliveryActivity.this, android.R.layout.simple_spinner_item, cities);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        deliveryCitySpinner.setAdapter(aa);


        addDeliveryBtn = findViewById(R.id.add_delivery_btn);
        addDeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemTitle = deliveryDesc.getText().toString();
                city = deliveryCitySpinner.getSelectedItem().toString();
                if (itemTitle.isEmpty()) {
                    Toast.makeText(AddDeliveryActivity.this, "Enter Title " + itemTitle, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (city.isEmpty()) {
                    Toast.makeText(AddDeliveryActivity.this, "Enter city " + city, Toast.LENGTH_SHORT).show();
                    return;
                }
                addDelivery();

            }
        });
        getAllCityNames();

    }

    public void addDelivery() {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<AddDeliveryResponseModel> call = apiService.addDelivery(session.getUserId(), "", itemTitle, "4000", "4", "1", "1", "0", city, "saudia", "550655214");
        call.enqueue(new Callback<AddDeliveryResponseModel>() {
            @Override
            public void onResponse(Call<AddDeliveryResponseModel> call, Response<AddDeliveryResponseModel> response) {
                AddDeliveryResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {
                    progress_circular.setVisibility(View.GONE);

                    //  Toast.makeText(AddDeliveryActivity.this, "" + responseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();


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
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
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
                if (deliveryCitySpinner.getAdapter() == null) {
                    ArrayAdapter aa = new ArrayAdapter(AddDeliveryActivity.this, android.R.layout.simple_spinner_item, cities);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    deliveryCitySpinner.setAdapter(aa);
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