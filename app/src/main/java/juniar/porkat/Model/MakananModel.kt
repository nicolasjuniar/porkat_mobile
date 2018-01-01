package juniar.porkat.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by Nicolas Juniar on 01/01/2018.
 */
data class MakananModel(@SerializedName("nama_menu") val nama_menu:String,
                        @SerializedName("jumlah") val jumlah:Int,
                        @SerializedName("foto") val foto:String)