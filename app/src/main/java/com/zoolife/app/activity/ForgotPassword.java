package com.zoolife.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.Reset.ResetResponseModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppBaseActivity {

    EditText editTextUserName;
    ProgressBar progress_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //forceRTLIfSupported();
        setContentView(R.layout.activity_forgot_password);

        editTextUserName = findViewById(R.id.editTextUserName);
        progress_circular = findViewById(R.id.progress_circular);

        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(isValidEmail(editTextUserName.getText().toString()))
//                {
                resetPassword(editTextUserName.getText().toString());
//                }
//                else
//                {
//                    Toast.makeText(getApplicationContext(),"\n" +
//                            "لو سمحت أدخل بريد إليكتروني صالح",Toast.LENGTH_LONG).show();
//                }
            }
        });

        findViewById(R.id.loginTV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLightStatusBar();
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    private void resetPassword(String email) {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<JsonObject> call = apiService.resetPassword(email);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                ResetResponseModel responseModel = response.body();
                if (response.isSuccessful() &&
                        !response.body().getAsJsonObject().get("error").getAsBoolean()) {

                    Gson gson = new Gson();
                    ResetResponseModel responseModel = gson.fromJson(response.body().getAsJsonObject().toString(), ResetResponseModel.class);

                    Toast.makeText(getApplicationContext(), responseModel.getMessage(), Toast.LENGTH_LONG).show();
                    progress_circular.setVisibility(View.GONE);

                    Intent intent = new Intent(getBaseContext(), OTPVerification.class);
                    intent.putExtra("otp", responseModel.getOtp());
                    intent.putExtra("email", editTextUserName.getText().toString());
                    intent.putExtra("from", "forgotpassword");
                    startActivity(intent);

                } else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                    Toast.makeText(ForgotPassword.this, response.body().getAsJsonObject().get("message").toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(ForgotPassword.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }
}