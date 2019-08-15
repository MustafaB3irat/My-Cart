package com.example.icart.presenters;

import android.database.Cursor;
import com.example.icart.interfaces.CategorySummary;
import com.example.icart.models.CategorySummaryModel;
import com.example.icart.views.fragments.CategorySummaryFragment;
import java.util.ArrayList;
import java.util.List;

public class CategorySummaryPresenter implements CategorySummary.CategorySummaryPresneter {


    private CategorySummary.CategorySummaryModel categorySummaryModel;
    private CategorySummaryFragment view;


    public CategorySummaryPresenter(CategorySummaryFragment categorySummaryFragment) {

        this.categorySummaryModel = new CategorySummaryModel(categorySummaryFragment.getActivity());

        this.view = categorySummaryFragment;
    }

    @Override
    public void getCategories() {

        Cursor cursor = categorySummaryModel.getCategories();

        List<com.example.icart.models.data.CategorySummary> catagories = new ArrayList<>();

        while (cursor.moveToNext()) {

            catagories.add(new com.example.icart.models.data.CategorySummary(cursor.getString(cursor.getColumnIndex("catagory_name")), cursor.getString(cursor.getColumnIndex("total"))));

        }

        view.setRecyclerViewAdapter(catagories);

    }
}
