package com.dark.weatherapp.model;

import com.dark.weatherapp.R;

public enum Fonts {

    DOTTED("Dotted", R.font.dotted),
    AMIKO("Amiko", R.font.amiko),
    JERSEY("Jersey", R.font.jersey_regular),
    MADIMI("Madimi", R.font.madimi_one);

    private final String fontName;
    private final int fontID;

    Fonts(String fontName, int fontID) {
        this.fontName = fontName;
        this.fontID = fontID;
    }

    public int getFontID() {
        return fontID;
    }

    public String getFontName() {
        return fontName;
    }
}
