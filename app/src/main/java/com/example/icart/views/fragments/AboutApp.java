package com.example.icart.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.icart.R;
import com.example.icart.databinding.AboutAppBinding;

public class AboutApp extends Fragment {

    private AboutAppBinding aboutAppBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        aboutAppBinding = DataBindingUtil.inflate(inflater, R.layout.about_app, container, false);


        return aboutAppBinding.getRoot();
    }
}
