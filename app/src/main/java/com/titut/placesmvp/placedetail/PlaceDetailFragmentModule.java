package com.titut.placesmvp.placedetail;

import com.titut.placesmvp.places.PlacesContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by karthi.ponnusamy on 2/6/17.
 */

@Module
public class PlaceDetailFragmentModule {
    private final PlaceDetailContract.View mView;

    public PlaceDetailFragmentModule(PlaceDetailContract.View view){
        this.mView = view;
    }

    @Provides
    PlaceDetailContract.View providePlacesFragment(){
        return mView;
    }
}
