package csci4448.cs.colorado.edu.whattowearweather;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CLOTHING_TABLE_NAME = "clothing";
    public static final String CLOTHING_COLUMN_ID = "id";
    public static final String CLOTHING_COLUMN_TEMPTYPE = "mTempType";
    public static final String CLOTHING_COLUMN_PRECIPTYPE = "mPrecipType";
    public static final String CLOTHING_COLUMN_BODYPART = "mBodyPart";
    public static final String CLOTHING_COLUMN_GENDERTYPE = "mGenderType";
    public static final String CLOTHING_COLUMN_DESCRIPTION = "mDescription";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + CLOTHING_TABLE_NAME + " ("
                        +CLOTHING_COLUMN_ID+" integer primary key, "
                        +CLOTHING_COLUMN_TEMPTYPE   + " text,"
                        +CLOTHING_COLUMN_PRECIPTYPE + " text,"
                        +CLOTHING_COLUMN_BODYPART   + " text,"
                        +CLOTHING_COLUMN_GENDERTYPE + " integer,"
                        +CLOTHING_COLUMN_DESCRIPTION+ " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+CLOTHING_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertClothingItem (Types.Temp mTempType, Types.Precip mPrecipType, Types.BodyPart mBodyPart,
            int mGenderType, String mDescription)
    {
        String tempTypeName = mTempType.name();
        String precipTypeName = mPrecipType.name();
        String bodyTypeName = mBodyPart.name();
        //String genderTypeName = mGenderType.name();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mTempType", tempTypeName);
        contentValues.put("mPrecipType", precipTypeName);
        contentValues.put("mBodyPart", mBodyPart.name());
        contentValues.put("mGenderType", mGenderType);
        contentValues.put("mDescription", mDescription);
        db.insert("clothingItems", null, contentValues);
        return true;
    }

    public Cursor getDataFromID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CLOTHING_TABLE_NAME+" where id="+id+"", null );
        return res;
    }

    public Cursor getData(int temperature, int gender){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CLOTHING_TABLE_NAME+" where mTempType="+temperature
                +" and mGenderType="+gender, null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CLOTHING_TABLE_NAME);
        return numRows;
    }

    /* Not functional, delete if not needed
    public boolean updateClothingItem (Types.Temp mTempType, Types.Precip mPrecipType, Types.BodyPart mBodyPart,
                                       int mGenderType, String mDescription)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mTempType", mTempType.name());
        contentValues.put("mPrecipType", mPrecipType.name());
        contentValues.put("mBodyPart", mBodyPart.name());
        contentValues.put("mGenderType", mGenderType);
        contentValues.put("mDescription", mDescription);
        db.update(CLOTHING_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    */

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CLOTHING_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllClothing()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CLOTHING_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CLOTHING_COLUMN_TEMPTYPE)));
            res.moveToNext();
        }
        return array_list;
    }

}