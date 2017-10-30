package juniar.porkat.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.R;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public class SettingFragment extends Fragment {

    @BindView(R.id.editprofile)
    CardView editprofile;
    @BindView(R.id.changepassword)
    CardView changepassword;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_setting, container, false);

        ButterKnife.bind(this,view);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Ubah Profile", Toast.LENGTH_SHORT).show();
            }
        });

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Ubah Katasandi", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
