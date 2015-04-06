package fr.ac.codeprod.beautifulopenweather.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class Function {
    public static String TAG = Function.class.getSimpleName();


    public static int getConvertFloatInt(float value) {
        int finalValue = (int) Math.round(value);
        //return String.valueOf(finalValue);
        return finalValue;
    }

    public static String getDayToTimestamp(long value) {
        long timestamp = value * 1000;
        Date mDate = new Date(timestamp);
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("EEE");
        return mSimpleDateFormat.format(mDate);
    }


}
