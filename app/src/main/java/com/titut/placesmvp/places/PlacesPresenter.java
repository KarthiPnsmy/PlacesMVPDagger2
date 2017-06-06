package com.titut.placesmvp.places;

import android.content.Context;
import android.util.Log;

import com.titut.placesmvp.PlacesApplication;
import com.titut.placesmvp.dagger.ActivityContext;
import com.titut.placesmvp.dagger.AppComponent;
import com.titut.placesmvp.model.Place;
import com.titut.placesmvp.model.PlaceResponse;
import com.titut.placesmvp.datasource.PlacesApi;
import com.titut.placesmvp.utils.Constants;
import com.titut.placesmvp.utils.PlacesCache;
import com.titut.placesmvp.utils.SharedPrefsHelper;

import java.util.List;

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

public class PlacesPresenter implements PlacesContract.Presenter {

    PlacesContract.View mView;

    @Inject
    PlacesApi placesApi;

    @Inject
    Retrofit retrofit;

    @Inject
    SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    public PlacesPresenter(PlacesCache placesCache, PlacesContract.View view, @ActivityContext Context context) {
        this.mView = view;

        AppComponent appComponent = ((PlacesApplication) context.getApplicationContext()).getAppComponent();
        appComponent.inject(this);

        Log.d("@@##", "PlacesPresenter >> last visited "+mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_LAST_VISITED_ACTIVITY, "NA"));
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_LAST_VISITED_ACTIVITY, "Places Listing");

        mView.setPresenter(this);
    }

    @Override
    public void loadPlaces() {
        mView.showLoading();
        placesApi.getPlaces().enqueue(new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {

                if (response.code() != 200) {
                    mView.showErrorMessage();
                } else {
                    List<Place> places = response.body().results;
                    mView.showPlaces(places);

                }
                mView.hideLoading();
            }

            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {
                mView.showErrorMessage();
                mView.hideLoading();
            }
        });
    }
}
