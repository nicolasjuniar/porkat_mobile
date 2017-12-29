package juniar.porkat.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by Nicolas Juniar on 29/12/2017.
 */
data class PengantaranModel(
        @SerializedName("id_pesan") var id_pesan:Int,
        @SerializedName("id_detailpesan") var id_detailpesan:Int,
        @SerializedName("nama_lengkap") var nama_lengkap:String,
        @SerializedName("catatan") var catatan:String,
        @SerializedName("alamat") var alamat:String,
        @SerializedName("longitude") var longitude:Double,
        @SerializedName("latitude") var latitude:Double,
        @SerializedName("waktu_pengantaran") var waktu_pengataran:String,
        @SerializedName("nama_menu") var nama_menu:String,
        @SerializedName("foto") var foto:String,
        @SerializedName("status") var status:String
)