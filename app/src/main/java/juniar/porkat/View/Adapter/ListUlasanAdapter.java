package juniar.porkat.View.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.UlasanModel;
import juniar.porkat.R;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class ListUlasanAdapter extends RecyclerView.Adapter<ListUlasanAdapter.ViewHolder> {

    List<UlasanModel> list;
    Context context;

    public View view;
    private Random rnd;
    private int color;

    public ListUlasanAdapter(List<UlasanModel> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ListUlasanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_ulasan, parent, false);
        ListUlasanAdapter.ViewHolder holder = new ListUlasanAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ListUlasanAdapter.ViewHolder holder, int position) {
        UlasanModel model=list.get(position);

       holder.tv_fullname.setText(model.getNama_lengkap());
       holder.rating.setRating(model.getRating());
       holder.tv_ulasan.setText(model.getUlasan());
       holder.tv_waktuulasan.setText(parseDateTime(model.getWaktu_ulasan()));
       holder.tv_character.setText(model.getNama_lengkap().substring(0,1));
       rnd = new Random();
       color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
       holder.img_profile.setColorFilter(color);
    }

    private static String parseDateTime(String input){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTime dateTime  = DateTime.parse(input, DateTimeFormat.forPattern(pattern));
        return dateTime.toString("d MMM yyyy HH:mm");
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

        @BindView(R.id.tv_fullname)
        TextView tv_fullname;
        @BindView(R.id.rating)
        RatingBar rating;
        @BindView(R.id.tv_waktuulasan)
        TextView tv_waktuulasan;
        @BindView(R.id.tv_ulasan)
        TextView tv_ulasan;
        @BindView(R.id.img_profile)
        ImageView img_profile;
        @BindView(R.id.tv_character)
        TextView tv_character;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            ButterKnife.bind(this,view);
        }
    }
}
