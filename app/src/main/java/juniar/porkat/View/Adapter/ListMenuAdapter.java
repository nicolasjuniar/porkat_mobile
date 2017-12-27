package juniar.porkat.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.MenuKateringModel;
import juniar.porkat.R;

import static juniar.porkat.Main.base_url;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class ListMenuAdapter extends RecyclerView.Adapter<ListMenuAdapter.ViewHolder> {

    List<MenuKateringModel> list;
    Context context;

    public View view;

    public ListMenuAdapter(List<MenuKateringModel> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ListMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_menu, parent, false);
        ListMenuAdapter.ViewHolder holder = new ListMenuAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ListMenuAdapter.ViewHolder holder, int position) {
        holder.model=list.get(position);
        holder.tv_namamenu.setText(holder.model.getNama_menu());
        holder.tv_harga.setText(convertToIDR(String.valueOf(holder.model.getHarga())));
        Picasso.with(context).load(base_url+"foto/katering/"+holder.model.getFoto()).fit().into(holder.img_menu);
    }

    public String convertToIDR(String harga)
    {
        Locale indonesia=new Locale("id","ID");
        NumberFormat indoFormat=NumberFormat.getCurrencyInstance(indonesia);
        String idr = indoFormat.format(new BigDecimal(harga.toString()));

        return  idr;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_menu)
        ImageView img_menu;
        @BindView(R.id.tv_namamenu)
        TextView tv_namamenu;
        @BindView(R.id.tv_harga)
        TextView tv_harga;

        MenuKateringModel model;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            ButterKnife.bind(this,view);
        }
    }
}
