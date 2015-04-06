package fr.ac.codeprod.beautifulopenweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class CityDetails /*implements Parcelable*/ {


    public City city;

    @SerializedName("list")
    public ArrayList<Forecast> forecasts;


    public CityDetails() {
        super();

    }

    /*public CityDetails(Parcel in) {
        this.name = in.readString();
        this.country = in.readString();
        this.temperature = in.readInt();
        this.windSpeed = in.readInt();
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.country);
        dest.writeInt(this.temperature);
        dest.writeInt(this.windSpeed);
    }
    public static final Parcelable.Creator<CityDetails> CREATOR = new Parcelable.Creator<CityDetails>() {
        @Override
        public CityDetails createFromParcel(Parcel source) {
            return new CityDetails(source);
        }

        @Override
        public CityDetails[] newArray(int size) {
            return new CityDetails[size];
        }
    };*/
    @Override
    public String toString() {
        return "City => " + this.city.toString();
    }
}
