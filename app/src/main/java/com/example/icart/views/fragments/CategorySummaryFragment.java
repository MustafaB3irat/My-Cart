package com.example.icart.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.icart.R;
import com.example.icart.adapters.CategorySummaryAdapter;
import com.example.icart.databinding.CategorySummaryFragmentBinding;
import com.example.icart.interfaces.CategorySummary;
import com.example.icart.presenters.CategorySummaryPresenter;

import java.util.List;

public class CategorySummaryFragment extends Fragment implements CategorySummary.CateogrySummaryFragment {


    private CategorySummaryFragmentBinding categorySummaryFragmentBinding;
    private CategorySummary.CategorySummaryPresneter categorySummaryPresneter;
    private CategorySummaryAdapter categorySummaryAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        categorySummaryFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.category_summary_fragment, container, false);
        categorySummaryPresneter = new CategorySummaryPresenter(this);

        categorySummaryPresneter.getCategories();

        return categorySummaryFragmentBinding.getRoot();
    }

    @Override
    public void setRecyclerViewAdapter(List<com.example.icart.models.data.CategorySummary> catagoryList) {


        categorySummaryAdapter = new CategorySummaryAdapter(catagoryList);
        categorySummaryFragmentBinding.categoriesRecyclerview.setAdapter(categorySummaryAdapter);
        categorySummaryFragmentBinding.categoriesRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));

    }
}
