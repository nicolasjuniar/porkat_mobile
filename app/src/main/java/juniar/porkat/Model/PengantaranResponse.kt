package juniar.porkat.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by Nicolas Juniar on 30/12/2017.
 */
data class PengantaranResponse(@SerializedName("listtransaksi") var listtransaksi:ArrayList<PengantaranModel>)