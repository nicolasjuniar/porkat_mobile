package juniar.porkat.API;

import juniar.porkat.Model.ListMenuResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nicolas Juniar on 01/11/2017.
 */

public interface ListMenuAPI {
    @GET("menu/list")
    Call<ListMenuResponse> getListMeu(@Query("id_katering") int id_katering);
}
