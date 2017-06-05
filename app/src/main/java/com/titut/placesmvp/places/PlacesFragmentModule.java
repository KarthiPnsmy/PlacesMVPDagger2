package com.titut.placesmvp.places;

import com.titut.placesmvp.places.PlacesContract;
import com.titut.placesmvp.places.PlacesPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by karthi.ponnusamy on 2/6/17.
 */

@Module
public class PlacesFragmentModule {
    private final PlacesContract.View mView;

    public PlacesFragmentModule(PlacesContract.View view){
        this.mView = view;
    }

    @Provides
    PlacesContract.View providePlacesFragment(){
        return mView;
    }
}
