package com.example.icart.interfaces;

import android.database.Cursor;

import java.util.List;

public interface MostConsuming {


    interface MostConsumingFragment {

        void setRecyclerViewAdapter(List<com.example.icart.models.data.MostConsuming> mostConsumings);

        void initSortButton();
    }

    interface MostConsumingModel {

        Cursor getElements();
    }

    interface MostConsumingPresenter {

        void getElements();

        void reverseList();
    }
}
