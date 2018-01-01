package juniar.porkat.API

import io.reactivex.Observable
import juniar.porkat.Model.ListMakananResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Nicolas Juniar on 01/01/2018.
 */
interface ListMakananAPI
{
    @GET("transaksi/makanan/list")
    fun getListMakanan(@Query("id_katering") id_katering:Int,
                       @Query("tanggal") tanggal:String): Observable<ListMakananResponse>
}