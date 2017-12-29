package juniar.porkat.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class ListPilihMenu2Adapter extends RecyclerView.Adapter<ListPilihMenu2Adapter.ViewHolder> {

    List<MenuKateringModel> list;
    Context context;
    onMenuClickCallback callback;

    public View view;

    public ListPilihMenu2Adapter(List<MenuKateringModel> list, Context context,onMenuClickCallback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @Override
    public ListPilihMenu2Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pilihmenu2, parent, false);
        ListPilihMenu2Adapter.ViewHolder holder = new ListPilihMenu2Adapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ListPilihMenu2Adapter.ViewHolder holder, int position) {
        holder.model=list.get(position);
        holder.tv_namamenu.setText(holder.model.getNama_menu());
        holder.tv_harga.setText(convertToIDR(String.valueOf(holder.model.getHarga())));
        Picasso.with(context).load(base_url+"foto/katering/"+holder.model.getFoto()).into(holder.img_menu);
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
        @BindView(R.id.btn_pilihmenu)
        Button btn_pilihmenu;

        MenuKateringModel model;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            ButterKnife.bind(this,view);

            btn_pilihmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onMenuClickMenu(model);
                }
            });
        }
    }

    public interface onMenuClickCallback
    {
        void onMenuClickMenu(MenuKateringModel model);
    }
}
