package com.zoolife.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.UpdateDeviceInfo.UpdateDeviceInfoResponse;
import com.zoolife.app.fragments.HomeFragment;
import com.zoolife.app.fragments.MessageFragment;
import com.zoolife.app.fragments.NotificationFragment;
import com.zoolife.app.fragments.ViewMoreFragment;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;
import com.zoolife.app.utility.GPSTracker;
import com.zoolife.app.utility.LocaleHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppBaseActivity {

    ImageView icon_home, icon_favourite, icon_notif, icon_message, ivViewMore;
    TextView text_home, text_favourite, text_notif, toolbar_title, text_message, tvUsername, tvViewMore;
    LinearLayout llHome, llSearch, llNotification, llMessage, llSetting;
    NavigationView navigation_view, navigation_view_left;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    String MYPREF = "MyPref";
    SharedPreferences.Editor editor;
    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 599;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 991;
    LocationListener locationListener;
    LocationManager lm;
    String latitude = "", longitude = "";
    String TAG = "MainActivityyyy";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        icon_home = findViewById(R.id.icon_home);
        icon_favourite = findViewById(R.id.icon_favourite);
        icon_notif = findViewById(R.id.icon_notif);
        icon_message = findViewById(R.id.icon_message);
        text_home = findViewById(R.id.text_home);
        text_favourite = findViewById(R.id.text_favourite);
        text_notif = findViewById(R.id.text_notif);
        text_message = findViewById(R.id.text_message);
        toolbar_title = findViewById(R.id.toolbar_title);
        drawerLayout = findViewById(R.id.drawer_layout);
        // navigation_view_left = findViewById(R.id.navigation_view_left);
        //navigation_view = findViewById(R.id.navigation_view);
        tvViewMore = findViewById(R.id.tvViewMore);
        ivViewMore = findViewById(R.id.ivViewMore);

        llHome = findViewById(R.id.home_btn);
        llMessage = findViewById(R.id.message_btn);
        llNotification = findViewById(R.id.notification_btn);
        llSearch = findViewById(R.id.favourite_btn);
        llSetting = findViewById(R.id.viewMoreBtn);

        setLightStatusBar();

        readLocation();
        getToken();
        setFragment(new HomeFragment());

        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragment(new HomeFragment());
                setLightStatusBar();
                findViewById(R.id.toolbar_2).setVisibility(View.GONE);
                changeColor(llHome, icon_home, text_home);
                resetColor(llSearch, icon_favourite, text_favourite);
                resetColor(llNotification, icon_notif, text_notif);
                resetColor(llMessage, icon_message, text_message);
            }
        });

//        findViewById(R.id.searchAdBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                startActivity(intent);
//
//                /*if(!drawerLayout.isDrawerOpen(Gravity.LEFT)){
//                    drawerLayout.openDrawer(Gravity.LEFT);
//                    findViewById(R.id.menu_back).setVisibility(View.VISIBLE);
//                    findViewById(R.id.addAdBtn).setVisibility(View.INVISIBLE);
//                }
//                else{
//                    drawerLayout.closeDrawer(Gravity.LEFT);
//                    findViewById(R.id.searchAdBtn).setVisibility(View.VISIBLE);
//                    findViewById(R.id.addAdBtn).setVisibility(View.VISIBLE);
//                    findViewById(R.id.menu_back).setVisibility(View.INVISIBLE);
//                }*/
//            }
//        });

