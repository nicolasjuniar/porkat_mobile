package juniar.porkat.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import juniar.porkat.Model.RegisterKateringRequest;
import juniar.porkat.Presenter.RegisterPresenter;
import juniar.porkat.R;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Interface.RegisterListener;

/**
 * Created by Nicolas Juniar on 08/11/2017.
 */

public class RegisterKateringActivity extends AppCompatActivity implements RegisterListener {
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_confirmationpassword)
    EditText et_confirmationpassword;
    @BindView(R.id.et_fullname)
    EditText et_fullname;
    @BindView(R.id.et_contact)
    EditText et_contact;
    @BindView(R.id.et_address)
    EditText et_address;
    @BindView(R.id.et_verification)
    EditText et_verification;
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    RegisterPresenter presenter;
    private ProgressDialog progressDialog;
    PreferenceHelper preferences;

    private final int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerkatering);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitleActionBar("Halaman Pendaftaran");

        preferences=PreferenceHelper.getInstance(getApplicationContext());
        presenter=new RegisterPresenter(this,preferences);

        setDrawableTint(R.drawable.ic_username, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_password, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_home, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_fullname, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_contact, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_verification, ContextCompat.getColor(this, R.color.colorPrimary));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearError();
                if(checkInput())
                {
                    if(progressDialog ==null)
                    {
                        progressDialog = new ProgressDialog(RegisterKateringActivity.this);
                        progressDialog.setMessage("Mencoba masuk...");
                        progressDialog.setCancelable(false);
                    }
                    progressDialog.show();
                    RegisterKateringRequest requestBody=new RegisterKateringRequest(et_username.getText().toString(),et_password.getText().toString(),et_contact.getText().toString(),et_fullname.getText().toString(),et_address.getText().toString(),et_verification.getText().toString());
                    presenter.registerKatering(requestBody);
                }
            }
        });

        et_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(RegisterKateringActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String address=getAddress(place.getLatLng().latitude,place.getLatLng().longitude);
                et_address.setText(address);

            }
        }
    }

    public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String address="";
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            address = obj.getAddressLine(0);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return address;
    }

    public void setTitleActionBar(String title)
    {
        getSupportActionBar().setTitle(title);
    }

    public boolean checkInput()
    {
        boolean cek=true;
        if(et_username.getText().toString().isEmpty())
        {
            et_username.setError("id pengguna tidak boleh kosong");
            cek=false;
        }
        if(et_password.getText().toString().isEmpty())
        {
            et_password.setError("katasandi tidak boleh kosong");
            cek=false;
        }
        else if(et_password.getText().toString().length()<8)
        {
            et_password.setError("katasandi minimal memiliki 8 karakter");
            cek=false;
        }
        if(!et_confirmationpassword.getText().toString().equalsIgnoreCase(et_password.getText().toString()))
        {
            et_confirmationpassword.setError("konfirmasi katasandi tidak sama dengan katasandi");
            cek=false;
        }
        if(et_address.getText().toString().isEmpty())
        {
            et_address.setError("alamat tidak boleh kososng");
            cek=false;
        }
        if(et_fullname.getText().toString().isEmpty())
        {
            et_fullname.setError("nama lengkap tidak boleh kosong");
            cek=false;
        }
        if(et_contact.getText().toString().isEmpty())
        {
            et_contact.setError("nomor hp tidak boleh kososng");
            cek=false;
        }
        if(et_verification.getText().toString().isEmpty())
        {
            et_verification.setError("no verifikasi tidak boleh kosong");
            cek=false;
        }

        return cek;
    }

    public void clearError()
    {
        et_username.setError(null);
        et_password.setError(null);
        et_confirmationpassword.setError(null);
        et_fullname.setError(null);
        et_contact.setError(null);
        et_address.setError(null);
        et_verification.setError(null);
    }

    public void setDrawableTint(int drawable,int tint)
    {
        Drawable icon = getResources().getDrawable(drawable);
        icon = DrawableCompat.wrap(icon);
        DrawableCompat.setTint(icon, tint);
    }

    @Override
    public void onRegisterResponse(boolean error, boolean success, String message, Throwable t) {
        if ((progressDialog != null) && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if(!error)
        {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            if(success)
            {
                startActivity(new Intent(this,MenuKateringActivity.class));
                finishAffinity();
            }
        }
        else
        {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

