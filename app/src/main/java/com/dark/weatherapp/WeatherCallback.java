package com.dark.weatherapp;

import com.dark.weatherapp.model.weather.WeatherData;

public interface WeatherCallback {
    void onSuccess(WeatherData data);

    void onFailure(String errorMessage);
}
