package juniar.porkat.Presenter;

import juniar.porkat.API.ListKateringAPI;
import juniar.porkat.Model.ListKateringResponse;
import juniar.porkat.Utils.NetworkConfig;
import juniar.porkat.View.Interface.ListKateringListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 30/10/2017.
 */

public class ListKateringPresenter {
    ListKateringListener listener;
    ListKateringAPI service;
    Call<ListKateringResponse> callResponse;

    public ListKateringPresenter(ListKateringListener listener) {
        this.listener = listener;
        service= NetworkConfig.createService(ListKateringAPI.class);
    }

    public void getListKateringByRating()
    {
        callResponse=service.GetListKateringByRating();
        callResponse.enqueue(new Callback<ListKateringResponse>() {
            @Override
            public void onResponse(Call<ListKateringResponse> call, Response<ListKateringResponse> response) {
                listener.onGetListKateringResponse(false,response.body().getListkatering(),null);
            }

            @Override
            public void onFailure(Call<ListKateringResponse> call, Throwable t) {
                listener.onGetListKateringResponse(true,null,t);
            }
        });
    }

    public void getListKateringByDistance(double latitude,double longitude)
    {
        callResponse=service.GetListKateringByDistance(latitude,longitude);
        callResponse.enqueue(new Callback<ListKateringResponse>() {
            @Override
            public void onResponse(Call<ListKateringResponse> call, Response<ListKateringResponse> response) {
                listener.onGetListKateringResponse(false,response.body().getListkatering(),null);
            }

            @Override
            public void onFailure(Call<ListKateringResponse> call, Throwable t) {
                listener.onGetListKateringResponse(true,null,t);
            }
        });
    }


}
