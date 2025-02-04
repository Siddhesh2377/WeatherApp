package com.dark.weatherapp.model;

import android.graphics.Color;

public enum ColorTheme {

    //Light Themes
    BlackAndWhiteLight(0,
            Color.parseColor("#181C14"),
            Color.parseColor("#FFFFFF"),
            Color.parseColor("#EBEBEB"),
            Color.parseColor("#4D181C14")
    ),

    //Dark Themes
    BlackAndWhiteDark(1,
            Color.parseColor("#FFFFFF"),
            Color.parseColor("#171717"),
            Color.parseColor("#0f0f0f"),
            Color.parseColor("#333333")
    );

    private final int themeID;

    private final int textColor;
    private final int surface;
    private final int onSurface;
    private final int item;

    ColorTheme(int themeID, int textColor, int surface, int onSurface, int item) {
        this.themeID = themeID;
        this.textColor = textColor;
        this.surface = surface;
        this.onSurface = onSurface;
        this.item = item;
    }

    public int getThemeID() {
        return themeID;
    }

    public int getSurface() {
        return surface;
    }

    public int getOnSurface() {
        return onSurface;
    }

    public int getItem() {
        return item;
    }

    public int getTextColor() {
        return textColor;
    }
}
