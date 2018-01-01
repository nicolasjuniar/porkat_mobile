package juniar.porkat.Presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import juniar.porkat.API.ListMakananAPI
import juniar.porkat.Utils.NetworkConfig
import juniar.porkat.View.Interface.LihatMakananListener

/**
 * Created by Nicolas Juniar on 02/01/2018.
 */
class LihatMakananPresenter(listener:LihatMakananListener)
{
    var listener:LihatMakananListener
    init {
        this.listener=listener
    }

    fun getListMakanan(id_katering:Int,tanggal:String)
    {
        NetworkConfig.createService(ListMakananAPI::class.java)
                .getListMakanan(id_katering, tanggal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result-> listener.onGetListMakanan(false,result.listmakanan,null)},
                        {error->listener.onGetListMakanan(true,null,error)}
                )

    }
}