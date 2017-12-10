package juniar.porkat.View.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.TransaksiMenuModel;
import juniar.porkat.R;
import juniar.porkat.View.Activity.TransaksiActivity;
import juniar.porkat.View.Adapter.ListPilihMenuAdapter;
import juniar.porkat.View.Interface.TransaksiCallback;

/**
 * Created by Nicolas Juniar on 03/12/2017.
 */

public class PilihMenuFragment extends Fragment {

    @BindView(R.id.rv_menu)
    RecyclerView rv_menu;
    @BindView(R.id.btn_lanjut)
    Button btn_lanjut;

    private final int ADD_MENU=1;
    private final int GET_MENU=2;

    ArrayList<TransaksiMenuModel> listTransaksiMenu;
    ListPilihMenuAdapter adapter;
    private int n;
    TransaksiCallback callback;
    Bundle bundle;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callback = (TransaksiActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement setTotalMenu");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pilihmenu, container, false);
        ButterKnife.bind(this,view);

        listTransaksiMenu=new ArrayList<>();
        listTransaksiMenu.add(new TransaksiMenuModel());
        listTransaksiMenu.add(new TransaksiMenuModel());
        listTransaksiMenu.add(new TransaksiMenuModel());
        bundle=getArguments();

        DefaultItemAnimator animator = new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        };
        adapter=new ListPilihMenuAdapter(listTransaksiMenu,getActivity(),bundle);
        rv_menu.setAdapter(adapter);
        rv_menu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_menu.setItemAnimator(animator);

        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cekTransaksiMenu())
                {
                    ((TransaksiActivity)callback).nextTransaksi(listTransaksiMenu);
                }
                else
                {
                    Toast.makeText(getActivity(), "Isi semua menu terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public boolean cekTransaksiMenu()
    {
        boolean cek=true;
        for(TransaksiMenuModel model:listTransaksiMenu)
        {
            if(model.getType()==ADD_MENU && model.isVisibility())
            {
                cek=false;
            }
        }

        return cek;
    }

    public void setAdapterSize(int total)
    {
        if(n<total)
        {
            for(int i=n;i<total;i++)
            {
                listTransaksiMenu.get(i).setVisibility(true);
                adapter.notifyItemChanged(i);
            }
            n=total;
        }
        else
        {
            for(int i=n-1;i!=total-1;i--)
            {
                listTransaksiMenu.get(i).setVisibility(false);
                adapter.notifyItemChanged(i);
            }
            n=total;
        }
    }

}
