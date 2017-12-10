package juniar.porkat.View.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.MapsFragment;
import juniar.porkat.Model.DetailTransaksiModel;
import juniar.porkat.Model.PelangganModel;
import juniar.porkat.Model.TransaksiMenuModel;
import juniar.porkat.Model.TransaksiRequest;
import juniar.porkat.Presenter.TransaksiPresenter;
import juniar.porkat.R;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Fragment.DetailTransaksiFragment;
import juniar.porkat.View.Fragment.PilihLokasiFragment;
import juniar.porkat.View.Fragment.PilihMenuFragment;
import juniar.porkat.View.Interface.TransaksiCallback;
import juniar.porkat.View.Interface.TransaksiListener;


/**
 * Created by Nicolas Juniar on 03/12/2017.
 */

public class TransaksiActivity extends AppCompatActivity implements DetailTransaksiFragment.setTotalMenu,TransaksiCallback,TransaksiListener{

    @BindView(R.id.img_detailtransaksi)
    ImageView img_detailtransaksi;
    @BindView(R.id.img_pilihmenu)
    ImageView img_pilihmenu;
    @BindView(R.id.img_map)
    ImageView img_map;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;

    public final int DESKRIPSI=1;
    public final int MENU=2;
    public final int MAP=3;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    DetailTransaksiFragment detailTransaksiFragment;
    PilihMenuFragment pilihMenuFragment;
    PilihLokasiFragment pilihLokasiFragment;
    Bundle bundle;

    private String TAG=getClass().getSimpleName();

    TransaksiRequest requestBody;

    int position=DESKRIPSI;
    int id_katering,id_pelanggan,total,lama_pesan;
    ArrayList<TransaksiMenuModel> listMenuModel;
    DateTime tgl_mulai;
    PreferenceHelper preferences;

    TransaksiPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pemesanan Katering");
        bundle = getIntent().getExtras();
        id_katering=bundle.getInt("id_katering");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        preferences=PreferenceHelper.getInstance(getApplicationContext());
        PelangganModel model=new Gson().fromJson(preferences.getString("profile_pelanggan",""),PelangganModel.class);
        id_pelanggan=model.getId_pelanggan();
        detailTransaksiFragment=new DetailTransaksiFragment();
        pilihMenuFragment=new PilihMenuFragment();
        pilihMenuFragment.setArguments(bundle);
        pilihLokasiFragment=new PilihLokasiFragment();
        requestBody=new TransaksiRequest();
        presenter=new TransaksiPresenter(this);

        requestBody.getPesan().setId_katering(id_katering);
        requestBody.getPesan().setId_pelanggan(id_pelanggan);

