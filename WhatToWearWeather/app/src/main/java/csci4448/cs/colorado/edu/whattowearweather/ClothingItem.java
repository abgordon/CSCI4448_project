package csci4448.cs.colorado.edu.whattowearweather;

import java.lang.reflect.Type;

/**
 * Created by dan on 12/4/15.
 */
public class ClothingItem {

    public Types.Temp getmTempType() {
        return mTempType;
    }

    public Types.Precip getmPrecipType() {
        return mPrecipType;
    }

    public Types.BodyPart getmBodyPart() {
        return mBodyPart;
    }

    public int getmGenderType() {
        return mGenderType;
    }

    public String getmName() {
        return mName;
    }

    private Types.Temp mTempType;
    private Types.Precip mPrecipType;
    private Types.BodyPart mBodyPart;
    private int mGenderType;
    private String mName;

    public ClothingItem(Types.Temp temp,Types.Precip precip, Types.BodyPart bodypart,  int gender, String name) {
        mTempType = temp;
        mPrecipType = precip;
        mBodyPart = bodypart;
        mGenderType = gender;
        mName = name;
    }



}
