package com.dark.weatherapp.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;

public class UI {

    public static Drawable roundedBackground(int color, float radius) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(color);
        shape.setCornerRadius(radius);
        return shape;
    }

    public static float pxToDp(Context context, int px){
        Resources r = context.getResources();

        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                px,
                r.getDisplayMetrics()
        );
    }

}
