package com.example.icart.interfaces;

import android.database.Cursor;

public interface AddElement {

    interface AddElementDialog {

        void initAddButton();

        boolean validate();

        void initOnTextChanged();

        void calculateTotalPriceOnTextChanged();

        void initGetCategories();

    }

    interface AddElementModel {

        boolean addElement(String categoryName, String elementName, float elementPrice, int elementQuantity, float elementTotalPrice);
        Cursor getCategories();
    }

    interface AddElementPresenter {

        boolean addElement(String categoryName, String elementName, float elementPrice, int elementQuantity, float elementTotalPrice);
        Cursor getCategories();
    }
}
