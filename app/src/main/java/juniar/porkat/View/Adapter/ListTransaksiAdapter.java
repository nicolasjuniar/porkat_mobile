package juniar.porkat.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.GetTransaksiModel;
import juniar.porkat.R;
import juniar.porkat.View.Activity.DetailTransaksiActivity;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class ListTransaksiAdapter extends RecyclerView.Adapter<ListTransaksiAdapter.ViewHolder> {

    List<GetTransaksiModel> list;
    Context context;

    public View view;

    public ListTransaksiAdapter(List<GetTransaksiModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ListTransaksiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_transaksipelanggan, parent, false);
        ListTransaksiAdapter.ViewHolder holder = new ListTransaksiAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ListTransaksiAdapter.ViewHolder holder, int position) {
        GetTransaksiModel model=list.get(position);
        holder.tv_namakatering.setText(model.getNama_katering());
        holder.tv_tglpesan.setText(changeDateFormat(model.getTgl_mulai())+" s/d "+ changeDateFormat(model.getTgl_selesai()));
        holder.tv_status.setText("Status transaksi : "+model.getStatus());
        holder.getTransaksiModel=model;
    }

    private String changeDateFormat(String input){
        DateTimeFormatter oldFormat=DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime oldDateTime=oldFormat.parseDateTime(input);
        DateTimeFormatter newFormat=DateTimeFormat.forPattern("d MMM yyyy");
        DateTime newDateTime=DateTime.parse(newFormat.print(oldDateTime),newFormat);
        Locale locale=new Locale("in_ID");
        return newDateTime.toString("d MMM yyyy",locale);
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

        @BindView(R.id.tv_namakatering)
        TextView tv_namakatering;
        @BindView(R.id.tv_tglpesan)
        TextView tv_tglpesan;
        @BindView(R.id.tv_status)
        TextView tv_status;

        GetTransaksiModel getTransaksiModel;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            ButterKnife.bind(this,view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle b=new Bundle();
                    b.putString("detail_transaksi",new Gson().toJson(getTransaksiModel));
                    Intent i=new Intent(view.getContext(), DetailTransaksiActivity.class);
                    i.putExtras(b);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }
    }
}
