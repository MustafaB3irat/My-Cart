package com.example.icart.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.icart.interfaces.DatabaseHelper;
import com.example.icart.models.data.Catagory;
import com.example.icart.models.data.Element;

import java.sql.Timestamp;
import java.util.Date;

public class DatabaseHelpers extends SQLiteOpenHelper implements com.example.icart.interfaces.DatabaseHelper {


    private static final String CATAGORIES = "catagories";
    private static final String ELEMENT = "element";


    private SQLiteDatabase db;

    public DatabaseHelpers(Context context) {
        super(context, "iCart.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table if not EXISTS " + CATAGORIES + "(" +
                "catagory_name TEXT PRIMARY KEY NOT NULL , catagory_avatar TEXT NOT NULL DEFAULT 'default' , " +
                " created_at TEXT NOT NULL)");


        sqLiteDatabase.execSQL("create table if not EXISTS " + ELEMENT + "(eid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " +
                "element_name TEXT NOT NULL , price FLOAT NOT NULL , quantity INTEGER NOT NULL ," +
                " total FLOAT NOT NULL , catagory_name TEXT NoT NULL,  created_at TEXT NOT NULL ," +
                " FOREIGN KEY(catagory_name) references catagories (catagory_name))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists " + CATAGORIES);
        sqLiteDatabase.execSQL("drop table if exists " + ELEMENT);
        onCreate(sqLiteDatabase);

    }

    @Override
    public Cursor getCategories() {

        db = getWritableDatabase();

        return db.rawQuery("select * from " + CATAGORIES + " order by created_at DESC", null);
    }

    @Override
    public Cursor getElements(String catagory) {

        db = getWritableDatabase();
        return db.rawQuery("select * from element where catagory_name = '" +
                catagory + "'", null);
    }

    @Override
    public boolean setLock(String password) {
        return false;
    }

    @Override
    public boolean addElement(Element element, String category_name) {

        db = this.getWritableDatabase();

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        int elementQuantity = Integer.parseInt(element.getQuantity());
        float elementPrice = Float.parseFloat(element.getPrice());
        float elementTotal = Float.parseFloat(element.getTotal());


        db.execSQL("insert into " + ELEMENT + " (element_name  , quantity , price  , total , created_at , catagory_name) values ('" + element.getName() + "' , " + elementQuantity + " , " + elementPrice + ", " + elementTotal + ", '" + element.getCreated_at() + "','" + category_name + "')");
        return true;
    }

    @Override
    public boolean addCategory(Catagory catagory) {

        db = this.getWritableDatabase();

        db.execSQL("insert into " + CATAGORIES + " (catagory_name  , created_at , catagory_avatar ) values ('" + catagory.getName() + "' , '" + catagory.getCreated_at() + "' , '" + catagory.getCatagory_avatar() + "')");
        return true;

    }

    @Override
    public boolean deleteCategory(String categoryName) {

        db = this.getWritableDatabase();

        try {


            db.execSQL("delete from " + ELEMENT + " where catagory_name = '" + categoryName + "'");
            db.execSQL("delete from " + CATAGORIES + " where catagory_name = '" + categoryName + "'");

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean editCategory(String categoryName, Catagory catagory) {

        this.db = getWritableDatabase();

        try {
            db.execSQL("UPDATE " + CATAGORIES + " set catagory_name = '" + catagory.getName() + "' , created_at = '" + catagory.getCreated_at() + "' where catagory_name = '" + categoryName + "'");

            db.execSQL("update " + ELEMENT + " set catagory_name =  '" + catagory.getName() + "' where catagory_name = '" + categoryName + "'");
        } catch (Exception e) {
            return false;
        }


        return true;
    }

    @Override
    public boolean deleteElement(String elementName) {
        return false;
    }
}
