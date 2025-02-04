package com.dark.weatherapp.model.weather;

import android.icu.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

public class DailyWeatherData {

    private final String day;
    private final String[] temp;
    private final WeatherType weather;

    public DailyWeatherData(long day, String[] temp, WeatherType weather) {
        this.day = convertUnixToDay(day);
        this.temp = temp;
        this.weather = weather;
    }

    private String convertUnixToDay(long unixTimestamp) {
        Date date = new Date(unixTimestamp * 1000L); // Convert to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.ENGLISH); // "EEEE" gives full day name
        return sdf.format(date);
    }

    public WeatherType getWeather() {
        return weather;
    }

    public String[] getTemp() {
        return temp;
    }

    public String getDay() {
        return day;
    }
}
