package com.dark.weatherapp.model.weather;

import androidx.annotation.NonNull;

public class WeatherData {

    public double temp, feelsLike, windSpeed, rainChance;
    public int humidity, pressure, visibility;
    public long sunset;
    public String condition, description;
    public WeatherType weatherType;

    public WeatherData(double temp, double feelsLike, int humidity, int pressure, double windSpeed, int visibility, long sunset, String condition, String description, double rainChance) {
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.sunset = sunset;
        this.condition = condition;
        this.description = description;
        this.rainChance = rainChance;
        weatherType = WeatherType.fromCondition(condition);
    }

    @NonNull
    @Override
    public String toString() {
        return "WeatherData{" + "temp=" + temp + ", \nfeelsLike=" + feelsLike + ", \nwindSpeed=" + windSpeed + ", \nrainChance=" + rainChance + ", \nhumidity=" + humidity + ", \npressure=" + pressure + ", \nvisibility=" + visibility + ", \nsunset=" + sunset + ", \ncondition='" + condition + '\'' + ", \ndescription='" + description + '\'' + '}';
    }

}
