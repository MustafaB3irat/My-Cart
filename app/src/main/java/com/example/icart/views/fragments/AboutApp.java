package com.example.icart.views.fragments;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.icart.R;
import com.example.icart.databinding.AboutAppBinding;

public class AboutApp extends Fragment {

    private AboutAppBinding aboutAppBinding;
    private GestureDetector gestureDetector;
    private int index = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        aboutAppBinding = DataBindingUtil.inflate(inflater, R.layout.about_app, container, false);

        gestureDetector = new GestureDetector(this.getContext(), new Gesture());


        aboutAppBinding.layout.setOnTouchListener((view, motionEvent) -> {
            gestureDetector.onTouchEvent(motionEvent);
            return true;
        });


        return aboutAppBinding.getRoot();
    }


    public class Gesture extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (distanceX < 0) {

                if (index < 3)
                    index++;
                else
                    index = 1;

                switch (index) {
                    case 1: {
                        aboutAppBinding.avatar.setImageResource(R.drawable.hani);
                        aboutAppBinding.dot1.setImageResource(R.drawable.ic_circle);
                        YoYo.with(Techniques.FadeIn).duration(150).playOn(aboutAppBinding.dot1);
                        aboutAppBinding.dot2.setImageResource(R.drawable.ic_empty_circle);
                        aboutAppBinding.dot3.setImageResource(R.drawable.ic_empty_circle);
                        break;
                    }

                    case 2: {
                        aboutAppBinding.avatar.setImageResource(R.drawable.mustafa);
                        aboutAppBinding.dot1.setImageResource(R.drawable.ic_empty_circle);
                        aboutAppBinding.dot2.setImageResource(R.drawable.ic_circle);
                        YoYo.with(Techniques.FadeIn).duration(150).playOn(aboutAppBinding.dot2);
                        aboutAppBinding.dot3.setImageResource(R.drawable.ic_empty_circle);
                        break;
                    }
                    case 3: {
                        aboutAppBinding.avatar.setImageResource(R.drawable.ahmed);
                        aboutAppBinding.dot1.setImageResource(R.drawable.ic_empty_circle);
                        aboutAppBinding.dot2.setImageResource(R.drawable.ic_empty_circle);
                        aboutAppBinding.dot3.setImageResource(R.drawable.ic_circle);
                        YoYo.with(Techniques.FadeIn).duration(150).playOn(aboutAppBinding.dot3);
                        break;
                    }

                }

            }
            if (distanceX > 0) {

                if (index > 0)
                    index--;
                else
                    index = 3;

                switch (index) {
                    case 1: {
                        aboutAppBinding.avatar.setImageResource(R.drawable.hani);
                        aboutAppBinding.dot1.setImageResource(R.drawable.ic_circle);
                        YoYo.with(Techniques.FadeIn).duration(150).playOn(aboutAppBinding.dot1);
                        aboutAppBinding.dot2.setImageResource(R.drawable.ic_empty_circle);
                        aboutAppBinding.dot3.setImageResource(R.drawable.ic_empty_circle);
                        break;
                    }

                    case 2: {
                        aboutAppBinding.avatar.setImageResource(R.drawable.mustafa);
                        aboutAppBinding.dot1.setImageResource(R.drawable.ic_empty_circle);
                        aboutAppBinding.dot2.setImageResource(R.drawable.ic_circle);
                        YoYo.with(Techniques.FadeIn).duration(150).playOn(aboutAppBinding.dot2);
                        aboutAppBinding.dot3.setImageResource(R.drawable.ic_empty_circle);
                        break;
                    }
                    case 3: {
                        aboutAppBinding.avatar.setImageResource(R.drawable.ahmed);
                        aboutAppBinding.dot1.setImageResource(R.drawable.ic_empty_circle);
                        aboutAppBinding.dot2.setImageResource(R.drawable.ic_empty_circle);
                        aboutAppBinding.dot3.setImageResource(R.drawable.ic_circle);
                        YoYo.with(Techniques.FadeIn).duration(150).playOn(aboutAppBinding.dot3);
                        break;
                    }

                }
            }

            return true;


        }

    }
}
