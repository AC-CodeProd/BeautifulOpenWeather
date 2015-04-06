package fr.ac.codeprod.beautifulopenweather.request;

import android.util.Log;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import fr.ac.codeprod.beautifulopenweather.API;
import fr.ac.codeprod.beautifulopenweather.model.CityDetails;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class ForecastDailyWeatherRequest extends RetrofitSpiceRequest<CityDetails, API> {
    private String name;
    private String lang;
    private String units;
    private int cnt;

    public ForecastDailyWeatherRequest(String name, String lang, String units, int cnt) {
        super(CityDetails.class, API.class);
        this.name = name;
        this.lang = lang;
        this.units = units;
        this.cnt = cnt;
    }

    @Override
    public CityDetails loadDataFromNetwork() {
        return getService().getForecastDailyWeatherByName(name, lang, units, cnt);
    }

}