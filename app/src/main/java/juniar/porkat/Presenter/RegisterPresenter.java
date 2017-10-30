package juniar.porkat.Presenter;

import juniar.porkat.API.RegisterAPI;
import juniar.porkat.Model.RegisterResponse;
import juniar.porkat.Utils.NetworkConfig;
import juniar.porkat.View.Interface.RegisterView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public class RegisterPresenter {
    RegisterAPI service;
    Call<RegisterResponse> callResponse;

    RegisterView listener;

    public RegisterPresenter(RegisterView listener) {
        this.listener = listener;
    }

    public void register(String id_pengguna,String katasandi,String no_telp,String nama_lengkap,String alamat)
    {
        service= NetworkConfig.createService(RegisterAPI.class);
        callResponse=service.register(id_pengguna, katasandi, no_telp, nama_lengkap, alamat);
        callResponse.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse=response.body();
                listener.onRegisterResponse(false,registerResponse.isStatus(),registerResponse.getMessage(),null);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                listener.onRegisterResponse(true,false,null,t);
            }
        });
    }
}
