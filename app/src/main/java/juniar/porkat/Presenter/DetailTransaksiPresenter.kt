package juniar.porkat.Presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import juniar.porkat.API.DetailTransaksiApi
import juniar.porkat.Utils.NetworkConfig
import juniar.porkat.View.Interface.UpdateNotaListener

/**
 * Created by Nicolas Juniar on 26/12/2017.
 */

class DetailTransaksiPresenter(listener:UpdateNotaListener)
{
    var listener:UpdateNotaListener
    init {
        this.listener=listener
    }

    fun updateNota(id_pesan:Int,nota:String,foto_nota:String)
    {
        NetworkConfig.createService(DetailTransaksiApi::class.java)
                .updateNota(id_pesan,nota,foto_nota)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result->listener.onUpdateNota(false,result.message,null)},
                        {error->listener.onUpdateNota(true,error.message,error.cause)}
                )
    }
}