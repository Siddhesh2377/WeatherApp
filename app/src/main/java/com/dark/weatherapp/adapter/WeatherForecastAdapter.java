package com.dark.weatherapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dark.weatherapp.R;
import com.dark.weatherapp.model.weather.WeatherForecastData;
import com.dark.weatherapp.theme.ThemeManager;

import java.util.List;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
    private final List<WeatherForecastData> itemList;
    private final Context context;
    private final ThemeManager themeManager;

    public WeatherForecastAdapter(Context context, List<WeatherForecastData> itemList, ThemeManager themeManager) {
        this.context = context;
        this.itemList = itemList;
        this.themeManager = themeManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hourly_weather_forecast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherForecastData item = itemList.get(position);

        holder.main.setCardBackgroundColor(themeManager.getSurface());

        holder.temp.setTextColor(themeManager.getTextColor());
        holder.time.setTextColor(themeManager.getTextColor());

        holder.temp.setTypeface(themeManager.getFont());
        holder.time.setTypeface(Typeface.create(themeManager.getFont(), Typeface.BOLD));

        holder.weather.setColorFilter(themeManager.getTextColor());


        holder.time.setText(item.getTitle());
        holder.temp.setText(item.getTemp());
        holder.weather.setImageResource(item.getIcon());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView weather;
        TextView time, temp;
        CardView main;

        public ViewHolder(View itemView) {
            super(itemView);
            main = itemView.findViewById(R.id.main);
            weather = itemView.findViewById(R.id.weather);
            time = itemView.findViewById(R.id.time);
            temp = itemView.findViewById(R.id.temp);
        }
    }
}