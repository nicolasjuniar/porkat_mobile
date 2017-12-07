package juniar.porkat.View.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.R;
import juniar.porkat.Utils.TabPagerAdapter;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public class HomeFragment extends Fragment{

    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    KateringByRatingFragment kateringByRatingFragment;
    KateringByDistanceFragment kateringByDistanceFragment;

    private View view;
    boolean cek=false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);

        if(!cek)
        {
            kateringByDistanceFragment=new KateringByDistanceFragment();
            kateringByRatingFragment=new KateringByRatingFragment();
            cek=true;
        }

        setupViewPager(viewpager);
        tab_layout.setupWithViewPager(viewpager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager());
        adapter.addFragment(kateringByRatingFragment, "Rekomendasi");
        adapter.addFragment(kateringByDistanceFragment, "Sekitar");
        viewPager.setAdapter(adapter);
    }
}
