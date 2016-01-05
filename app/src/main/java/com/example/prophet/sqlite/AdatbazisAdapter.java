package com.example.prophet.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Prophet on 2016. 01. 04..
 */
public class AdatbazisAdapter {

    static final String DATABASE_NAME = "adatbazis.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    public static final String TABLE_NAME = "adatbazis";
    public static final String TABLE_ID = "ID";
    public static final String TABLE_FELHASZNALONEV = "FELHASZNALONEV";
    public static final String TABLE_JELSZO = "JELSZO";
    public static final String TABLE_TELJESNEV = "TELJESNEV";
    static final String DATABASE_CREATE = "create table " + TABLE_NAME + "( " + TABLE_ID + " integer primary key autoincrement, " + TABLE_FELHASZNALONEV + " text, " + TABLE_JELSZO + " text, " + TABLE_TELJESNEV + " text); ";

    public SQLiteDatabase db;
    private final Context context;
    private AdatbazisHelper dbHelper;
    public AdatbazisAdapter(Context _context)
    {
        context = _context;
        dbHelper = new AdatbazisHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public AdatbazisAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String userName, String passWord1, String teljesNev)
    {
        ContentValues values = new ContentValues();

        values.put(TABLE_FELHASZNALONEV, userName);
        values.put(TABLE_JELSZO, passWord1);
        values.put(TABLE_TELJESNEV, teljesNev);

        db.insert(TABLE_NAME, null, values);
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query(TABLE_NAME, null, " FELHASZNALONEV = ?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(TABLE_JELSZO));
        cursor.close();
        return password;
    }
}
