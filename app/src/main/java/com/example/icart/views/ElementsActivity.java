package com.example.icart.views;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.icart.R;
import com.example.icart.adapters.ElementsAdapter;
import com.example.icart.databinding.ElementsLayoutBinding;
import com.example.icart.interfaces.Elements;
import com.example.icart.models.data.Element;
import com.example.icart.presenters.ElementsPresenter;

import java.util.List;

public class ElementsActivity extends AppCompatActivity implements Elements.ElementsView {


    private Elements.ElementsPresenter presenter;
    private ElementsLayoutBinding elementsLayoutBinding;
    private ElementsAdapter elementsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        elementsLayoutBinding = DataBindingUtil.setContentView(this, R.layout.elements_layout);
        presenter = new ElementsPresenter(this);

        presenter.getElements(getIntent().getStringExtra("category"));
        initBackButton();


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setRecyclerViewAdapter(List<Element> elementList) {

        float total_price = 0;

        for (Element element : elementList) {
            total_price += Float.parseFloat(element.getTotal());
        }


        elementsAdapter = new ElementsAdapter(elementList, this);
        elementsLayoutBinding.elementsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        elementsLayoutBinding.elementsRecyclerview.setAdapter(elementsAdapter);

        elementsLayoutBinding.totalMoney.setText(getResources().getString(R.string.total_price1) + " " + String.valueOf(total_price) + MainActivity.currency);
    }

    @Override
    public void initBackButton() {
        elementsLayoutBinding.back.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    public void initAddElement() {
        elementsLayoutBinding.add.setOnClickListener(view->{

        });
    }
}
