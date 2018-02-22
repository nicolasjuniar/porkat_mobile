package juniar.porkat.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by Nicolas Juniar on 01/01/2018.
 */
data class ListMakananResponse(@SerializedName("listmenu") val listmenu:ArrayList<MakananModel>)