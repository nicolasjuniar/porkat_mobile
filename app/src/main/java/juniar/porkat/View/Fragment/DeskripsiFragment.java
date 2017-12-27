package juniar.porkat.View.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.KateringModel;
import juniar.porkat.R;

/**
 * Created by Nicolas Juniar on 03/11/2017.
 */

public class DeskripsiFragment extends Fragment {
    @BindView(R.id.tv_fullname)
    TextView tv_fullname;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_contact)
    TextView tv_contact;
    @BindView(R.id.tv_rating)
    TextView tv_rating;
    @BindView(R.id.cv_contact)
    CardView cv_contact;

    private Bundle b;
    private KateringModel katering;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.aaa, container, false);
        ButterKnife.bind(this,view);

        b = getArguments();

        katering =new Gson().fromJson(b.getString("detail_katering"),KateringModel.class);

        tv_fullname.setText(katering.getNama_katering());
        tv_address.setText(katering.getAlamat());
        tv_contact.setText(katering.getNo_telp());
        tv_rating.setText(String.valueOf(katering.getRating()));

        cv_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialContactPhone(tv_contact.getText().toString());
            }
        });

        return view;
    }

    private void dialContactPhone(final String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        getContext().startActivity(callIntent);
    }
}