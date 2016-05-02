package com.joanfuentes.hintcase.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DimenUtils {
    public static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = 0;
        Resources resources = context.getResources();
        int orientation = context.getResources().getConfiguration().orientation;
        String orientationAttribute =
                (orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape");
        int id = resources.getIdentifier(orientationAttribute, "dimen", "android");
        if (id > 0) {
            navigationBarHeight = resources.getDimensionPixelSize(id);
        }
        return navigationBarHeight;
    }

    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources resources = context.getResources();
        int id = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (id > 0) {
            statusBarHeight = resources.getDimensionPixelSize(id);
        }
        return statusBarHeight;
    }

    public static int getStatusBarWidth(Context context) {
        int statusBarWidth = 0;
        Resources resources = context.getResources();
        int id = resources.getIdentifier("status_bar_width", "dimen", "android");
        if (id > 0) {
            statusBarWidth = resources.getDimensionPixelSize(id);
        }
        return statusBarWidth;
    }

    public static int getActionBarHeight(Context context) {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    public static int getTopBars(Context context) {
        return getStatusBarHeight(context) + getActionBarHeight(context);
    }

    public static int dipToPixels(Context context, float valueInDP) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDP, displayMetrics);
    }
}
