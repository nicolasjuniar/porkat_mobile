package juniar.porkat.API;

import juniar.porkat.Model.RegisterKateringRequest;
import juniar.porkat.Model.RegisterPelangganRequest;
import juniar.porkat.Model.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public interface RegisterAPI {
    @POST("pelanggan/register")
    Call<RegisterResponse> registerPelanggan(@Body RegisterPelangganRequest requestBody);

    @POST("katering/register")
    Call<RegisterResponse> registerKatering(@Body RegisterKateringRequest requestBody);
}
