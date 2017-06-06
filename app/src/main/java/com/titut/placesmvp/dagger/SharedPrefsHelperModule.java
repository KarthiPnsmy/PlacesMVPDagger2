package com.titut.placesmvp.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.titut.placesmvp.utils.SharedPrefsHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by karthi.ponnusamy on 6/6/17.
 */

@Module
public class SharedPrefsHelperModule {
    private String PREF_NAME = "prefs";

    @Provides
    @Singleton
    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SharedPrefsHelper provideSharedPrefsHelper(SharedPreferences sharedPreferences) {
        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(sharedPreferences);
        return sharedPrefsHelper;
    }
}
