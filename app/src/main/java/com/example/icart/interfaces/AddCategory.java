package com.example.icart.interfaces;

public interface AddCategory {
    interface AddCategoryDialog {

        void initAddButton();

        boolean validate();

        void initOnTextChanged();

    }

    interface AddCategoryModel {

        boolean addCategory(String categoryName , String category_avatar);
    }

    interface AddCategoryPresenter {

        boolean addCategory(String categoryName , String category_avatar);
    }
}
