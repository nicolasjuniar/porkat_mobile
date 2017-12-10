package juniar.porkat.Presenter;

import android.util.Log;

import juniar.porkat.API.TransaksiAPI;
import juniar.porkat.Model.TransaksiRequest;
import juniar.porkat.Model.TransaksiResponse;
import juniar.porkat.Utils.NetworkConfig;
import juniar.porkat.View.Interface.TransaksiListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 09/12/2017.
 */

public class TransaksiPresenter {
    Call<TransaksiResponse> callResponse;
    TransaksiAPI service;
    TransaksiListener listener;

    public TransaksiPresenter(TransaksiListener listener) {
        this.listener = listener;
    }

    public void pesan(TransaksiRequest requestBody)
    {
        service= NetworkConfig.createService(TransaksiAPI.class);
        callResponse=service.pesanKatering(requestBody);
        callResponse.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                listener.onResponseTransaksi(false,response.body().getMessage(),null);
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                listener.onResponseTransaksi(true,"",t);
            }
        });
    }
}
