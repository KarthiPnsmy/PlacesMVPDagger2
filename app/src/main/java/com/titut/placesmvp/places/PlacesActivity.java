package com.titut.placesmvp.places;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.titut.placesmvp.PlacesApplication;
import com.titut.placesmvp.R;
import com.titut.placesmvp.dagger.ActivityModule;

import javax.inject.Inject;

public class PlacesActivity extends AppCompatActivity {

    @Inject
    PlacesPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        PlacesFragment placesFragment = (PlacesFragment) getSupportFragmentManager().findFragmentById(R.id.contentContainer);
        if (placesFragment == null) {
            placesFragment = PlacesFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentContainer, placesFragment);
            transaction.commit();
        }

        DaggerPlacesActivityComponent.builder()
                .appComponent(((PlacesApplication) getApplication()).getAppComponent())
                .activityModule(new ActivityModule(this))
                .placesFragmentModule(new PlacesFragmentModule(placesFragment))
                .build().inject(this);

    }
}
