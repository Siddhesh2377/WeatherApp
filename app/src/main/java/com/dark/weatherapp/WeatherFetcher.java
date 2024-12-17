package com.dark.weatherapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dark.weatherapp.model.WeatherData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherFetcher {
    private static final String API_KEY = "2ff9ae9a7eb0ab73a871ac03746bdf46";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public void fetchWeather(String cityName, WeatherCallback callback) {
        OkHttpClient client = new OkHttpClient();

        String url = BASE_URL + "?q=" + cityName + "&appid=" + API_KEY + "&units=metric";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String responseData = response.body().string();
                    try {
                        // Parse JSON
                        JSONObject json = new JSONObject(responseData);
                        JSONObject main = json.getJSONObject("main");
                        JSONObject wind = json.getJSONObject("wind");
                        JSONObject sys = json.getJSONObject("sys");
                        JSONArray weatherArray = json.getJSONArray("weather");
                        JSONObject weather = weatherArray.getJSONObject(0);

                        double temp = main.getDouble("temp");
                        double feelsLike = main.getDouble("feels_like");
                        int humidity = main.getInt("humidity");
                        int pressure = main.getInt("pressure");
                        double windSpeed = wind.getDouble("speed");
                        int visibility = json.getInt("visibility");
                        long sunset = sys.getLong("sunset");
                        String condition = weather.getString("main");
                        String description = weather.getString("description");

                        // Create a WeatherData object
                        WeatherData weatherData = new WeatherData(temp, feelsLike, humidity, pressure,
                                windSpeed, visibility, sunset, condition, description);

                        // Pass data to the callback
                        callback.onSuccess(weatherData);

                    } catch (Exception e) {
                        callback.onFailure(e.getMessage());
                    }
                } else {
                    callback.onFailure("Response not successful");
                }
            }
        });
    }
}
