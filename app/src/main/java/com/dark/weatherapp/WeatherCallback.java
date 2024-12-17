package com.dark.weatherapp;

import com.dark.weatherapp.model.WeatherData;

public interface WeatherCallback {
    void onSuccess(WeatherData data);
    void onFailure(String errorMessage);
}
