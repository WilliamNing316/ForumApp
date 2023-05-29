package com.example.ngm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;

public class PopupWindow extends Dialog {
    private Context mContext;
    public PopupWindow(@NonNull Context context, int theme){
        super(context, theme);
        this.mContext = context;
    }

    public void show(Context context, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("是", listener)
                .setNegativeButton("否", listener);
        builder.create().show();
    }

}
