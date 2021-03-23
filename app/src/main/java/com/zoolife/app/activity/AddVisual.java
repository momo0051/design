//package com.leadtrance.zoolife.activity;
//
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.viewpager.widget.ViewPager;
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.app.DatePickerDialog;
//import android.app.TimePickerDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.Point;
//import android.graphics.PorterDuff;
//import android.graphics.drawable.BitmapDrawable;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.text.Editable;
//import android.text.SpannableString;
//import android.text.TextWatcher;
//import android.text.style.AbsoluteSizeSpan;
//import android.util.Log;
//import android.view.Display;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.ScrollView;
//import android.widget.TextView;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.tabs.TabLayout;
//import com.google.firebase.Timestamp;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//import com.google.firebase.firestore.Transaction;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;
//import com.zomato.photofilters.imageprocessors.Filter;
//import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
//import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;
//import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubfilter;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//
//
//public class AddVisual extends AppCompatActivity implements EditImageFragmentListener, FiltersListFragment.FiltersListFragmentListener {
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult ( requestCode, permissions, grantResults );
//
//
//        if (requestCode == 0) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getPermissionOpenGallery ();
//            }
//        }
//        if (requestCode == 1) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getPermissionOpenGallery ();
//            } else {
//                openGallery ();
//            }
//
//        }
//    }
//
//
//    TextView date;
//    TextView time;
//    FusedLocationProviderClient fusedLocationProviderClient;
//    TextView exactLocation;
//    Geocoder geocoder;
//    List<Address> addresses;
//    EditText place;
//    ImageView visual;
//    ImageView visual2;
//    ConstraintLayout parentLayout;
//    ImageView addPhotoIcon;
//    TextView addPhotoText;
//    ScrollView scroll1;
//    ScrollView scroll2;
//    EditText description;
//    EditText hashtags;
//    FirebaseAuth mAuth;
//    FirebaseFirestore database;
//    FirebaseStorage storage;
//    TextView letterCount;
//    public static final String pictureName = "";
//
//    TabLayout tabLayout;
//    NonSwipeableViewPager viewPager;
//
//    Bitmap originalBitmap;
//    Bitmap filteredBitmap;
//    Bitmap finalBitmap;
//
//    FiltersListFragment filtersListFragment;
//    EditImageFragment editImageFragment;
//
//    ImageView whiteActionBackground;
//
//    int brightnessFinal = 0;
//    float saturationFinal = 1.0f;
//    float contrastFinal = 1.0f;
//
//    Button postPhotoButton;
//
//    boolean imageUploaded;
//    Button editPhotoButton;
//    Button setInfoButton;
//
//    Uri selectedImageUri;
//
//    ProgressBar progressBar;
//
//    TextView finalVisualText;
//
//    String myEmail;
//
//    ImageView backButton;
//
//    ImageView videoImage;
//
//    DatePickerDialog datePickerDialog;
//    TimePickerDialog timePickerDialog;
//    Calendar calendar;
//    int day;
//    int month;
//    int year;
//    String dateChosen;
//    String timeChosen;
//    DateFormat dateFormatter;
//    Date dateMoment;
//
//    String locationFromCreateMap;
//
//    String latFromCreateMap;
//    String longFromCreateMap;
//
//    int MIN_DATE = 157770000;
//
//    static {
//        System.loadLibrary ( "NativeImageProcessor" );
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate ( savedInstanceState );
//        customizeAction ();
//        setContentView ( R.layout.activity_add_visual );
//        initiateVariable ();
//        findId ();
//        setNavColor ();
//        getInstance ();
//        setMyEmail ();
//        setAlpha ();
//        setVisualDimensions ();
//        setDateAndTime ();
//        getLocation ();
//        textChange ();
//        startHashtag ();
//        setUpViewPager ( viewPager );
//        tabLayout.setupWithViewPager ( viewPager );
//
//
//    }
//
//
//    public void locationClicked(View view) {
//        Intent intent = new Intent ( AddVisual.this, LocationMap.class );
//        intent.putExtra ( "parentActivity", "Profile" );
//        startActivityForResult ( intent, 3 );
//    }
//
//    public void open12() {
//        calendar = Calendar.getInstance ();
//        final int hour = calendar.get ( Calendar.DAY_OF_MONTH );
//        final int minute = calendar.get ( Calendar.MONTH );
//        timePickerDialog = new TimePickerDialog ( AddVisual.this, new TimePickerDialog.OnTimeSetListener () {
//            @Override
//            public void onTimeSet(TimePicker view, int selectedHourOfDay, int selectedMinute) {
//                String newTime = adjustDurationFormat ( selectedHourOfDay + ":" + selectedMinute );
//                newTime = adjustTimeFormat ( newTime );
//                timeChosen = newTime;
//                time.setText ( newTime );
//            }
//        }, hour, minute, false );
//        timePickerDialog.show ();
//    }
//
//    public void open24() {
//        calendar = Calendar.getInstance ();
//        int hour = calendar.get ( Calendar.DAY_OF_MONTH );
//        int minute = calendar.get ( Calendar.MONTH );
//
//        timePickerDialog = new TimePickerDialog ( AddVisual.this, new TimePickerDialog.OnTimeSetListener () {
//            @Override
//            public void onTimeSet(TimePicker view, int selectedHourOfDay, int selectedMinute) {
//                String newTime = adjustDurationFormat ( selectedHourOfDay + ":" + selectedMinute );
//                time.setText ( newTime );
//                timeChosen = adjustTimeFormat ( newTime );
//                ;
//
//            }
//        }, hour, minute, true );
//
//
//        timePickerDialog.show ();
//    }
//
//
//    public void timeClicked(View view) {
//        get12or24 ();
//    }
//
//    public void dateClicked(View view) {
//        calendar = Calendar.getInstance ();
//        day = calendar.get ( Calendar.DAY_OF_MONTH );
//        month = calendar.get ( Calendar.MONTH );
//        year = calendar.get ( Calendar.YEAR );
//
//
//        datePickerDialog = new DatePickerDialog ( AddVisual.this, new DatePickerDialog.OnDateSetListener () {
//            @Override
//            public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
//                dateChosen = ((mMonth + 1) + "." + mDay + "." + mYear);
//                try {
//                    SimpleDateFormat dateFormatThis = new SimpleDateFormat ( "MM.dd.yyyy" );
//                    Date dateThis = dateFormatThis.parse ( dateChosen );
//                    if (dateThis != null) {
//                        date.setText ( SimpleDateFormat.getDateInstance ( DateFormat.LONG ).format ( dateThis ) );
//                    }
//
//                } catch (ParseException e) {
//                    e.printStackTrace ();
//
//                }
//
//            }
//        }, day, month, year );
//
//        datePickerDialog.getDatePicker ().setMinDate ( MIN_DATE );
//        datePickerDialog.getDatePicker ().setMaxDate ( System.currentTimeMillis () );
//        datePickerDialog.getDatePicker ().getTouchables ().get ( 0 ).performClick ();
//        datePickerDialog.show ();
//    }
//
//    public void get12or24() {
//        SimpleDateFormat sdf = new SimpleDateFormat ( "HH:mm" );
//        Timestamp timestamp1 = Timestamp.now ();
//        Date dateChosen = timestamp1.toDate ();
//        String time = sdf.getTimeInstance ().format ( dateChosen );
//        if (time.contains ( "M" )) {
//            open12 ();
//        } else {
//            open24 ();
//        }
//    }
//
//    public void disableTouch() {
//        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE );
//
//    }
//
//    public void enableTouch() {
//        getWindow ().clearFlags ( WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE );
//    }
//
//
//    public void getPermissionOpenGallery() {
//        if (ContextCompat.checkSelfPermission ( AddVisual.this, Manifest.permission.READ_EXTERNAL_STORAGE )
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions ( AddVisual.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0 );
//
//        } else if (ContextCompat.checkSelfPermission ( AddVisual.this, Manifest.permission.CAMERA )
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions ( AddVisual.this, new String[]{Manifest.permission.CAMERA}, 1 );
//
//        } else {
//            openGallery ();
//
//        }
//    }
//
//
//    public void setMyEmail() {
//        if (mAuth.getCurrentUser () != null && mAuth.getCurrentUser ().getEmail () != null) {
//            myEmail = mAuth.getCurrentUser ().getEmail ();
//        } else {
//            myEmail = "";
//            takeErrorAction ();
//        }
//
//    }
//
//    public void setWarnings(String string) {
//        SpannableString text = new SpannableString ( string );
//        text.setSpan ( new AbsoluteSizeSpan ( 40 ), 0, text.length (), 0 );
//        new AlertDialog.Builder ( AddVisual.this )
//                .setMessage ( text )
//                .setCancelable ( false )
//                .setPositiveButton ( getResources ().getString ( R.string.cancel ), new DialogInterface.OnClickListener () {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        dialog.dismiss ();
//                    }
//                } ).create ().show ();
//    }
//
//    public void setInformationClicked(View view) {
//        setInformationVisible ();
//    }
//
//
//    public void editPictureClicked(View view) {
//        if (imageUploaded) {
//            setFilterVisible ();
//        } else {
//            setWarnings ( getResources ().getString ( R.string.upload_photo_first ) );
//        }
//    }
//
//    public void initiateVariable() {
//        imageUploaded = false;
//        dateFormatter = new SimpleDateFormat ( "MM.dd.yyyy hh:mm a" );
//    }
//
//    public void takeErrorAction() {
//        Toast toast = Toast.makeText ( AddVisual.this, R.string.verify_internet, Toast.LENGTH_SHORT );
//        View view = toast.getView ();
//        if (view != null) {
//            view.getBackground ().setColorFilter ( getResources ().getColor ( R.color.formalColor ), PorterDuff.Mode.SRC_IN );
//            TextView text1 = view.findViewById ( android.R.id.message );
//            text1.setTextColor ( getResources ().getColor ( R.color.colorPrimary ) );
//
//        }
//        toast.show ();
//        finish ();
//    }
//
//
//    public void setInformationVisible() {
//        scroll1.setVisibility ( View.VISIBLE );
//        scroll2.setVisibility ( View.INVISIBLE );
//        tabLayout.setVisibility ( View.INVISIBLE );
//    }
//
//    public void setFilterVisible() {
//        scroll1.setVisibility ( View.INVISIBLE );
//        scroll2.setVisibility ( View.VISIBLE );
//        tabLayout.setVisibility ( View.VISIBLE );
//    }
//
//
//    public void getInstance() {
//        mAuth = FirebaseAuth.getInstance ();
//        database = FirebaseFirestore.getInstance ();
//        storage = FirebaseStorage.getInstance ();
//    }
//
//
//    public void videoImageClicked(View view) {
//        finish ();
//        Intent intent = new Intent ( AddVisual.this, AddVideos.class );
//        startActivity ( intent );
//    }
//
//
//    public void setAlpha() {
//        date.getBackground ().setAlpha ( 50 );
//        time.getBackground ().setAlpha ( 50 );
//        exactLocation.getBackground ().setAlpha ( 50 );
//        place.getBackground ().setAlpha ( 50 );
//        hashtags.getBackground ().setAlpha ( 50 );
//        description.getBackground ().setAlpha ( 50 );
//
//    }
//
//
//    public void findId() {
//        date = findViewById ( R.id.date );
//        time = findViewById ( R.id.time );
//        place = findViewById ( R.id.place );
//        parentLayout = findViewById ( R.id.parentLayout );
//        visual = findViewById ( R.id.visual );
//        visual2 = findViewById ( R.id.visual2 );
//        exactLocation = findViewById ( R.id.exactLocation );
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient ( AddVisual.this );
//        geocoder = new Geocoder ( this, Locale.getDefault () );
//        addPhotoIcon = findViewById ( R.id.addPhotoIcon );
//        addPhotoText = findViewById ( R.id.addPhotoText );
//        scroll1 = findViewById ( R.id.scroll );
//        scroll2 = findViewById ( R.id.scroll2 );
//        description = findViewById ( R.id.description );
//        hashtags = findViewById ( R.id.hashtags );
//        letterCount = findViewById ( R.id.letterCount );
//        viewPager = findViewById ( R.id.viewpager );
//        tabLayout = findViewById ( R.id.tabs );
//        editImageFragment = new EditImageFragment ();
//        filtersListFragment = new FiltersListFragment ();
//        postPhotoButton = findViewById ( R.id.postPhotoButton );
//        editPhotoButton = findViewById ( R.id.editPictureButton );
//        setInfoButton = findViewById ( R.id.setInformationButton );
//        progressBar = findViewById ( R.id.progressBar );
//        finalVisualText = findViewById ( R.id.finalVisualText );
//        backButton = findViewById ( R.id.backButton );
//        videoImage = findViewById ( R.id.videoImage );
//        whiteActionBackground = findViewById ( R.id.whiteActionBackground );
//
//
//    }
//
//    public void setDateAndTime() {
//        Timestamp timestamp = Timestamp.now ();
//        SimpleDateFormat sdf = new SimpleDateFormat ( "HH:mm" );
//        Date dateChosen = timestamp.toDate ();
//        date.setText ( SimpleDateFormat.getDateInstance ( DateFormat.LONG ).format ( dateChosen ) );
//        time.setText ( addZero ( removeSeconds ( sdf.getTimeInstance ().format ( dateChosen ) ) ) );
//    }
//
//
//    public String addZero(String timeNoZero) {
//        if (timeNoZero.charAt ( 1 ) == ':') {
//            return "0" + timeNoZero;
//        } else {
//            return timeNoZero;
//        }
//    }
//
//    public String removeSeconds(String timeSec) {
//        String firstPart = "";
//        String secondPart = "";
//        for (int i = timeSec.length () - 1; i >= 0; i--) {
//            char letter = timeSec.charAt ( i );
//            if (Character.isDigit ( letter )) {
//                if (i == timeSec.length () - 1) {
//                    firstPart = timeSec.substring ( 0, i - 2 );
//                    return firstPart;
//                } else {
//                    firstPart = timeSec.substring ( 0, i - 2 );
//                    secondPart = timeSec.substring ( i + 1 );
//                    return firstPart + secondPart;
//                }
//
//            }
//        }
//        return timeSec;
//    }
//
//    public void getLocation() {
//        if (ActivityCompat.checkSelfPermission ( this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
//            fusedLocationProviderClient.getLastLocation ()
//                    .addOnSuccessListener ( new OnSuccessListener<Location> () {
//                        @Override
//                        public void onSuccess(Location location) {
//                            if (location != null) {
//                                try {
//                                    addresses = geocoder.getFromLocation ( location.getLatitude (), location.getLongitude (), 1 );
//                                    String address = addresses.get ( 0 ).getAddressLine ( 0 );
//                                    exactLocation.setText ( address );
//
//                                } catch (IOException e) {
//                                    e.printStackTrace ();
//                                }
//                            }
//
//                        }
//                    } ); }
//    }
//
//
//
//
//    public void visualClicked(View view) {
//        getPermissionOpenGallery ();
//    }
//
//
//    public void setNavColor(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            getWindow().setNavigationBarColor(getColor ( R.color.formal_beige ));
//            getWindow().setStatusBarColor( getColor ( R.color.snow_white ));
//
//        }
//        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            getWindow().setNavigationBarColor( getResources ().getColor ( R.color.formal_beige ));
//
//        }
//    }
//
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult ( requestCode, resultCode, data );
//
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && data != null) {
//            CropImage.ActivityResult result = CropImage.getActivityResult ( data );
//            if (resultCode == RESULT_OK) {
//                selectedImageUri = result.getUri ();
//
//                try {
//
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap ( this.getContentResolver (), selectedImageUri );
//                    visual.setImageBitmap ( bitmap );
//                    visual2.setImageBitmap ( bitmap );
//                    addPhotoIcon.setVisibility ( View.INVISIBLE );
//                    addPhotoText.setVisibility ( View.INVISIBLE );
//
//                    originalBitmap = bitmap;
//                    filteredBitmap = originalBitmap.copy ( Bitmap.Config.ARGB_8888, true );
//                    finalBitmap = originalBitmap.copy ( Bitmap.Config.ARGB_8888, true );
//
//                    filtersListFragment.prepareThumbnail ( originalBitmap );
//                    imageUploaded = true;
//
//
//                } catch (IOException e) {
//                    e.printStackTrace ();
//                }
//            }
//
//            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Exception error = result.getError ();
//                if(error.getMessage () != null) {
//                    Log.d ( "error", error.getMessage () );
//                }
//            }
//        }
//
//        else if (requestCode == 3 && resultCode == RESULT_OK && data != null) {
//            locationFromCreateMap = data.getStringExtra ( "locationFromCreateMap" );
//            latFromCreateMap = data.getStringExtra ( "latFromCreateMap" );
//            longFromCreateMap = data.getStringExtra ( "longFromCreateMap" );
//            if(locationFromCreateMap != null && locationFromCreateMap.length () != 0 && latFromCreateMap != null && latFromCreateMap.length () != 0) {
//                exactLocation.setText ( locationFromCreateMap );
//            }
//        }
//    }
//
//
//
//
//    public void openGallery() {
//        CropImage.activity()
//                .setGuidelines( CropImageView.Guidelines.ON)
//                .setActivityMenuIconColor ( getResources ().getColor ( R.color.formalColor ) )
//                .setBorderCornerColor ( getResources ().getColor ( R.color.formalColor ) )
//                .setAllowRotation ( true )
//                .setAllowFlipping ( true )
//                .start(this);
//    }
//
//
//
//    public void startHashtag() {
//        hashtags.addTextChangedListener ( new TextWatcher () {
//            int c ;
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                c = count;
//
//
//            }
//
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(!s.toString ().startsWith ( "#" ) && c!= 0 ){
//                    s.insert ( 0,"#" );
//                }
//
//
//
//
//            }
//        } );
//    }
//
//
//    public void updateFeed(ArrayList followers, boolean profilePresentMe, String fullNameMe, String profilePictureNumberMe, String visualNumberMe, Timestamp timestampMe){
//
//        database.collection ( "Feed" ).get ()
//                .addOnCompleteListener ( new OnCompleteListener<QuerySnapshot> () {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful () && task.getResult () != null) {
//                            for (QueryDocumentSnapshot document2 : task.getResult ()) {
//                                String email = document2.getString ( "email" );
//                                if (document2.exists () && email != null) {
//                                    if (followers.contains ( email )) {
//
//                                        ArrayList feedList = (ArrayList) document2.get ( "feedList" );
//                                        HashMap<String,Object> feedMap = new HashMap <String,Object>();
//
//                                        feedMap.put ( "class", "photo" );
//                                        feedMap.put ( "email", myEmail );
//                                        feedMap.put ( "visualNumber", visualNumberMe );
//                                        feedMap.put ( "location", exactLocation.getText ().toString () );
//                                        feedMap.put ( "place", place.getText ().toString () );
//                                        feedMap.put ( "visualDate", timestampMe );
//                                        feedMap.put ( "explanation", description.getText ().toString () );
//                                        feedMap.put ( "hashtags", hashtags.getText ().toString () );
//                                        feedMap.put ( "profilePictureNumber", profilePictureNumberMe );
//                                        feedMap.put ( "profilePresent", profilePresentMe );
//                                        feedMap.put ( "fullName", fullNameMe );
//
//                                        feedList.add ( 0, feedMap );
//
//                                        database.collection ( "Feed" ).document ( email )
//                                                .update ( "feedList", feedList );
//
//
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } );
//    }
//
//
//
//
//
//    private final TextWatcher mTextEditorWatcher = new TextWatcher () {
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        }
//
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            letterCount.setText ( String.valueOf ( s.length () + "/240" ) );
//        }
//
//        public void afterTextChanged(Editable s) {
//        }
//    };
//
//    public void textChange() {
//        description.addTextChangedListener ( mTextEditorWatcher );
//    }
//
//
//
//
//    public ArrayList<String> getHashtags(String hashtag){
//
//        ArrayList<String> hashtagList = new ArrayList<String> ();
//        ArrayList<Integer> hashtagPositions = new ArrayList<Integer> ();
//        if(hashtag.length () == 0 || hashtag.length () == 1){
//            return hashtagList;
//        }
//
//        else {
//
//            hashtag = hashtag.replaceAll(" ", "");
//
//
//            for(int i = 0; i < hashtag.length (); i ++){
//                char letter = hashtag.charAt ( i );
//                if(letter == '#'){
//                    hashtagPositions.add ( i );
//                }
//            }
//
//            for(int i = 0; i < hashtagPositions.size (); i ++){
//                if(i == hashtagPositions.size () - 1){
//                    String singleHashtag = hashtag.substring ( hashtagPositions.get ( i ) );
//                    hashtagList.add ( singleHashtag );
//                }
//                else {
//                    String singleHashtag = hashtag.substring ( hashtagPositions.get ( i ), hashtagPositions.get ( i + 1 ) );
//                    hashtagList.add ( singleHashtag );
//                }
//
//            }
//
//            return hashtagList;
//        }
//
//
//    }
//
//
//
//
//    public void setUpViewPager(ViewPager viewPager){
//        ViewPagerAdapter adapter = new ViewPagerAdapter ( getSupportFragmentManager (), 0 );
//
//        filtersListFragment = new FiltersListFragment ();
//        filtersListFragment.setListener ( this );
//
//        editImageFragment = new EditImageFragment ();
//        editImageFragment.setListener ( this );
//
//        adapter.addFragment ( filtersListFragment, "FILTERS" );
//        adapter.addFragment ( editImageFragment, "EDIT" );
//
//        viewPager.setAdapter ( adapter );
//    }
//
//
//    @Override
//    public void onBrightnessChanged(int brightness) {
//
//        brightnessFinal = brightness;
//        Filter myFilter = new Filter ();
//        myFilter.addSubFilter ( new BrightnessSubFilter ( brightness ) );
//        visual.setImageBitmap ( myFilter.processFilter ( finalBitmap.copy ( Bitmap.Config.ARGB_8888, true ) ) );
//        visual2.setImageBitmap ( myFilter.processFilter ( finalBitmap.copy ( Bitmap.Config.ARGB_8888, true ) ) );
//    }
//
//    @Override
//    public void onSaturationChanged(float saturation) {
//        saturationFinal = saturation;
//        Filter myFilter = new Filter ();
//        myFilter.addSubFilter ( new SaturationSubfilter ( saturation ) );
//        visual.setImageBitmap ( myFilter.processFilter ( finalBitmap.copy ( Bitmap.Config.ARGB_8888, true ) ) );
//        visual2.setImageBitmap ( myFilter.processFilter ( finalBitmap.copy ( Bitmap.Config.ARGB_8888, true ) ) );
//
//    }
//
//    @Override
//    public void onContrastChanged(float contrast) {
//        contrastFinal = contrast;
//        Filter myFilter = new Filter ();
//        myFilter.addSubFilter ( new ContrastSubFilter ( contrast ) );
//        visual.setImageBitmap ( myFilter.processFilter ( finalBitmap.copy ( Bitmap.Config.ARGB_8888, true ) ) );
//        visual2.setImageBitmap ( myFilter.processFilter ( finalBitmap.copy ( Bitmap.Config.ARGB_8888, true ) ) );
//
//
//    }
//
//    @Override
//    public void onEditStarted() {
//
//    }
//
//    @Override
//    public void onEditCompleted() {
//
//        Bitmap bitmap = filteredBitmap.copy ( Bitmap.Config.ARGB_8888, true );
//
//        Filter myFilter = new Filter ();
//        myFilter.addSubFilter ( new BrightnessSubFilter ( brightnessFinal ) );
//        myFilter.addSubFilter ( new SaturationSubfilter ( saturationFinal ) );
//        myFilter.addSubFilter ( new ContrastSubFilter ( contrastFinal ) );
//
//        finalBitmap = myFilter.processFilter ( bitmap );
//    }
//
//    @Override
//    public void onFilterSelected(Filter filter) {
//        resetControl();
//        filteredBitmap = originalBitmap.copy ( Bitmap.Config.ARGB_8888, true );
//        visual.setImageBitmap ( filter.processFilter ( filteredBitmap ) );
//        visual2.setImageBitmap ( filter.processFilter ( filteredBitmap ) );
//        finalBitmap = filteredBitmap.copy ( Bitmap.Config.ARGB_8888, true );
//    }
//
//
//
//    private void resetControl() {
//        if(editImageFragment != null){
//            editImageFragment.resetControls ();
//        }
//        brightnessFinal = 0;
//        saturationFinal = 1.0f;
//        contrastFinal = 1.0f;
//    }
//
//
//
//    public void uploadFinal(ArrayList followers, boolean profilePresentMe, String fullNameMe, String profilePictureNumberMe, String visualNumberMe, Timestamp timestamp){
//        loadPhoto ( followers, profilePresentMe, fullNameMe, profilePictureNumberMe, visualNumberMe, timestamp );
//
//    }
//
//
//    public void loadPhoto(ArrayList followers, boolean profilePresentMe, String fullNameMe, String profilePictureNumberMe, String visualNumberMe, Timestamp timestamp){
//
//        StorageReference storageReference = storage.getReference ().child ( "Visuals/" + myEmail + "-" + visualNumberMe );
//        visual.setDrawingCacheEnabled ( true );
//        visual.buildDrawingCache ();
//        Bitmap bitmap = ((BitmapDrawable) visual.getDrawable ()).getBitmap ();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream ();
//        bitmap.compress ( Bitmap.CompressFormat.JPEG, 100, baos );
//        byte[] data = baos.toByteArray ();
//
//        UploadTask uploadTask = storageReference.putBytes ( data );
//        uploadTask.addOnFailureListener ( new OnFailureListener () {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                enableTouch ();
//                progressBar.setVisibility ( View.INVISIBLE );
//                showError ();
//                database.collection ( "Visuals" ).document ( myEmail + "-" + visualNumberMe ).delete ();
//
//            }
//        } ).addOnSuccessListener ( new OnSuccessListener<UploadTask.TaskSnapshot> () {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                finish ();
//                updateFeed ( followers, profilePresentMe, fullNameMe, profilePictureNumberMe, visualNumberMe, timestamp );
//
//            }
//        } );
//    }
//
//    public Timestamp getVisualDate(){
//        try {
//            dateMoment = dateFormatter.parse ( dateChosen + " " + timeChosen );
//            return new Timestamp ( dateMoment );
//        } catch (ParseException e){
//            e.printStackTrace ();
//            return Timestamp.now ();
//        }
//    }
//
//
//    public void uploadUserVisualToDatabase(ArrayList followers, boolean profilePresentMe, String fullNameMe, String profilePictureNumberMe, String finalVisual){
//        Timestamp timestamp = getVisualDate ();
//
//        DocumentReference userReference = database.collection ( "Users" ).document ( myEmail );
//        DocumentReference visualReference = database.collection ( "Visuals" ).document ( myEmail + "-" + finalVisual );
//
//        database.runTransaction ( new Transaction.Function<Void> () {
//
//            @Nullable
//            @Override
//            public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
//
//                String type = "photo";
//
//                String Description = "";
//                if (description.length () != 0) {
//                    Description = description.getText ().toString ();
//                }
//
//                ArrayList comments = new ArrayList ();
//
//
//                String Location = "";
//                if (exactLocation.length () != 0) {
//                    Location = exactLocation.getText ().toString ();
//                }
//
//                String status = "exist";
//                HashMap visualRatings = new HashMap ();
//
//                String Place = "";
//                if (place.length () != 0) {
//                    Place = place.getText ().toString ();
//                }
//
//                ArrayList hashtagList = getHashtags ( hashtags.getText ().toString () );
//
//                for(int i = 0; i < hashtagList.size (); i++){
//                    String hash = (String) hashtagList.get ( i );
//                    if(hash.length () == 1){
//                        hashtagList.remove ( i );
//                        i = i - 1;
//                    }
//                }
//
//                HashMap<String,Object> visualMap = new HashMap <String,Object>();
//
//                visualMap.put ( "type", type );
//                visualMap.put ( "description", Description );
//                visualMap.put ( "comments", comments );
//                visualMap.put ( "date", timestamp);
//                visualMap.put ( "location", Location );
//                visualMap.put ( "status", status );
//                visualMap.put ( "email", myEmail );
//                visualMap.put ( "visualNumber", finalVisual );
//                visualMap.put ( "visualRatings", visualRatings );
//                visualMap.put ( "place", Place );
//                visualMap.put ( "hashtags", hashtagList );
//
//                transaction.set ( visualReference, visualMap );
//                transaction.update ( userReference, "currentVisualNumber", finalVisual );
//
//                return null;
//            }
//        } ).addOnCompleteListener ( new OnCompleteListener<Void> () {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                uploadFinal (followers, profilePresentMe, fullNameMe, profilePictureNumberMe, finalVisual, timestamp );
//            }
//        } ).addOnFailureListener ( new OnFailureListener () {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                enableTouch ();
//                progressBar.setVisibility ( View.INVISIBLE );
//                showError ();
//
//            }
//        } );
//
//    }
//
//
//
//
//    public void postPhotoClicked(View view) {
//        if (!imageUploaded) {
//            setWarnings ( getResources ().getString ( R.string.upload_photo ) );
//
//        }
//        else {
//
//            disableTouch ();
//            progressBar.setVisibility ( View.VISIBLE );
//
//
//            try {
//                HashMap userMap = (HashMap) InternalStorage.readObject ( getApplicationContext (), "USER" + myEmail );
//
//                if(userMap == null){
//                    enableTouch ();
//                    progressBar.setVisibility ( View.INVISIBLE );
//                    showError ();
//                    return;
//                }
//
//                String visualNumberMe =  (String) userMap.get ( "currentVisualNumber" );
//                if(visualNumberMe == null){
//                    enableTouch ();
//                    progressBar.setVisibility ( View.INVISIBLE );
//                    showError ();
//                    return;
//                }
//
//                int visualNumber = Integer.parseInt ( visualNumberMe );
//                visualNumber = visualNumber + 1;
//
//                ArrayList followers = new ArrayList ();
//                if(userMap.get ( "followers" ) != null) {
//                    followers = (ArrayList) userMap.get ( "followers" );
//                }
//
//                if(followers == null){
//                    followers = new ArrayList ();
//                }
//
//                boolean profilePresentMe;
//                if(userMap.get ( "profilePresent" ) != null) {
//                    profilePresentMe = (boolean) userMap.get ( "profilePresent" );
//                }
//                else {
//                    profilePresentMe = false;
//                }
//
//                String fullNameMe = (String) userMap.get ( "fullName" );
//
//                if(fullNameMe == null){
//                    fullNameMe = "";
//                }
//
//                String profilePictureNumberMe = (String) userMap.get ( "profilePictureNumber" );
//                if(profilePictureNumberMe == null){
//                    profilePictureNumberMe = "0";
//                }
//
//
//                uploadUserVisualToDatabase (followers, profilePresentMe, fullNameMe, profilePictureNumberMe, String.valueOf ( visualNumber ));
//
//            }
//
//            catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace ();
//                enableTouch ();
//                progressBar.setVisibility ( View.INVISIBLE );
//                showError ();
//            }
//
//
//
//
//        }
//
//    }
//
//
//    public void showError(){
//        Toast toast = Toast.makeText(AddVisual.this, R.string.smt_went_wrong, Toast.LENGTH_SHORT);
//        View view = toast.getView();
//        view.getBackground().setColorFilter(getResources ().getColor ( R.color.formalColor ), PorterDuff.Mode.SRC_IN);
//        TextView text1 = view.findViewById(android.R.id.message);
//        text1.setTextColor(getResources ().getColor ( R.color.colorPrimary ));
//        toast.show();
//    }
//
//
//
//    public void backButtonClicked(View view) {
//        finish ();
//    }
//
//
//
//
//
//
//    public void mapIconClicked(View view) {
//        Intent intent = new Intent ( AddVisual.this, Map.class );
//        startActivity ( intent );
//
//    }
//
//
//    public void searchIconClicked(View view) {
//        Intent intent = new Intent ( AddVisual.this, Explore.class );
//        startActivity ( intent );
//
//    }
//
//
//    public void feedIconClicked(View view) {
//        Intent intent = new Intent ( AddVisual.this, Feed.class );
//        startActivity ( intent );
//
//    }
//
//
//    public void eventsIconClicked(View view) {
//        Intent intent = new Intent ( AddVisual.this, Events.class );
//        startActivity ( intent );
//
//    }
//
//
//    public void profileIconClicked(View view){
//        Intent intent = new Intent ( AddVisual.this, MyProfile.class );
//        startActivity ( intent );
//    }
//
//
//    public void customizeAction() {
//        if (getSupportActionBar () != null) {
//            getSupportActionBar ().hide ();
//        }
//    }
//
//
//
//    public void setVisualDimensions() {
//        Display display = getWindowManager ().getDefaultDisplay ();
//        Point size = new Point ();
//        display.getSize ( size );
//        visual.getLayoutParams ().height = size.x *3/4;
//        visual.getLayoutParams ().width = size.x;
//        visual2.getLayoutParams ().height = size.x *3/4;
//        visual2.getLayoutParams ().width = size.x;
//    }
//
//
//    public String adjustTimeFormat(String time) {
//        String AM_PM;
//        String firstTime = time.substring ( 0, 2 );
//        String secondTime = time.substring ( 3 );
//        int firstTimeNumber = Integer.parseInt ( firstTime );
//        if (firstTime.equals ( "00" )) {
//            firstTime = "12";
//        }
//        if (firstTimeNumber < 12) {
//            AM_PM = "AM";
//        } else {
//            AM_PM = "PM";
//        }
//        if (firstTimeNumber >= 13) {
//            int newFirstTimeNumber = firstTimeNumber - 12;
//            if(newFirstTimeNumber < 10) {
//                firstTime = String.valueOf ( "0" + newFirstTimeNumber );
//            }
//            else {
//                firstTime = String.valueOf ( newFirstTimeNumber );
//            }
//
//        }
//        return firstTime + ":" + secondTime + " " + AM_PM;
//    }
//
//
//
//    public String adjustDurationFormat(String dur) {
//        String firstPart = "";
//        String secondPart = "";
//        if (dur.length () != 0) {
//            for (int i = 0; i < dur.length (); i++) {
//                if (!Character.isDigit ( dur.charAt ( i ) )) {
//                    firstPart = dur.substring ( 0, i );
//                    secondPart = dur.substring ( i + 1 );
//                }
//            }
//
//            if (firstPart.length () != 2) {
//                firstPart = "0" + firstPart;
//            }
//            if (secondPart.length () != 2) {
//                secondPart = "0" + secondPart;
//            }
//        }
//
//        return firstPart + ":" + secondPart;
//    }
//
//
//
//
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
