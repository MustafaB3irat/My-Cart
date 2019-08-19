package com.example.icart.interfaces;

import android.view.View;

public interface Main {


    interface MainModel {
    }

    interface MainPresenter {
    }

    interface MainView {

        void initMenuButton();

        void initAddButton();

        void initNavigationDrawerMenItems();

        void moveToCategoriesFragment();


    }
}
