package com.abdulkarim.ocrkameratranslate.Utility;

import android.app.AlertDialog;
import android.content.Context;

public class Dialog {
    public static void showDialog (Context context, int judul, int pesan){
        AlertDialog.Builder builder =  new AlertDialog.Builder(context);
        builder.setTitle(judul);
        builder.setMessage(pesan);
        builder.setPositiveButton("OK", null);
        builder.show();

    }
}