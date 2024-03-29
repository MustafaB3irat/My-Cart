package com.example.icart.models;

import com.crashlytics.android.Crashlytics;
import com.example.icart.interfaces.AddCategory;
import com.example.icart.models.data.Catagory;
import com.example.icart.views.dialogs.AddCategoryDialog;

import java.sql.Timestamp;
import java.util.Date;

public class AddCategoryModel implements AddCategory.AddCategoryModel {


    private DatabaseHelpers databaseHelpersModel;

    public AddCategoryModel(AddCategoryDialog dialog) {

        databaseHelpersModel = new DatabaseHelpers(dialog.getContext());
    }

    @Override
    public boolean addCategory(String categoryName, String category_avatar) {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        String created_at = timestamp.toString().split(":")[0] + ":" + timestamp.toString().split(":")[1];
        return databaseHelpersModel.addCategory(new Catagory(categoryName, category_avatar, created_at));
    }



    @Override
    public boolean updateCategory(String categoryName, String newCategoryName) {

        try {


            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());

            String created_at = timestamp.toString().split(":")[0] + ":" + timestamp.toString().split(":")[1];

            Catagory catagory = new Catagory(newCategoryName, "default", created_at);

            return databaseHelpersModel.editCategory(categoryName, catagory);

        } catch (Exception e) {
            Crashlytics.getInstance().crash();
        }

        return false;
    }
}
