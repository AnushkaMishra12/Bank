package com.example.bank;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

public class DialogManager {
    public static void showCustomAlertDialog(Context context, final ColorDialogCallback callback) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.color_palette);
        final TextView dayTheme = dialog.findViewById(R.id.redColor);
        dayTheme.setOnClickListener(view -> {
            callback.onChosen(dayTheme.getText().toString());
            dialog.cancel();

        });
        final TextView blackColor = dialog.findViewById(R.id.blackColor);
        blackColor.setOnClickListener(view -> {
            callback.onChosen(blackColor.getText().toString());
            dialog.cancel();

        });
        final TextView blueColor = dialog.findViewById(R.id.blueColor);
        blueColor.setOnClickListener(view -> {
            callback.onChosen(blueColor.getText().toString());
            dialog.cancel();
        });
        dialog.show();
    }
}

