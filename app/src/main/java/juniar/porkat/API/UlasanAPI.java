package juniar.porkat.API;

import juniar.porkat.Model.CekUlasanResponse;
import juniar.porkat.Model.DeleteUlasanResponse;
import juniar.porkat.Model.GetUlasanResponse;
import juniar.porkat.Model.InsertUlasanRequest;
import juniar.porkat.Model.InsertUlasanResponse;
import juniar.porkat.Model.UpdateUlasanRequest;
import juniar.porkat.Model.UpdateUlasanResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nicolas Juniar on 15/11/2017.
 */

public interface UlasanAPI {
    @GET("pelanggan/ulasan/cek")
    Call<CekUlasanResponse> isUlas(@Query("id_pelanggan") int id_pelanggan,
                                   @Query("id_katering") int id_katering);

    @GET("pelanggan/ulasan/list")
    Call<GetUlasanResponse> getUlasan(@Query("id_katering") int id_katering,
                                      @Query("id_pelanggan") int id_pelanggan);

    @PUT("pelanggan/ulasan/update")
    Call<UpdateUlasanResponse> updateUlasan(@Body UpdateUlasanRequest requestBody);

    @POST("pelanggan/ulasan/insert")
    Call<InsertUlasanResponse> ulas(@Body InsertUlasanRequest requestBody);

    @DELETE("pelanggan/ulasan/delete/{id_ulasan}")
    Call<DeleteUlasanResponse> deleteUlasan(@Path("id_ulasan") int id_ulasan);

}
