package fr.ac.codeprod.beautifulopenweather.request;

import android.database.Observable;
import android.util.Log;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.io.InputStream;

import fr.ac.codeprod.beautifulopenweather.model.CityDetails;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class ForecastDailyWeatherIconRequest extends RetrofitSpiceRequest<Response, ForecastDailyWeatherIconRequest.IconAPI> {
    private String code;

    public ForecastDailyWeatherIconRequest(String code) {
        super(Response.class, ForecastDailyWeatherIconRequest.IconAPI.class);
        this.code = code;
    }

    @Override
    public Response loadDataFromNetwork() throws Exception {
        /*InputStream mInputStream = getService().getIconWeatherByCode(code);
        Log.v("loadDataFromNetwork",mInputStream.toString());*/
        return getService().getIconWeatherByCode(code);
    }

    public interface IconAPI {
        @GET("/{code}")
        Response getIconWeatherByCode(@Path("code") String code);

    }


}
