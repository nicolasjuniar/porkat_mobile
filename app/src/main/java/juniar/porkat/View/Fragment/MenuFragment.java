package juniar.porkat.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.MenuKateringModel;
import juniar.porkat.Presenter.ListMenuPresenter;
import juniar.porkat.R;
import juniar.porkat.View.Adapter.ListMenuAdapter;
import juniar.porkat.View.Interface.ListMenuListener;

/**
 * Created by Nicolas Juniar on 01/11/2017.
 */

public class MenuFragment extends Fragment implements ListMenuListener {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipe_refresh_layout;
    @BindView(R.id.rv_menukatering)
    RecyclerView rv_menukatering;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.ll_menu404)
    LinearLayout ll_menu404;

    ListMenuAdapter adapter;
    ListMenuPresenter presenter;
    Bundle b;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu, container, false);

        ButterKnife.bind(this,view);
        presenter =new ListMenuPresenter(this);

        b = getArguments();

        presenter.getListMenu(b.getInt("id_katering"));

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getListMenu(b.getInt("id_katering"));
            }
        });

        rv_menukatering.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
    public void onGetListMenuResponse(boolean error, ArrayList<MenuKateringModel> ListMenu, Throwable t) {
        if(!error)
        {
            if(ListMenu.size()==0)
            {
                ll_menu404.setVisibility(View.VISIBLE);
            }
            else
            {
                ll_menu404.setVisibility(View.GONE);
            }
            adapter=new ListMenuAdapter(ListMenu,getActivity());
            rv_menukatering.setAdapter(adapter);
            rv_menukatering.setLayoutManager(new LinearLayoutManager(getActivity()));
            progressBar.setVisibility(View.GONE);
            swipe_refresh_layout.setRefreshing(false);
        }
        else
        {
            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            swipe_refresh_layout.setRefreshing(false);
        }
    }
}