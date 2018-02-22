package juniar.porkat.View.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.PelangganModel;
import juniar.porkat.Presenter.SettingPelangganPresenter;
import juniar.porkat.R;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Interface.MenuPelangganListener;
import juniar.porkat.View.Interface.SettingListener;

/**
 * Created by Nicolas Juniar on 07/11/2017.
 */

public class EditProfileActivity extends AppCompatActivity implements SettingListener {

    @BindView(R.id.et_fullname)
    EditText et_fullname;
    @BindView(R.id.et_address)
    EditText et_address;
    @BindView(R.id.et_contact)
    EditText et_contact;
    @BindView(R.id.btn_update)
    Button btn_update;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    PreferenceHelper preferences;
    PelangganModel model;
    ProgressDialog progressDialog;
    SettingPelangganPresenter presenter;
    MenuPelangganListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ubah Profil");

        preferences=PreferenceHelper.getInstance(getApplicationContext());
        presenter=new SettingPelangganPresenter(this,preferences);
        model=new Gson().fromJson(preferences.getString("profile_pelanggan",""),PelangganModel.class);

        listener=(MenuPelangganListener) getIntent().getParcelableExtra("listener");

        setDrawableTint(R.drawable.ic_fullname, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_contact, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_home, ContextCompat.getColor(this, R.color.colorPrimary));
        setOldProfile();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearError();
                if(checkInput())
                {
                    if(progressDialog ==null)
                    {
                        progressDialog = new ProgressDialog(EditProfileActivity.this);
                        progressDialog.setMessage("Mengubah katasandi...");
                        progressDialog.setCancelable(false);
                    }
                    progressDialog.show();
                    model.setAlamat(et_address.getText().toString());
                    model.setNo_telp(et_contact.getText().toString());
                    model.setNama_lengkap(et_fullname.getText().toString());
                    presenter.editProfile(model);
                }
            }
        });
    }

    public void setDrawableTint(int drawable,int tint)
    {
        Drawable icon = getResources().getDrawable(drawable);
        icon = DrawableCompat.wrap(icon);
        DrawableCompat.setTint(icon, tint);
    }

    public void setOldProfile()
    {
        et_address.setText(model.getAlamat());
        et_contact.setText(model.getNo_telp());
        et_fullname.setText(model.getNama_lengkap());
    }

    public boolean checkInput()
    {
        boolean cek=true;
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

        return cek;
    }

    public void clearError()
    {
        et_fullname.setError(null);
        et_contact.setError(null);
        et_address.setError(null);
    }

    @Override
    public void onUpdateProfile(boolean error, String message, Throwable t) {
        if(!error)
        {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Intent i = new Intent();
            i.putExtra("nama_lengkap",model.getNama_lengkap());
            setResult(Activity.RESULT_OK,i);
            finish();
        }
        else
        {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
