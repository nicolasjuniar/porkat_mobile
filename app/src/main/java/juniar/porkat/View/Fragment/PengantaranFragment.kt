package juniar.porkat.View.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import juniar.porkat.Model.KateringModel
import juniar.porkat.Model.PengantaranModel
import juniar.porkat.Presenter.PengantaranPresenter
import juniar.porkat.R
import juniar.porkat.Utils.PreferenceHelper
import juniar.porkat.View.Adapter.PengantaranAdapter
import juniar.porkat.View.Interface.PengataranListener
import kotlinx.android.synthetic.main.fragment_kelolapengantaran.*

/**
 * Created by Nicolas Juniar on 30/12/2017.
 */

class PengantaranFragment:Fragment(),PengataranListener{

    lateinit var presenter: PengantaranPresenter
    lateinit var pengantaranAdapter: PengantaranAdapter
    lateinit var preferences: PreferenceHelper

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater?.inflate(R.layout.fragment_kelolapengantaran,container,false)
        val katering= Gson().fromJson(preferences.getString("profile_katering",""),KateringModel::class.java)
        preferences= PreferenceHelper.getInstance(context)

        presenter= PengantaranPresenter(this)
        presenter.getListPengataran(katering.id_katering)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val katering= Gson().fromJson(preferences.getString("profile_katering",""),KateringModel::class.java)
        setRefreshLayout(katering)
    }

    fun setRefreshLayout(katering:KateringModel)
    {
        swipe_refresh_layout.setOnRefreshListener{ presenter.getListPengataran(katering.id_katering) }

        rv_transaksi.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val topRowVerticalPosition = if (recyclerView == null || recyclerView.childCount == 0) 0 else recyclerView.getChildAt(0).top
                swipe_refresh_layout.isEnabled=topRowVerticalPosition >= 0
            }
        })
    }

    override fun onGetListPengataran(error: Boolean, listPengataran: ArrayList<PengantaranModel>?, t: Throwable?) {
        progressBar.visibility=View.GONE
        if(!error)
        {
            pengantaranAdapter = PengantaranAdapter(listPengataran!!,activity)
            rv_transaksi.adapter= pengantaranAdapter
            rv_transaksi.layoutManager=LinearLayoutManager(activity)
        }
        else
        {
            Toast.makeText(activity,t?.message,Toast.LENGTH_SHORT).show()
        }
    }

}