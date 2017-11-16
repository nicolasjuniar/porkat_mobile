package juniar.porkat.API;

import juniar.porkat.Model.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("pelanggan/register")
    Call<RegisterResponse> registerPelanggan(@Field("id_pengguna") String id_pengguna,
                                             @Field("katasandi") String katasandi,
                                             @Field("no_telp") String no_telp,
                                             @Field("nama_lengkap") String nama_lengkap,
                                             @Field("alamat") String alamat);

    @FormUrlEncoded
    @POST("katering/register")
    Call<RegisterResponse> registerKatering(@Field("id_pengguna") String id_pengguna,
                                            @Field("katasandi") String katasandi,
                                            @Field("nama_katering") String nama_katering,
                                            @Field("no_telp") String no_telp,
                                            @Field("alamat") String alamat,
                                            @Field("no_verifikasi") String no_verifikasi);
}
