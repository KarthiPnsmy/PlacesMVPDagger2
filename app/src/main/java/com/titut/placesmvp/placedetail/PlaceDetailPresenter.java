package com.titut.placesmvp.placedetail;

import android.content.Context;
import android.util.Log;

import com.titut.placesmvp.PlacesApplication;
import com.titut.placesmvp.dagger.ActivityContext;
import com.titut.placesmvp.dagger.AppComponent;
import com.titut.placesmvp.model.Place;
import com.titut.placesmvp.model.PlaceDetailResponse;
import com.titut.placesmvp.datasource.PlacesApi;
import com.titut.placesmvp.utils.Constants;
import com.titut.placesmvp.utils.PlacesCache;
import com.titut.placesmvp.utils.SharedPrefsHelper;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by karthi.ponnusamy on 31/5/17.
 */

public class PlaceDetailPresenter implements PlaceDetailContract.Presenter {
    private final PlaceDetailContract.View mView;

    @Inject
    PlacesApi mPlacesApi;

    @Inject
    PlacesCache mPlacesCache;

    @Inject
    SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    public PlaceDetailPresenter(PlaceDetailContract.View view, @ActivityContext Context context, PlacesCache placesCache) {
        this.mView = view;

        AppComponent appComponent = ((PlacesApplication) context.getApplicationContext()).getAppComponent();
        appComponent.inject(this);

        Log.d("@@##", "Last visited page = "+mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_LAST_VISITED_ACTIVITY, "NA"));
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_LAST_VISITED_ACTIVITY, "Place Detail");

        this.mView.setPresenter(this);
    }

    @Override
    public void loadPlace(String placeId) {
        if (mPlacesCache != null) {
            if (mPlacesCache.getPlaceDetailCache().get(placeId) == null) {
                fetchPlace(placeId);
            } else {
                mView.showPlace((Place) mPlacesCache.getPlaceDetailCache().get(placeId));
            }
        }
    }

    private void fetchPlace(final String placeId){
        mView.showLoading();
        mPlacesApi.getPlaceDetail(placeId).enqueue(new Callback<PlaceDetailResponse>() {
            @Override
            public void onResponse(Call<PlaceDetailResponse> call, Response<PlaceDetailResponse> response) {

                if (response.code() != 200) {
                    mView.showErrorMessage();
                } else {
                    Place place = response.body().result;
                    mView.showPlace(place);
                    mPlacesCache.addPlaceToCache(placeId, place);
                }
                mView.hideLoading();
            }

            @Override
            public void onFailure(Call<PlaceDetailResponse> call, Throwable t) {
                mView.showErrorMessage();
                mView.hideLoading();
            }
        });
    }
}
