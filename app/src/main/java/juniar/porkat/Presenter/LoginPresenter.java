package juniar.porkat.Presenter;

import com.google.gson.Gson;

import juniar.porkat.API.LoginAPI;
import juniar.porkat.Model.LoginResponse;
import juniar.porkat.Utils.NetworkConfig;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Interface.LoginView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 17/10/2017.
 */

public class LoginPresenter {
    LoginAPI service;
    Call<LoginResponse> callResponse;

    LoginView listener;
    PreferenceHelper preferences;

    public LoginPresenter(LoginView listener, PreferenceHelper preferences) {
        this.listener = listener;
        this.preferences=preferences;
        service= NetworkConfig.createService(LoginAPI.class);
    }

    public void login(String username, String password)
    {
        callResponse=service.login(username,password);
        callResponse.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse=response.body();
                if(loginResponse.isStatus())
                {
                    savePreferences(response.body());
                }
                listener.onLoginResponse(false,loginResponse.isStatus(),loginResponse.getMessage(),null);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                listener.onLoginResponse(true,false,null,t);
            }
        });
    }

    public void savePreferences(LoginResponse response)
    {
        preferences.putBoolean("session",true);
        preferences.putString("role",response.getRole());
        preferences.putString("profile_katering",new Gson().toJson(response.getDatakatering()));
        preferences.putString("profile_pelanggan",new Gson().toJson(response.getDatapelanggan()));
    }
}
