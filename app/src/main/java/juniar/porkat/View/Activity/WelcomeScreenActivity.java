package juniar.porkat.View.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.MapsFragment;
import juniar.porkat.R;
import juniar.porkat.Utils.SliderPagerAdapter;
import juniar.porkat.View.Fragment.DetailTransaksiFragment;
import juniar.porkat.View.Fragment.PilihMenuFragment;

/**
 * Created by Nicolas Juniar on 02/12/2017.
 */

public class WelcomeScreenActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,DetailTransaksiFragment.setTotalMenu{

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_indicator)
    LinearLayout layout_indicator;

    List<Fragment> fragmentList;
    private ArrayList<ImageView> indicators;
    private final int NUM_PAGES = 3;
    MenuItem menu_selesai;
    Menu menu;
    DetailTransaksiFragment detailTransaksiFragment;
    PilihMenuFragment pilihMenuFragment;
    MapsFragment mapsFragment;
    boolean menu_visibility =false;
    SliderPagerAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pemesanan Katering");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        detailTransaksiFragment=new DetailTransaksiFragment();
        pilihMenuFragment=new PilihMenuFragment();
        mapsFragment=new MapsFragment();

        indicators = new ArrayList<>(NUM_PAGES);
        fragmentList=new ArrayList<>();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setupSliderPager();
        setupPagerIndicator(NUM_PAGES);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            {
                onBackPressed();
                return true;
            }
            case R.id.menu_selesai:
            {
                Toast.makeText(this, "h", Toast.LENGTH_SHORT).show();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setupSliderPager()
    {
        fragmentList.add(detailTransaksiFragment);
        fragmentList.add(pilihMenuFragment);
        fragmentList.add(mapsFragment);
        adapter=new SliderPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(this);
        viewpager.setOffscreenPageLimit(NUM_PAGES);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_transaksi, menu);
        menu_selesai=menu.findItem(R.id.menu_selesai);
        menu_selesai.setVisible(menu_visibility);
//        menu_selesai.getActionView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(TransaksiActivity.this, "sampn", Toast.LENGTH_SHORT).show();
//            }
//        });
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (int i = 0; i < NUM_PAGES; i++) {
            indicators.get(i).setImageDrawable(ContextCompat.getDrawable(WelcomeScreenActivity.this, R.drawable.dot_indicator_disabled));
        }
        indicators.get(position).setImageDrawable(ContextCompat.getDrawable(WelcomeScreenActivity.this, R.drawable.dot_indicator));
        invalidateOptionsMenu();
        if(position==(NUM_PAGES-1))
        {
            menu_visibility =true;
        }
        else
        {
            menu_visibility =false;
        }
        invalidateOptionsMenu();
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setupPagerIndicator(final int size) {

        for (int i = 0; i < size; i++) {
            indicators.add(new ImageView(WelcomeScreenActivity.this));
            indicators.get(i).setImageDrawable(
                    ContextCompat.getDrawable(WelcomeScreenActivity.this,
                            (i == 0) ? R.drawable.dot_indicator : R.drawable.dot_indicator_disabled)
            );

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            int margin = getResources().getDimensionPixelSize(R.dimen.dimen_8dp);
            params.setMargins(
                    margin,
                    0,
                    margin,
                    0
            );
            layout_indicator.addView(indicators.get(i), params);
        }
        indicators.get(0).setSelected(true);
    }

    @Override
    public void onChangeTotalMenu(int total) {
        PilihMenuFragment fragment=(PilihMenuFragment) adapter.getItem(1);
        fragment.setAdapterSize(total);
    }
}

