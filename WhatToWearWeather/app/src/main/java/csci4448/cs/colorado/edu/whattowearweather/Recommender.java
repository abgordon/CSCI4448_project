package csci4448.cs.colorado.edu.whattowearweather;

/**
 * Created by dan on 12/4/15.
 */
public class Recommender {

    private ClothingItem mFeet;
    private ClothingItem mLegs;
    private ClothingItem mChest;
    private ClothingItem mHands;
    private ClothingItem mHead;
    private ClothingItem mOther;

    private double mTemp;

    private Types.Temp mTempType;


    public Recommender(Forecast forecast) {
        mTemp = forecast.getTemp();


        mTempType = Types.Temp.VERY_COLD;
        for (Types.Temp t : Types.Temp.values()) {
            if (mTemp >= t.showTemp()) {
                mTempType = t;
            }
        }
    }
}
