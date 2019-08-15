package com.example.icart.presenters;

import android.database.Cursor;

import androidx.fragment.app.FragmentActivity;

import com.example.icart.interfaces.MostConsuming;
import com.example.icart.models.MostConsumingModel;
import com.example.icart.views.fragments.MostConsumingFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MostConsumingPresenter implements MostConsuming.MostConsumingPresenter {

    private MostConsumingFragment activity;
    private MostConsuming.MostConsumingModel mostConsumingModel;

    List<com.example.icart.models.data.MostConsuming> mostConsumings;


    public MostConsumingPresenter(MostConsumingFragment activity) {
        this.activity = activity;

        mostConsumingModel = new MostConsumingModel(activity.getActivity());
    }

    @Override
    public void getElements() {

        Cursor cursor = mostConsumingModel.getElements();

        List<com.example.icart.models.data.MostConsuming> mostConsumings = new ArrayList<>();

        this.mostConsumings = mostConsumings;

        while (cursor.moveToNext()) {


            if (Integer.parseInt(cursor.getString(cursor.getColumnIndex("numOfTimes"))) > 1)
                mostConsumings.add(new com.example.icart.models.data.MostConsuming(cursor.getString(cursor.getColumnIndex("element_name")), cursor.getString(cursor.getColumnIndex("numOfTimes")), cursor.getString(cursor.getColumnIndex("totalSum"))));
        }

        activity.setRecyclerViewAdapter(mostConsumings);


    }

    @Override
    public void reverseList() {

        Collections.reverse(mostConsumings);

        activity.setRecyclerViewAdapter(mostConsumings);

    }
}
