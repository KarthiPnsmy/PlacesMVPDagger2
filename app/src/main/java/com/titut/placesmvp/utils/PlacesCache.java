package com.titut.placesmvp.utils;

import com.titut.placesmvp.model.Place;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by karthi.ponnusamy on 1/6/17.
 */

@Singleton
public class PlacesCache extends HashMap<String, Object> {

    private static PlacesCache cache;
    private HashMap<String, Place> placeDetailCache = new HashMap<>();


    @Inject
    public PlacesCache() {

    }

    public HashMap<String, Place> getPlaceDetailCache(){
        return placeDetailCache;
    }

    public void addPlaceToCache(String placeId, Place place){
        placeDetailCache.put(placeId, place);
    }
}
