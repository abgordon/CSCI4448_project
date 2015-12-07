package csci4448.cs.colorado.edu.whattowearweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

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

    public ClothingItem getRecommendation(Forecast forecast, Types.BodyPart bodyPart, DBHelper dbhelper, Context context){
        // Get forecast data

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int mGender = Integer.parseInt(prefs.getString("genderType", "0"));

        double mTemp = forecast.getTemp();
        Types.Temp mTempType = Types.Temp.COLD;
        for (Types.Temp t : Types.Temp.values()) {
            if (mTemp >= t.showTemp()) {
                mTempType = t;
            }
        }

        ClothingItem result = dbhelper.getClothingItem(mTempType, bodyPart, mGender);
        return result;
    }


}