package com.dark.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dark.weatherapp.databinding.ActivityMainBinding;
import com.dark.weatherapp.model.City;
import com.dark.weatherapp.model.WeatherData;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    List<City> cities;
    List<String> recentCities;
    int currentCityIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        binding.cityName.setOnClickListener(v -> openSearch());

        binding.searchIcon.setOnClickListener(v -> {
            fetchWeather(binding.searchTxt.getText().toString());
        });
    }

    private void init() {
        recentCities = new ArrayList<>();

        //Just For Ease...
        Runnable task = () -> {
            cities = parseCities();
            startSearch();
        };

        Thread thread = new Thread(task);
        thread.start();
        nextorPrv();
    }

    private void openSearch() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(binding.main.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        binding.searchTxt.requestFocus();
        binding.searchTxt.showDropDown();
    }

    private void closeSearch() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        binding.cardWeatherInfo.requestFocus();
    }

    private void startSearch() {
        List<String> c = new ArrayList<>();

        for (City city : cities) {
            c.add(city.getName());
        }

        // Switching back to the main thread to update UI
        runOnUiThread(() -> {
            openSearch();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, c);
            binding.searchTxt.setThreshold(1);
            binding.searchTxt.setAdapter(adapter);
            binding.searchTxt.setDropDownWidth(450);
            binding.searchTxt.addTextChangedListener(new android.text.TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(android.text.Editable s) {
                    String input = s.toString();
                    for (String cityName : c) {
                        if (input.equalsIgnoreCase(cityName)) {
                            // Manually trigger the suggestion dropdown
                            binding.searchTxt.showDropDown();
                            break;
                        }
                    }
                }
            });

            // Ensure dropdown is shown on focus
            binding.searchTxt.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    binding.searchTxt.showDropDown();
                }
            });
        });
    }

    private List<City> parseCities() {
        List<City> cities = new ArrayList<>();
        try {
            // Load JSON file as a stream
            InputStream is = getAssets().open("city_list.json");
            JsonReader reader = new JsonReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            // Start reading the JSON array
            reader.beginArray();
            while (reader.hasNext()) {
                String name = "";
                double lat = 0.0, lon = 0.0;

                // Start reading each JSON object
                reader.beginObject();
                while (reader.hasNext()) {
                    String key = reader.nextName();
                    if (key.equals("name")) {
                        name = reader.nextString();
                    } else if (key.equals("coord")) {
                        // Read the "coord" object
                        reader.beginObject();
                        while (reader.hasNext()) {
                            String coordKey = reader.nextName();
                            if (coordKey.equals("lat")) {
                                lat = reader.nextDouble();
                            } else if (coordKey.equals("lon")) {
                                lon = reader.nextDouble();
                            } else {
                                reader.skipValue();
                            }
                        }
                        reader.endObject();
                    } else {
                        reader.skipValue(); // Skip unused fields
                    }
                }
                reader.endObject();

                // Add city to the list
                cities.add(new City(name, lat, lon));
            }
            reader.endArray();
            reader.close();
        } catch (Exception e) {
            Log.e("MainActivity", "Error parsing JSON", e);
        }
        return cities;
    }

    @SuppressLint("SetTextI18n")
    private void fetchWeather(String name) {
        WeatherFetcher fetcher = new WeatherFetcher();

        // Show a loading message initially
        binding.weatherTypeText.setText("Loading...");
        binding.temp.setText("Loading...");
        binding.valFeelTemp.setText("-");
        binding.valHumidity.setText("-");
        binding.valPressure.setText("-");
        binding.valWind.setText("-");
        binding.valVisibility.setText("-");
        binding.valSunset.setText("-");

        fetcher.fetchWeather(name, new WeatherCallback() {
            @Override
            public void onSuccess(WeatherData data) {
                runOnUiThread(() -> {
                    closeSearch();

                    // Update UI with fetched weather data
                    binding.cityName.setText(name);
                    binding.temp.setText(data.getTemperature() + "°C");
                    binding.valFeelTemp.setText(data.getFeelsLike() + "°C");
                    binding.valHumidity.setText(data.getHumidity() + "%");
                    binding.valPressure.setText(data.getPressure() + " hPa");
                    binding.valWind.setText(data.getWindSpeed() + " km/h");
                    binding.valVisibility.setText(data.getVisibility() + " M");
                    binding.valSunset.setText(new java.text.SimpleDateFormat("HH:mm")
                            .format(new java.util.Date(data.getSunset() * 1000)));
                    binding.weatherTypeText.setText(data.getCondition() + "\n" + data.getDescription());
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> {
                    closeSearch();

                    // Clear weather info and display specific error
                    binding.cityName.setText("Error");
                    binding.temp.setText("N/A");
                    binding.valFeelTemp.setText("N/A");
                    binding.valHumidity.setText("N/A");
                    binding.valPressure.setText("N/A");
                    binding.valWind.setText("N/A");
                    binding.valVisibility.setText("N/A");
                    binding.valSunset.setText("N/A");

                    if (isInternetAvailable()) {
                        if ("Response not successful".contains(errorMessage)) {
                            binding.weatherTypeText.setText("City not found.");
                        } else {
                            binding.weatherTypeText.setText(errorMessage);
                        }
                    } else {
                        binding.weatherTypeText.setText("Check your Internet Connection..");
                    }

                    // Check for specific error messages

                    Log.e("Main", errorMessage);
                });
            }
        });
    }

    private void nextorPrv() {
        binding.prv.setOnClickListener(v -> {
            if (currentCityIndex > 0) {
                currentCityIndex--;
                String cityName = recentCities.get(currentCityIndex);
                fetchWeather(cityName);
            } else {
                Log.d("Navigation", "No previous city available.");
            }
        });

        binding.nxt.setOnClickListener(v -> {
            if (currentCityIndex < recentCities.size() - 1) {
                currentCityIndex++;
                String cityName = recentCities.get(currentCityIndex);
                fetchWeather(cityName);
            } else {
                Log.d("Navigation", "No next city available.");
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateWeatherUI(String name, WeatherData data) {
        binding.cityName.setText(name);
        binding.temp.setText(data.getTemperature() + "°C");
        binding.valFeelTemp.setText(data.getFeelsLike() + "°C");
        binding.valHumidity.setText(data.getHumidity() + "%");
        binding.valPressure.setText(data.getPressure() + " hPa");
        binding.valWind.setText(data.getWindSpeed() + " km/h");
        binding.valVisibility.setText(data.getVisibility() + " M");
        binding.valSunset.setText(new java.text.SimpleDateFormat("HH:mm")
                .format(new java.util.Date(data.getSunset() * 1000)));
        binding.weatherTypeText.setText(data.getCondition() + "\nFeels Like " + data.getDescription());
    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            // For Android Marshmallow and above
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            return networkCapabilities != null &&
                    (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        }
        return false;
    }

}