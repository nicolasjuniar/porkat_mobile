package juniar.porkat.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.GetTransaksiModel;
import juniar.porkat.Model.PelangganModel;
import juniar.porkat.Presenter.ListTransaksiPresenter;
import juniar.porkat.R;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Adapter.ListTransaksiAdapter;
import juniar.porkat.View.Interface.ListTransaksiListener;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public class RiwayatTransaksiFragment extends Fragment implements ListTransaksiListener{

    @BindView(R.id.rv_transaksi)
    RecyclerView rv_transaksi;
    @BindView(R.id.tv_alert)
    TextView tv_alert;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipe_refresh_layout;

    ListTransaksiAdapter adapter;
    ListTransaksiPresenter presenter;
    PreferenceHelper preferences;
    PelangganModel model;
    int id_pelanggan;
    View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_riwayattransaksi, container, false);
            ButterKnife.bind(this,view);
            presenter=new ListTransaksiPresenter(this);
            preferences=PreferenceHelper.getInstance(getContext());
            model=new Gson().fromJson(preferences.getString("profile_pelanggan",""),PelangganModel.class);
            id_pelanggan=model.getId_pelanggan();

            presenter.getListTransaksiPelanggan(id_pelanggan);
        }

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getListTransaksiPelanggan(id_pelanggan);
            }
        });

        rv_transaksi.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipe_refresh_layout.setEnabled(topRowVerticalPosition >= 0);
            }
        });

        return view;
    }

    @Override
    public void onGetListTransaksi(boolean error, ArrayList<GetTransaksiModel> ListTransaksi, Throwable t) {
        if (!error) {
            adapter = new ListTransaksiAdapter(ListTransaksi,getActivity());
            rv_transaksi.setAdapter(adapter);
            rv_transaksi.setLayoutManager(new LinearLayoutManager(getActivity()));
            progressBar.setVisibility(View.GONE);
            swipe_refresh_layout.setRefreshing(false);
        } else {
            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            swipe_refresh_layout.setRefreshing(false);
        }
    }
}
