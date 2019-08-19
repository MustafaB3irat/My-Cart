package com.example.icart.views.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
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
import com.example.icart.models.data.Element;
import com.example.icart.presenters.AddElementPresenter;
import com.example.icart.views.ElementsActivity;

import java.util.ArrayList;
import java.util.List;

public class AddElementDialog extends DialogFragment implements AddElement.AddElementDialog {

    private AddElementDialogBinding addElementDialogBinding;
    private AlertDialog.Builder builder;
    private AddElement.AddElementPresenter presenter;
    private Element oldElement;
    private String oldCategory;
    private String CategoryName;


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


        if (this.getTag().equals("Update Element")) {
            addElementDialogBinding.categoryNames.setVisibility(View.GONE);
            addElementDialogBinding.categoryNameText.setVisibility(View.GONE);
            addElementDialogBinding.categoryNames.setSelection(((ArrayAdapter) addElementDialogBinding.categoryNames.getAdapter()).getPosition(oldCategory));
            addElementDialogBinding.elementName.setText(oldElement.getName());
            addElementDialogBinding.elementTotalPrice.setText(oldElement.getTotal());
            addElementDialogBinding.elementQuantity.setText(oldElement.getQuantity());
            addElementDialogBinding.elementPrice.setText(oldElement.getPrice());
            addElementDialogBinding.eid.setText(oldElement.getEid());

            addElementDialogBinding.addElementButton.setText(getResources().getString(R.string.edit));

        }

        if (CategoryName != null) {
            addElementDialogBinding.categoryNames.setSelection(((ArrayAdapter) addElementDialogBinding.categoryNames.getAdapter()).getPosition(CategoryName));
            addElementDialogBinding.categoryNames.setVisibility(View.GONE);
            addElementDialogBinding.categoryNameText.setVisibility(View.GONE);
        }


        return builder.create();
    }

    @Override
    public void initAddButton() {

        addElementDialogBinding.addElementButton.setOnClickListener(view -> {

            if (validate()) {

                float totalPrice = Float.parseFloat(addElementDialogBinding.elementPrice.getText().toString()) * Integer.parseInt(addElementDialogBinding.elementQuantity.getText().toString());


                if (this.getTag().equals("Update Element")) {


                    if (presenter.editElement(addElementDialogBinding.elementName.getText().toString(),
                            Float.parseFloat(addElementDialogBinding.elementPrice.getText().toString()),
                            Integer.parseInt(addElementDialogBinding.elementQuantity.getText().toString()), totalPrice, oldElement.getEid(), oldCategory)) {
                        dismiss();

                        Intent intent = new Intent(getActivity(), getActivity().getClass());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("category", oldCategory);
                        startActivity(intent);
                        getActivity().overridePendingTransition(0, 0);
                        getActivity().finish();

                        Toast.makeText(this.getContext(), getResources().getString(R.string.update_element_successfully), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this.getContext(), getResources().getString(R.string.update_element_error), Toast.LENGTH_SHORT).show();
                    }


                } else {

                    if (presenter.addElement(addElementDialogBinding.categoryNames.getSelectedItem().toString(), addElementDialogBinding.elementName.getText().toString(),
                            Float.parseFloat(addElementDialogBinding.elementPrice.getText().toString()),
                            Integer.parseInt(addElementDialogBinding.elementQuantity.getText().toString()), totalPrice, addElementDialogBinding.eid.getText().toString())) {
                        dismiss();

                        if (CategoryName != null) {
                            Intent intent = getActivity().getIntent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            intent.putExtra("category", CategoryName);
                            getActivity().finish();
                            startActivity(intent);
                            getActivity().overridePendingTransition(0, 0);
                        }


                        Toast.makeText(this.getContext(), getResources().getString(R.string.added_element_successfully), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this.getContext(), getResources().getString(R.string.added_element_error), Toast.LENGTH_SHORT).show();
                    }

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
        adapter.setDropDownViewResource(R.layout.spinner_text);
        s.setAdapter(adapter);
    }

    @Override
    public void setOldElementForEdit(Element oldElementForEdit, String categoryName) {

        this.oldCategory = categoryName;
        this.oldElement = oldElementForEdit;
    }


    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
