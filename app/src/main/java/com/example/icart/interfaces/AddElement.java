package com.example.icart.interfaces;

import android.database.Cursor;

import com.example.icart.models.data.Element;

import java.util.List;

public interface AddElement {

    interface AddElementDialog {

        void initAddButton();

        boolean validate();

        void initOnTextChanged();

        void calculateTotalPriceOnTextChanged();

        void initGetCategories();

        void setOldElementForEdit(Element oldElementForEdit, String categoryName);

        void initAutoCompleteText(List<String> elements);

    }

    interface AddElementModel {

        boolean addElement(String categoryName, String elementName, float elementPrice, int elementQuantity, float elementTotalPrice , String eid);

        boolean editElement(String elementName, float elementPrice, int elementQuantity, float elementTotalPrice, String oldElementName , String categoryName);

        Cursor getCategories();

        Cursor getElements(String categoryName);
    }

    interface AddElementPresenter {

        boolean addElement(String categoryName, String elementName, float elementPrice, int elementQuantity, float elementTotalPrice , String eid);

        boolean editElement(String elementName, float elementPrice, int elementQuantity, float elementTotalPrice, String oldElementName , String categoryName);

        Cursor getCategories();
        void getElements(String categoryName);
    }
}
