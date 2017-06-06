package com.titut.placesmvp.places;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.titut.placesmvp.placedetail.PlaceDetailActivity;
import com.titut.placesmvp.utils.SharedPrefsHelper;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by karthi.ponnusamy on 31/5/17.
 */

public class PlacesFragment extends Fragment implements PlacesContract.View {

    private View mRootView;
    private RecyclerView mPlacesRecyclerView;
    private ProgressDialog mProgressDialog;

    PlacesContract.Presenter mPresenter;

    @Inject
    SharedPrefsHelper mSharedPrefsHelper;

    public PlacesFragment(){

    }

    public static PlacesFragment newInstance() {
        return new PlacesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent appComponent = ((PlacesApplication) getActivity().getApplicationContext()).getAppComponent();
        appComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_places_list, container, false);

        mPlacesRecyclerView = (RecyclerView) mRootView.findViewById(R.id.places_recyclerView);
        mPlacesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading...");

        Log.d("@@##", "PlacesFragment >> last visited "+mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_LAST_VISITED_ACTIVITY, "NA"));

        mPresenter.loadPlaces();

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
    public void showPlaces(List<Place> places) {
        mPlacesRecyclerView.setAdapter(new PlacesAdapter(places));
        mPlacesRecyclerView.getAdapter().notifyDataSetChanged();

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
    public void setPresenter(PlacesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void launchPlaceDetail(Place place) {
        PlaceDetailActivity.start(getActivity(), place.place_id);
    }

    class PlacesAdapter extends RecyclerView.Adapter<PlaceViewHolder> {
        private List<Place> placeList;

        PlacesAdapter(List<Place> foodzItemList) {
            this.placeList = foodzItemList;
        }

        @Override
        public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new PlaceViewHolder(inflater.inflate(R.layout.list_item_place, parent, false));
        }

        @Override
        public void onBindViewHolder(PlaceViewHolder holder, int position) {
            final Place placeItem = placeList.get(position);
            holder.getPlaceName().setText(placeItem.name);
            holder.getPlaceAddress().setText(placeItem.vicinity);
            holder.getContainer().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchPlaceDetail(placeItem);
                }
            });
        }

        @Override
        public int getItemCount() {
            return placeList.size();
        }
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {

        private ViewGroup container;
        private TextView placeName;
        private TextView placeAddress;

        PlaceViewHolder(View view) {
            super(view);
            container = (ViewGroup) view.findViewById(R.id.list_item_place_container);
            placeName = (TextView) view.findViewById(R.id.place_name);
            placeAddress = (TextView) view.findViewById(R.id.place_address);
        }

        public ViewGroup getContainer() {
            return container;
        }

        public TextView getPlaceName() {
            return placeName;
        }

        public TextView getPlaceAddress() {
            return placeAddress;
        }
    }
}
