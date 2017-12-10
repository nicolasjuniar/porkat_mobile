package juniar.porkat.View.Activity;

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
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Presenter.LoginPresenter;
import juniar.porkat.R;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Interface.LoginListener;

/**
 * Created by Nicolas Juniar on 16/10/2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginListener {
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ProgressDialog progressDialog;
    LoginPresenter presenter;
    PreferenceHelper preferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitleActionBar("Halaman Masuk");

        preferences=PreferenceHelper.getInstance(getApplicationContext());

        setDrawableTint(R.drawable.ic_password, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_username, ContextCompat.getColor(this, R.color.colorPrimary));

        presenter=new LoginPresenter(this,preferences);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearError();
                if(checkInput())
                {
                    if(progressDialog==null)
                    {
                        progressDialog = new ProgressDialog(LoginActivity.this);
                        progressDialog.setMessage("Mencoba masuk...");
                        progressDialog.setCancelable(false);
                    }
                    progressDialog.show();
                    presenter.login(et_username.getText().toString(),et_password.getText().toString());
                }
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterPelangganActivity.class));
            }
        });
    }

    public void setTitleActionBar(String title)
    {
        getSupportActionBar().setTitle(title);
    }

    public boolean checkInput()
    {
        boolean check=true;
        if(et_username.getText().toString().isEmpty())
        {
            et_username.setError("Id pengguna tidak boleh kosong");
            check=false;
        }
        if(et_password.getText().toString().isEmpty())
        {
            et_password.setError("Katasandi tidak boleh kosong");
            check=false;
        }

        return check;
    }

    public void clearError()
    {
        et_username.setError(null);
        et_password.setError(null);
    }

    public void setDrawableTint(int drawable,int tint)
    {
        Drawable icon = getResources().getDrawable(drawable);
        icon = DrawableCompat.wrap(icon);
        DrawableCompat.setTint(icon, tint);
    }

    @Override
    public void onLoginResponse(boolean error, boolean success,String message, Throwable t) {
        if ((progressDialog != null) && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if(!error)
        {
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            if(success)
            {
                startActivity(new Intent(LoginActivity.this,MenuPelangganActivity.class));
                finishAffinity();
            }
        }
        else
        {
            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}