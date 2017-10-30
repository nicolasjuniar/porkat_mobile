package juniar.porkat.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
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
import juniar.porkat.View.Interface.LoginView;

/**
 * Created by Nicolas Juniar on 16/10/2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.bt_login)
    Button bt_login;
    @BindView(R.id.tv_register)
    TextView tv_register;

    private ProgressDialog progressDialog;
    LoginPresenter presenter;
    PreferenceHelper preferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        preferences=PreferenceHelper.getInstance(this);

        setDrawableTint(R.drawable.ic_password_orange, ContextCompat.getColor(this, R.color.colorPrimary));
        setDrawableTint(R.drawable.ic_username_orange, ContextCompat.getColor(this, R.color.colorPrimary));

        presenter=new LoginPresenter(this,preferences);

        bt_login.setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
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
                finish();
            }
        }
        else
        {
            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
