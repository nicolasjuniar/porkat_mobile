package juniar.porkat.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.KateringModel;
import juniar.porkat.Model.PelangganModel;
import juniar.porkat.R;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.Utils.ViewPagerAdapter;
import juniar.porkat.View.Fragment.DeskripsiFragment;
import juniar.porkat.View.Fragment.MenuFragment;
import juniar.porkat.View.Fragment.UlasanFragment;

public class DetailKateringActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailkatering);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitleActionBar("Detail Katering");

        preferences=PreferenceHelper.getInstance(getApplicationContext());
        pelanggan=new Gson().fromJson(preferences.getString("profile_pelanggan",""),PelangganModel.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bundle = getIntent().getExtras();
        katering =new Gson().fromJson(bundle.getString("detail_katering"),KateringModel.class);

        session=preferences.getBoolean("session",false);
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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        DeskripsiFragment deskripsiFragment=new DeskripsiFragment();
        MenuFragment menuFragment=new MenuFragment();
        UlasanFragment ulasanFragment=new UlasanFragment();
        bundle.putInt("id_katering",katering.getId_katering());
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
}
