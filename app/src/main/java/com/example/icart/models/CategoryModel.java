package com.example.icart.models;

import android.database.Cursor;

import com.example.icart.interfaces.Categories;
import com.example.icart.views.fragments.CategoriesFragment;

public class CategoryModel implements Categories.CategoriesModel {


    private DatabaseHelpers database;


    public CategoryModel(CategoriesFragment fragment) {
        this.database = new DatabaseHelpers(fragment.getContext());
    }

    @Override
    public Cursor getCategories() {
        return database.getCategories();
    }
}
