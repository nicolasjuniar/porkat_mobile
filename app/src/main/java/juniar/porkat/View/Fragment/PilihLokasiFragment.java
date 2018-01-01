package juniar.porkat.View.Fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.R;
import juniar.porkat.View.Activity.HomeActivity;
import juniar.porkat.View.Activity.MenuPelangganActivity;
import juniar.porkat.View.Activity.TransaksiActivity;
import juniar.porkat.View.Interface.TransaksiCallback;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Nicolas Juniar on 08/12/2017.
 */

public class PilihLokasiFragment extends Fragment {
    @BindView(R.id.et_lokasi)
    EditText et_lokasi;
    @BindView(R.id.et_catatan)
    EditText et_catatan;
    @BindView(R.id.btn_selesai)
    Button btn_selesai;

    TransaksiCallback callback;
    String lokasi_pengiriman;
    double longitude;
    double latitude;
    String catatan;

    private final int PLACE_PICKER_REQUEST = 1;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callback = (TransaksiActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement setTotalMenu");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pilihlokasi, container, false);
        ButterKnife.bind(this,view);

        setDrawableTint(R.drawable.ic_description, ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_map,ContextCompat.getColor(getActivity(), R.color.colorPrimary));

        et_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearError();
                if(checkInput())
                {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Pesan Katering")
                            .setMessage("Apakah anda yakin ingin memesan katering?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    ((TransaksiActivity)callback).nextTransaksi(lokasi_pengiriman,longitude,latitude,et_catatan.getText().toString());
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .show();
                }
            }
        });

        return view;
    }

    public boolean checkInput()
    {
        boolean cek=true;
        if(et_lokasi.getText().toString().isEmpty())
        {
            et_lokasi.setError("tambahkan lokasi pengiriman");
            cek=false;
        }
        return  cek;
    }

    public void clearError()
    {
        et_lokasi.setError(null);
    }

    public void setDrawableTint(int drawable,int tint)
    {
        Drawable icon = getResources().getDrawable(drawable);
        icon = DrawableCompat.wrap(icon);
        DrawableCompat.setTint(icon, tint);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
                String address=getAddress(place.getLatLng().latitude,place.getLatLng().longitude);
                et_lokasi.setText(address);
                lokasi_pengiriman=address;
                longitude=place.getLatLng().longitude;
                latitude=place.getLatLng().latitude;
            }
        }
    }

    public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        String address="";
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            address=addresses.get(0).getThoroughfare()+" "+addresses.get(0).getSubThoroughfare()+", "+addresses.get(0).getSubLocality();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return address;
    }
}
