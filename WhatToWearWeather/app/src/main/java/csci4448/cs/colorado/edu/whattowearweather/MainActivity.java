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
<<<<<<< HEAD
import android.widget.Toast;
=======
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
>>>>>>> 8df5a24dc285967643b0b435b2c604314cf2fecc

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

        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.NONE, Types.BodyPart.CHEST, 0, "Cozy Womens Trail Model Fleece Jacket");
        /*
            hardcode our clothing items for now using following constructor signature:
            public ClothingItem(Types.Temp temp,Types.Precip precip, Types.BodyPart bodypart,  int gender, String name)
         */
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.NONE, Types.BodyPart.CHEST, 0, "Cozy Womens Trail Model Fleece Jacket");
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.NONE, Types.BodyPart.CHEST, 1, "Cozy Men's Trail Model Fleece Jacket");
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.RAIN, Types.BodyPart.CHEST, 0, "North Face MEN'S VENTURE JACKET");
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.RAIN, Types.BodyPart.CHEST, 1, "Face WOMEN'S VENTURE JACKET");
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.SNOW, Types.BodyPart.CHEST, 0, "Carhartt Men's Duck Chore Blanket-Lined Coat");
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.SNOW, Types.BodyPart.CHEST, 1, "FlyLow Gear Jody Down Jacket - Women's");

        mydb.insertClothingItem(Types.Temp.WARM, Types.Precip.NONE, Types.BodyPart.CHEST, 0, "Floral Print Fit-and-Flare Dress");
        mydb.insertClothingItem(Types.Temp.WARM, Types.Precip.NONE, Types.BodyPart.CHEST, 1, "dead sexy warm bodies tank top");
        mydb.insertClothingItem(Types.Temp.WARM, Types.Precip.RAIN, Types.BodyPart.CHEST, 0, "Marmot PreCip Jacket - Men's");
        mydb.insertClothingItem(Types.Temp.WARM, Types.Precip.RAIN, Types.BodyPart.CHEST, 1, "Marmot PreCip Jacket - Women's");

        SQLiteDatabase clothes_db = openOrCreateDatabase("clothes_db",MODE_PRIVATE,null);
        clothes_db.execSQL("CREATE TABLE IF NOT EXISTS Clothes(Username VARCHAR,Password VARCHAR);");
        clothes_db.execSQL("INSERT INTO Clothes VALUES('admin','admin');");

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

<<<<<<< HEAD

        mSummary = (TextView)findViewById(R.id.Summary);
        if (forecast.getSummary() == null) {
            Toast.makeText(this, "Network Unvailable", Toast.LENGTH_LONG).show();
            mSummary.setText("No forecast available");
        }
        else {
            mSummary.setText(forecast.getSummary());
        }
=======
>>>>>>> 8df5a24dc285967643b0b435b2c604314cf2fecc
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
