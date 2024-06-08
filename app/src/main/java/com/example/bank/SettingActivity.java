package com.example.bank;

import static com.example.bank.DialogManager.showCustomAlertDialog;
import static com.example.bank.ThemeManager.setCustomizedThemes;
import static com.example.bank.ThemeStorage.getThemeColor;
import static com.example.bank.ThemeStorage.setThemeColor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SettingActivity extends AppCompatActivity {
    private static final String TAG = "SettingActivity";
    Button button;
    TextView color_choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomizedThemes(this, getThemeColor(this));
        setContentView(R.layout.activity_setting);
        getSupportActionBar().hide();
        color_choose = findViewById(R.id.color_choose);
        button = findViewById(R.id.button_color);
        button.setOnClickListener(v -> {
            Intent i = new Intent(SettingActivity.this,DashBoardActivity.class);
            startActivity(i);
            finish();
        });
        color_choose.setOnClickListener(v -> {
            chooseColor();
        });
    }

    private void chooseColor() {
        showCustomAlertDialog(this, chosenColor -> {
            if (chosenColor.equals(getThemeColor(getApplicationContext()))) {
              recreate();
            }
            Log.d(TAG, chosenColor);
            setThemeColor(getApplicationContext(), chosenColor);
            setCustomizedThemes(getApplicationContext(), chosenColor);
            recreate();

        });
    }

}
