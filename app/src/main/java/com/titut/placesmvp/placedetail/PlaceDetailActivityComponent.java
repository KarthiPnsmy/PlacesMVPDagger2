package com.titut.placesmvp.placedetail;

import com.titut.placesmvp.dagger.ActivityModule;
import com.titut.placesmvp.dagger.AppComponent;
import com.titut.placesmvp.dagger.PerActivity;
import com.titut.placesmvp.places.PlacesActivity;
import com.titut.placesmvp.places.PlacesFragmentModule;

import dagger.Component;

/**
 * Created by karthi.ponnusamy on 4/6/17.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = {PlaceDetailFragmentModule.class, ActivityModule.class})
public interface PlaceDetailActivityComponent {
    void inject(PlaceDetailActivity target);
}
