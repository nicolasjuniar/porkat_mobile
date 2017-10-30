package juniar.porkat.API;

import juniar.porkat.Model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Nicolas Juniar on 17/10/2017.
 */

public interface LoginAPI {
    @FormUrlEncoded
    @POST("pengguna/login")
    Call<LoginResponse> login(@Field("id_pengguna") String id_pengguna,
                              @Field("katasandi") String katasandi);
}
