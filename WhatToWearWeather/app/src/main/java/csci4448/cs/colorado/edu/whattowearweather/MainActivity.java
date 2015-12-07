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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mSummary;
    private TextView mChestTextView;
    private TextView mLegsTextView;
    private Button mRefreshButton;

    LocationFinder mLocationFinder;
    Location mCurrentLocation;
    Forecast mForecast;

    DBHelper mydb;
    ClothesRecLoader recLoader;
    Recommender mRecommender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mydb = new DBHelper(this);
        recLoader = new ClothesRecLoader(mydb);
        mRecommender = new Recommender();


        updateLocation();
        updateForecast();
        updateUI();

        mRefreshButton = (Button) findViewById(R.id.refreshButton);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLocation();
                updateForecast();
                updateUI();

            }
        });


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


    public ClothingItem getRecommendation(Types.BodyPart bodyPart) {
        ClothingItem item = mRecommender.getRecommendation(mForecast, bodyPart, mydb, this);
        return item;
    }

    public  void updateUI() {

        mSummary = (TextView)findViewById(R.id.Summary);
        if (mForecast.getSummary() == null) {
            Toast.makeText(this, "Network Unvailable", Toast.LENGTH_LONG).show();
            mSummary.setText("No forecast available");
        }
        else {
            mSummary.setText(mForecast.getSummary());

            mChestTextView = (TextView)findViewById(R.id.shirt);
            mLegsTextView = (TextView)findViewById(R.id.pant);

            mChestTextView.setText(getRecommendation(Types.BodyPart.CHEST).getmName());
            mLegsTextView.setText(getRecommendation(Types.BodyPart.LEGS).getmName());
        }

    }

    public void updateLocation() {
        mLocationFinder = new LocationFinder(this);
        mCurrentLocation = mLocationFinder.updateLocation();
    }

    public void updateForecast() {

        mForecast = new Forecast(this,mCurrentLocation);

        //Have to run networking in background instead of UI thread
        Thread t = new Thread(new Runnable() {
            public void run() {
                mForecast.updateForecast();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
