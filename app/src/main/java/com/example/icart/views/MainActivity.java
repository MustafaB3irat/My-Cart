package com.example.icart.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.icart.R;
import com.example.icart.databinding.ActivityMainBinding;
import com.example.icart.interfaces.Main;
import com.example.icart.views.fragments.AddFragment;
import com.example.icart.views.fragments.CategoriesFragment;
import com.example.icart.views.fragments.CategorySummaryFragment;
import com.example.icart.views.fragments.MostConsumingFragment;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MainActivity extends AppCompatActivity implements Main.MainView {

    private ActivityMainBinding activityMainBinding;
    private FloatingActionMenu actionMenu;
    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.sidebar.inflateHeaderView(R.layout.sidebar_header);

        moveToCategoriesFragment();
        initNavigationDrawerMenItems();
        setSupportActionBar(activityMainBinding.toolbar);
        initMultiMenuButton();
        initMenuButton();


        toolbar = activityMainBinding.toolbar;


    }

    @Override
    public void initMenuButton() {

        activityMainBinding.menu.setOnClickListener(view -> {

            activityMainBinding.drawer.openDrawer(GravityCompat.START);
            actionMenu.close(true);

        });


    }


    @Override
    public void initMultiMenuButton() {

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this).setLayoutParams(new FrameLayout.LayoutParams(200, 200));

        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(getResources().getDrawable(R.drawable.add_icon));
        SubActionButton add = itemBuilder.setContentView(itemIcon).build();


        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit));
        SubActionButton edit = itemBuilder.setContentView(itemIcon1).build();

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_minus_sign_inside_a_black_circle));
        SubActionButton delete = itemBuilder.setContentView(itemIcon2).build();


        actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(add)
                .addSubActionView(edit)
                .addSubActionView(delete)
                .attachTo(activityMainBinding.multiMenuButton)
                .build();

        add.setOnClickListener(initAddButtonListener());


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

            }


            return true;
        });
    }

    @Override
    public void moveToCategoriesFragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.fragments, new CategoriesFragment()).commitNow();
    }

    @Override
    public View.OnClickListener initAddButtonListener() {

        View.OnClickListener listener = view -> {

            actionMenu.close(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragments, new AddFragment()).commitNow();


        };

        return listener;
    }

    @Override
    public View.OnClickListener initRemoveButtonListener() {
        return null;
    }

    @Override
    public View.OnClickListener initEditButtonListener() {
        return null;
    }

    @Override
    public void onBackPressed() {

        if (activityMainBinding.drawer.isDrawerOpen(GravityCompat.START)) {
            activityMainBinding.drawer.closeDrawer(GravityCompat.START);
        } else
            finish();
    }
}
