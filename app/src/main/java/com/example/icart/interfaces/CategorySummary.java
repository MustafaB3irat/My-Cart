package com.example.icart.interfaces;

import android.database.Cursor;

import java.util.List;

public interface CategorySummary {

    interface CateogrySummaryFragment {

        void setRecyclerViewAdapter(List<com.example.icart.models.data.CategorySummary> catagoryList);
    }

    interface CategorySummaryPresneter {

        void getCategories();
    }

    interface CategorySummaryModel {

        Cursor getCategories();
    }
}
