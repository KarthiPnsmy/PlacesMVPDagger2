package com.titut.placesmvp.places;

import com.titut.placesmvp.dagger.ActivityModule;
import com.titut.placesmvp.dagger.AppComponent;
import com.titut.placesmvp.dagger.PerActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

/**
 * Created by karthi.ponnusamy on 4/6/17.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = {PlacesFragmentModule.class, ActivityModule.class})
public interface PlacesActivityComponent {

    //PlacesPresenter getPlacesPresenter();

    void inject(PlacesActivity target);
    //void inject(PlacesPresenter target);
    //void inject(PlacesFragment __);
}
