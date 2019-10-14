package com.abdulkarim.ocrkameratranslate.slider;

import android.content.Context;
import android.content.SharedPreferences;

class SharedPreference {
    private final static String PREF_FILE = "PREF";

    void setStatus(Context context, String key, String value){
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    String getStatus(Context context, String key, String defValue){
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        return settings.getString(key, defValue);
    }

}
