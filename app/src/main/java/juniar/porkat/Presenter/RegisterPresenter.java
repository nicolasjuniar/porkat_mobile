package juniar.porkat.Presenter;

import com.google.gson.Gson;

import juniar.porkat.API.RegisterAPI;
import juniar.porkat.Model.RegisterResponse;
import juniar.porkat.Utils.NetworkConfig;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Interface.RegisterListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public class RegisterPresenter {
    RegisterAPI service;
    Call<RegisterResponse> callResponse;
    PreferenceHelper preferences;

    RegisterListener listener;

    public RegisterPresenter(RegisterListener listener, PreferenceHelper preferences) {
        this.listener = listener;
        this.preferences=preferences;
        service= NetworkConfig.createService(RegisterAPI.class);
    }

    public void registerPelanggan(String id_pengguna, String katasandi, String no_telp, String nama_lengkap, String alamat)
    {
        callResponse=service.registerPelanggan(id_pengguna, katasandi, no_telp, nama_lengkap, alamat);
        callResponse.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse =response.body();
                savePreferences(registerResponse,"pelanggan");
                listener.onRegisterResponse(false, registerResponse.isStatus(), registerResponse.getMessage(),null);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                listener.onRegisterResponse(true,false,null,t);
            }
        });
    }

    public void registerKatering(String id_pengguna,String katasandi,String nama_katering,String no_telp,String alamat,String no_verifikasi)
    {
        callResponse=service.registerKatering(id_pengguna, katasandi, nama_katering, no_telp, alamat, no_verifikasi);
        callResponse.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse =response.body();
                savePreferences(registerResponse,"katering");
                listener.onRegisterResponse(false, registerResponse.isStatus(), registerResponse.getMessage(),null);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                listener.onRegisterResponse(true,false,null,t);
            }
        });
    }

    public void savePreferences(RegisterResponse response, String role)
    {
        preferences.putBoolean("session",true);
        preferences.putString("role",role);
        preferences.putString("profile_katering",new Gson().toJson(response.getDatakatering()));
        preferences.putString("profile_pelanggan",new Gson().toJson(response.getDatapelanggan()));
    }
}
