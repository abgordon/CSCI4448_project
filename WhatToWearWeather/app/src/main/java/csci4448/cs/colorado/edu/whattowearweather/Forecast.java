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

    private boolean fetchFinished = false;

    private String apiKey = "0054ddc6f867a627fb6464b0c69c30dc";

    public Forecast(Context context, Location location) {
        mContext = context;
        mLocation = location;
    }


    //
    public void updateForecast() {

        if (mLocation != null) {

            double lat = mLocation.getLatitude();
            double lon = mLocation.getLongitude();

            String forecastURL = "https://api.forecast.io/forecast/" + apiKey + "/" + lat + "," + lon;

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(forecastURL)
                    .build();

            //Fetch request not async
            try {
                Response response = client.newCall(request).execute();
                String rawJSON = response.body().string();
                JSONObject parsedJSON = new JSONObject(rawJSON);
                parseWeatherJSON(parsedJSON);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
               // Toast.makeText(mContext, "Weather data request failed (in onResponse)", Toast.LENGTH_LONG).show();
            }

            //Fetch request async, calling function in new thread so not necessary
            /*
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
                        fetchFinished = true;
                        //System.out.println("SUMMARY IS " + mSummary);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(mContext, "Weather data request failed (in onResponse)", Toast.LENGTH_LONG).show();
                    }
                }
            });
            */

        }
        else {
            System.out.println("location was null in updateForecast()");
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

    public void printSummary() {
        System.out.println(mSummary);
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

    public Location getmLocation() {
        return mLocation;
    }

    public boolean isFetchFinished() {
        return fetchFinished;
    }
}
