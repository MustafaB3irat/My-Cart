package com.example.icart.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.icart.R;
import com.example.icart.databinding.SettingsBinding;
import com.example.icart.interfaces.Settings;
import com.example.icart.views.dialogs.LockFeatureDialog;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.CountryCurrencyPicker;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;
import com.scrounger.countrycurrencypicker.library.PickerType;

public class SettingsActivity extends AppCompatActivity implements Settings.SettingsView {

    private SettingsBinding settingsBinding;
    private static final String SHARED_PREF = "sharedpref";
    private final static String IS_CHECKED = "switch";
    private SharedPreferences sharedPreferences;

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

        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

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

        settingsBinding.currencyButton.setOnClickListener(view -> {
            CountryCurrencyPicker pickerDialog = CountryCurrencyPicker.newInstance(PickerType.COUNTRYandCURRENCY, new CountryCurrencyPickerListener() {
                @Override
                public void onSelectCountry(Country country) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    if (country.getCurrency() == null) {

                        editor.putString("currency", country.getCode());
                        editor.apply();
                        MainActivity.currency = country.getCode();
                    } else {
                        editor.putString("currency", country.getCurrency().getSymbol());
                        editor.apply();
                        MainActivity.currency = country.getCurrency().getSymbol();
                    }

                    Intent intent = getIntent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(intent);
                    overridePendingTransition(0,0);

                    Toast.makeText(SettingsActivity.this, getResources().getString(R.string.update_currency_sucessfullr), Toast.LENGTH_SHORT).show();



                }

                @Override
                public void onSelectCurrency(Currency currency) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("currency", currency.getSymbol());
                    editor.apply();
                    MainActivity.currency = currency.getSymbol();

                    Intent intent = getIntent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(intent);
                    overridePendingTransition(0,0);

                    Toast.makeText(SettingsActivity.this, getResources().getString(R.string.update_currency_sucessfullr), Toast.LENGTH_SHORT).show();

                }
            });

            pickerDialog.show(getSupportFragmentManager(), CountryCurrencyPicker.DIALOG_NAME);
        });


    }

    @Override
    public void initTextSeekBar() {

    }

}
