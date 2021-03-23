package com.zoolife.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.OTP.OTPResponseModel;
import com.zoolife.app.ResponseModel.SignInResponse.SignInResponseModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPVerification extends AppBaseActivity implements View.OnClickListener,
        OnOtpCompletionListener {
    private OtpView otpView;
    String otp1 = "";
    String email1 = "";
    String from = "";
    String password="";
    private TextView otpTV;
    ProgressBar progress_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_verification);

        initializeUi();
        setListeners();

        otp1 = getIntent().getStringExtra("otp");
        email1 = getIntent().getStringExtra("email");
        from = getIntent().getStringExtra("from");
        password=getIntent().getStringExtra("password");
        otpTV.setText(otp1);

    }

@Override
    protected void onResume() {
        super.onResume();
        setLightStatusBar();
    }    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.otpTV) {
            // Toast.makeText(this, otpView.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeUi() {
        otpView = findViewById(R.id.otp_view);
        otpTV = findViewById(R.id.otpTV);
        progress_circular = findViewById(R.id.progress_circular);
    }

    private void setListeners() {
        otpView.setOtpCompletionListener(this);
    }

    @Override
    public void onOtpCompleted(String otp) {
        // do Stuff

        verifyOTP(otp);


    }

    private void verifyOTP(String otp) {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<OTPResponseModel> call = apiService.otpVerify(email1, otp);
        call.enqueue(new Callback<OTPResponseModel>() {
            @Override
            public void onResponse(Call<OTPResponseModel> call, Response<OTPResponseModel> response) {
                try {


                    OTPResponseModel responseModel = response.body();
                    if (responseModel != null && !responseModel.isError()) {
                        finishAffinity();
                        session.setIsLogin(true);
                        session.setEmail(email1);
                        Toast.makeText(getApplicationContext(), "OTP Verified", Toast.LENGTH_LONG).show();

                        if (from != null && from.equalsIgnoreCase("forgotpassword")) {
                            Intent intent = new Intent(getBaseContext(), ChangePassword.class);
                            startActivity(intent);
                        } else {
                            Call<JsonObject> call2 = apiService.signIn(email1, password);
                            call2.enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    if (response.isSuccessful() && response.body().getAsJsonObject().get("data").isJsonObject()
                                            && !response.body().getAsJsonObject().get("error").getAsBoolean()) {

                                        Gson gson = new Gson();
                                        SignInResponseModel responseModel = gson.fromJson(response.body().getAsJsonObject().toString(), SignInResponseModel.class);

//                    SignInResponseModel responseModel =   new Gson().toJson(response.body());
//                    SignInResponseModel responseModel = response.body().getAsJsonObject();


                                        session.setSurName(responseModel.getData().getSurname());
                                        session.setFullName(responseModel.getData().getFullname());
                                        session.setUserId(responseModel.getData().getId());
                                        session.setEmail(responseModel.getData().getEmail());
                                        session.setPhone(responseModel.getData().getPhone());
                                        session.setLanguage(responseModel.getData().getLanguage());
                                        session.setCountryId(responseModel.getData().getCountryId());
                                        session.setCountry(responseModel.getData().getCountry());
                                        session.setCity(responseModel.getData().getCity());
                                        session.setCityId(responseModel.getData().getCityId());
                                        session.setYear(responseModel.getData().getBYear());
                                        session.setMonth(responseModel.getData().getBMonth());
                                        session.setDay(responseModel.getData().getBDay());
                                        finish();
                                        Intent intent = new Intent(OTPVerification.this, MainActivity.class);
                                        startActivity(intent);


                                        session.setIsLogin(true);
                                        // infoDialog("تهانينا ! لقد قمت بتسجيل الدخول بنجاح");
                                        progress_circular.setVisibility(View.GONE);

                                    } else {

                                        progress_circular.setVisibility(View.GONE);

                                        Toast.makeText(getApplicationContext(), response.body().getAsJsonObject().get("message").toString(), Toast.LENGTH_LONG).show();
                                        //infoDialog("Server Error.");
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {
                                    t.printStackTrace();
                                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                    progress_circular.setVisibility(View.GONE);

                                }
                            });




                        }


                        progress_circular.setVisibility(View.GONE);
                    } else {
                        // infoDialog("Server Error.");
                        progress_circular.setVisibility(View.GONE);
                        Toast.makeText(OTPVerification.this, responseModel.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.e("TAG", "Exception at verify " + e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<OTPResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(OTPVerification.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }
}