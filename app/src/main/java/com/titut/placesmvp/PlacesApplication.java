package com.titut.placesmvp;


import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.titut.placesmvp.dagger.AppComponent;
import com.titut.placesmvp.dagger.AppModule;
import com.titut.placesmvp.dagger.DaggerAppComponent;

/**
 * Created by karthi.ponnusamy on 2/6/17.
 */

public class PlacesApplication extends MultiDexApplication {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    protected AppComponent initDagger(PlacesApplication application){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

}
