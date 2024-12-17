package com.dark.weatherapp.model;

public class WeatherData {
    private double temperature;
    private double feelsLike;
    private int humidity;
    private int pressure;
    private double windSpeed;
    private int visibility;
    private long sunset;
    private String condition;        // e.g., "Clouds"
    private String description;      // e.g., "few clouds"

    // Constructor
    public WeatherData(double temperature, double feelsLike, int humidity, int pressure,
                       double windSpeed, int visibility, long sunset,
                       String condition, String description) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.sunset = sunset;
        this.condition = condition;
        this.description = description;
    }

    // Getters and Setters
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temperature=" + temperature +
                ", feelsLike=" + feelsLike +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", windSpeed=" + windSpeed +
                ", visibility=" + visibility +
                ", sunset=" + new java.util.Date(sunset * 1000) +
                ", condition='" + condition + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public int getVisibility() {
        return visibility;
    }

    public long getSunset() {
        return sunset;
    }
}
