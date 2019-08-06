package com.example.icart.views.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.icart.R;
import com.example.icart.customListeners.MyTextWatcher;
import com.example.icart.databinding.AddElementDialogBinding;
import com.example.icart.interfaces.AddElement;
import com.example.icart.presenters.AddElementPresenter;

import java.util.ArrayList;
import java.util.List;

public class AddElementDialog extends DialogFragment implements AddElement.AddElementDialog {

    private AddElementDialogBinding addElementDialogBinding;
    private AlertDialog.Builder builder;
    private AddElement.AddElementPresenter presenter;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        addElementDialogBinding = DataBindingUtil.inflate(inflater, R.layout.add_element_dialog, null, false);
        presenter = new AddElementPresenter(this);

        builder.setView(addElementDialogBinding.getRoot()).setTitle("");


        initGetCategories();
        initOnTextChanged();
        initAddButton();
        calculateTotalPriceOnTextChanged();

        return builder.create();
    }

    @Override
    public void initAddButton() {

        addElementDialogBinding.addElementButton.setOnClickListener(view -> {

            if (validate()) {

                float totalPrice = Float.parseFloat(addElementDialogBinding.elementPrice.getText().toString()) * Integer.parseInt(addElementDialogBinding.elementQuantity.getText().toString());

                if (
                        presenter.addElement(addElementDialogBinding.categoryNames.getSelectedItem().toString(), addElementDialogBinding.elementName.getText().toString(),
                                Float.parseFloat(addElementDialogBinding.elementPrice.getText().toString()),
                                Integer.parseInt(addElementDialogBinding.elementQuantity.getText().toString()), totalPrice)
                ) {
                    Toast.makeText(this.getContext(), getResources().getString(R.string.added_element_successfully), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this.getContext(), getResources().getString(R.string.added_element_error), Toast.LENGTH_SHORT).show();
                }

            }

        });


    }

    @Override
    public boolean validate() {

        boolean isValid = true;


        if (addElementDialogBinding.categoryNames.getSelectedItem() == null || TextUtils.isEmpty(addElementDialogBinding.categoryNames.getSelectedItem().toString())) {
            addElementDialogBinding.categoryNamesError.setText(getResources().getString(R.string.category_names_isEmpty));
            isValid = false;
        }


        if (TextUtils.isEmpty(addElementDialogBinding.elementName.getText().toString())) {
            addElementDialogBinding.elementNameError.setText(getResources().getString(R.string.element_name_isEmpty));
            isValid = false;
        }


        if (TextUtils.isEmpty(addElementDialogBinding.elementPrice.getText().toString())) {
            addElementDialogBinding.elementPriceError.setText(getResources().getString(R.string.element_price_isEmpty));
            isValid = false;
        }


        if (TextUtils.isEmpty(addElementDialogBinding.elementQuantity.getText().toString())) {
            addElementDialogBinding.elementQuantityError.setText(getResources().getString(R.string.element_quantity_isEmpty));
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void initOnTextChanged() {

        addElementDialogBinding.categoryNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                addElementDialogBinding.categoryNamesError.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        addElementDialogBinding.elementName.addTextChangedListener(new MyTextWatcher(() -> {

            addElementDialogBinding.elementNameError.setText("");

        }));


        addElementDialogBinding.elementPrice.addTextChangedListener(new MyTextWatcher(() -> {

            addElementDialogBinding.elementPriceError.setText("");

        }));


        addElementDialogBinding.elementQuantity.addTextChangedListener(new MyTextWatcher(() -> {

            addElementDialogBinding.elementQuantityError.setText("");

        }));


    }

    @Override
    public void calculateTotalPriceOnTextChanged() {


        addElementDialogBinding.elementQuantity.addTextChangedListener(new MyTextWatcher(() -> {

            if (!TextUtils.isEmpty(addElementDialogBinding.elementQuantity.getText()) && !TextUtils.isEmpty(addElementDialogBinding.elementPrice.getText())) {

                float totalPrice = Float.parseFloat(addElementDialogBinding.elementPrice.getText().toString()) * Integer.parseInt(addElementDialogBinding.elementQuantity.getText().toString());
                String totalPriceString = String.valueOf(totalPrice);
                addElementDialogBinding.elementTotalPrice.setText(totalPriceString);
            }

        }));

    }

    @Override
    public void initGetCategories() {

        Cursor categories = presenter.getCategories();
        List<String> categoriesArraylist = new ArrayList<>();

        while (categories.moveToNext()) {
            categoriesArraylist.add(categories.getString(categories.getColumnIndex("catagory_name")));
        }

        String[] categoriesList = new String[categoriesArraylist.size()];
        for (int i = 0; i < categoriesArraylist.size(); i++)
            categoriesList[i] = categoriesArraylist.get(i);

        Spinner s = addElementDialogBinding.categoryNames;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_item, categoriesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }


}
