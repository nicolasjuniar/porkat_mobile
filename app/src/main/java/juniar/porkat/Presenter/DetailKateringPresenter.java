package juniar.porkat.Presenter;

import juniar.porkat.API.ListMenuAPI;
import juniar.porkat.Model.ListMenuResponse;
import juniar.porkat.Utils.NetworkConfig;
import juniar.porkat.View.Interface.DetailKateringListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 07/12/2017.
 */

public class DetailKateringPresenter {
    ListMenuAPI service;
    Call<ListMenuResponse> callResponse;
    DetailKateringListener listener;

    public DetailKateringPresenter(DetailKateringListener listener) {
        this.listener = listener;
    }

    public void cekListMenuSize(int id_katering)
    {
        service= NetworkConfig.createService(ListMenuAPI.class);
        callResponse=service.getListMeu(id_katering);
        callResponse.enqueue(new Callback<ListMenuResponse>() {
            @Override
            public void onResponse(Call<ListMenuResponse> call, Response<ListMenuResponse> response) {
                if(response.body().getListmenu().size()==0)
                {
                    listener.cekSize(false,false,null);
                }
                else
                {
                    listener.cekSize(false,true,null);
                }
            }

            @Override
            public void onFailure(Call<ListMenuResponse> call, Throwable t) {
                listener.cekSize(true,false,t);
            }
        });
    }

}
