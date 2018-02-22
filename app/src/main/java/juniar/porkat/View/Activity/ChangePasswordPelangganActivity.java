package juniar.porkat.View.Activity;

import android.app.ProgressDialog;
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
import juniar.porkat.View.Interface.SettingListener;

/**
 * Created by Nicolas Juniar on 03/11/2017.
 */

public class ChangePasswordPelangganActivity extends AppCompatActivity implements SettingListener {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_confirmationpassword)
    EditText et_confirmationpassword;
    @BindView(R.id.btn_update)
    Button btn_update;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ProgressDialog progressDialog;
    PreferenceHelper preferences;
    private PelangganModel pelanggan;
    SettingPelangganPresenter presenter;
    PelangganModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ubah katasandi");

        preferences=PreferenceHelper.getInstance(getApplicationContext());
        presenter=new SettingPelangganPresenter(this,preferences);

        model=new Gson().fromJson(preferences.getString("profile_pelanggan",""),PelangganModel.class);

        setDrawableTint(R.drawable.ic_username, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_password, ContextCompat.getColor(this, R.color.colorPrimary));

        pelanggan = new Gson().fromJson(preferences.getString("profile_pelanggan", ""), PelangganModel.class);
        et_username.setText(pelanggan.getId_pengguna());

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
                        progressDialog = new ProgressDialog(ChangePasswordPelangganActivity.this);
                        progressDialog.setMessage("Mengubah katasandi...");
                        progressDialog.setCancelable(false);
                    }
                    progressDialog.show();
                    model.setKatasandi(et_password.getText().toString());
                    presenter.changePassword(model);
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

    public void clearError()
    {
        et_password.setError(null);
        et_confirmationpassword.setError(null);
    }

    public boolean checkInput()
    {
        boolean cek=true;
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

        return cek;

    }

    @Override
    public void onUpdateProfile(boolean error, String message, Throwable t) {
        if(!error)
        {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
