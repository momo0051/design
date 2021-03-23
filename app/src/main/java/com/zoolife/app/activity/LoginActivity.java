package com.zoolife.app.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.SignInResponse.SignInResponseModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;
import com.zoolife.app.utility.LocaleHelper;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppBaseActivity {

    RelativeLayout loginButton;
    TextView signupTextview;
    ToggleButton ivPLogin;
    EditText editTextUserName, editTextPassword;
    Typeface typeface;
    String TAG = "LoginActivityyyyy";
    ProgressBar progress_circular;
    private ImageView ivCross;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //forceRTLIfSupported();
        setContentView(R.layout.activity_login);

        loginButton = (RelativeLayout) findViewById(R.id.loginButton);
        signupTextview = (TextView) findViewById(R.id.signupTextview);
        ivPLogin =  findViewById(R.id.ivPLogin);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);
        ivCross = (ImageView) findViewById(R.id.iv_cross);

        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signupTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.fogotPasswrdTextview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        ivPLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {

                    editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    editTextPassword.setTypeface(typeface);
                    editTextPassword.setSelection(editTextPassword.getText().length());
                    ivPLogin.setBackgroundResource(R.drawable.eyehide);

                } else {
                    editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    editTextPassword.setTypeface(typeface);
                    editTextPassword.setSelection(editTextPassword.getText().length());
                    ivPLogin.setBackgroundResource(R.drawable.eyeshow);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValid()) {
                    //sham12
                    signInUser();
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
        if (editTextUserName.getText().toString().equals("")) {
            editTextUserName.setError("أدخل اسم مستخدم صالح");
            return false;
        } else if (editTextPassword.getText().toString().equals("")) {
            editTextPassword.setError("أدخل كلمة المرور");
            return false;
        }

        return true;


    }

    public void signInUser() {

        progress_circular.setVisibility(View.VISIBLE);
        String username = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<JsonObject> call = apiService.signIn(username, password);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {


                if (response.isSuccessful() && response.body().getAsJsonObject().get("data").isJsonObject()
                        && !response.body().getAsJsonObject().get("error").getAsBoolean()) {

                    Gson gson = new Gson();
                    SignInResponseModel responseModel = gson.fromJson(response.body().getAsJsonObject().toString(), SignInResponseModel.class);

//                    SignInResponseModel responseModel =   new Gson().toJson(response.body());
//                    SignInResponseModel responseModel = response.body().getAsJsonObject();

                    Log.d(TAG, response.toString());
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


                    Log.i("asjhdfvwgv","hey:"+responseModel.getData().getId()+":"+session.getUserId());






                    finish();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);


                    session.setIsLogin(true);
                    // infoDialog("تهانينا ! لقد قمت بتسجيل الدخول بنجاح");
                    progress_circular.setVisibility(View.GONE);

                } else {
                    Log.d(TAG, "Server Error.");
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


    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = LocaleHelper.onAttach(newBase);
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
}