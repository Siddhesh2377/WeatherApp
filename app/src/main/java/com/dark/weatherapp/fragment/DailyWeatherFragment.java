package com.dark.weatherapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dark.weatherapp.databinding.FragmentDailyWeatherBinding;
import com.dark.weatherapp.theme.ThemeManager;

public class DailyWeatherFragment extends Fragment {

    FragmentDailyWeatherBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDailyWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ThemeManager themeManager = new ThemeManager(requireActivity());

        binding.title.setTextColor(themeManager.getTextColor());
        binding.title.setTypeface(themeManager.getFont());
        binding.getRoot().setBackgroundColor(themeManager.getSurface());
    }
}