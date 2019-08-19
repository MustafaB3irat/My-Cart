package com.example.icart.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import com.example.icart.R;
import com.example.icart.databinding.ActivityMainBinding;
import com.example.icart.interfaces.Main;
import com.example.icart.views.dialogs.SigninDialog;
import com.example.icart.views.fragments.AboutApp;
import com.example.icart.views.fragments.AddFragment;
import com.example.icart.views.fragments.CategoriesFragment;
import com.example.icart.views.fragments.CategorySummaryFragment;
import com.example.icart.views.fragments.MostConsumingFragment;
public class MainActivity extends AppCompatActivity implements Main.MainView {

    private ActivityMainBinding activityMainBinding;
    public Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    private final static String IS_CHECKED = "switch";
    private static final String SHARED_PREF = "sharedpref";
    private static final String CURRENCY = "currency";
    public static String currency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.sidebar.inflateHeaderView(R.layout.sidebar_header);

        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

        if (sharedPreferences != null) {
            if (sharedPreferences.getBoolean(IS_CHECKED, false) && currency == null) {
                SigninDialog signinDialog = new SigninDialog();
                signinDialog.setCancelable(false);
                signinDialog.show(getSupportFragmentManager(), "sign in");
            }

            currency = sharedPreferences.getString(CURRENCY, "USD");

        } else {
            currency = "USD";
        }

        moveToCategoriesFragment();
        initNavigationDrawerMenItems();
        setSupportActionBar(activityMainBinding.toolbar);
        initAddButton();
        initMenuButton();


        toolbar = activityMainBinding.toolbar;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.toolbar, null));
        }


    }

    @Override
    public void initMenuButton() {

        activityMainBinding.menu.setOnClickListener(view -> {

            activityMainBinding.drawer.openDrawer(GravityCompat.START);

        });


    }


    @Override
    public void initAddButton() {

        activityMainBinding.multiMenuButton.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragments, new AddFragment()).commitNow();
        });

    }

    @Override
    public void initNavigationDrawerMenItems() {

        activityMainBinding.sidebar.setNavigationItemSelectedListener(item -> {


            switch (item.getItemId()) {

                case R.id.main: {

                    activityMainBinding.drawer.closeDrawer(GravityCompat.START);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragments, new CategoriesFragment()).commitNow();

                    break;
                }

                case R.id.summary: {

                    activityMainBinding.drawer.closeDrawer(GravityCompat.START);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragments, new CategorySummaryFragment()).commitNow();

                    break;
                }

                case R.id.consumption: {

                    activityMainBinding.drawer.closeDrawer(GravityCompat.START);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragments, new MostConsumingFragment()).commitNow();

                    break;
                }

                case R.id.about_app: {

                    activityMainBinding.drawer.closeDrawer(GravityCompat.START);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragments, new AboutApp()).commitNow();

                    break;
                }

                case R.id.Settings: {

                    activityMainBinding.drawer.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(this, SettingsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    break;
                }

                case R.id.help: {

                    activityMainBinding.drawer.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(this, HelpActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    break;
                }

            }


            return true;
        });
    }

    @Override
    public void moveToCategoriesFragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.fragments, new CategoriesFragment()).commitNow();
    }

    @Override
    public void onBackPressed() {

        if (activityMainBinding.drawer.isDrawerOpen(GravityCompat.START)) {
            activityMainBinding.drawer.closeDrawer(GravityCompat.START);
        } else
            finish();
    }

}
