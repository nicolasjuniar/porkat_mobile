package juniar.porkat.API;

import juniar.porkat.Model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Nicolas Juniar on 17/10/2017.
 */

public interface LoginAPI {
    @GET("pengguna/login")
    Call<LoginResponse> login(@Query("id_pengguna") String id_pengguna,
                              @Query("katasandi") String katasandi);

}
