package juniar.porkat.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.MenuKateringModel;
import juniar.porkat.Presenter.ListMenuPresenter;
import juniar.porkat.R;
import juniar.porkat.View.Adapter.ListMenuAdapter;
import juniar.porkat.View.Adapter.ListPilihMenu2Adapter;
import juniar.porkat.View.Interface.ListMenuListener;

/**
 * Created by Nicolas Juniar on 04/12/2017.
 */

public class PilihMenuDialog extends DialogFragment implements ListMenuListener,ListPilihMenu2Adapter.onMenuClickCallback {
    View view;
    @BindView(R.id.rv_menukatering)
    RecyclerView rv_menukatering;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    ListPilihMenu2Adapter adapter;
    ListMenuPresenter presenter;
    Bundle b;
    private int id_katering;
    GetMenuCallback callback;

    public void setCallback(GetMenuCallback callback)
    {
        this.callback=callback;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_pilihmenu, container, false);
        ButterKnife.bind(this,view);

        b = getArguments();

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);

        id_katering=b.getInt("id_katering");

        presenter =new ListMenuPresenter(this);
        presenter.getListMenu(id_katering);

        return view;
    }

    @Override
    public void onGetListMenuResponse(boolean error, ArrayList<MenuKateringModel> ListMenu, Throwable t) {
        if(!error)
        {
            adapter=new ListPilihMenu2Adapter(ListMenu,getActivity(),this);
            rv_menukatering.setAdapter(adapter);
            rv_menukatering.setLayoutManager(new LinearLayoutManager(getActivity()));
            progressBar.setVisibility(View.GONE);
        }
        else
        {
            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onMenuClickMenu(MenuKateringModel model) {
        callback.getMenu(model);
        dismiss();
    }

    public interface GetMenuCallback
    {
        void getMenu(MenuKateringModel model);
    }
}
