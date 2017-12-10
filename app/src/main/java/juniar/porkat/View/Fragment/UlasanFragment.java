package juniar.porkat.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.GetUlasanResponse;
import juniar.porkat.Model.InsertUlasanResponse;
import juniar.porkat.Model.UlasanModel;
import juniar.porkat.Model.UpdateUlasanResponse;
import juniar.porkat.Presenter.UlasanPresenter;
import juniar.porkat.R;
import juniar.porkat.View.Adapter.ListUlasanAdapter;
import juniar.porkat.View.Interface.ListUlasanListener;
import juniar.porkat.View.Interface.RefreshUlasanListener;

/**
 * Created by Nicolas Juniar on 01/11/2017.
 */

public class UlasanFragment extends Fragment implements ListUlasanListener,RefreshUlasanListener {

    //cardview ulasan
    @BindView(R.id.cv_ulasan)
    CardView cv_ulasan;
    @BindView(R.id.rb_ulasan)
    RatingBar rb_ulasan;
    @BindView(R.id.tv_ulasan)
    TextView tv_ulasan;
    @BindView(R.id.img_editulasan)
    ImageView img_editulasan;

    ///recycleview ulasan
    @BindView(R.id.rv_ulasan)
    RecyclerView rv_ulasan;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipe_refresh_layout;

    UlasanPresenter presenter;
    ListUlasanAdapter adapter;
    private Bundle bundle;
    int id_katering,id_pelanggan;
    String nama_lengkap;
    boolean session;
    ArrayList<UlasanModel> listUlasan;

    UlasanModel myUlasan;
    private FragmentManager fm;
    private UlasanDialog ulasanDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_ulasan, container, false);
        ButterKnife.bind(this,view);

        presenter=new UlasanPresenter(this);
        listUlasan=new ArrayList<>();
        myUlasan=new UlasanModel();

        bundle = getArguments();
        id_katering=bundle.getInt("id_katering");
        id_pelanggan=bundle.getInt("id_pelanggan");
        nama_lengkap=bundle.getString("nama_lengkap");
        session=bundle.getBoolean("session");

        if(!session)
        {
            cv_ulasan.setVisibility(View.GONE);
        }
        fm =getFragmentManager();
        ulasanDialog =new UlasanDialog();
        ulasanDialog.setTargetFragment(UlasanFragment.this,1);

        presenter.getUlasan(id_katering,id_pelanggan);

        img_editulasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("tipe","edit");
                bundle.putString("ulasan",new Gson().toJson(myUlasan));
                ulasanDialog.setArguments(bundle);
                ulasanDialog.show(fm,"edit ulasan");
            }
        });

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getUlasan(id_katering,id_pelanggan);
            }
        });

        rv_ulasan.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipe_refresh_layout.setEnabled(topRowVerticalPosition >= 0);
            }
        });

        return view;
    }

    private static String getDateTimeNow(){
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        DateTime dateTime  = DateTime.parse(String.valueOf(new DateTime()), DateTimeFormat.forPattern(pattern));
        return dateTime.toString("yyyy-MM-dd HH:mm:ss");
    }

    public void clearUlasan()
    {
        img_editulasan.setVisibility(View.GONE);
        rb_ulasan.setRating(0.0f);
        tv_ulasan.setText("Tambahkan Ulasan");
        cv_ulasan.setClickable(true);
    }

    @Override
    public void onGetUlasanResponse(boolean error, GetUlasanResponse response, Throwable t) {
        if(!error)
        {
            myUlasan=response.getUlasanpelanggan();
            if(myUlasan==null)
            {
                clearUlasan();
                cv_ulasan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bundle.putString("tipe","add");
                        ulasanDialog.setArguments(bundle);
                        ulasanDialog.show(fm,"edit ulasan");
                    }
                });
            }
            else
            {
                setUlasan(myUlasan);
                img_editulasan.setVisibility(View.VISIBLE);
                cv_ulasan.setClickable(false);
            }
            listUlasan=response.getListulasan();
            adapter=new ListUlasanAdapter(listUlasan,getActivity());
            rv_ulasan.setAdapter(adapter);
            rv_ulasan.setLayoutManager(new LinearLayoutManager(getActivity()));
            progressBar.setVisibility(View.GONE);
            swipe_refresh_layout.setRefreshing(false);
        }
        else
        {
            progressBar.setVisibility(View.GONE);
            swipe_refresh_layout.setRefreshing(false);
            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("onGetUlasanResponse: ",t.getMessage());
        }
    }

    public void setUlasan(UlasanModel ulasan)
    {
        tv_ulasan.setText(ulasan.getUlasan());
        rb_ulasan.setRating(ulasan.getRating());
    }

    @Override
    public void onInsertedUlasan(InsertUlasanResponse response) {
        myUlasan=new UlasanModel(response.getId_ulasan(),response.getUlasan(),response.getRating(),getDateTimeNow(),"",nama_lengkap);
        listUlasan.add(0,myUlasan);
        adapter.notifyItemInserted(0);
        rv_ulasan.smoothScrollToPosition(0);
        setUlasan(myUlasan);
        img_editulasan.setVisibility(View.VISIBLE);
        cv_ulasan.setClickable(false);
    }

    @Override
    public void onUpdatedUlasan(UpdateUlasanResponse response) {
        int position=listUlasan.indexOf(myUlasan);
        listUlasan.remove(position);
        adapter.notifyItemRemoved(position);
        myUlasan.setWaktu_ulasan(getDateTimeNow());
        myUlasan.setUlasan(response.getUlasan());
        myUlasan.setRating(response.getRating());
        setUlasan(myUlasan);
        listUlasan.add(0,myUlasan);
        adapter.notifyItemInserted(0);
        rv_ulasan.smoothScrollToPosition(0);
    }

    @Override
    public void onDeletedUlasan() {
        clearUlasan();
        cv_ulasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("tipe","add");
                ulasanDialog.setArguments(bundle);
                ulasanDialog.show(fm,"edit ulasan");
            }
        });
        int position=listUlasan.indexOf(myUlasan);
        listUlasan.remove(position);
        adapter.notifyItemRemoved(position);
    }
}