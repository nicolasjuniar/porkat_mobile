package juniar.porkat.View.Fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import com.google.gson.Gson
import juniar.porkat.Model.KateringModel
import juniar.porkat.Model.MakananModel
import juniar.porkat.Presenter.LihatMakananPresenter
import juniar.porkat.R
import juniar.porkat.Utils.PreferenceHelper
import juniar.porkat.View.Adapter.MakananAdapter
import juniar.porkat.View.Interface.LihatMakananListener
import kotlinx.android.synthetic.main.fragment_lihatmakanan.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.*

/**
 * Created by Nicolas Juniar on 02/01/2018.
 */
class LihatMakananFragment : Fragment(),LihatMakananListener
{
    lateinit var presenter:LihatMakananPresenter
    lateinit var preferences:PreferenceHelper
    lateinit var makananAdapter:MakananAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_lihatmakanan,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences= PreferenceHelper.getInstance(context)
        val katering= Gson().fromJson(preferences.getString("profile_katering",""), KateringModel::class.java)
        presenter= LihatMakananPresenter(this)
        presenter.getListMakanan(katering.id_katering,"")

        tv_tanggal.text=getDateNow()

        swipe_refresh_layout.setOnRefreshListener{ presenter.getListMakanan(katering.id_katering,"") }

        rv_makanan.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val topRowVerticalPosition = if (recyclerView == null || recyclerView.childCount == 0) 0 else recyclerView.getChildAt(0).top
                swipe_refresh_layout.isEnabled=topRowVerticalPosition >= 0
            }
        })

        val calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        var hariMulai: Int=0
        var bulanMulai: Int=0
        var tahunMulai: Int=0
        var cek:Boolean=false


        val datePickerDialog = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            val bulan = (if ((month + 1) / 10 == 0) "0" + (month + 1) else month + 1).toString()
            tv_tanggal.text=changeDateFormat(day.toString() + "/" + bulan.toString() + "/" + year.toString())
            hariMulai = day
            bulanMulai = month
            tahunMulai = year
            cek = true
        }, year, month, day)
        datePickerDialog.datePicker.minDate=System.currentTimeMillis() - 1000

        cv_tanggal.setOnClickListener{
            if (cek) {
                datePickerDialog.updateDate(tahunMulai, bulanMulai, hariMulai)
            }
            datePickerDialog.show()}
    }

    fun changeDateFormat(input: String): String {
        val oldFormat = DateTimeFormat.forPattern("dd/MM/yyyy")
        val oldDateTime = oldFormat.parseDateTime(input)
        val newFormat = DateTimeFormat.forPattern("d MMM yyyy")
        val newDateTime = DateTime.parse(newFormat.print(oldDateTime), newFormat)
        return newDateTime.toString("d MMM yyyy")
    }

    fun getDateNow(): String {
        val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        val dateTime = DateTime.parse(DateTime().toString(), DateTimeFormat.forPattern(pattern))
        val locale = Locale("in_ID")
        return dateTime.toString("d MMM yyyy", locale)
    }

    override fun onGetListMakanan(error: Boolean, listMakanan: ArrayList<MakananModel>?, t: Throwable?) {
        if(!error)
        {
            makananAdapter = MakananAdapter(listMakanan!!)
            rv_makanan.adapter= makananAdapter
            rv_makanan.layoutManager=LinearLayoutManager(activity)
            progressBar.visibility=View.GONE
            swipe_refresh_layout.isRefreshing=false
        }
        else
        {
            Toast.makeText(activity,t?.message,Toast.LENGTH_SHORT).show()
            Log.d("error sem: ",t?.message)
            progressBar.visibility=View.GONE
            swipe_refresh_layout.isRefreshing=false
        }
    }
}