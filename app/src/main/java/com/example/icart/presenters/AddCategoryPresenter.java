package com.example.icart.presenters;

import com.example.icart.interfaces.AddCategory;
import com.example.icart.models.AddCategoryModel;
import com.example.icart.views.dialogs.AddCategoryDialog;

public class AddCategoryPresenter implements AddCategory.AddCategoryPresenter {

    private AddCategory.AddCategoryModel addCategoryModel;
    private AddCategory.AddCategoryDialog addCategoryDialog;


    public AddCategoryPresenter(AddCategoryDialog addCategoryDialog) {
        this.addCategoryDialog = addCategoryDialog;
        this.addCategoryModel = new AddCategoryModel(addCategoryDialog);
    }


    @Override
    public boolean addCategory(String categoryName, String category_avatar) {
        return addCategoryModel.addCategory(categoryName,category_avatar);
    }
}
