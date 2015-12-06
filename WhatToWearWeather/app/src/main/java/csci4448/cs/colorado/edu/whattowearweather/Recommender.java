package csci4448.cs.colorado.edu.whattowearweather;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by dan on 12/4/15.
 */
public class Recommender {

//    private ClothingItem mFeet;
//    private ClothingItem mLegs;
    private ClothingItem mChest;
//    private ClothingItem mHands;
//    private ClothingItem mHead;
//    private ClothingItem mOther;

    private double mTemp;

    private Types.Temp mTempType;

    int gender = 0;

    public ArrayList<ClothingItem> getRecommendation(Forecast forecast){
        // Get forecast data
        mTemp = forecast.getTemp();
        mTempType = Types.Temp.VERY_COLD;
        for (Types.Temp t : Types.Temp.values()) {
            if (mTemp >= t.showTemp()) {
                mTempType = t;
            }
        }
        // Build the ClothingItem array
        ArrayList<ClothingItem> recommendations = new ArrayList<ClothingItem>();

        return recommendations;
    }

    public ArrayList<ClothingItem> Recommender(Forecast forecast) {

        //SQLiteDatabase clothes_db = openOrCreateDatabase("clothes_db", MODE_PRIVATE, null);
        /*
        String request = new String("Select * from TutorialsPoint where Temperature=%d and Gender=%d and BodyPart=CHEST", mTempType, gender);
        Cursor resultSet = clothes_db.rawQuery(request, null);


        ArrayList<ClothingItem> recommendations = new ArrayList<ClothingItem>();
        ClothingItem current;
        while (resultSet.next()) {
            //create clothing items and return them
            int temp = resultSet.getInt("Temperature");
            int gender = resultSet.getInt("Gender");
            String name = resultSet.getString("Name");
            String bodypart = new String("CHEST");
            Types.Precip precip = resultSet.getString("Precipitation");


            current = new ClothingItem(temp, precip, bodypart, gender, name);
            recommendations.add(current);
        }
        resultSet.close();
        */
        ArrayList<ClothingItem> recommendations = new ArrayList<ClothingItem>();
        ;
        return recommendations;

        }

    }