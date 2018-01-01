package juniar.porkat.View.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import juniar.porkat.Main.base_url
import juniar.porkat.Model.MakananModel
import juniar.porkat.Model.PengantaranModel
import juniar.porkat.R
import kotlinx.android.synthetic.main.viewholder_makanan.view.*
import kotlinx.android.synthetic.main.viewholder_pengataran.view.*
import org.joda.time.DateTime
import org.joda.time.Duration
import org.joda.time.format.DateTimeFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Nicolas Juniar on 21/12/2017.
 */

class MakananAdapter(list: MutableList<MakananModel>) : RecyclerView.Adapter<MakananAdapter.viewHolder>() {

    var list:MutableList<MakananModel>
    init {
        this.list=list
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): viewHolder {
        val layoutInflater=LayoutInflater.from(parent?.context)
        return viewHolder(layoutInflater.inflate(R.layout.viewholder_makanan, parent, false))
    }


    class viewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val img_makanan=view.img_makanan
        val tv_makanan=view.tv_makanan
        val tv_jumlah=view.tv_jumlah
        var view:View

        init {
            this.view=view
        }


        fun bind(model: MakananModel)
        {
            tv_makanan.text=model.nama_menu
            tv_jumlah.text="Jumlah: ${model.jumlah}"
            Picasso.with(view.context).load(base_url + "foto/menu/" + model.foto).into(img_makanan)
        }
    }
}