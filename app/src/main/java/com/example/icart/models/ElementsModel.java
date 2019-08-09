package com.example.icart.models;

import android.database.Cursor;

import com.example.icart.interfaces.Elements;
import com.example.icart.views.ElementsActivity;

public class ElementsModel implements Elements.ElementsModel {


    private DatabaseHelpers databaseHelpers;


    public ElementsModel(ElementsActivity elementsActivity) {

        this.databaseHelpers = new DatabaseHelpers(elementsActivity);
    }

    @Override
    public Cursor getElements(String categoryName) {
        return databaseHelpers.getElements(categoryName);
    }
}
