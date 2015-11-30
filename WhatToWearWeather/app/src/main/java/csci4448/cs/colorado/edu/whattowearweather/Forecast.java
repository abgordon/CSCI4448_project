package csci4448.cs.colorado.edu.whattowearweather;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by dan on 11/30/15.
 */
public class Forecast {

    private double mTemp;
    private double mWindSpeed;
    private String mSummary;
    private String mSkyCond;

    private Location mLocation;
    private Context mContext;
    private String mProvider;

    private String apiKey = "0054ddc6f867a627fb6464b0c69c30dc";

    public Forecast(Context context) {
        mContext = context;
    }

    public Location updateLocation() {
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                //makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // Creating an empty criteria object
        Criteria criteria = new Criteria();

        // Getting the name of the provider that meets the criteria
        mProvider = locationManager.getBestProvider(criteria, true);

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(mProvider, 0, 0, locationListener);

        mLocation = locationManager.getLastKnownLocation(mProvider);

        // Remove the listener you previously added
        locationManager.removeUpdates(locationListener);

        return mLocation;
    }

    //
    public void updateForecast() {

        updateLocation();

        if (mLocation != null) {

            double lat = mLocation.getLatitude();
            double lon = mLocation.getLongitude();

            String forecastURL = "https://api.forecast.io/forecast/" + apiKey + "/" + lat + "," + lon;

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(forecastURL)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "Weather data request failed (in Onfailure)", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String rawJSON = response.body().string();
                    try {
                        JSONObject parsedJSON = new JSONObject(rawJSON);
                        parseWeatherJSON(parsedJSON);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(mContext, "Weather data request failed (in onResponse)", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(mContext, "Location not available", Toast.LENGTH_LONG).show();
        }
    }

    public void parseWeatherJSON(JSONObject weatherJSON) throws JSONException {

        JSONObject hourly = weatherJSON.getJSONObject("hourly"); //gives the weather summary for day
        JSONObject daily = weatherJSON.getJSONObject("daily"); //gives daily forecast

        mSummary = hourly.getString("summary");

        JSONArray dailyForecasts =  daily.getJSONArray("data");
        JSONObject todayForecast = dailyForecasts.getJSONObject(0);

        mTemp = todayForecast.getDouble("temperatureMax");
        mWindSpeed = todayForecast.getDouble("windSpeed");

    }

    public String getSkyCond() {
        return mSkyCond;
    }

    public void setSkyCond(String mSkyCond) {
        this.mSkyCond = mSkyCond;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String mSummary) {
        this.mSummary = mSummary;
    }

    public double getTemp() {
        return mTemp;
    }

    public void setTemp(double mTemp) {
        this.mTemp = mTemp;
    }

    public double getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(double mWindSpeed) {
        this.mWindSpeed = mWindSpeed;
    }
}
