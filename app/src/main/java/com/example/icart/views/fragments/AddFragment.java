package com.example.icart.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.icart.R;
import com.example.icart.databinding.AddLayoutBinding;
import com.example.icart.interfaces.Add;
import com.example.icart.interfaces.AddElement;
import com.example.icart.views.dialogs.AddCategoryDialog;
import com.example.icart.views.dialogs.AddElementDialog;

public class AddFragment extends Fragment implements Add.AddFragment {

    private AddLayoutBinding addLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        addLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.add_layout, container, false);


        initAddButtons();
        return addLayoutBinding.getRoot();


    }

    @Override
    public void initAddButtons() {

        addLayoutBinding.addElement.setOnClickListener(view -> {

            AddElementDialog dialog = new AddElementDialog();
            dialog.show(getFragmentManager(), "Add New Element");
        });

        addLayoutBinding.addCategory.setOnClickListener(view -> {

            AddCategoryDialog addCategoryDialog = new AddCategoryDialog();
            addCategoryDialog.show(getFragmentManager(), "Add New Category");
        });

        addLayoutBinding.addHome.setOnClickListener(view -> {

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragments, new CategoriesFragment()).commitNow();
        });


    }
}
