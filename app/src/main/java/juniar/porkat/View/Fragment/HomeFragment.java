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
import juniar.porkat.Utils.ViewPagerAdapter;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public class HomeFragment extends Fragment{

    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);

        setupViewPager(viewpager);
        tab_layout.setupWithViewPager(viewpager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new KateringByRatingFragment(), "Rekomendasi");
        adapter.addFragment(new KateringByDistanceFragment(), "Sekitar");
        viewPager.setAdapter(adapter);

    }
}
