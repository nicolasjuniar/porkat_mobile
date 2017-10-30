package juniar.porkat.Presenter;

import juniar.porkat.API.ListKateringAPI;
import juniar.porkat.Model.ListKateringResponse;
import juniar.porkat.Utils.NetworkConfig;
import juniar.porkat.View.Interface.ListKateringView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 30/10/2017.
 */

public class ListKateringPresenter {
    ListKateringView listener;
    ListKateringAPI service;
    Call<ListKateringResponse> callResponse;

    public ListKateringPresenter(ListKateringView listener) {
        this.listener = listener;
    }

    public void getListKateringByRating()
    {
        service= NetworkConfig.createService(ListKateringAPI.class);
        callResponse=service.GetListKatering();
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
