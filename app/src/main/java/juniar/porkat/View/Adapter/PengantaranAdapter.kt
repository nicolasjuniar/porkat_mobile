package juniar.porkat.View.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import juniar.porkat.Main.base_url
import juniar.porkat.Model.PengantaranModel
import juniar.porkat.R
import kotlinx.android.synthetic.main.viewholder_pengataran.view.*

/**
 * Created by Nicolas Juniar on 21/12/2017.
 */

class PengantaranAdapter(list: MutableList<PengantaranModel>, context:Context) : RecyclerView.Adapter<PengantaranAdapter.viewHolder>() {

    var list:MutableList<PengantaranModel>
    var context:Context
    init {
        this.list=list
        this.context=context
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): viewHolder {
        val layoutInflater=LayoutInflater.from(parent?.context)
        return viewHolder(layoutInflater.inflate(R.layout.viewholder_pengataran, parent, false))
    }


    class viewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tv_namalengkap=view.tv_namalengkap
        val tv_alamat=view.tv_alamat
        val tv_waktu=view.tv_waktu
        val img_menu=view.img_menu
        val img_indicator=view.img_indicator
        var view:View

        init {
            this.view=view
        }


        fun bind(model: PengantaranModel)
        {
            tv_namalengkap.text=model.nama_lengkap
            tv_alamat.text=model.alamat
            tv_waktu.text=model.waktu_pengataran
            Picasso.with(view.context).load(base_url + "foto/menu/" + model.foto).into(img_menu)
            img_indicator.setImageResource(R.color.Red)
        }
    }
}