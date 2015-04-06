package fr.ac.codeprod.beautifulopenweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class City {

    public int id;
    public String name;
    public String country;

    public City(String name, String country) {
        super();
        this.name = name;
        this.country = country;
    }

    @Override
    public String toString() {
        return "id: " + this.id + " name: " + this.name + " country: " + this.country;
    }
}
