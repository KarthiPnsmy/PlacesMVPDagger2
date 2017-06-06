package com.titut.placesmvp.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.titut.placesmvp.utils.PlacesCache;
import com.titut.placesmvp.utils.SharedPrefsHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by karthi.ponnusamy on 2/6/17.
 */

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideApplication() {
        return application;
    }
}
