package com.titut.placesmvp.dagger;

import com.titut.placesmvp.datasource.PlacesApi;
import com.titut.placesmvp.placedetail.PlaceDetailPresenter;
import com.titut.placesmvp.places.PlacesActivity;
import com.titut.placesmvp.places.PlacesContract;
import com.titut.placesmvp.places.PlacesFragment;
import com.titut.placesmvp.places.PlacesPresenter;
import com.titut.placesmvp.utils.PlacesCache;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by karthi.ponnusamy on 2/6/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    Retrofit retrofit();
    PlacesApi providePlacesApi();
    PlacesCache providePlacesCache();

    void inject(PlacesPresenter target);
    void inject(PlaceDetailPresenter target);
}
