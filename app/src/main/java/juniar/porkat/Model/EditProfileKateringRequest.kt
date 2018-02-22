package juniar.porkat.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by Nicolas Juniar on 02/01/2018.
 */
data class EditProfileKateringRequest(@SerializedName("id_katering") val id_katering:Int,
                                      @SerializedName("no_telp") val no_telp:String,
                                      @SerializedName("alamat") val alamat:String,
                                      @SerializedName("foto") val foto:String,
                                      @SerializedName("longitude") val longitude:Double,
                                      @SerializedName("latitude") val latitude:Double)