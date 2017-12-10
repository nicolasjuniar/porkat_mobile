package juniar.porkat.Presenter;

import juniar.porkat.API.TransaksiAPI;
import juniar.porkat.Model.GetTransaksiResponse;
import juniar.porkat.Utils.NetworkConfig;
import juniar.porkat.View.Interface.ListTransaksiListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 10/12/2017.
 */

public class ListTransaksiPresenter {

    ListTransaksiListener listener;
    TransaksiAPI service;
    Call<GetTransaksiResponse> callResponse;

    public ListTransaksiPresenter(ListTransaksiListener listener) {
        this.listener = listener;
    }

    public void getListTransaksiPelanggan(int id_pelanggan)
    {
        service= NetworkConfig.createService(TransaksiAPI.class);
        callResponse=service.getListTransaksiPelanggan(id_pelanggan);
        callResponse.enqueue(new Callback<GetTransaksiResponse>() {
            @Override
            public void onResponse(Call<GetTransaksiResponse> call, Response<GetTransaksiResponse> response) {
                listener.onGetListTransaksi(false,response.body().getListtransaksi(),null);
            }

            @Override
            public void onFailure(Call<GetTransaksiResponse> call, Throwable t) {
                listener.onGetListTransaksi(true,null,t);
            }
        });
    }
}
