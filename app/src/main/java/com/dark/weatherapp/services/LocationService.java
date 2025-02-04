package com.dark.weatherapp.services;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Locale;
import java.util.Objects;

public class LocationService {
    private static final String TAG = "LocationService";
    private final FusedLocationProviderClient fusedLocationClient;
    private final Activity activity;
    private final ActivityResultLauncher<String> requestPermissionLauncher;
    private onLocationFetchListener locationFetchListener;
    private onLocationPermissionListener locationPermissionListener;
    private double latitude;
    private double longitude;

    public LocationService(Fragment fragment) {
        this.activity = fragment.requireActivity();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);

        requestPermissionLauncher = fragment.registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                if (locationPermissionListener != null) {
                    locationPermissionListener.onLocationPermissionGranted();
                }
                fetchLastLocation();
            } else {
                Log.d(TAG, "Location permission denied.");
            }
        });
    }


    public void setOnLocationFetchListener(onLocationFetchListener listener) {
        this.locationFetchListener = listener;
        if (isLocationPermission()) {
            fetchLastLocation();
        } else {
            requestLocationPermission();
        }
    }

    public void setOnLocationPermissionListener(onLocationPermissionListener listener) {
        this.locationPermissionListener = listener;
    }

    public boolean isLocationPermission() {
        return ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestLocationPermission() {
        if (!isLocationPermission()) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    @SuppressLint("MissingPermission")
    private void fetchLastLocation() {
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                if (locationFetchListener != null) {
                    locationFetchListener.onLocationFetched(latitude, longitude);
                }
            } else {
                Log.d(TAG, "Last known location is null");
            }
        });
    }

    public String getCityName() {
        try {
            Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
            return Objects.requireNonNull(geocoder.getFromLocation(latitude, longitude, 1)).get(0).getLocality();
        } catch (Exception e) {
            return e.toString();
        }
    }

    public interface onLocationFetchListener {
        void onLocationFetched(double latitude, double longitude);
    }

    public interface onLocationPermissionListener {
        void onLocationPermissionGranted();
    }
}
