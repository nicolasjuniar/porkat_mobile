package juniar.porkat.View.Interface

import juniar.porkat.Model.MakananModel

/**
 * Created by Nicolas Juniar on 02/01/2018.
 */
interface LihatMakananListener
{
    fun onGetListMakanan(error:Boolean,listMakanan:ArrayList<MakananModel>?,t:Throwable?)
}