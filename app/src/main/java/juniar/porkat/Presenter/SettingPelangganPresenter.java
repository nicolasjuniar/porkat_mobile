package juniar.porkat.Presenter;

import com.google.gson.Gson;

import juniar.porkat.API.SettingPelangganAPI;
import juniar.porkat.Model.ChangePasswordPelangganRequest;
import juniar.porkat.Model.ChangePasswordResponse;
import juniar.porkat.Model.EditProfilePelangganRequest;
import juniar.porkat.Model.EditProfileResponse;
import juniar.porkat.Model.PelangganModel;
import juniar.porkat.Utils.NetworkConfig;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Interface.SettingListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 07/11/2017.
 */

public class SettingPelangganPresenter {
    SettingPelangganAPI service;
    SettingListener listener;
    PreferenceHelper preferences;

    Call<ChangePasswordResponse> callPasswordResponse;
    Call<EditProfileResponse> callEditProfileResponse;

    public SettingPelangganPresenter(SettingListener listener, PreferenceHelper preferences) {
        this.listener = listener;
        this.preferences=preferences;
        service= NetworkConfig.createService(SettingPelangganAPI.class);
    }

    public void changePassword(final PelangganModel model)
    {
        ChangePasswordPelangganRequest requestBody=new ChangePasswordPelangganRequest(model.getId_pelanggan(),model.getKatasandi());
        callPasswordResponse=service.changePassword(requestBody);
        callPasswordResponse.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                preferences.putString("profile_pelanggan",new Gson().toJson(model));
                listener.onUpdateProfile(false,response.body().getMessage(),null);
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                listener.onUpdateProfile(true,"",t);
            }
        });
    }

    public void editProfile(final PelangganModel model)
    {
        EditProfilePelangganRequest requestBody=new EditProfilePelangganRequest(model.getId_pelanggan(),model.getNo_telp(), model.getNama_lengkap(), model.getAlamat());
        callEditProfileResponse=service.editProfile(requestBody);
        callEditProfileResponse.enqueue(new Callback<EditProfileResponse>() {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                preferences.putString("profile_pelanggan",new Gson().toJson(model));
                listener.onUpdateProfile(false,response.body().getMessage(),null);
            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                listener.onUpdateProfile(true,"",t);
            }
        });
    }
}
