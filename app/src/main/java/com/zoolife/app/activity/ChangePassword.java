package com.zoolife.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.ChangePassword.ChangePasswordResponseModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppBaseActivity {
    ProgressBar progress_circular;

    EditText editTextConfirmPassword, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        progress_circular = findViewById(R.id.progress_circular);

        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    changePassword();

                }
            }
        });
    }

    private void changePassword() {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<ChangePasswordResponseModel> call = apiService.updatePassword(editTextPassword.getText().toString(), session.getPhone());
        Log.e("TAG", "Change Pass " + editTextPassword.getText().toString() + " Phone " + session.getEmail());
        call.enqueue(new Callback<ChangePasswordResponseModel>() {
            @Override
            public void onResponse(Call<ChangePasswordResponseModel> call, Response<ChangePasswordResponseModel> response) {
                ChangePasswordResponseModel responseModel = response.body();
                if (responseModel != null && !responseModel.isError()) {
                    Toast.makeText(getApplicationContext(), responseModel.getMessage(), Toast.LENGTH_LONG).show();
                    progress_circular.setVisibility(View.GONE);
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                    finishAffinity();

                } else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                    Toast.makeText(getBaseContext(), responseModel.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ChangePasswordResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

    private boolean isValid() {

        if (editTextPassword.getText().toString().isEmpty()) {
            editTextPassword.setError("أدخل كلمة المرور");
            return false;
        } else if (editTextConfirmPassword.getText().toString().isEmpty()) {
            editTextConfirmPassword.setError("أدخل تأكيد كلمة المرور");
            return false;
        } else if (!editTextConfirmPassword.getText().toString().equals(editTextPassword.getText().toString())) {
            editTextConfirmPassword.setError("يجب أن تكون كلتا كلمتي المرور متطابقتين");
            return false;
        }
        return true;

    }
}