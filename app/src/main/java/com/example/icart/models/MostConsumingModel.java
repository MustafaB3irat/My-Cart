package com.example.icart.models;

import android.database.Cursor;

import androidx.fragment.app.FragmentActivity;

import com.example.icart.interfaces.DatabaseHelper;
import com.example.icart.interfaces.MostConsuming;

public class MostConsumingModel implements MostConsuming.MostConsumingModel {


    private DatabaseHelpers databaseHelpers;


    public MostConsumingModel(FragmentActivity fragmentActivity) {

        databaseHelpers = new DatabaseHelpers(fragmentActivity);
    }

    @Override
    public Cursor getElements() {
        return databaseHelpers.getMostConsuming();
    }
}
