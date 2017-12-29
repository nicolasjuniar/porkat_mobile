package juniar.porkat.View.Interface

import juniar.porkat.Model.PengantaranModel

/**
 * Created by Nicolas Juniar on 30/12/2017.
 */
interface PengataranListener{
    fun onGetListPengataran(error:Boolean,listPengataran:ArrayList<PengantaranModel>?,t:Throwable?)
}