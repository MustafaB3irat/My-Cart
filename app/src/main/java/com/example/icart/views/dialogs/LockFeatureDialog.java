package com.example.icart.views.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.icart.R;
import com.example.icart.customListeners.MyTextWatcher;
import com.example.icart.databinding.LockFeatureDialogBinding;
import com.example.icart.interfaces.LockFeature;

public class LockFeatureDialog extends DialogFragment implements LockFeature.LockFeatureDialog {


    private AlertDialog.Builder builder;
    private LockFeatureDialogBinding lockFeatureDialogBinding;
    private SharedPreferences sharedPreferences;
    private final static String IS_CHECKED = "switch";
    private static final String SHARED_PREF = "sharedpref";
    private static final String PASSWORD = "password";
    private static final String HINT = "hint";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        builder = new AlertDialog.Builder(getContext());


        LayoutInflater inflater = getActivity().getLayoutInflater();
        lockFeatureDialogBinding = DataBindingUtil.inflate(inflater, R.layout.lock_feature_dialog, null, false);

        builder.setView(lockFeatureDialogBinding.getRoot());
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);


        initActivateButton();
        initTextChangeListener();

        return builder.create();
    }

    @Override
    public boolean validate() {

        if (TextUtils.isEmpty(lockFeatureDialogBinding.passwordText.getText())) {
            lockFeatureDialogBinding.passwordError.setText(getResources().getString(R.string.enter_password_error));

            return false;
        }
        return true;
    }

    @Override
    public void initActivateButton() {


        lockFeatureDialogBinding.activate.setOnClickListener(view -> {

            if (validate()) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean(IS_CHECKED, true);
                editor.putString(PASSWORD, lockFeatureDialogBinding.password.getText().toString());
                editor.putString(HINT, lockFeatureDialogBinding.hint.getText().toString());

                editor.apply();

                dismiss();

                Toast.makeText(getContext(), getResources().getString(R.string.activate_lock_feature_successfully), Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public void initTextChangeListener() {

        lockFeatureDialogBinding.password.addTextChangedListener(new MyTextWatcher(() -> {

            lockFeatureDialogBinding.passwordError.setText("");
        }));
    }
}
