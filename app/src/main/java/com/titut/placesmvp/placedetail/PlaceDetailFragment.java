package com.titut.placesmvp.placedetail;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.titut.placesmvp.PlacesApplication;
import com.titut.placesmvp.dagger.AppComponent;
import com.titut.placesmvp.model.Place;
import com.titut.placesmvp.R;
import com.titut.placesmvp.utils.SharedPrefsHelper;

import javax.inject.Inject;

/**
 * Created by karthi.ponnusamy on 31/5/17.
 */

public class PlaceDetailFragment extends Fragment implements PlaceDetailContract.View {

    private View mRootView;
    private ProgressDialog mProgressDialog;
    private Place mPlace;
    private String mPlaceId;
    private TextView mTextViewPlaceName;
    private TextView mTextViewPlaceAddress;
    private TextView mTextViewPlacePhoneNo;

    @Inject
    SharedPrefsHelper mSharedPrefsHelper;

    private PlaceDetailContract.Presenter mPresenter;
    public PlaceDetailFragment(){

    }

    public static PlaceDetailFragment newInstance(String placeId) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(PlaceDetailActivity.PLACE_ID, placeId);

        PlaceDetailFragment fragment = new PlaceDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent appComponent = ((PlacesApplication) getActivity().getApplicationContext()).getAppComponent();
        appComponent.inject(this);

        mPlaceId = getArguments().getString(PlaceDetailActivity.PLACE_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_place_detail, container, false);

        mTextViewPlaceName = (TextView) mRootView.findViewById(R.id.place_name);
        mTextViewPlaceAddress = (TextView) mRootView.findViewById(R.id.place_detail_address);
        mTextViewPlacePhoneNo = (TextView) mRootView.findViewById(R.id.place_phoneno);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading...");

        Log.d("@@##", "PlaceDetailFragment >> last visited "+mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_LAST_VISITED_ACTIVITY, "NA"));

        mPresenter.loadPlace(mPlaceId);

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showPlace(Place place) {
        if (place != null){
            mTextViewPlaceName.setText(place.name);
            mTextViewPlaceAddress.setText(place.formatted_address);
            mTextViewPlacePhoneNo.setText(place.international_phone_number);
        } else {
            showErrorMessage();
        }
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        if (mProgressDialog != null){
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void setPresenter(PlaceDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
