package csci4448.cs.colorado.edu.whattowearweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mSummary;

    private ArrayList<ClothingItem> mClothes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
            hardcode our clothing items for now using following constructor signature:
            public ClothingItem(Types.Temp temp,Types.Precip precip, Types.BodyPart bodypart,  int gender, String name)

         */
        this.mClothes = new ArrayList<ClothingItem>();

        ClothingItem c_n = new ClothingItem(Types.Temp.COLD, Types.Precip.NONE, Types.BodyPart.CHEST, 0, "Cozy Womens Trail Model Fleece Jacket");
        mClothes.add(c_n);
        c_n = new ClothingItem(Types.Temp.COLD, Types.Precip.NONE, Types.BodyPart.CHEST, 1, "Cozy Men's Trail Model Fleece Jacket");
        mClothes.add(c_n);
        c_n = new ClothingItem(Types.Temp.COLD, Types.Precip.RAIN, Types.BodyPart.CHEST, 0, "North Face MEN'S VENTURE JACKET");
        mClothes.add(c_n);
        c_n = new ClothingItem(Types.Temp.COLD, Types.Precip.RAIN, Types.BodyPart.CHEST, 1, "Face WOMEN'S VENTURE JACKET");
        mClothes.add(c_n);
        c_n = new ClothingItem(Types.Temp.COLD, Types.Precip.SNOW, Types.BodyPart.CHEST, 0, "Carhartt Men's Duck Chore Blanket-Lined Coat");
        mClothes.add(c_n);
        c_n = new ClothingItem(Types.Temp.COLD, Types.Precip.SNOW, Types.BodyPart.CHEST, 1, "FlyLow Gear Jody Down Jacket - Women's");
        mClothes.add(c_n);

        c_n = new ClothingItem(Types.Temp.WARM, Types.Precip.NONE, Types.BodyPart.CHEST, 0, "Floral Print Fit-and-Flare Dress");
        mClothes.add(c_n);
        c_n = new ClothingItem(Types.Temp.WARM, Types.Precip.NONE, Types.BodyPart.CHEST, 1, "dead sexy warm bodies tank top");
        mClothes.add(c_n);
        c_n = new ClothingItem(Types.Temp.WARM, Types.Precip.RAIN, Types.BodyPart.CHEST, 0, "Marmot PreCip Jacket - Men's");
        mClothes.add(c_n);
        c_n = new ClothingItem(Types.Temp.WARM, Types.Precip.RAIN, Types.BodyPart.CHEST, 1, "Marmot PreCip Jacket - Women's");
        mClothes.add(c_n);

        SQLiteDatabase clothes_db = openOrCreateDatabase("clothes_db",MODE_PRIVATE,null);



        clothes_db.execSQL("CREATE TABLE IF NOT EXISTS Clothes(Username VARCHAR,Password VARCHAR);");
        clothes_db.execSQL("INSERT INTO Clothes VALUES('admin','admin');");

        final Forecast forecast = new Forecast(this);
        forecast.updateLocation();
        forecast.updateForecast();

        //Horrible hack to wait for background thread to finish
        while (forecast.isFetchFinished() != true) {

        }
        mSummary = (TextView)findViewById(R.id.Summary);
        mSummary.setText(forecast.getSummary());


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
