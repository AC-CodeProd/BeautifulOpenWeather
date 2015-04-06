package fr.ac.codeprod.beautifulopenweather.service;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import fr.ac.codeprod.beautifulopenweather.API;
import fr.ac.codeprod.beautifulopenweather.request.ForecastDailyWeatherIconRequest;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class APIOpenWeatherIconSpiceService extends RetrofitGsonSpiceService {
    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(ForecastDailyWeatherIconRequest.IconAPI.class);
    }

    @Override
    protected String getServerUrl() {
        return "http://openweathermap.org/img/w/";
    }
}
