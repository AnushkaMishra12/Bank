package com.example.bank;

import static androidx.biometric.BiometricPrompt.ERROR_NEGATIVE_BUTTON;

import static com.example.bank.ThemeManager.setCustomizedThemes;
import static com.example.bank.ThemeStorage.getThemeColor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    private BiometricPrompt biometricPrompt = null;
    private final Executor executor = Executors.newSingleThreadExecutor();
    RecyclerView banner;
    ImageView auth;
    TextView text;

    Dialog customDialog;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomizedThemes(this, getThemeColor(this));
        setContentView(R.layout.login_activity);

        getSupportActionBar().hide();

        banner = findViewById(R.id.banner);
        auth = findViewById(R.id.authenticate);
        text = findViewById(R.id.text);


        auth.setOnClickListener(v -> checkAndAuthenticate());
        if (biometricPrompt == null) {
            biometricPrompt = new BiometricPrompt(this, executor, callback);
        }
        text.setOnClickListener(v -> customDialog.show());

        customDialog = new Dialog(LoginActivity.this);
        customDialog.setContentView(R.layout.custom_dailog);
        final Window window = customDialog.getWindow();
        customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        submit = customDialog.findViewById(R.id.btnSubmit);


        submit.setOnClickListener(view -> {
            Toast.makeText(LoginActivity.this, "You Click On Submit Button", Toast.LENGTH_SHORT).show();
            customDialog.dismiss();
        });

        ArrayList<BannerData> list = new ArrayList<>();

        list.add(new BannerData(R.drawable.img_3));
        list.add(new BannerData(R.drawable.img_3));
        list.add(new BannerData(R.drawable.img_3));
        list.add(new BannerData(R.drawable.img_3));
        list.add(new BannerData(R.drawable.img_3));

        RecyclerBannerAdapter recyclerBannerAdapter = new RecyclerBannerAdapter(list);
        banner.setAdapter(recyclerBannerAdapter);
        int COLUMN_COUNT1 = 1;
        GridLayoutManager manager = new GridLayoutManager(this, COLUMN_COUNT1, GridLayoutManager.HORIZONTAL, false);
        banner.setLayoutManager(manager);

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (manager.findLastCompletelyVisibleItemPosition() < (recyclerBannerAdapter.getItemCount() - 1)) {
                    manager.smoothScrollToPosition(banner, new RecyclerView.State(),
                            manager.findLastCompletelyVisibleItemPosition() + 1);
                } else {
                    manager.smoothScrollToPosition(banner, new RecyclerView.State(), 0);
                }
            }

        }, 0, 3000);
    }

    @SuppressLint("SwitchIntDef")
    private void checkAndAuthenticate() {
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {

            case BiometricManager.BIOMETRIC_SUCCESS:
                BiometricPrompt.PromptInfo promptInfo = buildBiometricPrompt();
                biometricPrompt.authenticate(promptInfo);
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                snack("Biometric Authentication currently unavailable");
                break;

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                snack("Your device doesn't support Biometric Authentication");
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                snack("Your device doesn't have any fingerprint enrolled");
                break;
        }
    }

    private BiometricPrompt.PromptInfo buildBiometricPrompt() {
        return new BiometricPrompt.PromptInfo.Builder().setTitle("Login")
                .setSubtitle("FingerPrint Authentication").setDescription
                        ("Please place your finger on the sensor to unlock")
                .setNegativeButtonText("Cancel").build();
    }

    private void snack(String text) {
        View view = findViewById(R.id.view);
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        snackbar.setTextColor(getResources().getColor(R.color.white));
        snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    private final BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
        @Override
        public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
            if (errorCode == ERROR_NEGATIVE_BUTTON && biometricPrompt != null)
                biometricPrompt.cancelAuthentication();
            snack((String) errString);
        }

        @Override
        public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
            snack("Authenticated");
            Intent i = new Intent(LoginActivity.this, DashBoardActivity.class);
            startActivity(i);
        }

        @Override
        public void onAuthenticationFailed() {
            snack("The FingerPrint was not recognized.Please Try Again!");
        }
    };
}
