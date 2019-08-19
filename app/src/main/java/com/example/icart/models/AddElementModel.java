package com.example.icart.models;

import android.database.Cursor;

import androidx.fragment.app.DialogFragment;

import com.example.icart.interfaces.AddElement;
import com.example.icart.models.data.Element;

import java.sql.Timestamp;
import java.util.Date;

public class AddElementModel implements AddElement.AddElementModel {

    private DatabaseHelpers database;

    public AddElementModel(DialogFragment dialogFragment) {

        this.database = new DatabaseHelpers(dialogFragment.getContext());
    }

    @Override
    public boolean addElement(String categoryName, String elementName, float elementPrice, int elementQuantity, float elementTotalPrice , String eid) {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());


        String created_at = timestamp.toString().split(":")[0] + ":" + timestamp.toString().split(":")[1];

        Element element = new Element(elementName, created_at, String.valueOf(elementQuantity), String.valueOf(elementPrice), String.valueOf(elementTotalPrice), eid);

        database.updateCategoryTimestamp(created_at, categoryName);

        return database.addElement(element, categoryName);
    }

    @Override
    public boolean editElement(String elementName, float elementPrice, int elementQuantity, float elementTotalPrice, String eid , String categoryName) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());


        String created_at = timestamp.toString().split(":")[0] + ":" + timestamp.toString().split(":")[1];

        Element element = new Element(elementName, created_at, String.valueOf(elementQuantity), String.valueOf(elementPrice), String.valueOf(elementTotalPrice) ,eid );

        database.updateCategoryTimestamp(created_at , categoryName);
        return database.editElement(eid, element);
    }

    @Override
    public Cursor getCategories() {
        return database.getCategories();
    }

    @Override
    public Cursor getElements(String categoryName) {
        return database.getElements(categoryName);
    }
}
