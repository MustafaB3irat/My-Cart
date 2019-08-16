package com.example.icart.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.icart.R;
import com.example.icart.databinding.SettingsBinding;
import com.example.icart.interfaces.Settings;

public class SettingsActivity extends AppCompatActivity implements Settings.SettingsView {

    private SettingsBinding settingsBinding;

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

    }

    @Override
    public void initChangeCurrency() {

    }

    @Override
    public void initTextSeekBar() {

    }

}
