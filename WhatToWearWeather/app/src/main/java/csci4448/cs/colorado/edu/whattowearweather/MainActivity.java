package csci4448.cs.colorado.edu.whattowearweather;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mSummary;

    private ArrayList<ClothingItem> mClothes;

    DBHelper mydb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mydb = new DBHelper(this);
        ClothesRecLoader recLoader = new ClothesRecLoader(mydb);

        LocationFinder locationFinder = new LocationFinder(this);
        Location currentLocation = locationFinder.updateLocation();

        final Forecast forecast = new Forecast(this,currentLocation);

        //Have to run networking in background instead of UI thread
        Thread t = new Thread(new Runnable() {
            public void run() {
                forecast.updateForecast();

            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Recommender recommender = new Recommender();
        ClothingItem item = recommender.getRecommendation(forecast, mydb);

        mSummary = (TextView)findViewById(R.id.Summary);
        if (forecast.getSummary() == null) {
            Toast.makeText(this, "Network Unvailable", Toast.LENGTH_LONG).show();
            mSummary.setText("No forecast available");
        }
        else {
            mSummary.setText(forecast.getSummary());
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
