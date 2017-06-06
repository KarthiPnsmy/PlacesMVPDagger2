package com.titut.placesmvp.utils;

import android.content.SharedPreferences;

public class SharedPrefsHelper {

    public static String PREF_KEY_LAST_VISITED_ACTIVITY = "page-name";

    private SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public void put(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public void deleteSavedData(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }
}