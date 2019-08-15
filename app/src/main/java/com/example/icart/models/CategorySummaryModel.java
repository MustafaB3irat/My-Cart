package com.example.icart.models;

import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.icart.interfaces.CategorySummary;

public class CategorySummaryModel implements CategorySummary.CategorySummaryModel {


    private DatabaseHelpers databaseHelpers;


    private FragmentActivity activity;

    public CategorySummaryModel(FragmentActivity activity) {
        this.activity = activity;

        this.databaseHelpers = new DatabaseHelpers(activity);
    }

    @Override
    public Cursor getCategories() {
        return databaseHelpers.getCategorySummary();
    }
}
