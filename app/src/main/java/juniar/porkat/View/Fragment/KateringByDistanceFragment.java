package juniar.porkat.View.Fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.KateringModel;
import juniar.porkat.Presenter.ListKateringPresenter;
import juniar.porkat.R;
import juniar.porkat.Utils.MyLocation;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Adapter.ListKateringAdapter;
import juniar.porkat.View.Interface.KateringListener;

/**
 * Created by Nicolas Juniar on 30/10/2017.
 */

public class KateringByDistanceFragment extends Fragment implements KateringListener {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipe_refresh_layout;
    @BindView(R.id.rv_katering)
    RecyclerView rv_katering;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    ListKateringAdapter adapter;
    ListKateringPresenter presenter;
    PreferenceHelper preferences;
    double longitude;
    double latitude;

    private View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_kateringbydistance, container, false);
            presenter = new ListKateringPresenter(this);
            preferences = PreferenceHelper.getInstance(getContext());
            longitude = Double.parseDouble(preferences.getString("my_longitude", "0"));
            latitude = Double.parseDouble(preferences.getString("my_latitude", "0"));
            presenter.getListKateringByDistance(latitude, longitude);
        }

        ButterKnife.bind(this, view);

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMyLocation();
                presenter.getListKateringByDistance(latitude, longitude);
            }
        });

        rv_katering.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipe_refresh_layout.setEnabled(topRowVerticalPosition >= 0);
            }
        });

        return view;
    }

    public void getMyLocation()
    {
        MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
            @Override
            public void gotLocation(Location location){
                LatLng loc =new LatLng(location.getLatitude(),location.getLongitude());
                preferences.putString("my_longitude",String.valueOf(loc.longitude));
                preferences.putString("my_latitude",String.valueOf(loc.latitude));
                latitude=loc.latitude;
                longitude=loc.longitude;
                Toast.makeText(getActivity(), "lokasi didapatkan", Toast.LENGTH_SHORT).show();

            }
        };
        MyLocation myLocation = new MyLocation(getActivity());
        myLocation.getLocation(getActivity(), locationResult);
    }


    @Override
    public void onGetListKateringResponse(boolean error, ArrayList<KateringModel> ListKatering, Throwable t) {
        if (!error) {
            adapter = new ListKateringAdapter(ListKatering, getActivity(), preferences);
            rv_katering.setAdapter(adapter);
            rv_katering.setLayoutManager(new LinearLayoutManager(getActivity()));
            progressBar.setVisibility(View.GONE);
            swipe_refresh_layout.setRefreshing(false);
        } else {
            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            swipe_refresh_layout.setRefreshing(false);
        }
    }
}
