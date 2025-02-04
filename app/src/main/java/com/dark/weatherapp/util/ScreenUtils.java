package com.dark.weatherapp.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtils {
    public static int getSmallestWidthDp(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float widthDp = metrics.widthPixels / metrics.density;
        float heightDp = metrics.heightPixels / metrics.density;
        return (int) Math.min(widthDp, heightDp); // Smallest width in dp
    }
}
