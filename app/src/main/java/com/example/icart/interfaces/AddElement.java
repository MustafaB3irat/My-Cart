package com.example.icart.interfaces;

import android.database.Cursor;

import com.example.icart.models.data.Element;

public interface AddElement {

    interface AddElementDialog {

        void initAddButton();

        boolean validate();

        void initOnTextChanged();

        void calculateTotalPriceOnTextChanged();

        void initGetCategories();

        void setOldElementForEdit(Element oldElementForEdit, String categoryName);

    }

    interface AddElementModel {

        boolean addElement(String categoryName, String elementName, float elementPrice, int elementQuantity, float elementTotalPrice);

        boolean editElement(String elementName, float elementPrice, int elementQuantity, float elementTotalPrice, String oldElementName);

        Cursor getCategories();
    }

    interface AddElementPresenter {

        boolean addElement(String categoryName, String elementName, float elementPrice, int elementQuantity, float elementTotalPrice);

        boolean editElement(String elementName, float elementPrice, int elementQuantity, float elementTotalPrice, String oldElementName);

        Cursor getCategories();
    }
}
