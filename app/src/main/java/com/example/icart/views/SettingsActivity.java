package com.example.icart.views;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.icart.R;
import com.example.icart.databinding.SettingsBinding;
import com.example.icart.interfaces.LockFeature;
import com.example.icart.interfaces.Settings;
import com.example.icart.views.dialogs.LockFeatureDialog;
import com.suke.widget.SwitchButton;

public class SettingsActivity extends AppCompatActivity implements Settings.SettingsView {

    private SettingsBinding settingsBinding;
    private static final String SHARED_PREF = "sharedpref";
    private final static String IS_CHECKED = "switch";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settingsBinding = DataBindingUtil.setContentView(this, R.layout.settings);

        initBackButton();
        initChangeCurrency();
        initLockFeatureSwitch();
        initTextSeekBar();
    }

    @Override
    public void initBackButton() {

        settingsBinding.back.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    public void initLockFeatureSwitch() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

        settingsBinding.lockSwitch.setChecked(sharedPreferences.getBoolean(IS_CHECKED, false));

        settingsBinding.lockSwitch.setOnCheckedChangeListener((view, isChecked) -> {

            if (isChecked) {

                LockFeatureDialog dialog = new LockFeatureDialog();
                dialog.show(getSupportFragmentManager(), "Activate Lock Feature");
            } else {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean(IS_CHECKED, false);
                editor.apply();
            }

        });

    }

    @Override
    public void initChangeCurrency() {

    }

    @Override
    public void initTextSeekBar() {

    }

}
