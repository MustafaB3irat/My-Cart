package com.example.icart.presenters;

import android.database.Cursor;

import com.example.icart.interfaces.AddElement;
import com.example.icart.models.AddElementModel;
import com.example.icart.models.data.Element;
import com.example.icart.views.dialogs.AddElementDialog;

import java.util.ArrayList;
import java.util.List;

public class AddElementPresenter implements AddElement.AddElementPresenter {


    private AddElement.AddElementModel addElementModel;
    private AddElement.AddElementDialog addElementDialog;


    public AddElementPresenter(AddElementDialog addElementDialog) {

        this.addElementDialog = addElementDialog;
        this.addElementModel = new AddElementModel(addElementDialog);
    }

    @Override
    public boolean addElement(String categoryName, String elementName, float elementPrice, int elementQuantity, float elementTotalPrice, String eid) {
        return addElementModel.addElement(categoryName, elementName, elementPrice, elementQuantity, elementTotalPrice, eid);
    }

    @Override
    public boolean editElement(String elementName, float elementPrice, int elementQuantity, float elementTotalPrice, String oldElementName, String categoryName) {
        return addElementModel.editElement(elementName, elementPrice, elementQuantity, elementTotalPrice, oldElementName, categoryName);
    }

    @Override
    public Cursor getCategories() {
        return addElementModel.getCategories();
    }

    @Override
    public void getElements(String categoryName) {

        List<String> elements = new ArrayList<>();

        Cursor cursor = addElementModel.getElements(categoryName);

        while (cursor.moveToNext()) {

            elements.add(cursor.getString(cursor.getColumnIndex("element_name")));

        }

        addElementDialog.initAutoCompleteText(elements);

    }
}
