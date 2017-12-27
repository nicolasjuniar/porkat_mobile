package juniar.porkat.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.R;
import juniar.porkat.Utils.TabPagerAdapter;
import juniar.porkat.View.Fragment.KateringByDistanceFragment;
import juniar.porkat.View.Fragment.KateringByRatingFragment;

/**
 * Created by Nicolas Juniar on 29/11/2017.
 */

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    KateringByRatingFragment kateringByRatingFragment;
    KateringByDistanceFragment kateringByDistanceFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        kateringByDistanceFragment=new KateringByDistanceFragment();
        kateringByRatingFragment=new KateringByRatingFragment();

        setupViewPager(viewpager);
        tab_layout.setupWithViewPager(viewpager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_login:
            {
                startActivity(new Intent(this,LoginActivity.class));
                break;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(kateringByRatingFragment, "Rekomendasi");
        adapter.addFragment(kateringByDistanceFragment, "Sekitar");
        viewPager.setAdapter(adapter);
    }
}
