package com.example.icart.presenters;

import android.database.Cursor;

import com.example.icart.interfaces.Categories;
import com.example.icart.models.CategoryModel;
import com.example.icart.models.data.Catagory;
import com.example.icart.views.fragments.CategoriesFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPresenter implements Categories.CategoriesPresenter {


    private Categories.CategoriesView view;
    private Categories.CategoriesModel model;


    public CategoriesPresenter(CategoriesFragment view) {
        this.view = view;
        this.model = new CategoryModel(view);
    }

    @Override
    public void getCategories() {
        Cursor cursor = model.getCategories();

        List<Catagory> catagories = new ArrayList<>();

        while (cursor.moveToNext()) {
            catagories.add(new Catagory(cursor.getString(cursor.getColumnIndex("catagory_name")),
                    cursor.getString(cursor.getColumnIndex("catagory_avatar")), cursor.getString(cursor.getColumnIndex("created_at"))));
        }

        view.setRecyclerViewAdapter(catagories);
    }
}
