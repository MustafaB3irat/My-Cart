package com.example.icart.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.icart.R;
import com.example.icart.adapters.CataegoryAdpater;
import com.example.icart.customCallbacks.MyItemTouchHelperSimpleCallBack;
import com.example.icart.databinding.CategoriesLayoutBinding;
import com.example.icart.interfaces.Categories;
import com.example.icart.models.data.Catagory;
import com.example.icart.presenters.CategoriesPresenter;

import java.util.List;

public class CategoriesFragment extends Fragment implements Categories.CategoriesView {

    private Categories.CategoriesPresenter presenter;
    private CategoriesLayoutBinding categoriesLayoutBinding;
    private CataegoryAdpater cataegoryAdpater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        categoriesLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.categories_layout, container, false);
        presenter = new CategoriesPresenter(this);

        presenter.getCategories();

        return categoriesLayoutBinding.getRoot();
    }

    @Override
    public void setRecyclerViewAdapter(List<Catagory> catagoryList) {

        cataegoryAdpater = new CataegoryAdpater(catagoryList, this);
        categoriesLayoutBinding.categoriesRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));
        categoriesLayoutBinding.categoriesRecyclerview.setAdapter(cataegoryAdpater);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchHelperSimpleCallBack(cataegoryAdpater));
        itemTouchHelper.attachToRecyclerView(categoriesLayoutBinding.categoriesRecyclerview);

    }
}
