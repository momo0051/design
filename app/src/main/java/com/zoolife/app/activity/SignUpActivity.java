package com.zoolife.app.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.SignupResponseModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;
import com.zoolife.app.utility.LocaleHelper;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppBaseActivity {

    RelativeLayout signupButton;
    TextView loginTextview;
    ToggleButton ivSignup;
    Typeface typeface;
    EditText editTextMobileNumber, editTextSPassword, editFullName, editTextEmail;
    String TAG = "SignUpActivityyyyy";
    ProgressBar progress_circular;
    LinearLayout linearLayout5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //forceRTLIfSupported();
        setContentView(R.layout.activity_sign_up);

        signupButton = (RelativeLayout) findViewById(R.id.signupButton);
        loginTextview = (TextView) findViewById(R.id.loginTextview);
        ivSignup = (ToggleButton) findViewById(R.id.ivSignup);
        editTextMobileNumber = (EditText) findViewById(R.id.editTextMobileNumber);
        editTextSPassword = (EditText) findViewById(R.id.editTextSPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editFullName = (EditText) findViewById(R.id.editFullName);
        progress_circular = findViewById(R.id.progress_circular);
        linearLayout5 = findViewById(R.id.linearLayout5);
        linearLayout5.setVisibility(View.GONE);
        loginTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ivSignup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {

                    editTextSPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    editTextSPassword.setTypeface(typeface);
                    editTextSPassword.setSelection(editTextSPassword.getText().length());
                    ivSignup.setBackgroundResource(R.drawable.eyehide);

                } else {
                    editTextSPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    editTextSPassword.setTypeface(typeface);
                    editTextSPassword.setSelection(editTextSPassword.getText().length());
                    ivSignup.setBackgroundResource(R.drawable.eyeshow);
                }
            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    registerUser();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setLightStatusBar();
    }

    private boolean isValid() {
        if (editFullName.getText().toString().equals("")) {
            editFullName.setError("أدخل اسمًا صالحًا");
            return false;
        }
//        else if (!isValidEmail(editTextEmail.getText().toString())){
       /* else if (editTextEmail.getText().toString().equals("")) {
            editTextEmail.setError("أدخل البريد الإلكتروني");
            return false;
        } */

        else if (editTextSPassword.getText().toString().equals("")) {
            editTextSPassword.setError("أدخل كلمة المرور");
            return false;
        } else if (editTextMobileNumber.getText().toString().equals("")) {
            editTextMobileNumber.setError("أدخل رقم صحيح");
            return false;
        }


        return true;


    }

    public void registerUser() {

        progress_circular.setVisibility(View.VISIBLE);
        String password = editTextSPassword.getText().toString();
        String contactNumber = editTextMobileNumber.getText().toString();
        String email = editTextEmail.getText().toString();
        String fullname = editFullName.getText().toString();

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
//        Call<JsonObject> call=apiService.signUp("register",fullname,email,contactNumber,password);
        Call<JsonObject> call = apiService.signUp(fullname, email, contactNumber, password);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                SignupResponseModel responseModel = response.body();
                if (response.isSuccessful() &&
                        !response.body().getAsJsonObject().get("error").getAsBoolean()) {

                    Gson gson = new Gson();
                    SignupResponseModel responseModel = gson.fromJson(response.body().getAsJsonObject().toString(), SignupResponseModel.class);

                    Log.d(TAG, response.toString());

                    Intent intent = new Intent(getBaseContext(), OTPVerification.class);
                    intent.putExtra("otp", responseModel.getOtp());
                    intent.putExtra("email", editTextMobileNumber.getText().toString());
                    intent.putExtra("password",editTextSPassword.getText().toString());
                    startActivity(intent);
                    progress_circular.setVisibility(View.GONE);
                } else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                    Toast.makeText(SignUpActivity.this, response.body().getAsJsonObject().get("message").toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Log.d(TAG, strr);
//                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = LocaleHelper.onAttach(newBase);
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}