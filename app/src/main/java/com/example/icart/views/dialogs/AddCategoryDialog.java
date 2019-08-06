package com.example.icart.views.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.icart.R;
import com.example.icart.customListeners.MyTextWatcher;
import com.example.icart.databinding.AddCategoryDialogBinding;
import com.example.icart.interfaces.AddCategory;
import com.example.icart.presenters.AddCategoryPresenter;
import com.example.icart.presenters.AddElementPresenter;

public class AddCategoryDialog extends DialogFragment implements AddCategory.AddCategoryDialog {


    private AlertDialog.Builder builder;
    private AddCategoryDialogBinding addElementDialogBinding;
    private AddCategory.AddCategoryPresenter presenter;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        addElementDialogBinding = DataBindingUtil.inflate(inflater, R.layout.add_category_dialog, null, false);
        presenter = new AddCategoryPresenter(this);

        builder.setView(addElementDialogBinding.getRoot()).setTitle("");

        initOnTextChanged();
        initAddButton();


        return builder.create();
    }

    @Override
    public void initAddButton() {

        addElementDialogBinding.addCategoryButton.setOnClickListener(view -> {

            if (validate()) {
                if (
                        presenter.addCategory(addElementDialogBinding.categoryName.getText().toString(), "default")
                ) {
                    Toast.makeText(this.getContext(), getResources().getString(R.string.added_category_successfully), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this.getContext(), getResources().getString(R.string.added_category_error), Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    @Override
    public boolean validate() {

        boolean isValid = true;

        if (TextUtils.isEmpty(addElementDialogBinding.categoryName.getText())) {
            isValid = false;
            addElementDialogBinding.categoryNameError.setText(getResources().getString(R.string.category_name_isEmpty));
        }
        return isValid;
    }

    @Override
    public void initOnTextChanged() {

        addElementDialogBinding.categoryName.addTextChangedListener(new MyTextWatcher(() -> {
            addElementDialogBinding.categoryNameError.setText("");
        }));

    }
}