//        findViewById(R.id.menu_back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!drawerLayout.isDrawerOpen(Gravity.RIGHT)){
//                    drawerLayout.openDrawer(Gravity.RIGHT);
//                    findViewById(R.id.addAdBtn).setVisibility(View.INVISIBLE);
//                    findViewById(R.id.menu_back).setVisibility(View.VISIBLE);
//                }else{
//                    drawerLayout.closeDrawer(Gravity.RIGHT);
//                    findViewById(R.id.addAdBtn).setVisibility(View.VISIBLE);
//                    findViewById(R.id.menu_back).setVisibility(View.INVISIBLE);
//                }
//            }
//        });

        findViewById(R.id.viewMoreBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar_title.setText(getResources().getString(R.string.more));
                setLightStatusBar();
                findViewById(R.id.toolbar_2).setVisibility(View.GONE);
                setFragment(new ViewMoreFragment());
                changeColor(llSetting, ivViewMore, tvViewMore);
                resetColor(llSearch, icon_favourite, text_favourite);
                resetColor(llNotification, icon_notif, text_notif);
                resetColor(llMessage, icon_message, text_message);
                resetColor(llHome, icon_home, text_home);
            }
        });

        findViewById(R.id.home_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setFragment(new HomeFragment());
                findViewById(R.id.toolbar_2).setVisibility(View.GONE);
                setLightStatusBar();
                changeColor(llHome, icon_home, text_home);
                resetColor(llSearch, icon_favourite, text_favourite);
                resetColor(llNotification, icon_notif, text_notif);
                resetColor(llMessage, icon_message, text_message);
                resetColor(llSetting, ivViewMore, tvViewMore);
            }
        });

        findViewById(R.id.favourite_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLightStatusBar();
//                toolbar_title.setText(getResources().getString(R.string.favourites));
//                findViewById(R.id.toolbar_2).setVisibility(View.VISIBLE);
//                setFragment(new FavouriteFragment());
//                resetColor(icon_home,text_home);
//                changeColor(icon_favourite,text_favourite);
//                resetColor(icon_notif,text_notif);
//                resetColor(icon_message,text_message);
//                resetColor(ivViewMore,tvViewMore);
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });

        findViewById(R.id.notification_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if(session.isLogin()) {
                toolbar_title.setText(getResources().getString(R.string.notification));
                setDarkStatusBar();
                findViewById(R.id.toolbar_2).setVisibility(View.VISIBLE);
                setFragment(new NotificationFragment());
                resetColor(llHome, icon_home, text_home);
                resetColor(llSearch, icon_favourite, text_favourite);
                changeColor(llNotification, icon_notif, text_notif);
                resetColor(llMessage, icon_message, text_message);
                resetColor(llSetting, ivViewMore, tvViewMore);
            }
//                else
//                {
//                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//                }
//            }
        });

        findViewById(R.id.message_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (session.isLogin()) {

                    Log.i("asdhbverbv","saiufgbvwreb");
                    toolbar_title.setText(getResources().getString(R.string.message));
                    setDarkStatusBar();
                    findViewById(R.id.toolbar_2).setVisibility(View.VISIBLE);
                    setFragment(new MessageFragment(session));
                    resetColor(llHome, icon_home, text_home);
                    resetColor(llSearch, icon_favourite, text_favourite);
                    resetColor(llNotification, icon_notif, text_notif);
                    changeColor(llMessage, icon_message, text_message);
                    resetColor(llSetting, ivViewMore, tvViewMore);
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });


       /* searchLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });*/


    }


    public void changeColor(LinearLayout linearLayout, ImageView imageView, TextView textView) {
        linearLayout.setSelected(true);
        imageView.setColorFilter(getResources().getColor(R.color.app_bg));
        textView.setTextColor(getResources().getColor(R.color.app_bg));
    }

    public void resetColor(LinearLayout linearLayout, ImageView imageView, TextView textView) {
        linearLayout.setSelected(false);
        imageView.setColorFilter(getResources().getColor(R.color.light_steel_blue));
        textView.setTextColor(getResources().getColor(R.color.light_steel_blue));
    }

    protected void setFragment(Fragment fragment) {
        findViewById(R.id.frame_container).clearFocus();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }

    private void enableLoc() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(@Nullable Bundle bundle) {

                        }

                        @Override
                        public void onConnectionSuspended(int i) {

                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult connectionResult) {

                            Log.d(TAG, connectionResult.getErrorCode() + "");

                        }
                    }).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            builder.setAlwaysShow(true);

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                status.startResolutionForResult((Activity) MainActivity.this, REQUEST_LOCATION);
                            } catch (IntentSender.SendIntentException e) {

                            }
                            break;
                    }
                }
            });
        }

    }

    public void readLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        } else {

            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    if (location != null) {
                        longitude = String.valueOf(location.getLongitude());
                        latitude = String.valueOf(location.getLatitude());
                        sharedPreferences.edit().putString("latitude", latitude + "").apply();
                        sharedPreferences.edit().putString("longitude", longitude + "").apply();

                        Log.d(TAG, "location :" + longitude + "," + latitude);
                        getAddresss(location.getLatitude(), location.getLongitude());

                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {
                    GPSTracker tracker = new GPSTracker(MainActivity.this);
                    Location location = tracker.getLocation();

                    if (location != null) {
                        longitude = String.valueOf(location.getLongitude());
                        latitude = String.valueOf(location.getLatitude());

                        sharedPreferences.edit().putString("latitude", latitude + "").apply();
                        sharedPreferences.edit().putString("longitude", longitude + "").apply();

                    } else {
                        Log.d(TAG, "null location");
                    }


                }

                @Override
                public void onProviderDisabled(String provider) {
                    enableLoc();
                }
            };
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (lm != null) {
                if (lm.getAllProviders().contains(LocationManager.GPS_PROVIDER) && lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, locationListener);
                } else {
                    enableLoc();
                }
            }
            if (lm != null) {
                if (lm.getAllProviders().contains(LocationManager.NETWORK_PROVIDER) && lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 10, locationListener);
                }
            }

        }

    }

    public void getAddresss(Double latitude, Double longitude) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1);

            String address = addresses.get(0).getAddressLine(0) != null ? addresses.get(0).getAddressLine(0) : "";
            String city = addresses.get(0).getLocality() != null ? addresses.get(0).getLocality() : "";
            String state = addresses.get(0).getAdminArea() != null ? addresses.get(0).getAdminArea() : "";
            String country = addresses.get(0).getCountryName() != null ? addresses.get(0).getCountryName() : "";
            String postalCode = addresses.get(0).getPostalCode() != null ? addresses.get(0).getPostalCode() : "";
            String knownName = addresses.get(0).getFeatureName() != null ? addresses.get(0).getFeatureName() : "";
            sharedPreferences.edit().putString("countryNameFromPhone", country).apply();
            //  updateLocation(sharedPreferences.getString("countryNameFromPhone",""));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void requestPermission() {
        checkPermissions();
    }

    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        } else {
            readLocation();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_LOCATION:
                switch (resultCode) {
                    case Activity.RESULT_CANCELED: {
                        // finish();
                        // break;
                    }
                    case Activity.RESULT_OK: {
                        readLocation();

                    }

                    default: {
                        readLocation();
                    }
                }
                break;
        }
    }

    public void getToken() {

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.d(TAG, token);
                        if (session.isLogin()) {
                            updateDeviceInfo(session.getUserId(), token);
                        }


                    }
                });

    }

    private void updateDeviceInfo(String userId, String deviceToken) {

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<UpdateDeviceInfoResponse> call = apiService.updateUserDeviceToken(userId, deviceToken, "android");
        call.enqueue(new Callback<UpdateDeviceInfoResponse>() {
            @Override
            public void onResponse(Call<UpdateDeviceInfoResponse> call, Response<UpdateDeviceInfoResponse> response) {
                UpdateDeviceInfoResponse updateDeviceInfoResponse = response.body();
                if (updateDeviceInfoResponse != null && !updateDeviceInfoResponse.isError()) {
                    Log.d("Response", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<UpdateDeviceInfoResponse> call, Throwable t) {
                t.printStackTrace();
                Log.d("Failure", t.getMessage().toString());
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        readLocation();
        getToken();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = LocaleHelper.onAttach(newBase);
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}