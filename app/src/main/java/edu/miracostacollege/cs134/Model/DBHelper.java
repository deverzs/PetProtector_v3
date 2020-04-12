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

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PetProtector" ;
    public static final String TABLE_NAME = "Pets" ;
    public static final int VERSION = 1;

    public static final String KEY_FIELD_ID = "_id" ;
    public static final String FIELD_DETAILS = "details" ;
    public static final String FIELD_PHONE = "phone" ;
    public static final String FIELD_NAME = "name" ;
    public static final String FIELD_IMAGE = "image" ;



    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_DETAILS + " TEXT, "
                + FIELD_PHONE + " TEXT, "
                + FIELD_IMAGE + " TEXT"
                + ")";
        Log.i(DATABASE_NAME, createSQL) ;
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(newVersion > oldVersion)
        {
            String dropSQl = "DROP TABLE IF EXISTS " +  TABLE_NAME ;
            Log.i(DATABASE_NAME, dropSQl) ;
            db.execSQL(dropSQl);
            onCreate(db);
        }

    }

    public long addPet(Pet pet) {
        SQLiteDatabase db = getWritableDatabase() ;
        ContentValues values = new ContentValues() ;

        values.put(FIELD_DETAILS, pet.getmDetails());
        values.put(FIELD_NAME, pet.getmName());
        values.put(FIELD_PHONE, pet.getmPhone());
        values.put(FIELD_IMAGE, String.valueOf(pet.getmImageURI()));

        db.close();
        return 0;
    }

    public List<Pet> getAllPets()   {
        List<Pet> allPets = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase() ;

        Cursor cursor = db.query(TABLE_NAME,
                new String[] {KEY_FIELD_ID, FIELD_DETAILS, FIELD_NAME, FIELD_PHONE, FIELD_IMAGE},
                null,
                null,
                null,
                null,
                null) ;


        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong( 0);
                String details = cursor.getString(1);
                String name = cursor.getString(2);
                String phone = cursor.getString(3);
                String image = cursor.getString(4);

                allPets.add(new Pet(details, id, name, phone, Uri.parse(image))) ;
            } while (cursor.moveToNext()) ;
        }

        cursor.close();
        db.close();
        return  allPets ;
    }
}
