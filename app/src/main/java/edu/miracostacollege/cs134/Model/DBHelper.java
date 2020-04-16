package edu.miracostacollege.cs134.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * DBHelper that uses SQLiteDatabase to store the data
 */
public class DBHelper extends SQLiteOpenHelper {

    //status Strings
    public static final String DATABASE_NAME = "PetProtector" ;
    public static final String TABLE_NAME = "Pets" ;
    public static final int VERSION = 1;

    //Database fields
    public static final String KEY_FIELD_ID = "_id" ;
    public static final String FIELD_DETAILS = "details" ;
    public static final String FIELD_PHONE = "phone" ;
    public static final String FIELD_NAME = "name" ;
    public static final String FIELD_IMAGE = "image" ;


    /**
     * Creates DBHelper
     * @param context  the context who is using the helper
     */
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * On creation of the DBHelper
     * @param db the SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create a new table in the database
        String table = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_DETAILS + " TEXT, "
                + FIELD_PHONE + " TEXT, "
                + FIELD_IMAGE + " TEXT"
                + ")";
        Log.i(DATABASE_NAME, table) ;

        //execute the table command
        db.execSQL(table);
    }

    /**
     * If the databse gets updated
     * @param db  the database to update
     * @param oldVersion  the old version number
     * @param newVersion  the new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //check if we need to update it
        if(newVersion > oldVersion)
        {
            //drop the table
            String dropSQl = "DROP TABLE IF EXISTS " +  TABLE_NAME ;
            Log.i(DATABASE_NAME, dropSQl) ;
            db.execSQL(dropSQl);

            //create the new one
            onCreate(db);
        }
    }

    /**
     * Adding a pet to the database
     * @param pet  which pet to add
     * @return  the long int that is the new pet added
     */
    public long addPet(Pet pet) {

        //allow databse to write
        SQLiteDatabase db = getWritableDatabase() ;
        //the values to add
        ContentValues values = new ContentValues() ;

        //the values put into the database
        values.put(FIELD_NAME, pet.getmName());
        values.put(FIELD_DETAILS, pet.getmDetails());
        values.put(FIELD_PHONE, pet.getmPhone());
        values.put(FIELD_IMAGE, pet.getmImageURI().toString());

        //find the new long int that is the new pet row
        long id = db.insert(TABLE_NAME, null, values) ;

        //set the id
        pet.setmId(id);

        //close the database
        db.close();
        return 0;
    }

    /**
     * Get all the pets from the database
     * @return the list with all the pets
     */
    public List<Pet> getAllPets()   {

        //new list of pets
        List<Pet> allPets = new ArrayList<>();
        //read the database
        SQLiteDatabase db = getReadableDatabase() ;

        //Cursor to retrieve the data
        Cursor cursor = db.query(TABLE_NAME,
                new String[] {KEY_FIELD_ID, FIELD_NAME, FIELD_DETAILS, FIELD_PHONE, FIELD_IMAGE},
                null,
                null,
                null,
                null,
                null) ;

        //move cursor to the first row
        if (cursor.moveToFirst()) {
            //loop through the row and get the data
            do {
                long id = cursor.getLong( 0);
                String name = cursor.getString(1);
                String details = cursor.getString(2);
                String phone = cursor.getString(3);
                String image = cursor.getString(4);

                //create a new Pet to add the data to
                allPets.add(new Pet(id, name, details, phone, Uri.parse(image))) ;

            } while (cursor.moveToNext()) ; //move to next row
        }

        //close cursor and database
        cursor.close();
        db.close();

        //return the list of pets
        return  allPets ;
    }
}
