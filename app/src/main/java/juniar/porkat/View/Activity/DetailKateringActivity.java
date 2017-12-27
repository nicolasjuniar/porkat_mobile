package juniar.porkat.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.KateringModel;
import juniar.porkat.Model.PelangganModel;
import juniar.porkat.Presenter.DetailKateringPresenter;
import juniar.porkat.R;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.Utils.TabPagerAdapter;
import juniar.porkat.View.Fragment.DeskripsiFragment;
import juniar.porkat.View.Fragment.MenuFragment;
import juniar.porkat.View.Fragment.UlasanFragment;
import juniar.porkat.View.Interface.DetailKateringListener;

public class DetailKateringActivity extends AppCompatActivity implements DetailKateringListener {

    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private Bundle bundle;
    private KateringModel katering;
    PelangganModel pelanggan;
    PreferenceHelper preferences;
    boolean session=false;
    boolean cek=false;
    DetailKateringPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailkatering);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitleActionBar("Detail Katering");

        presenter=new DetailKateringPresenter(this);
        preferences=PreferenceHelper.getInstance(getApplicationContext());
        pelanggan=new Gson().fromJson(preferences.getString("profile_pelanggan",""),PelangganModel.class);
        bundle = getIntent().getExtras();
        katering =new Gson().fromJson(bundle.getString("detail_katering"),KateringModel.class);
        bundle.putInt("id_katering",katering.getId_katering());
        session=preferences.getBoolean("session",false);

        presenter.cekListMenuSize(katering.getId_katering());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cek)
                {
                    Intent i=new Intent(DetailKateringActivity.this,TransaksiActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(DetailKateringActivity.this, "Tidak dapat melakukan memesan, katering ini tidak memiliki menu", Toast.LENGTH_SHORT).show();
                }
            }
        });


        if(!session)
        {
            fab.setVisibility(View.GONE);
        }

        setupViewPager(viewpager);
        tab_layout.setupWithViewPager(viewpager);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }




    public void setTitleActionBar(String title)
    {
        getSupportActionBar().setTitle(title);
    }

    private void setupViewPager(ViewPager viewPager) {
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());

        DeskripsiFragment deskripsiFragment=new DeskripsiFragment();
        MenuFragment menuFragment=new MenuFragment();
        UlasanFragment ulasanFragment=new UlasanFragment();
        bundle.putBoolean("session",session);
        if(pelanggan!=null)
        {
            bundle.putInt("id_pelanggan",pelanggan.getId_pelanggan());
            bundle.putString("nama_lengkap",pelanggan.getNama_lengkap());
        }
        deskripsiFragment.setArguments(bundle);
        menuFragment.setArguments(bundle);
        ulasanFragment.setArguments(bundle);

        adapter.addFragment(deskripsiFragment,"Deskripsi");
        adapter.addFragment(menuFragment, "Menu");
        adapter.addFragment(ulasanFragment, "Ulasan");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void cekSize(boolean error, boolean enable, Throwable t) {
        if(!error)
        {
            cek=enable;
        }
        else
        {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
