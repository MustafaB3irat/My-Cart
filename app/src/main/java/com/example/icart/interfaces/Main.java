package com.example.icart.interfaces;

import android.view.View;

public interface Main {


    interface MainModel {
    }

    interface MainPresenter {
    }

    interface MainView {

        void initMenuButton();
        void initMultiMenuButton();
        void initNavigationDrawerMenItems();

        void moveToCategoriesFragment();

        View.OnClickListener initAddButtonListener();
        View.OnClickListener initRemoveButtonListener();
        View.OnClickListener initEditButtonListener();

    }
}