        setFragmentTransaction();
        setTransaksi(position);
    }

    @Override
    public void onBackPressed() {
        if(position!=DESKRIPSI)
        {
            position--;
            setTransaksi(position);
        }
        else
        {
            super.onBackPressed();
        }
    }

    public void setTransaksi(int transaksi)
    {
        fragmentTransaction=fragmentManager.beginTransaction();
        switch (transaksi)
        {
            case DESKRIPSI:
            {
                fragmentTransaction.hide(pilihMenuFragment);
                fragmentTransaction.hide(pilihLokasiFragment);
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.show(detailTransaksiFragment).commit();
                setTintColor(img_detailtransaksi,R.color.colorPrimary);
                setTintColor(img_pilihmenu,R.color.Gray);
                setTintColor(img_map,R.color.Gray);
                tv_title.setText("Detail");
                break;
            }
            case MENU:
            {
                fragmentTransaction.hide(detailTransaksiFragment);
                fragmentTransaction.hide(pilihLokasiFragment);
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.show(pilihMenuFragment).commit();
                setTintColor(img_detailtransaksi,R.color.colorPrimary);
                setTintColor(img_pilihmenu,R.color.colorPrimary);
                setTintColor(img_map,R.color.Gray);
                tv_title.setText("Pilih Menu");
                break;
            }
            case MAP:
            {
                fragmentTransaction.hide(pilihMenuFragment);
                fragmentTransaction.hide(detailTransaksiFragment);
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.show(pilihLokasiFragment).hide(pilihMenuFragment).commit();
                setTintColor(img_detailtransaksi,R.color.colorPrimary);
                setTintColor(img_pilihmenu,R.color.colorPrimary);
                setTintColor(img_map,R.color.colorPrimary);
                tv_title.setText("Pilih Lokasi");
                break;
            }
        }
    }

    public void setFragmentTransaction()
    {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction()
        .add(R.id.fl_transaksi,detailTransaksiFragment,"deskripsi")
        .add(R.id.fl_transaksi,pilihMenuFragment,"menu")
        .add(R.id.fl_transaksi,pilihLokasiFragment,"map");
        fragmentTransaction.commit();
    }

    public void setTintColor(ImageView image,int color)
    {
        image.setColorFilter(ContextCompat.getColor(this, color));
    }

    @Override
    public void onChangeTotalMenu(int total) {
        PilihMenuFragment fragment=(PilihMenuFragment) fragmentManager.findFragmentByTag(pilihMenuFragment.getTag());
        fragment.setAdapterSize(total);
    }

    public void nextTransaksi(DateTime tanggal_mulai, int lama_pesan)
    {
        requestBody.getPesan().setTgl_mulai(tanggal_mulai.toString("yyyy-MM-dd"));
        requestBody.getPesan().setTgl_selesai(tanggal_mulai.plusDays(lama_pesan).toString("yyyy-MM-dd"));
        this.lama_pesan=lama_pesan;
        this.tgl_mulai=tanggal_mulai;
        position++;
        setTransaksi(position);
    }

    public void nextTransaksi(ArrayList<TransaksiMenuModel> listMenuModel)
    {
        this.listMenuModel=listMenuModel;
        position++;
        setTransaksi(position);
    }

    private DateTime parseDateTime(String input){
        DateTimeFormatter oldFormat= DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateTime=oldFormat.parseDateTime(input);
        return dateTime;
    }

    public void nextTransaksi(String lokasi_pengiriman,double longitude,double latitude,String catatan)
    {
        requestBody.getPesan().setLongitude(longitude);
        requestBody.getPesan().setLatitude(latitude);
        requestBody.getPesan().setAlamat(lokasi_pengiriman);
        requestBody.getPesan().setCatatan(catatan);
        requestBody.setDetailpesan(new ArrayList<DetailTransaksiModel>());
        int jumlah_menu=0;
        ArrayList<DateTime> listWaktuPengantaran=new ArrayList<>();
        String waktupengantaran;
        DateTime waktu_pengantaran;
        for(TransaksiMenuModel menuModel:listMenuModel)
        {
            if(menuModel.isVisibility())
            {
                waktupengantaran=tgl_mulai.toString("yyyy-MM-dd")+" "+menuModel.getJam_pengantaran().toString("HH:mm")+":00";
                waktu_pengantaran=parseDateTime(waktupengantaran);
                listWaktuPengantaran.add(waktu_pengantaran);
                jumlah_menu++;
                total=total+lama_pesan*menuModel.getMenuKateringModel().getHarga();
            }
        }
        for(int i=0;i<lama_pesan;i++)
        {
            for(int j=0;j<jumlah_menu;j++)
            {
                requestBody.getDetailpesan().add(new DetailTransaksiModel(null,null,listWaktuPengantaran.get(j).plusDays(i).toString("yyyy-MM-dd HH:mm:ss"),listMenuModel.get(j).getMenuKateringModel().getId_menu()));
            }
        }
        requestBody.getPesan().setTotal(total);

        if(progressDialog==null)
        {
            progressDialog = new ProgressDialog(TransaksiActivity.this);
            progressDialog.setMessage("Memesan katering...");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
        presenter.pesan(requestBody);
    }

    @Override
    public void onResponseTransaksi(boolean error, String message, Throwable t) {
        if ((progressDialog != null) && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if(!error)
        {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
