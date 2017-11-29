package juniar.porkat.API;

import juniar.porkat.Model.ListKateringResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nicolas Juniar on 30/10/2017.
 */

public interface ListKateringAPI {
    @GET("katering/list/rating")
    Call<ListKateringResponse> GetListKateringByRating();

    @GET("katering/list/distance")
    Call<ListKateringResponse> GetListKateringByDistance(@Query("latitude") double latitude,
                                                         @Query("longitude") double longitude);
}
