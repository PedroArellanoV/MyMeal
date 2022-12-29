package com.example.mymealsproyect.utils;


import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private final String PREFS_NAME = "my_meal_prefs";


    private Context ctx;

    public PreferenceManager(Context ctx) {
        this.ctx = ctx;
    }

    public String getStringPref(String key) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public void setStringPref(String key, String value) {
        if(value == null){
            value = "";
        }

        SharedPreferences.Editor editor = ctx.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public boolean getBoolPref(String key) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        try{
            return prefs.getBoolean(key,false);
        }catch (Exception e){
            return false;
        }
    }

    public void setBoolPref(String key, boolean value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public int getIntPref(String key){
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }

    public void setIntPref(String key, int value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }



}
