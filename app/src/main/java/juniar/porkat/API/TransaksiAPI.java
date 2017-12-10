package juniar.porkat.API;

import juniar.porkat.Model.GetTransaksiResponse;
import juniar.porkat.Model.TransaksiRequest;
import juniar.porkat.Model.TransaksiResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Nicolas Juniar on 09/12/2017.
 */

public interface TransaksiAPI {
    @POST("pelanggan/pesan/insert")
    Call<TransaksiResponse> pesanKatering(@Body TransaksiRequest requestBody);

    @GET("pelanggan/pesan/list")
    Call<GetTransaksiResponse> getListTransaksiPelanggan(@Query("id_pelanggan") int id_pelanggan);
}
