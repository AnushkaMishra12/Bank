package com.example.bank;

import static com.example.bank.ThemeManager.setCustomizedThemes;
import static com.example.bank.ThemeStorage.getThemeColor;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoardActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomizedThemes(this, getThemeColor(this));
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().hide();

        coordinatorLayout = findViewById(R.id.container);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        setting = findViewById(R.id.setting);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.mHome);

        setting.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(i);
            finish();
        });
    }

    HomeFragment firstFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    SearchFragment searchFragment = new SearchFragment();
    ScannerFragment scannerFragment = new ScannerFragment();


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.mHome:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, firstFragment)
                        .commit();
                return true;
            case R.id.mPerson:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, profileFragment)
                        .commit();
                return true;
            case R.id.mSearch:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, searchFragment)
                        .commit();
                return true;
            case R.id.mSetting:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, scannerFragment)
                        .commit();
                return true;
        }
        return false;

    }
}