package juniar.porkat.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import juniar.porkat.R;
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

        preferences=PreferenceHelper.getInstance(this);

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
            startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
        }
    }
}
