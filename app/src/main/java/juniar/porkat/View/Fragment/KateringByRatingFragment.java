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
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.KateringModel;
import juniar.porkat.Presenter.ListKateringPresenter;
import juniar.porkat.R;
import juniar.porkat.View.Adapter.AdapterListKatering;
import juniar.porkat.View.Interface.ListKateringView;

/**
 * Created by Nicolas Juniar on 30/10/2017.
 */

public class KateringByRatingFragment extends Fragment implements ListKateringView{

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipe_referesh_layout;
    @BindView(R.id.adapter_kateringbyrating)
    RecyclerView adapter_kateringbyrating;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    AdapterListKatering adapter;
    ListKateringPresenter presenter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_kateringbyrating, container, false);
        ButterKnife.bind(this,view);

        presenter=new ListKateringPresenter(this);
        progressBar.setVisibility(View.VISIBLE);
        presenter.getListKateringByRating();

        swipe_referesh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getListKateringByRating();
            }
        });

        adapter_kateringbyrating.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipe_referesh_layout.setEnabled(topRowVerticalPosition >= 0);
            }
        });

        return view;
    }

    @Override
    public void onGetListKateringResponse(boolean error, ArrayList<KateringModel> ListKatering, Throwable t) {
        if(!error)
        {
            adapter=new AdapterListKatering(ListKatering,getActivity());
            adapter_kateringbyrating.setAdapter(adapter);
            adapter_kateringbyrating.setLayoutManager(new LinearLayoutManager(getActivity()));
            progressBar.setVisibility(View.GONE);
            swipe_referesh_layout.setRefreshing(false);
        }
        else
        {
            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
