package juniar.porkat.Presenter;

import android.util.Log;

import juniar.porkat.API.UlasanAPI;
import juniar.porkat.Model.DeleteUlasanResponse;
import juniar.porkat.Model.GetUlasanResponse;
import juniar.porkat.Model.InsertUlasanRequest;
import juniar.porkat.Model.InsertUlasanResponse;
import juniar.porkat.Model.UlasanModel;
import juniar.porkat.Model.UpdateUlasanRequest;
import juniar.porkat.Model.UpdateUlasanResponse;
import juniar.porkat.Utils.NetworkConfig;
import juniar.porkat.View.Interface.ListUlasanListener;
import juniar.porkat.View.Interface.UlasanListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 16/11/2017.
 */

public class UlasanPresenter {
    UlasanListener ulasanListener;
    ListUlasanListener listUlasanListener;
    UlasanAPI service;
    Call<GetUlasanResponse> callGetUlasanResponse;
    Call<InsertUlasanResponse> callInsertUlasanResponse;
    Call<UpdateUlasanResponse> callUpdateUlasanResponse;
    Call<DeleteUlasanResponse> callDeleteUlasanResponse;

    public UlasanPresenter(UlasanListener ulasanListener) {
        this.ulasanListener=ulasanListener;
        service= NetworkConfig.createService(UlasanAPI.class);
    }

    public UlasanPresenter(ListUlasanListener listUlasanListener)
    {
        this.listUlasanListener=listUlasanListener;
        service= NetworkConfig.createService(UlasanAPI.class);
    }

    public void getUlasan(int id_katering,int id_pelanggan)
    {
        callGetUlasanResponse =service.getUlasan(id_katering,id_pelanggan);
        callGetUlasanResponse.enqueue(new Callback<GetUlasanResponse>() {
            @Override
            public void onResponse(Call<GetUlasanResponse> call, Response<GetUlasanResponse> response) {
                listUlasanListener.onGetUlasanResponse(false, response.body(),null);
            }

            @Override
            public void onFailure(Call<GetUlasanResponse> call, Throwable t) {
                listUlasanListener.onGetUlasanResponse(true,null,t);
            }
        });
    }

    public void insertUlasan(InsertUlasanRequest requestBody)
    {
        callInsertUlasanResponse=service.ulas(requestBody);
        callInsertUlasanResponse.enqueue(new Callback<InsertUlasanResponse>() {
            @Override
            public void onResponse(Call<InsertUlasanResponse> call, Response<InsertUlasanResponse> response) {
                ulasanListener.onInsertUlasanResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<InsertUlasanResponse> call, Throwable t) {
                ulasanListener.onInsertUlasanResponse(true,null ,t);
            }
        });
    }

    public void updateUlasan(UpdateUlasanRequest requestBody)
    {
        callUpdateUlasanResponse=service.updateUlasan(requestBody);
        callUpdateUlasanResponse.enqueue(new Callback<UpdateUlasanResponse>() {
            @Override
            public void onResponse(Call<UpdateUlasanResponse> call, Response<UpdateUlasanResponse> response) {
                ulasanListener.onUpdateUlasanResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<UpdateUlasanResponse> call, Throwable t) {
                ulasanListener.onUpdateUlasanResponse(true,null,t);
            }
        });
    }

    public void deleteUlasan(int id_ulasan)
    {
        callDeleteUlasanResponse=service.deleteUlasan(id_ulasan);
        callDeleteUlasanResponse.enqueue(new Callback<DeleteUlasanResponse>() {
            @Override
            public void onResponse(Call<DeleteUlasanResponse> call, Response<DeleteUlasanResponse> response) {
                ulasanListener.onDeleteUlasanResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<DeleteUlasanResponse> call, Throwable t) {
                ulasanListener.onDeleteUlasanResponse(true,null,t);
            }
        });
    }
}
