package com.dark.weatherapp.model.weather;

import com.dark.weatherapp.R;

public enum WeatherType {

    SUNNY("Sunny", R.drawable.clear_sun_weather),
    CloudyDay("Cloudy Day", R.drawable.cloudy_weather),
    Rain("Rain", R.drawable.rainy_weather),
    Thunder("Thunder", R.drawable.thunder_weather);

    private final String weather;
    private final int weatherId;

    WeatherType(String weather, int weatherId) {
        this.weather = weather;
        this.weatherId = weatherId;
    }

    public static WeatherType fromCondition(String condition) {
        switch (condition) {
            case "Clouds":
                return WeatherType.CloudyDay;
            case "Rain":
                return WeatherType.Rain;
            case "Thunderstorm":
                return WeatherType.Thunder;
            default:
                return WeatherType.SUNNY;
        }
    }

    public int getWeatherId() {
        return weatherId;
    }

    public String getWeather() {
        return weather;
    }
}
