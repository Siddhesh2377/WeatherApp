package com.dark.weatherapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dark.weatherapp.model.weather.WeatherData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherFetcher {
    private static final String API_KEY = "2ff9ae9a7eb0ab73a871ac03746bdf46";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public void fetchWeather(double lat, double lon, WeatherCallback callback) {
        OkHttpClient client = new OkHttpClient();

        // Build URL for the "weather" endpoint using latitude and longitude.
        String url = BASE_URL + "?lat=" + lat + "&lon=" + lon + "&units=metric" + "&appid=" + API_KEY;

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        callback.onFailure("Response body is null");
                        return;
                    }

                    String responseData = response.body().string();
                    try {
                        // Parse JSON response from the "weather" endpoint
                        JSONObject json = new JSONObject(responseData);

                        // Extract temperature and other weather details from "main" object.
                        JSONObject main = json.getJSONObject("main");
                        double temp = main.getDouble("temp");
                        double feelsLike = main.getDouble("feels_like");
                        int humidity = main.getInt("humidity");
                        int pressure = main.getInt("pressure");

                        // Extract wind data from the "wind" object.
                        JSONObject wind = json.getJSONObject("wind");
                        double windSpeed = wind.getDouble("speed");

                        // Extract visibility from the root object.
                        int visibility = json.getInt("visibility");

                        // Extract sunset time from the "sys" object.
                        JSONObject sys = json.getJSONObject("sys");
                        long sunset = sys.getLong("sunset");

                        // Extract weather condition and description from the "weather" array.
                        JSONArray weatherArray = json.getJSONArray("weather");
                        JSONObject weather = weatherArray.getJSONObject(0);
                        String condition = weather.getString("main");
                        String description = weather.getString("description");

                        // For the /weather endpoint, a rain probability ("pop") is not provided.
                        double rainChance = 0.0;

                        // Create a WeatherData object using the extracted values.
                        WeatherData weatherData = new WeatherData(temp, feelsLike, humidity, pressure, windSpeed, visibility, sunset, condition, description, rainChance);

                        // Return the current weather data via the callback.
                        callback.onSuccess(weatherData);

                    } catch (Exception e) {
                        callback.onFailure("Parsing error: " + e.getMessage());
                    }
                } else {
                    String errorBody = response.body() != null ? response.body().string() : "No error body";
                    String errorMessage = "Response not successful: " + response.code() + " " + response.message() + ". Error body: " + errorBody;
                    Log.e("WeatherFetcher", errorMessage);
                    callback.onFailure(errorMessage);
                }
            }
        });
    }
}
