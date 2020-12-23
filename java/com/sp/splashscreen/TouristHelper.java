package com.sp.splashscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TouristHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "locationlist.db";
    private static final int SCHEMA_VERSION = 1;

    public TouristHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Will be called once when the database is not created
        db.execSQL("CREATE TABLE locations_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "locationName TEXT, locationDate TEXT, locationDescription TEXT, locationAddress TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Will not be called until SCHEMA_VERSION increases
        //Here we can upgrade the database e.g. add more tables
    }

    /*Read all records from locations_table*/
    public Cursor getAll() {
        return (getReadableDatabase().rawQuery(
                "SELECT _id, locationName, locationDate, locationDescription, locationAddress", null));
    }

    /*Read a particular record from locations_table with id provided*/
    public Cursor getById(String id) {
        String[] args = {id};

        return (getReadableDatabase().rawQuery(
                "SELECT _id, locationName, locationDate, locationDescription, locationAddress", args));
    }

    /*Write a record into locations_table*/
    public void insert(String locationName, String locationDate,
                       String locationDescription, String locationAddress) {
        ContentValues cv = new ContentValues();

        cv.put("locationName", locationName);
        cv.put("locationDate", locationDate);
        cv.put("locationDescription", locationDescription);
        cv.put("locationAddress", locationAddress);

        getWritableDatabase().insert("locations_table", "locationName",cv);
    }

    /*Update a particular record in restaurants_table with id provided*/
    public void update(String id, String locationName, String locationDate,
                       String locationDescription, String locationAddress) {
        ContentValues cv = new ContentValues();
        String[] args = {id};
        cv.put("locationName", locationName);
        cv.put("locationDate", locationDate);
        cv.put("locationDescription", locationDescription);
        cv.put("locationAddress", locationAddress);

        getWritableDatabase().update("locations_table", cv, " _ID = ?", args);
    }

    /* Read a record id value from restaurants_table*/
    public String getID(Cursor c) {
        return (c.getString(0));
    }

    public String getlocationName(Cursor c) {
        return (c.getString(1));
    }

    public String getlocationDate(Cursor c) {
        return (c.getString(2));
    }

    public String getlocationDescription(Cursor c) {
        return (c.getString(3));
    }

    public String getlocationAddress(Cursor c) {
        return (c.getString(4));
    }
}
