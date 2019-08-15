package com.example.icart.presenters;

import android.database.Cursor;

import com.example.icart.interfaces.Elements;
import com.example.icart.models.ElementsModel;
import com.example.icart.models.data.Element;
import com.example.icart.views.ElementsActivity;

import java.util.ArrayList;
import java.util.List;

public class ElementsPresenter implements Elements.ElementsPresenter {


    private Elements.ElementsView view;
    private Elements.ElementsModel model;


    public ElementsPresenter(ElementsActivity view) {
        this.view = view;
        this.model = new ElementsModel(view);
    }

    @Override
    public void getElements(String categoryName) {

        Cursor cursor = model.getElements(categoryName);

        List<Element> elements = new ArrayList<>();

        while (cursor.moveToNext()) {

            elements.add(new Element(cursor.getString(cursor.getColumnIndex("element_name")), cursor.getString(cursor.getColumnIndex("created_at")), String.valueOf(cursor.getInt(cursor.getColumnIndex("quantity"))),
                    String.valueOf(cursor.getFloat(cursor.getColumnIndex("price"))),
                    String.valueOf(cursor.getFloat(cursor.getColumnIndex("total"))), String.valueOf(cursor.getInt(cursor.getColumnIndex("eid")))));

        }

        view.setRecyclerViewAdapter(elements);

    }
}
