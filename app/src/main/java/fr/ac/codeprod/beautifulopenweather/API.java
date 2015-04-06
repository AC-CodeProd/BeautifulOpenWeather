package fr.ac.codeprod.beautifulopenweather;

import fr.ac.codeprod.beautifulopenweather.model.CityDetails;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public interface API {
    @GET("/weather")
    CityDetails getCurrentWeatherByName(@Query("q") String name);

    @GET("/forecast/daily")
    CityDetails getForecastDailyWeatherByName(@Query("q") String name, @Query("lang") String lang, @Query("units") String units, @Query("cnt") int cnt);
}
