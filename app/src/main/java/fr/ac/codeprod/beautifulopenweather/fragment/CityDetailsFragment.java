package fr.ac.codeprod.beautifulopenweather.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import fr.ac.codeprod.beautifulopenweather.R;
import fr.ac.codeprod.beautifulopenweather.adapter.ForecastsRecyclerViewAdapter;
import fr.ac.codeprod.beautifulopenweather.model.CityDetails;
import fr.ac.codeprod.beautifulopenweather.preference.BeautifulOpenWeatherPreference;
import fr.ac.codeprod.beautifulopenweather.request.ForecastDailyWeatherIconRequest;
import fr.ac.codeprod.beautifulopenweather.request.ForecastDailyWeatherRequest;
import fr.ac.codeprod.beautifulopenweather.service.APIOpenWeatherIconSpiceService;
import fr.ac.codeprod.beautifulopenweather.service.APIOpenWeatherSpiceService;
import fr.ac.codeprod.beautifulopenweather.utils.Function;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class CityDetailsFragment extends Fragment {
    public static final String ARG_NAME_CITY = "name_city";

    private RecyclerView mRecyclerViewForecasts;
    private RecyclerView.Adapter mForecastsRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static CityDetails mCityDetails;

    private SpiceManager mAPIOpenWeatherSpiceManager = new SpiceManager(APIOpenWeatherSpiceService.class);
    private SpiceManager mAPIOpenWeatherIconSpiceManager = new SpiceManager(APIOpenWeatherIconSpiceService.class);
    private String nameCity;

    public TextView cityDetailName;
    public TextView currentTemp;
    public TextView cityDetailMax;
    public TextView cityDetailMin;
    public TextView cityDetailDescription;
    public ImageView cityDetailIcon;

    private BeautifulOpenWeatherPreference mBeautifulOpenWeatherPreference;


    public static CityDetailsFragment newInstance(String name) {
        CityDetailsFragment fragment = new CityDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME_CITY, name);
        fragment.setArguments(args);
        return fragment;
    }

    public CityDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nameCity = getArguments().getString(ARG_NAME_CITY);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_city_details, container, false);
        onInitializeView(mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBeautifulOpenWeatherPreference = new BeautifulOpenWeatherPreference(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        mAPIOpenWeatherSpiceManager.start(getActivity());
        mAPIOpenWeatherIconSpiceManager.start(getActivity());
        getDetails();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAPIOpenWeatherSpiceManager.shouldStop();
        mAPIOpenWeatherIconSpiceManager.shouldStop();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void onInitializeView(View mView) {
        mRecyclerViewForecasts = (RecyclerView) mView.findViewById(R.id.recycler_view_forecasts);
        mRecyclerViewForecasts.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewForecasts.setLayoutManager(mLayoutManager);
        cityDetailName = (TextView) mView.findViewById(R.id.city_detail_name);
        currentTemp = (TextView) mView.findViewById(R.id.city_detail_temp_current);
        cityDetailMax = (TextView) mView.findViewById(R.id.city_detail_max);
        cityDetailMin = (TextView) mView.findViewById(R.id.city_detail_min);
        cityDetailDescription = (TextView) mView.findViewById(R.id.city_detail_description);
        cityDetailIcon = (ImageView) mView.findViewById(R.id.city_detail_icon);
    }

    public void getDetails() {
        if(mBeautifulOpenWeatherPreference.getCurrentCity() != null){
            nameCity=mBeautifulOpenWeatherPreference.getCurrentCity();
        }
        Log.v("getDetails","getUnits : "+mBeautifulOpenWeatherPreference.getUnits());

        ForecastDailyWeatherRequest request = new ForecastDailyWeatherRequest(nameCity, "fr", mBeautifulOpenWeatherPreference.getUnits(), 7);
        mAPIOpenWeatherSpiceManager.execute(request,
                "message_cache",
                DurationInMillis.ALWAYS_EXPIRED, new RequestListener<CityDetails>() {
                    @Override
                    public void onRequestFailure(SpiceException spiceException) {
                        Toast.makeText(getActivity(), "Error during request: " + spiceException.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onRequestSuccess(CityDetails cityDetails) {
                        onGenerateDetailsView(cityDetails);
                    }
                });


    }

    private void onGenerateDetailsView(CityDetails cityDetails) {
        mForecastsRecyclerViewAdapter = new ForecastsRecyclerViewAdapter(cityDetails.forecasts, mAPIOpenWeatherIconSpiceManager);
        mRecyclerViewForecasts.setAdapter(mForecastsRecyclerViewAdapter);
        cityDetailName.setText(cityDetails.city.name + "," + cityDetails.city.country);
        currentTemp.setText(Function.getConvertFloatInt(cityDetails.forecasts.get(0).temp.day) + "");
        cityDetailMax.setText("Max: " + Function.getConvertFloatInt(cityDetails.forecasts.get(0).temp.max) + "");
        cityDetailMin.setText("Min: " + Function.getConvertFloatInt(cityDetails.forecasts.get(0).temp.min) + "");
        cityDetailDescription.setText(cityDetails.forecasts.get(0).weather.get(0).description);
        ForecastDailyWeatherIconRequest request = new ForecastDailyWeatherIconRequest(cityDetails.forecasts.get(0).weather.get(0).icon + ".png");
        mAPIOpenWeatherIconSpiceManager.execute(request,
                "message_cache",
                DurationInMillis.ALWAYS_EXPIRED, new RequestListener<Response>() {
                    @Override
                    public void onRequestFailure(SpiceException spiceException) {

                    }

                    @Override
                    public void onRequestSuccess(Response response) {
                        TypedInput body = response.getBody();
                        InputStream mInputStream = null;
                        try {
                            mInputStream = body.in();
                            byte[] buffer = new byte[1024];
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();

                            while (mInputStream.read(buffer) != -1)
                                baos.write(buffer);

                            baos.toByteArray();
                            Bitmap img = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length);
                            cityDetailIcon.setImageBitmap(img);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                mInputStream.close();
                            } catch (Throwable t) {
                            }
                        }
                    }
                });
    }
}