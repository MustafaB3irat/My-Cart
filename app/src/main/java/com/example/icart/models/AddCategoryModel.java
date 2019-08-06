package com.example.icart.models;

import com.example.icart.interfaces.AddCategory;
import com.example.icart.interfaces.DatabaseHelper;
import com.example.icart.models.data.Catagory;
import com.example.icart.views.dialogs.AddCategoryDialog;

import java.sql.Timestamp;
import java.util.Date;

public class AddCategoryModel implements AddCategory.AddCategoryModel {


    private DatabaseHelperModel databaseHelperModel;

    public AddCategoryModel(AddCategoryDialog dialog) {

        databaseHelperModel = new DatabaseHelperModel(dialog.getContext());
    }

    @Override
    public boolean addCategory(String categoryName ,String category_avatar) {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        String created_at = timestamp.toString().split(":")[0] + ":" + timestamp.toString().split(":")[1];
        return databaseHelperModel.addCategory(new Catagory(categoryName, category_avatar, created_at));
    }
}