package com.example.icart.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.icart.R;
import com.example.icart.databinding.AppIntroScreenBinding;

public class AppIntroScreen {



    public static class AppIntroScreen3 extends Fragment {

        private AppIntroScreenBinding appIntroScreenBinding;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            appIntroScreenBinding = DataBindingUtil.inflate(inflater, R.layout.app_intro_screen, container, false);

            Glide.with(this).load(R.drawable.screen3).into(appIntroScreenBinding.screen);

            return appIntroScreenBinding.getRoot();
        }
    }



    public static class AppIntroScreen1 extends Fragment {

        private AppIntroScreenBinding appIntroScreenBinding;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            appIntroScreenBinding = DataBindingUtil.inflate(inflater, R.layout.app_intro_screen, container, false);

            Glide.with(this).load(R.drawable.screen1).into(appIntroScreenBinding.screen);

            return appIntroScreenBinding.getRoot();
        }
    }




    public static class AppIntroScreen2 extends Fragment {

        private AppIntroScreenBinding appIntroScreenBinding;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            appIntroScreenBinding = DataBindingUtil.inflate(inflater, R.layout.app_intro_screen, container, false);

            Glide.with(this).load(R.drawable.screen2).into(appIntroScreenBinding.screen);

            return appIntroScreenBinding.getRoot();
        }
    }



    public static class AppIntroScreen4 extends Fragment {

        private AppIntroScreenBinding appIntroScreenBinding;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            appIntroScreenBinding = DataBindingUtil.inflate(inflater, R.layout.app_intro_screen, container, false);

            Glide.with(this).load(R.drawable.screen4).into(appIntroScreenBinding.screen);

            return appIntroScreenBinding.getRoot();
        }
    }




    public static class AppIntroScreen5 extends Fragment {

        private AppIntroScreenBinding appIntroScreenBinding;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            appIntroScreenBinding = DataBindingUtil.inflate(inflater, R.layout.app_intro_screen, container, false);

            Glide.with(this).load(R.drawable.screen5).into(appIntroScreenBinding.screen);

            return appIntroScreenBinding.getRoot();
        }
    }




    public static class AppIntroScreen6 extends Fragment {
        private AppIntroScreenBinding appIntroScreenBinding;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            appIntroScreenBinding = DataBindingUtil.inflate(inflater, R.layout.app_intro_screen, container, false);

            Glide.with(this).load(R.drawable.screen6).into(appIntroScreenBinding.screen);

            return appIntroScreenBinding.getRoot();
        }
    }



    public static class AppIntroScreen7 extends Fragment {

        private AppIntroScreenBinding appIntroScreenBinding;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            appIntroScreenBinding = DataBindingUtil.inflate(inflater, R.layout.app_intro_screen, container, false);

            Glide.with(this).load(R.drawable.screen7).into(appIntroScreenBinding.screen);

            return appIntroScreenBinding.getRoot();
        }
    }




    public static class AppIntroScreen8 extends Fragment {

        private AppIntroScreenBinding appIntroScreenBinding;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            appIntroScreenBinding = DataBindingUtil.inflate(inflater, R.layout.app_intro_screen, container, false);

            Glide.with(this).load(R.drawable.screen8).into(appIntroScreenBinding.screen);

            return appIntroScreenBinding.getRoot();
        }
    }

}
