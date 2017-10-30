package juniar.porkat.API;

import juniar.porkat.Model.ListKateringResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nicolas Juniar on 30/10/2017.
 */

public interface ListKateringAPI {
    @GET("katering/list/rating")
    Call<ListKateringResponse> GetListKatering();
}
