package com.example.icart.interfaces;

import android.database.Cursor;

import com.example.icart.models.data.Element;

import java.util.List;

public interface Elements {


    interface ElementsView {


        void setRecyclerViewAdapter(List<Element> elementList);

        void initBackButton();

        void initAddElement();
    }

    interface ElementsPresenter {

        void getElements(String categoryName);
    }

    interface ElementsModel {

        Cursor getElements(String categoryName);
    }
}
