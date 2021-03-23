package com.zoolife.app.utility;

import android.content.Context;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeShow {
    public String covertTimeToText(Context context, String dataDate) {
        if (dataDate != null && !TextUtils.isEmpty(dataDate)) {
            if (dataDate.contains("T")) {
                dataDate = dataDate.substring(0, dataDate.lastIndexOf(".")).replace("T", " ");
                System.out.println("Altered Date-->" + dataDate);
                System.out.println("Altered Date After removing T-->" + dataDate.replace("T", " "));
            }
            TimeZone.setDefault(TimeZone.getTimeZone("AST"));

            String timeVal = "";

            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date past = format.parse(getDate(dataDate));
                Date now = new Date();
                long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
                long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
                long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
                long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());
//
//          System.out.println(TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime()) + " milliseconds ago");
//          System.out.println(TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime()) + " minutes ago");
//          System.out.println(TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()) + " hours ago");
//          System.out.println(TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago");

//            if (seconds < 60) {
////                System.out.println(seconds + " قبل ثانية");
//                timeVal =   "  ثانية "+seconds+"قبل ";
//            } else if (minutes < 60) {
////                System.out.println(minutes + " قبل دقيقة");
//                timeVal =   " دقيقة "+ minutes+"قبل ";
//            } else if (hours < 24) {
////                System.out.println(hours + " قبل ساعة");
//                timeVal =   " ساعة "+ hours+"قبل ";
//            }  else if (hours < 7*24) {
////                System.out.println(days + " قبل يوم");
//                timeVal =   " يوم " +days+"قبل ";
                if (LocaleHelper.getLanguage(context).equals("ar")) {
                    if (seconds < 60) {

                        timeVal = " قبل " + seconds + "ثانية ";
                    } else if (minutes < 60) {

                        timeVal = " قبل " + minutes + "دقيقة ";
                    } else if (hours < 24) {

                        timeVal = " قبل " + hours + " ساعة";
                    } else if (hours < 7 * 24) {

                        timeVal = " قبل " + days + "يوم ";
                    } else {
//                System.out.println(days + " days ago");
                        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
//                Date date = format.parse(dataDate.split(" ")[0]);

                        timeVal = outputFormat.format(past.getTime());
                    }
                } else {
                    if (seconds < 60) {

                        timeVal = seconds + " Seconds ago ";
                    } else if (minutes < 60) {

                        timeVal = minutes + " Minutes ago ";
                    } else if (hours < 24) {

                        timeVal = hours + " Hour(s) ago ";
                    } else if (hours < 7 * 24) {

                        timeVal = days + " Days ago ";
                    } else {
//                System.out.println(days + " days ago");
                        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
//                Date date = format.parse(dataDate.split(" ")[0]);

                        timeVal = outputFormat.format(past.getTime());
                    }
                }
//            return
            } catch (Exception j) {
                j.printStackTrace();
            }
            return timeVal;
        } else {
            return "N/A";
        }
    }

    public String getDate(String dateTime) {

        Locale loc = new Locale("ar");
        Locale.setDefault(loc);


        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = utcFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        pstFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        pstFormat.setTimeZone(TimeZone.getTimeZone("AST"));

//        System.out.println(pstFormat.format(date));
        return pstFormat.format(date);
    }

}
