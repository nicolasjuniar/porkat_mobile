package juniar.porkat.API

import io.reactivex.Observable
import juniar.porkat.Model.PengantaranModel
import juniar.porkat.Model.PengantaranResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Nicolas Juniar on 30/12/2017.
 */
interface PengataranAPI{
    @GET("transaksi/pengantaran/list")
    fun getListPengataran(@Query("id_katering") id_katering:Int):Observable<PengantaranResponse>
}