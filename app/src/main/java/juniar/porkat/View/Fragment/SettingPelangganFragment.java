package juniar.porkat.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.R;
import juniar.porkat.View.Activity.ChangePasswordPelangganActivity;
import juniar.porkat.View.Activity.EditProfileActivity;
import juniar.porkat.View.Interface.MenuPelangganListener;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public class SettingPelangganFragment extends Fragment {

    @BindView(R.id.cv_editprofile)
    CardView cv_editprofile;
    @BindView(R.id.cv_changepassword)
    CardView cv_changepassword;

    MenuPelangganListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listener = (MenuPelangganListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement listener ");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_setting, container, false);

        ButterKnife.bind(this,view);

        cv_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),EditProfileActivity.class);
                startActivityForResult(intent,1);
            }
        });

        cv_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ChangePasswordPelangganActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
        {
            if(resultCode==Activity.RESULT_OK)
            {
                listener.onUpdateProfile(data.getStringExtra("nama_lengkap"));
            }
        }
    }
}
