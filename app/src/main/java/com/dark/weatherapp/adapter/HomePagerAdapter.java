package com.dark.weatherapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dark.weatherapp.fragment.DailyWeatherFragment;
import com.dark.weatherapp.fragment.HourlyWeatherFragment;
import com.dark.weatherapp.fragment.SettingsFragment;

public class HomePagerAdapter extends FragmentStateAdapter {

    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HourlyWeatherFragment();
            case 1:
                return new DailyWeatherFragment();
            case 2:
                return new SettingsFragment();
            default:
                return new HourlyWeatherFragment(); // Default case to prevent null return
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
