package fr.ac.codeprod.beautifulopenweather.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.CardView;
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
import java.util.ArrayList;

import fr.ac.codeprod.beautifulopenweather.R;
import fr.ac.codeprod.beautifulopenweather.model.CityDetails;
import fr.ac.codeprod.beautifulopenweather.model.Forecast;
import fr.ac.codeprod.beautifulopenweather.request.ForecastDailyWeatherIconRequest;
import fr.ac.codeprod.beautifulopenweather.request.ForecastDailyWeatherRequest;
import fr.ac.codeprod.beautifulopenweather.utils.Function;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class ForecastsRecyclerViewAdapter extends RecyclerView.Adapter<ForecastsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Forecast> forecasts;
    private SpiceManager mAPIOpenWeatherIconSpiceManager;

    public ForecastsRecyclerViewAdapter(ArrayList<Forecast> forecasts, SpiceManager mAPIOpenWeatherIconSpiceManager) {
        this.forecasts = forecasts;
        this.mAPIOpenWeatherIconSpiceManager = mAPIOpenWeatherIconSpiceManager;
    }

    @Override
    public ForecastsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemForecastDay.setText(Function.getDayToTimestamp(forecasts.get(position + 1).dt));
        holder.itemForecastMax.setText("Max: " + Function.getConvertFloatInt(forecasts.get(position + 1).temp.max) + "");
        holder.itemForecastMin.setText("Min: " + Function.getConvertFloatInt(forecasts.get(position + 1).temp.min) + "");
        holder.itemForecastDescription.setText(forecasts.get(position + 1).weather.get(0).description);
        final ImageView mImageView = holder.itemForecastIcon;

        ForecastDailyWeatherIconRequest request = new ForecastDailyWeatherIconRequest(forecasts.get(position).weather.get(0).icon + ".png");
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
                            mImageView.setImageBitmap(img);

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


    @Override
    public int getItemCount() {
        return forecasts.size() - 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemForecastDay;
        public TextView itemForecastMax;
        public TextView itemForecastMin;
        public TextView itemForecastDescription;
        public ImageView itemForecastIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            itemForecastDay = (TextView) itemView.findViewById(R.id.item_forecast_day);
            itemForecastMax = (TextView) itemView.findViewById(R.id.item_forecast_max);
            itemForecastMin = (TextView) itemView.findViewById(R.id.item_forecast_min);
            itemForecastDescription = (TextView) itemView.findViewById(R.id.item_forecast_description);
            itemForecastIcon = (ImageView) itemView.findViewById(R.id.item_forecast_icon);
        }
    }
}
