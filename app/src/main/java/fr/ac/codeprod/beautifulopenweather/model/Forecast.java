package fr.ac.codeprod.beautifulopenweather.model;

import java.util.ArrayList;

/**
 * Created by CAJUSTE Alain on 06/04/2015.
 */
public class Forecast {
    public long dt;
    public Temp temp;
    public float pressure;
    public int humidity;
    public ArrayList<Weather> weather;
    public float speed;
    public int deg;
    public int clouds;
}
