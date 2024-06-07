package com.example.bank;

import android.content.Context;

public class ThemeManager {
    public static void setCustomizedThemes(Context context, String theme){
        switch (theme){
            case "orange":
                context.setTheme(R.style.AppTheme);
                break;
            case "black":
                context.setTheme(R.style.DarkTheme);
                break;
            case "red":
                context.setTheme(R.style.BlueTheme);
                break;
        }
    }

}
