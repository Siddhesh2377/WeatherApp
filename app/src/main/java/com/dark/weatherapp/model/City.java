package com.dark.weatherapp.model;

public class City {
    private String name;
    private double latitude;
    private double longitude;

    // Constructor
    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    @Override
    public String toString() {
        return "City{name='" + name + "', latitude=" + latitude + ", longitude=" + longitude + "}";
    }
}
