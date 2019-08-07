package com.example.icart.interfaces;

import android.database.Cursor;

import com.example.icart.models.data.Catagory;

import java.util.List;

public interface Categories {

    interface CategoriesView {

        void setRecyclerViewAdapter(List<Catagory> catagoryList);
    }

    interface CategoriesPresenter {

        void getCategories();

    }

    interface CategoriesModel {

        Cursor getCategories();
    }
}
