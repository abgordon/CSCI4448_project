package csci4448.cs.colorado.edu.whattowearweather;

/**
 * Created by dan on 12/6/15.
 */
public class ClothesRecLoader {

    private DBHelper mydb;

    ClothesRecLoader(DBHelper dbHelper) {
        mydb = dbHelper;
        insertClothes();
    }


    public void insertHead() {

    }

    public void insertChest() {

        mydb.insertClothingItem(Types.Temp.WARM, Types.Precip.NONE, Types.BodyPart.CHEST, 1, "Plain Solid t-hirt");

        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.NONE, Types.BodyPart.CHEST, 0, "Cozy Womens Trail Model Fleece Jacket");
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.NONE, Types.BodyPart.CHEST, 1, "Cozy Men's Trail Model Fleece Jacket");
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.RAIN, Types.BodyPart.CHEST, 0, "North Face MEN'S VENTURE JACKET");
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.RAIN, Types.BodyPart.CHEST, 1, "Face WOMEN'S VENTURE JACKET");
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.SNOW, Types.BodyPart.CHEST, 0, "Carhartt Men's Duck Chore Blanket-Lined Coat");
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.SNOW, Types.BodyPart.CHEST, 1, "FlyLow Gear Jody Down Jacket - Women's");

        mydb.insertClothingItem(Types.Temp.WARM, Types.Precip.NONE, Types.BodyPart.CHEST, 0, "Floral Print Fit-and-Flare Dress");
        mydb.insertClothingItem(Types.Temp.WARM, Types.Precip.NONE, Types.BodyPart.CHEST, 0, "dead sexy warm bodies tank top");
        mydb.insertClothingItem(Types.Temp.WARM, Types.Precip.RAIN, Types.BodyPart.CHEST, 1, "Marmot PreCip Jacket - Men's");
        mydb.insertClothingItem(Types.Temp.WARM, Types.Precip.RAIN, Types.BodyPart.CHEST, 0, "Marmot PreCip Jacket - Women's");
    }

    public void insertLegs() {

        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.NONE, Types.BodyPart.LEGS, 1, "Levi 501 Jeans");
        mydb.insertClothingItem(Types.Temp.COLD, Types.Precip.NONE, Types.BodyPart.LEGS, 0, "Levi 701 Jeans");

        mydb.insertClothingItem(Types.Temp.WARM, Types.Precip.NONE, Types.BodyPart.LEGS, 1, "CS Student Cargo Shorts");
        mydb.insertClothingItem(Types.Temp.WARM, Types.Precip.NONE, Types.BodyPart.LEGS, 0, "High Waisted Jean Shorts");

    }

    public void insertFeet() {

    }

    public void insertClothes() {

        insertChest();
        insertLegs();


    }
}
