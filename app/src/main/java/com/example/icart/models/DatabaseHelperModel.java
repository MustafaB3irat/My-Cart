package com.example.icart.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.icart.interfaces.DatabaseHelper;

public class DatabaseHelper extends SQLiteOpenHelper implements com.example.icart.interfaces.DatabaseHelper {


    private static final String CATAGORIES = "catagories";
    private static final String ELEMENT = "element";


    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, "iCart.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        db.execSQL("create table if not EXISTS "+CATAGORIES+ "");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public Cursor getCatagories() {
        return null;
    }

    @Override
    public Cursor getElements(String catagory) {
        return null;
    }

    @Override
    public boolean setLock(String password) {
        return false;
    }
}
