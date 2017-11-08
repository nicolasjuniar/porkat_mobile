package juniar.porkat.View.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.PelangganModel;
import juniar.porkat.R;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Fragment.SettingFragment;
import juniar.porkat.View.Fragment.HomeFragment;
import juniar.porkat.View.Fragment.TransactionHistoryFragment;
import juniar.porkat.View.Interface.MenuPelangganView;

public class MenuPelangganActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MenuPelangganView {

    TextView tv_fullname,tv_username,tv_role;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    Context context;

    private boolean exit=false;
    private PreferenceHelper preferences;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menupelanggan);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setTitleActionBar("Beranda");

        preferences=PreferenceHelper.getInstance(getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        context=this;

        View view=navigationView.getHeaderView(0);
        tv_fullname=ButterKnife.findById(view,R.id.tv_fullname);
        tv_username=ButterKnife.findById(view,R.id.tv_username);
        tv_role=ButterKnife.findById(view,R.id.tv_role);

        loadPreferences();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, new HomeFragment()).commit();

        navigationView.getMenu().getItem(0).setChecked(true);

        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setTitleActionBar(String title)
    {
        getSupportActionBar().setTitle(title);
    }

    public void loadPreferences()
    {
        PelangganModel pelanggan= new Gson().fromJson(preferences.getString("profile_pelanggan",""),PelangganModel.class);
        tv_fullname.setText(pelanggan.getNama_lengkap());
        tv_username.setText(pelanggan.getId_pengguna());
        tv_role.setText("pelanggan");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (exit) {
                finish();
            } else {
                Toast.makeText(this, "Tekan kembali lagi untuk keluar",
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);

            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        int n=0;

        Fragment fragment=null;
        if (id == R.id.nav_home) {
            n=0;
            setTitleActionBar("Beranda");
            fragment=new HomeFragment();
        } else if (id == R.id.nav_transaction) {
            n=1;
            setTitleActionBar("Transaksi");
            fragment=new TransactionHistoryFragment();
        } else if (id == R.id.nav_setting) {
            n=2;
            setTitleActionBar("Pengaturan");
            fragment=new SettingFragment();
        } else if (id == R.id.nav_logout) {
            navigationView.getMenu().getItem(n).setChecked(true);
            new AlertDialog.Builder(this)
                    .setTitle("Keluar")
                    .setMessage("Apa anda yakin ingin Keluar?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            preferences.putBoolean("session",false);
                            startActivity(new Intent(MenuPelangganActivity.this,LoginActivity.class));
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        if(fragment!=null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onUpdateProfile(String nama_lengkap) {
        tv_fullname.setText(nama_lengkap);
    }
}
