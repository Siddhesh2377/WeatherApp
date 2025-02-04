package com.dark.weatherapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dark.weatherapp.R;
import com.dark.weatherapp.model.weather.DailyWeatherData;
import com.dark.weatherapp.theme.ThemeManager;

import java.util.List;

public class DailyWeatherForecastAdapter extends RecyclerView.Adapter<DailyWeatherForecastAdapter.ViewHolder> {
    private final List<DailyWeatherData> itemList;
    private final Context context;
    private final ThemeManager themeManager;

    public DailyWeatherForecastAdapter(Context context, List<DailyWeatherData> itemList, ThemeManager themeManager) {
        this.context = context;
        this.itemList = itemList;
        this.themeManager = themeManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.daily_weather_forecast_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailyWeatherData item = itemList.get(position);

        holder.day.setTextColor(themeManager.getTextColor());
        holder.weather.setTextColor(themeManager.getTextColor());
        holder.temp.setTextColor(themeManager.getTextColor());

        holder.day.setTypeface(themeManager.getFont());
        holder.weather.setTypeface(themeManager.getFont());
        holder.temp.setTypeface(themeManager.getFont());


        holder.day.setText(item.getDay());
        holder.weather.setText(item.getWeather().getWeather());
        holder.temp.setText(item.getTemp()[0] + "/" + item.getTemp()[1]);
        holder.weatherIcon.setImageResource(item.getWeather().getWeatherId());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView weatherIcon;
        TextView day, weather, temp;

        public ViewHolder(View itemView) {
            super(itemView);
            weather = itemView.findViewById(R.id.weather);
            weatherIcon = itemView.findViewById(R.id.weatherIcon);
            day = itemView.findViewById(R.id.day);
            temp = itemView.findViewById(R.id.temp);
        }
    }
}