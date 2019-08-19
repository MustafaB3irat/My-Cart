package com.example.icart.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.SeekBar;
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
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

public class SettingsActivity extends AppCompatActivity implements Settings.SettingsView {

    private SettingsBinding settingsBinding;
    private static final String SHARED_PREF = "sharedpref";
    private final static String IS_CHECKED = "switch";
    private static final String FONT_SIZE = "fontsize";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settingsBinding = DataBindingUtil.setContentView(this, R.layout.settings);

        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.toolbar, null));
        }


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
                    overridePendingTransition(0, 0);

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
                    overridePendingTransition(0, 0);

                    Toast.makeText(SettingsActivity.this, getResources().getString(R.string.update_currency_sucessfullr), Toast.LENGTH_SHORT).show();

                }
            });

            pickerDialog.show(getSupportFragmentManager(), CountryCurrencyPicker.DIALOG_NAME);
        });


    }

    @Override
    public void initTextSeekBar() {


        if (sharedPreferences != null) {
            settingsBinding.textSizeSeekbar.setProgress(sharedPreferences.getFloat(FONT_SIZE, 20));
        }

        settingsBinding.textSizeSeekbar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

                settingsBinding.currencyText.setTextSize(TypedValue.COMPLEX_UNIT_SP, seekBar.getProgress());
                settingsBinding.lockFeatureText.setTextSize(TypedValue.COMPLEX_UNIT_SP, seekBar.getProgress());
                settingsBinding.settingsText.setTextSize(TypedValue.COMPLEX_UNIT_SP, seekBar.getProgress());
                settingsBinding.textSizeText.setTextSize(TypedValue.COMPLEX_UNIT_SP, seekBar.getProgress());

                seekBar.setProgress(seekBar.getProgressFloat());


                SharedPreferences prefs = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
                SharedPreferences.Editor ed = prefs.edit();
                if (seekBar.getProgress() != 20) {
                    ed.putFloat(FONT_SIZE, seekBar.getProgress());
                    ed.apply();
                } else {
                    ed.remove(FONT_SIZE).apply();
                }

            }
        });

    }

}
