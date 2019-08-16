package com.example.icart.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.icart.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIMEOUT = 2500;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.toolbar, null));
        }


        new Handler().postDelayed(() -> {

            YoYo.with(Techniques.FadeIn).duration(500).playOn(findViewById(R.id.r));
            YoYo.with(Techniques.Bounce).duration(700).playOn(findViewById(R.id.r));

        }, 1000);

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(this.getBaseContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();

        }, SPLASH_SCREEN_TIMEOUT);
    }


}
