package fr.ac.codeprod.beautifulopenweather.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.ac.codeprod.beautifulopenweather.R;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class BeautifulOpenWeatherPreference {
    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private  final String CITYS = "Citys";

    public BeautifulOpenWeatherPreference(Activity activity) {
        mSharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
        mContext = (Context) activity;
    }


    public String getCurrentCity() {
        return mSharedPreferences.getString(mContext.getString(R.string.pref_key_pref_current_city), mContext.getString(R.string.pref_current_city_default));
    }

    public void setCurrentCity(String city) {
        mSharedPreferences.edit().putString(mContext.getString(R.string.pref_key_pref_current_city), city).commit();
    }
    public void storeFavorites(List citys) {
        SharedPreferences.Editor editor;
        editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(citys);
        editor.putString(mContext.getString(R.string.pref_key_pref_list_city), jsonFavorites);
        editor.commit();
    }
    public ArrayList loadCity(){
        List citys = null;
       if (mSharedPreferences.contains(mContext.getString(R.string.pref_key_pref_list_city))) {
            String jsonFavorites = mSharedPreferences.getString(mContext.getString(R.string.pref_key_pref_list_city), null);
            Gson gson = new Gson();
            String[] citysItems = gson.fromJson(jsonFavorites,String[].class);
            citys = Arrays.asList(citysItems);
            citys = new ArrayList(citys);
       } else
            return null;

        return (ArrayList) citys;
    }
    public void addCity(String value) {
        List citys = loadCity();
        if (citys == null)
            citys = new ArrayList();
        citys.add(value);
        storeFavorites(citys);
    }
    public void removeFavorite(Context context, String value) {
        ArrayList citys = loadCity();
        if (citys != null) {
            citys.remove(value);
            storeFavorites(citys);
        }
    }


    public String getUnits() {
        boolean value = mSharedPreferences.getBoolean(mContext.getString(R.string.pref_key_pref_temperature), mContext.getResources().getBoolean(R.bool.pref_enabled_default));

        if (value) {
            return "metric";
        } else {
            return "imperial";
        }
    }
}
