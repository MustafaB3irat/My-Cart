package com.example.icart.models;

import android.database.Cursor;

import androidx.fragment.app.DialogFragment;

import com.example.icart.interfaces.AddElement;
import com.example.icart.models.data.Element;

import java.sql.Timestamp;
import java.util.Date;

public class AddElementModel implements AddElement.AddElementModel {

    private DatabaseHelperModel database;

    public AddElementModel(DialogFragment dialogFragment) {

        this.database = new DatabaseHelperModel(dialogFragment.getContext());
    }

    @Override
    public boolean addElement(String categoryName, String elementName, float elementPrice, int elementQuantity, float elementTotalPrice) {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());


        String created_at = timestamp.toString().split(":")[0] + ":" + timestamp.toString().split(":")[0];

        Element element = new Element(elementName, created_at, elementQuantity, elementPrice, elementTotalPrice);
        return database.addElement(element, categoryName);
    }

    @Override
    public Cursor getCategories() {
        return database.getCategory();
    }
}
