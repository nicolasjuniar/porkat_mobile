package juniar.porkat.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.R;
import juniar.porkat.View.Activity.ChangePasswordActivity;
import juniar.porkat.View.Activity.EditProfileActivity;
import juniar.porkat.View.Activity.MenuPelangganActivity;
import juniar.porkat.View.Interface.MenuPelangganView;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public class SettingFragment extends Fragment {

    @BindView(R.id.editprofile)
    CardView editprofile;
    @BindView(R.id.changepassword)
    CardView changepassword;

    MenuPelangganView listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listener = (MenuPelangganView ) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement listener ");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_setting, container, false);

        ButterKnife.bind(this,view);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),EditProfileActivity.class);
                startActivityForResult(intent,1);
            }
        });

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ChangePasswordActivity.class));
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
