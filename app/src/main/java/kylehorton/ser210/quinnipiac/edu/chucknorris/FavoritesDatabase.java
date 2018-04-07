package kylehorton.ser210.quinnipiac.edu.chucknorris;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Kyle Horton
 * SER210
 * 4/6/2018
 *
 * This class creates the database used to store the favorites.
 *
 */
public class FavoritesDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "favorites"; // name of the database
    private static final String COL1 = "joke"; // joke
    private static final int DB_VERSION = 1; // version of the database
    public static ArrayList<String> listData; // public variable is needed to access database

    //constructor
    public FavoritesDatabase(Context context){
        super(context, DB_NAME,null, DB_VERSION);
    }

    // creates the table database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + DB_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT)";
        sqLiteDatabase.execSQL(createTable);

    }

    // updates the table
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);

    }

    // adds the data to the table
    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues favoriteValues = new ContentValues();
        favoriteValues.put(COL1, item);

        long result = db.insert(DB_NAME, null, favoriteValues);

        // checks to see if data was added to database correctly
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    //returns all data from database
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DB_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    // deletes a specific joke from table database
    public void deleteJoke(String joke){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_NAME, "joke = ?", new String[]{joke});

    }

    // deletes all rows from table database
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_NAME, null, null);
    }

    // fills the ArrayList with data from the table database
    public void populate(){
        //get data and append it to the list
        Cursor data = getData();
        listData = new ArrayList<>();
        while (data.moveToNext()){
            // get data from column 1
            // add it to list
            listData.add(data.getString(1));
        }
    }
}
