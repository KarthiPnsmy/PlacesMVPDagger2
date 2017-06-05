package com.titut.placesmvp.places;

import com.titut.placesmvp.model.Place;

import java.util.List;

/**
 * Created by karthi.ponnusamy on 31/5/17.
 */

public interface PlacesContract {

    interface View {
        void showPlaces(List<Place> places);
        void showErrorMessage();
        void showLoading();
        void hideLoading();
        void setPresenter(PlacesContract.Presenter presenter);
    }

    interface Presenter {
        void loadPlaces();
    }
}
