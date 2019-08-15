package com.example.icart.presenters;

import android.database.Cursor;

import com.example.icart.interfaces.AddElement;
import com.example.icart.models.AddElementModel;
import com.example.icart.views.dialogs.AddElementDialog;

public class AddElementPresenter implements AddElement.AddElementPresenter {


    private AddElement.AddElementModel addElementModel;
    private AddElement.AddElementDialog addElementDialog;


    public AddElementPresenter(AddElementDialog addElementDialog) {

        this.addElementDialog = addElementDialog;
        this.addElementModel = new AddElementModel(addElementDialog);
    }

    @Override
    public boolean addElement(String categoryName, String elementName, float elementPrice, int elementQuantity, float elementTotalPrice) {
        return addElementModel.addElement(categoryName, elementName, elementPrice, elementQuantity, elementTotalPrice);
    }

    @Override
    public boolean editElement(String elementName, float elementPrice, int elementQuantity, float elementTotalPrice, String oldElementName) {
        return addElementModel.editElement(elementName, elementPrice, elementQuantity, elementTotalPrice, oldElementName);
    }

    @Override
    public Cursor getCategories() {
        return addElementModel.getCategories();
    }
}
