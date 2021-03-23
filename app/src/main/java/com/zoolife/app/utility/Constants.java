package com.zoolife.app.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.style.ReplacementSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.zoolife.app.R;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Constants {

    Context context;
    public static final String PARAMETER_SEP = "&";
    public static final String PARAMETER_EQUALS = "=";
    public static final String TRANS_URL = "https://secure.ccavenue.com/transaction/initTrans";
    boolean open = false;
    public String[] countries = {"United States", "United Kingdom", "Australia" , "Canada" ,"Afghanistan", "Akrotiri", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Ashmore and Cartier Islands", "Austria", "Azerbaijan", "The Bahamas", "Bahrain", "Bangladesh", "Barbados", "Bassas da India", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi", "Cambodia", "Cameroon", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Clipperton Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Democratic Republic of the Congo", "Republic of the Congo", "Cook Islands", "Coral Sea Islands", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Dhekelia", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Europa Island", "Falkland Islands (Islas Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "French Southern and Antarctic Lands", "Gabon", "The Gambia", "Gaza Strip", "Georgia", "Germany", "Ghana", "Gibraltar", "Glorioso Islands", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard Island and McDonald Islands", "Holy See (Vatican City)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Jan Mayen", "Japan", "Jersey", "Jordan", "Juan de Nova Island", "Kazakhstan", "Kenya", "Kiribati", "North Korea", "South Korea", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Federated States of Micronesia", "Moldova", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Namibia", "Nauru", "Navassa Island", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paracel Islands", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Montenegro", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Spratly Islands", "Sri Lanka", "Sudan", "Suriname", "Svalbard", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Tibet", "Timor-Leste", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tromelin Island", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands", "Wake Island", "Wallis and Futuna", "West Bank", "Western Sahara", "Yemen", "Zambia","Zimbabwe"};
    Calendar calendar = Calendar.getInstance();
    int year =0,month=0,day=0;
    int hh =0,mm=0;
    String returnDate = "";
    String returnTime = "";
    public static boolean isVisible = true;
    public static String NO_NETWORK = "No internet connection!";

    public Constants(Context context) {
        this.context = context;
    }

    public boolean checkEmail(String email){
        Pattern patterns = Patterns.EMAIL_ADDRESS;
        return patterns.matcher(email).matches();
    }

    public boolean checkKeyboardOpenOrNot(final View view){
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                view.getWindowVisibleDisplayFrame(r);
                int screenHeight = view.getRootView().getHeight();

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;

                Log.d("keyborad", "keypadHeight = " + keypadHeight);

                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    // keyboard is opened
                    open = true;
                }
                else {
                    open = false;
                    // keyboard is closed
                }
            }
        });
        return open;
    }
    public void onLogout(Context context){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_logout,null);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        TextView tvtitle = view.findViewById(R.id.tvtitle);
        builder.setView(view);
        tvtitle.setVisibility(View.VISIBLE);
        final androidx.appcompat.app.AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
               // getApp().getAppPreference().clearAll();

                try {
                    //LoginManager.getInstance().logOut();
                }catch (Exception e) {
                    e.printStackTrace();
                }
               /* startActivity(new Intent(BaseActivity.this, SignupLoginActivity.class));
                finishAffinity();*/
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void closeKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String getlongtoago(long createdAt) {
        DateFormat userDateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        DateFormat dateFormatNeeded = new SimpleDateFormat("MM/dd/yyyy HH:MM:SS");
        Date date = null;
        date = new Date(createdAt);
        String crdate1 = dateFormatNeeded.format(date);

        // Date Calculation
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        crdate1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);

        // get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        String currenttime = dateFormat.format(cal.getTime());

        Date CreatedAt = null;
        Date current = null;
        try {
            CreatedAt = dateFormat.parse(crdate1);
            current = dateFormat.parse(currenttime);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Get msec from each, and subtract.
        long diff = current.getTime() - CreatedAt.getTime();
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        String time = null;
        if (diffDays > 0) {
            if (diffDays == 1) {
                time = diffDays + "day ago ";
            } else {
                time = diffDays + "days ago ";
            }
        } else {
            if (diffHours > 0) {
                if (diffHours == 1) {
                    time = diffHours + "hr ago";
                } else {
                    time = diffHours + "hrs ago";
                }
            } else {
                if (diffMinutes > 0) {
                    if (diffMinutes == 1) {
                        time = diffMinutes + "min ago";
                    } else {
                        time = diffMinutes + "mins ago";
                    }
                } else {
                    if (diffSeconds > 0) {
                        time = diffSeconds + "moment ago";
                    }
                }

            }

        }
        return time;
    }

    public static RequestBody toBody(String values){
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),values);
        return body;
    }

    public static MultipartBody.Part prepareFilePart(String _paramName, File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData(_paramName, file.getName(), requestFile);
    }


    public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public Bitmap lessResolution (String filePath, int width, int height) {
        int reqHeight = height;
        int reqWidth = width;
        BitmapFactory.Options options = new BitmapFactory.Options();

        // First decode with inJustDecodeBounds=true to check dimensions
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    public String changeDateForMemeber(String _inputDate){
        String inPattern = "yyyy-MM-dd HH:mm:ss";
        String ouPattern = "yyyy";
        SimpleDateFormat inFormat = new SimpleDateFormat(inPattern, Locale.getDefault());
        SimpleDateFormat ouFormat = new SimpleDateFormat(ouPattern, Locale.getDefault());
        Date date = null;
        String str = "";
        try {
            date = inFormat.parse(_inputDate);
            str = ouFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    public boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }


    public String changeDateFormat(String _currentDateFormat, String _newDateFormat,String _inputDate) {
        String outPutDate = "";
        SimpleDateFormat _cDF = new SimpleDateFormat(_currentDateFormat);
        SimpleDateFormat _nDF = new SimpleDateFormat(_newDateFormat);
        Date date = null;
        try {
            date = _cDF.parse(_inputDate);
            outPutDate = _nDF.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            outPutDate = _inputDate;
        }

        return outPutDate;

    }

    public static class SpaceSpan extends ReplacementSpan {

        @Override
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            float padding = paint.measureText(" ", 0, 1);
            float textSize = paint.measureText(text, start, end);
            return (int) (padding + textSize);
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y,
                         int bottom, Paint paint) {
            canvas.drawText(text.subSequence(start, end) + " ", x, y, paint);
        }
    }

    public String maskCardNumber(String cardNumber, String mask) {

        // format the number
        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                maskedNumber.append(cardNumber.charAt(index));
                index++;
            } else if (c == 'x') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }

        // return the masked number
        return maskedNumber.toString();
    }

    public interface onButtonListener{
        void onYes();
        void onNo();
    }

    public static void Information_Dialog(Context context, String msg,String button_name){
        try {

            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.custom_alert_layout, null);
            TextView tv_Infomsg = (TextView) view.findViewById(R.id.tv_Infomsg);
            TextView btn_ok = (TextView) view.findViewById(R.id.btnok);
            //btn_ok.setText(button_name);
            tv_Infomsg.setText(msg);
            builder.setView(view);
            final androidx.appcompat.app.AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            if (dialog.getWindow()!=null){
                dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
            }
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    isVisible = true;
                }
            });

            if (isVisible) {
                dialog.show();
                isVisible = false;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }


    public static void onOptionDialog(Context mContext, final onButtonListener listener){
        if (mContext!=null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            View view = LayoutInflater.from(mContext).inflate(R.layout.custom_logout, null);
            Button btnLogout = view.findViewById(R.id.btnLogout);
            Button btnCancel = view.findViewById(R.id.btnCancel);
            TextView tvtitle = view.findViewById(R.id.tvtitle);
            TextView tvMsg = view.findViewById(R.id.tvMsg);
            btnLogout.setText("Yes");
            btnCancel.setText("No");
            tvMsg.setText("Are you sure?");
            builder.setView(view);
            tvtitle.setVisibility(View.VISIBLE);
            final AlertDialog dialog = builder.create();
            if (dialog.getWindow() != null) {
                dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
            }

            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    listener.onYes();
                    // getApp().getAppPreference().setIsTutorialVisible("no");
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    listener.onNo();
                    //getApp().getAppPreference().setIsTutorialVisible("yes");
                }
            });

            dialog.show();
        }

    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getReferrerDataDecoded(Context context,String raw) {

        if (raw == null) {
            return null;
        }

        try {
            String url = URLDecoder.decode(raw, "utf-8");
            try {
                String url2x = URLDecoder.decode(url, "utf-8");
                if (raw.equals(url2x)) {
                    return null;
                }
                return url2x;
            } catch (UnsupportedEncodingException uee) {
                // not URL 2x encoded but URL encoded
                if (raw.equals(url)) {
                    return null;
                }
                return url;
            }
        } catch (UnsupportedEncodingException uee) {
            // not URL encoded
        }
        return null;
    }

}
