package fr.ac.codeprod.beautifulopenweather.service;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import fr.ac.codeprod.beautifulopenweather.API;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class APIOpenWeatherSpiceService extends RetrofitGsonSpiceService {
    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(API.class);
    }

    @Override
    protected String getServerUrl() {
        return "http://api.openweathermap.org/data/2.5/";
    }
}
