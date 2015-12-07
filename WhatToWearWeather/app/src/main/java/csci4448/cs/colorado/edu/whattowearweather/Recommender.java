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

    public ClothingItem getRecommendation(Forecast forecast, DBHelper dbhelper){
        // Get forecast data
        int mGender = 0;
        double mTemp = forecast.getTemp();
        Types.Temp mTempType = Types.Temp.COLD;
        for (Types.Temp t : Types.Temp.values()) {
            if (mTemp >= t.showTemp()) {
                mTempType = t;
            }
        }
        // Build the ClothingItem array
        ArrayList<ClothingItem> recommendations = new ArrayList<ClothingItem>();

        ClothingItem result = dbhelper.getClothingItem(mTempType, Types.BodyPart.CHEST, mGender);
        //recommendations =;
        return result;
    }

    public ArrayList<ClothingItem> Recommender(Forecast forecast) {

        ArrayList<ClothingItem> recommendations = new ArrayList<ClothingItem>();
        return recommendations;

        }

    }