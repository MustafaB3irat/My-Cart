package com.example.icart.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.icart.R;
import com.example.icart.adapters.MostConsumingAdapter;
import com.example.icart.databinding.MostConsumingFragmentBinding;
import com.example.icart.interfaces.MostConsuming;
import com.example.icart.presenters.MostConsumingPresenter;

import java.util.List;

public class MostConsumingFragment extends Fragment implements MostConsuming.MostConsumingFragment {


    private MostConsumingFragmentBinding mostConsumingFragmentBinding;
    private MostConsuming.MostConsumingPresenter presenter;
    private MostConsumingAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mostConsumingFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.most_consuming_fragment, container, false);
        presenter = new MostConsumingPresenter(this);

        presenter.getElements();

        initSortButton();


        return mostConsumingFragmentBinding.getRoot();
    }

    @Override
    public void setRecyclerViewAdapter(List<com.example.icart.models.data.MostConsuming> mostConsumings) {

        adapter = new MostConsumingAdapter(mostConsumings, this);

        mostConsumingFragmentBinding.mostConsumingRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mostConsumingFragmentBinding.mostConsumingRecyclerview.setAdapter(adapter);

    }

    @Override
    public void initSortButton() {

        mostConsumingFragmentBinding.sortIcon.setOnClickListener(view -> {

            if (mostConsumingFragmentBinding.sortText.getText().toString().equals(getResources().getString(R.string.expensive)))
                mostConsumingFragmentBinding.sortText.setText(R.string.cheapest);


            else if (mostConsumingFragmentBinding.sortText.getText().toString().equals(getResources().getString(R.string.cheapest)))
                mostConsumingFragmentBinding.sortText.setText(R.string.expensive);


            presenter.reverseList();
        });
    }
}
