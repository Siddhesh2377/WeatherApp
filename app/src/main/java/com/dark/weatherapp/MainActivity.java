package com.dark.weatherapp;

import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.dark.weatherapp.adapter.HomePagerAdapter;
import com.dark.weatherapp.databinding.ActivityMainBinding;
import com.dark.weatherapp.model.weather.WeatherData;
import com.dark.weatherapp.theme.ThemeManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    ThemeManager themeManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        themeManager = new ThemeManager(this);



        binding.main.setBackgroundColor(themeManager.getSurface());
        binding.bottomBar.setBackgroundColor(themeManager.getOnSurface());
        binding.bottomBar.setItemIconTintList(ColorStateList.valueOf(themeManager.getTextColor()));
        binding.bottomBar.setItemTextColor(ColorStateList.valueOf(themeManager.getTextColor()));
        binding.bottomBar.setItemActiveIndicatorColor(ColorStateList.valueOf(themeManager.getItem()));

        // Set up ViewPager with adapter
        HomePagerAdapter adapter = new HomePagerAdapter(this);
        binding.homePager.setAdapter(adapter);

        // Bottom Navigation Item Selection (Replace switch with if-else)
        binding.bottomBar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.hourly) {
                binding.homePager.setCurrentItem(0);
                return true;
            } else if (itemId == R.id.daily) {
                binding.homePager.setCurrentItem(1);
                return true;
            } else if (itemId == R.id.settings) {
                binding.homePager.setCurrentItem(2);
                return true;
            }
            return false;
        });

        // Sync Bottom Navigation with ViewPager2 Swipes (Replace switch with if-else)
        binding.homePager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    binding.bottomBar.setSelectedItemId(R.id.hourly);
                } else if (position == 1) {
                    binding.bottomBar.setSelectedItemId(R.id.daily);
                } else if (position == 2) {
                    binding.bottomBar.setSelectedItemId(R.id.settings);
                }
            }
        });
    }



}
