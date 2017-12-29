package juniar.porkat.Presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import juniar.porkat.API.PengataranAPI
import juniar.porkat.Utils.NetworkConfig
import juniar.porkat.View.Interface.PengataranListener

/**
 * Created by Nicolas Juniar on 30/12/2017.
 */
class PengantaranPresenter(listener:PengataranListener){
    var listener:PengataranListener
    init {
        this.listener=listener
    }

    fun getListPengataran(id_katering:Int)
    {
        NetworkConfig.createService(PengataranAPI::class.java)
                .getListPengataran(id_katering)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result->listener.onGetListPengataran(false,result.listtransaksi,null)},
                        {error->listener.onGetListPengataran(true,null,error)}
                )
    }
}