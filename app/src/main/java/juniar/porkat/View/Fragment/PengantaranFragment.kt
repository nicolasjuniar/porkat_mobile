package juniar.porkat.View.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import juniar.porkat.Model.PengantaranModel
import juniar.porkat.Presenter.PengantaranPresenter
import juniar.porkat.R
import juniar.porkat.View.Adapter.PengantaranAdapter
import juniar.porkat.View.Interface.PengataranListener
import kotlinx.android.synthetic.main.fragment_kelolapengantaran.*

/**
 * Created by Nicolas Juniar on 30/12/2017.
 */

class PengantaranFragment:Fragment(),PengataranListener{


    lateinit var presenter: PengantaranPresenter
    lateinit var pengantaranAdapter: PengantaranAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater?.inflate(R.layout.fragment_kelolapengantaran,container,false)

        presenter= PengantaranPresenter(this)
        presenter.getListPengataran(1)

//        swipe_refresh_layout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { presenter.getListPengataran(1) })
//
//        rv_transaksi.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                val topRowVerticalPosition = if (recyclerView == null || recyclerView.childCount == 0) 0 else recyclerView.getChildAt(0).top
//                swipe_refresh_layout.setEnabled(topRowVerticalPosition >= 0)
//            }
//        })

        return view
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
            Log.d("error x_x :",t?.toString())
        }
    }

}