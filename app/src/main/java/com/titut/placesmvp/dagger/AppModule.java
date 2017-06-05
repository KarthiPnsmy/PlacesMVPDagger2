package com.titut.placesmvp.dagger;

import android.app.Application;
import android.content.Context;

import com.titut.placesmvp.utils.PlacesCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by karthi.ponnusamy on 2/6/17.
 */

@Module
public class AppModule {

    private Application application;

    private PlacesCache cache = new PlacesCache();

    public AppModule(Application application){
        this.application = application;
    }

    @Singleton
    @Provides
    public Context provideApplication() {
        return application;
    }

//    @Singleton
//    @Provides
//    PlacesCache providePlacesCache() {
//        return cache;
//    }
}
