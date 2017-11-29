package juniar.porkat.View.Activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import juniar.porkat.R;
import juniar.porkat.Utils.MyLocation;
import juniar.porkat.Utils.PreferenceHelper;

/**
 * Created by Nicolas Juniar on 16/10/2017.
 */

public class SplashScreenActivity extends AppCompatActivity{

    PreferenceHelper preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        preferences=PreferenceHelper.getInstance(getApplicationContext());
        getMyLocation();

        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    Log.d("Exception", "Exception" + e);
                } finally {
                    loadPreferences();
                }
                finish();
            }
        };
        logoTimer.start();
    }

    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            Log.v("IGA", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void getMyLocation()
    {
        MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
            @Override
            public void gotLocation(Location location){
                LatLng loc =new LatLng(location.getLatitude(),location.getLongitude());
                preferences.putString("my_longitude",String.valueOf(loc.longitude));
                preferences.putString("my_latitude",String.valueOf(loc.latitude));
//                getAddress(loc.latitude,loc.longitude);
                getAddress(-7.779941,110.416567);
            }
        };
        MyLocation myLocation = new MyLocation(this);
        myLocation.getLocation(this, locationResult);
    }

    public void loadPreferences()
    {
        if(preferences.getBoolean("session",false))
        {
            String role=preferences.getString("role","");
            if(role.equalsIgnoreCase("pelanggan"))
            {
                startActivity(new Intent(SplashScreenActivity.this,MenuPelangganActivity.class));
            }
            if(role.equalsIgnoreCase("katering"))
            {
                startActivity(new Intent(SplashScreenActivity.this,MenuPelangganActivity.class));
            }
        }
        else
        {
            startActivity(new Intent(SplashScreenActivity.this,HomeActivity.class));
        }
    }
}
