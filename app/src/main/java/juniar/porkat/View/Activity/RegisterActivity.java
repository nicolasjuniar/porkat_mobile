package juniar.porkat.View.Activity;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Presenter.RegisterPresenter;
import juniar.porkat.R;
import juniar.porkat.View.Interface.RegisterView;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterView {
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
    @BindView(R.id.bt_register)
    Button bt_register;

    RegisterPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpelanggan);
        ButterKnife.bind(this);

        presenter=new RegisterPresenter(this);

        setDrawableTint(R.drawable.ic_username_orange, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_password_orange, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_home_orange, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_fullname_orange, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_contact_orange, ContextCompat.getColor(this, R.color.colorPrimary));

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearError();
                if(checkInput())
                {
                    if(progressDialog ==null)
                    {
                        progressDialog = new ProgressDialog(RegisterActivity.this);
                        progressDialog.setMessage("Mencoba masuk...");
                        progressDialog.setCancelable(false);
                    }
                    progressDialog.show();
                    presenter.register(et_username.getText().toString(),et_password.getText().toString(),et_contact.getText().toString(),et_fullname.getText().toString(),et_address.getText().toString());
                }
            }
        });
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
            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
