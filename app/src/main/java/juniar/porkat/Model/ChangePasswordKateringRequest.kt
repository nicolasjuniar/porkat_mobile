package juniar.porkat.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by Nicolas Juniar on 02/01/2018.
 */
data class ChangePasswordKateringRequest(@SerializedName("id_katering") val id_katering:Int,
                                         @SerializedName("katasandi") val katasandi:String)