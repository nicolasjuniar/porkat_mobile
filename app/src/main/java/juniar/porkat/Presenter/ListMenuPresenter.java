package juniar.porkat.Presenter;

import juniar.porkat.API.ListMenuAPI;
import juniar.porkat.Model.ListMenuResponse;
import juniar.porkat.Utils.NetworkConfig;
import juniar.porkat.View.Interface.ListMenuListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 01/11/2017.
 */

public class ListMenuPresenter {
    ListMenuAPI service;
    ListMenuListener listener;
    Call<ListMenuResponse> callResponse;

    public ListMenuPresenter(ListMenuListener listener) {
        this.listener = listener;
        service= NetworkConfig.createService(ListMenuAPI.class);
    }

    public void getListMenu(int id_katering)
    {
        callResponse=service.getListMeu(id_katering);
        callResponse.enqueue(new Callback<ListMenuResponse>() {
            @Override
            public void onResponse(Call<ListMenuResponse> call, Response<ListMenuResponse> response) {
                listener.onGetListMenuResponse(false,response.body().getListmenu(),null);
            }

            @Override
            public void onFailure(Call<ListMenuResponse> call, Throwable t) {
                listener.onGetListMenuResponse(true,null,t);
            }
        });
    }
}
