package com.example.icart.views.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.icart.R;
import com.example.icart.customListeners.MyTextWatcher;
import com.example.icart.databinding.LockLayoutBinding;
import com.example.icart.interfaces.Singin;

public class SigninDialog extends DialogFragment implements Singin.SignInDialog {


    private LockLayoutBinding lockLayoutBinding;
    private SharedPreferences sharedPreferences;

    private static final String SHARED_PREF = "sharedpref";
    private static final String PASSWORD = "password";
    private static final String HINT = "hint";
    private AlertDialog.Builder builder;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        builder = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);


        LayoutInflater inflater = getActivity().getLayoutInflater();
        lockLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.lock_layout, null, false);

        builder.setView(lockLayoutBinding.getRoot());
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);


        initsignInButton();
        initOnTextChanged();

        return builder.create();
    }

    @Override
    public void initsignInButton() {

        lockLayoutBinding.signin.setOnClickListener(view -> {

            if (validate()) {

                if (lockLayoutBinding.password.getText().equals(sharedPreferences.getString(PASSWORD, ""))) {
                    this.dismiss();
                } else {

                    lockLayoutBinding.passwordError.setText(getResources().getString(R.string.password_error));
                }

            }


        });
    }

    @Override
    public void initOnTextChanged() {

        lockLayoutBinding.password.addTextChangedListener(new MyTextWatcher(() -> {

            lockLayoutBinding.passwordError.setText("");

        }));

    }

    @Override
    public boolean validate() {

        if (TextUtils.isEmpty(lockLayoutBinding.password.getText())) {

            lockLayoutBinding.passwordError.setText(getResources().getString(R.string.enter_password));

            return false;
        }
        return true;
    }
}
