package com.dark.weatherapp.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Icon;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dark.weatherapp.R;
import com.dark.weatherapp.WeatherCallback;
import com.dark.weatherapp.WeatherFetcher;
import com.dark.weatherapp.adapter.WeatherForecastAdapter;
import com.dark.weatherapp.databinding.FragmentHourlyWeatherBinding;
import com.dark.weatherapp.model.weather.WeatherData;
import com.dark.weatherapp.model.weather.WeatherForecastData;
import com.dark.weatherapp.services.LocationService;
import com.dark.weatherapp.theme.ThemeManager;
import com.dark.weatherapp.util.ScreenUtils;
import com.dark.weatherapp.util.UI;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HourlyWeatherFragment extends Fragment {

    private FragmentHourlyWeatherBinding binding;
    private LocationService locationService;
    private WeatherForecastAdapter adapter;
    private ThemeManager themeManager;

    public static String convertUnixToTime(long unixTimestamp) {
        // Convert Unix timestamp to milliseconds
        Date date = new Date(unixTimestamp * 1000L);

        // Format: hh:mm a (12-hour format with AM/PM)
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);

        return sdf.format(date);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHourlyWeatherBinding.inflate(inflater, container, false);
        themeManager = new ThemeManager(requireActivity());
        locationService = new LocationService(this);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
        workWithPermissions();
    }

    private void initUi() {
        binding.location.setTextColor(themeManager.getTextColor());
        binding.subTitle.setTextColor(themeManager.getTextColor());
        binding.temp.setTextColor(themeManager.getTextColor());
        binding.title.setTextColor(themeManager.getTextColor());
        binding.hourlyData.setBackground(UI.roundedBackground(themeManager.getOnSurface(), UI.pxToDp(requireContext(), 16)));

        binding.location.setTypeface(themeManager.getFont());
        binding.subTitle.setTypeface(themeManager.getFont());
        binding.temp.setTypeface(themeManager.getFont());
        binding.title.setTypeface(themeManager.getFont());

        if (ScreenUtils.getSmallestWidthDp(requireContext()) <= 376) {
            binding.weatherForecast.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        } else {
            binding.weatherForecast.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        }
    }

    private void workWithPermissions() {
        if (!locationService.isLocationPermission()) {
            locationService.requestLocationPermission();
        }
        locationService.setOnLocationPermissionListener(this::fetchWeather);
        if (locationService.isLocationPermission()) {
            fetchWeather();
        }
    }

    private void fetchWeather() {
        locationService.setOnLocationFetchListener((latitude, longitude) -> {
            new WeatherFetcher().fetchWeather(latitude, longitude, new WeatherCallback() {
                @Override
                public void onSuccess(WeatherData data) {
                    requireActivity().runOnUiThread(() -> initData(data));
                }

                @Override
                public void onFailure(String errorMessage) {
                    Log.e("Weather Error", errorMessage);
                }
            });
        });
    }

    @SuppressLint("SetTextI18n")
    private void initData(WeatherData data) {
        ArrayList<WeatherForecastData> itemList = new ArrayList<>();
        binding.location.setText(locationService.getCityName());
        binding.temp.setText(data.temp + " C");
        binding.subTitle.setText(data.description);

        itemList.add(new WeatherForecastData("Wind Speed", data.windSpeed + " km/h", R.drawable.wind_speed));
        itemList.add(new WeatherForecastData("Humidity", data.humidity + "%", R.drawable.humidity));
        itemList.add(new WeatherForecastData("Pressure", data.pressure + " hPa", R.drawable.pressure));
        itemList.add(new WeatherForecastData("Visibility", data.visibility + " m", R.drawable.visibility));
        itemList.add(new WeatherForecastData("Sunset", convertUnixToTime(data.sunset), R.drawable.sunset));
        itemList.add(new WeatherForecastData("Feels Like", data.feelsLike + " C", R.drawable.feels_like));

        binding.weatherIcon.setImageResource(data.weatherType.getWeatherId());

        adapter = new WeatherForecastAdapter(requireContext(), itemList, themeManager);
        binding.weatherForecast.setAdapter(adapter);
    }
}
