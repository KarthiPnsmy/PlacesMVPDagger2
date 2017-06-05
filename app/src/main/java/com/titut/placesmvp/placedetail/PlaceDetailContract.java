package com.titut.placesmvp.placedetail;

import com.titut.placesmvp.model.Place;
import com.titut.placesmvp.places.PlacesContract;

/**
 * Created by karthi.ponnusamy on 31/5/17.
 */

public interface PlaceDetailContract {

    interface View {
        void showPlace(Place place);
        void showErrorMessage();
        void showLoading();
        void hideLoading();
        void setPresenter(PlaceDetailContract.Presenter presenter);
    }

    interface Presenter {
        void loadPlace(String placeId);
    }
}
