package com.dark.weatherapp.model.weather;

public class WeatherForecastData {

    private final String title, temp;
    private int icon;

    public WeatherForecastData(String title, String temp, int icon) {
        this.title = title;
        this.temp = temp;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getTemp() {
        return temp;
    }

    public String getTitle() {
        return title;
    }
}
