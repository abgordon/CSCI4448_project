package csci4448.cs.colorado.edu.whattowearweather;

/**
 * Created by dan on 12/4/15.
 */
public class Types {

    public enum Clothing {

    }

    public enum Temp {
        /*
        VERY_COLD (20),
        COLD (30),
        CHILLY (40),
        MILD (50),
        WARM (70),
        HOT (80),
        VERY_HOT (90);
        */
        COLD (40),
        WARM (65);


        int temp;
        Temp(int t) {
            temp = t;
        }

        int showTemp() {
            return temp;
        }
    }

    public enum BodyPart {
        FEET, LEGS, CHEST, HANDS, ARMS, FACE, OTHER
    }

    public enum Precip {
        RAIN, SNOW, NONE
    }

    public enum Gender{
        MALE, FEMALE, BOTH
    }

}
