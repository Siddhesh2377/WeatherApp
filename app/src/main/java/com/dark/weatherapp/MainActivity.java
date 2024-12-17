package com.dark.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
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

        fetcher.fetchWeather(name, new WeatherCallback() {
            @Override
            public void onSuccess(WeatherData data) {
                runOnUiThread(() -> {
                    closeSearch();

                    // Add the city to recentCities if not already present
                    if (!recentCities.contains(name)) {
                        recentCities.add(name);
                        currentCityIndex = recentCities.size() - 1; // Set current index to the newly added city
                    }

                    // Update UI with the fetched data
                    updateWeatherUI(name, data);
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> Log.e("WeatherFetcher", "Error: " + errorMessage));
            }
        });

//
//        fetcher.fetchWeather(name, new WeatherCallback() {
//            @Override
//            public void onSuccess(WeatherData data) {
//                runOnUiThread(() -> {
//                    closeSearch();
//
//                    // Update UI with the fetched data
//                    binding.cityName.setText(name);
//                    binding.temp.setText(data.getTemperature() + "°C");
//                    binding.valFeelTemp.setText(data.getFeelsLike() + "°C");
//                    binding.valHumidity.setText(data.getHumidity() + "%");
//                    binding.valPressure.setText(data.getPressure() + " hPa");
//                    binding.valWind.setText(data.getWindSpeed() + " km/h");
//                    binding.valVisibility.setText(data.getVisibility() + " M");
//                    binding.valSunset.setText(new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(data.getSunset() * 1000)));
//                    binding.weatherTypeText.setText(data.getCondition() + "\n Feels Like " + data.getDescription());
//                });
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//                runOnUiThread(() -> Log.e("WeatherFetcher", "Error: " + errorMessage));
//            }
//        });
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


}