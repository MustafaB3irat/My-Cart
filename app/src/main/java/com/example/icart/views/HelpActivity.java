package com.example.icart.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.icart.R;
import com.example.icart.views.fragments.AppIntroScreen;
import com.github.paolorotolo.appintro.AppIntro;

public class HelpActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new AppIntroScreen.AppIntroScreen1());
        addSlide(new AppIntroScreen.AppIntroScreen2());
        addSlide(new AppIntroScreen.AppIntroScreen3());
        addSlide(new AppIntroScreen.AppIntroScreen4());
        addSlide(new AppIntroScreen.AppIntroScreen5());
        addSlide(new AppIntroScreen.AppIntroScreen6());
        addSlide(new AppIntroScreen.AppIntroScreen7());
        addSlide(new AppIntroScreen.AppIntroScreen8());


        setBarColor(getColor(R.color.toolbar));
        setSeparatorColor(getColor(R.color.toolbar));


        showSkipButton(true);
        setProgressButtonEnabled(true);


        setVibrate(true);
        setVibrateIntensity(30);

        setFadeAnimation();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.toolbar, null));
        }




    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);

        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        finish();
    }
}
