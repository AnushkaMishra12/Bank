package com.example.bank;

import static com.example.bank.ThemeManager.setCustomizedThemes;
import static com.example.bank.ThemeStorage.getThemeColor;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    Animation animSlide;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomizedThemes(this,getThemeColor(this));
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        Window w = getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        text=findViewById(R.id.app_name);

        Thread thread= new Thread(() -> {

            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            animSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
            Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
            text.startAnimation(animSlide);
            startActivity(intent);
            finish();
        });
        thread.start();

    }
}