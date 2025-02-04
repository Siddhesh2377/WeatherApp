package com.dark.weatherapp.theme;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import androidx.core.content.res.ResourcesCompat;
import com.dark.weatherapp.model.ColorTheme;
import com.dark.weatherapp.model.Fonts;

public class ThemeManager {
    private final Configuration configuration;
    private final Activity activity;
    private final Fonts fontType;
    private int textColor;
    private int surface;
    private int onSurface;
    private int item;
    public ThemeManager(Activity activity) {
        this.activity = activity;
        this.configuration = Resources.getSystem().getConfiguration();
        this.fontType = Fonts.DOTTED;
        setSystemTheme();
    }

    public int getTextColor() {
        return textColor;
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

    public Typeface getFont() {
        if (fontType != null) {
            return ResourcesCompat.getFont(activity.getApplicationContext(), fontType.getFontID());
        }
        return Typeface.DEFAULT;
    }

    public void setSystemTheme() {
        int nightMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
            ColorTheme colorTheme = ColorTheme.BlackAndWhiteDark;
            textColor = colorTheme.getTextColor();
            surface = colorTheme.getSurface();
            onSurface = colorTheme.getOnSurface();
            item = colorTheme.getItem();

            activity.getWindow().setStatusBarColor(colorTheme.getSurface());
            activity.getWindow().setNavigationBarColor(colorTheme.getOnSurface());
        } else {
            ColorTheme colorTheme = ColorTheme.BlackAndWhiteLight;
            textColor = colorTheme.getTextColor();
            surface = colorTheme.getSurface();
            onSurface = colorTheme.getOnSurface();
            item = colorTheme.getItem();

            activity.getWindow().setStatusBarColor(colorTheme.getSurface());
            activity.getWindow().setNavigationBarColor(colorTheme.getOnSurface());
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
