package com.dark.weatherapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.dark.weatherapp.R;
import com.dark.weatherapp.databinding.FragmentSettingsBinding;
import com.dark.weatherapp.model.ColorTheme;
import com.dark.weatherapp.theme.ThemeManager;


public class SettingsFragment extends Fragment {
    FragmentSettingsBinding binding;
    ThemeManager themeManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        themeManager = new ThemeManager(requireActivity());

        binding.textView.setTextColor(themeManager.getTextColor());
        binding.textView.setTypeface(themeManager.getFont());
        binding.getRoot().setBackgroundColor(themeManager.getSurface());

    }
}
