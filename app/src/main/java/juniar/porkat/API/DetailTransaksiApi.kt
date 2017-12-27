package juniar.porkat.API

import io.reactivex.Observable
import juniar.porkat.Model.UpdateNotaResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

/**
 * Created by Nicolas Juniar on 26/12/2017.
 */
interface DetailTransaksiApi
{
    @FormUrlEncoded
    @POST("pelanggan/pesan/update")
    fun updateNota(@Field("id_pesan") id_pesan:Int,
                   @Field("nota") nota:String,
                   @Field("foto_nota") foto_nota:String): Observable<UpdateNotaResponse>
}