package juniar.porkat.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.KateringModel;
import juniar.porkat.R;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Activity.DetailKateringActivity;

import static juniar.porkat.Main.base_url;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class AdapterListKatering extends RecyclerView.Adapter<AdapterListKatering.ViewHolder> {

    List<KateringModel> list;
    Context context;
    PreferenceHelper preferences;
    Location myLocation;
    Location kateringLocation;

    public View view;

    public AdapterListKatering(List<KateringModel> list, Context context, PreferenceHelper preferences) {
        this.list = list;
        this.context = context;
        this.preferences = preferences;
    }

    @Override
    public AdapterListKatering.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_katering, parent, false);
        AdapterListKatering.ViewHolder holder = new AdapterListKatering.ViewHolder(v);

        myLocation =new Location("");
        kateringLocation=new Location("");
        myLocation.setLongitude(Double.parseDouble(preferences.getString("my_longitude","0")));
        myLocation.setLatitude(Double.parseDouble(preferences.getString("my_latitude","0")));

        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterListKatering.ViewHolder holder, int position) {
        KateringModel model=list.get(position);
        holder.katering=model;
        holder.tv_fullname.setText(model.getNama_katering());
        holder.tv_address.setText(model.getAlamat());
        holder.tv_rating.setText(String.valueOf(model.getRating()));
        Picasso.with(context).load(base_url+"foto/katering/"+model.getFoto()).fit().into(holder.img_photokatering);
        kateringLocation.setLongitude(model.getLongitude());
        kateringLocation.setLatitude(model.getLatitude());
        double distance=myLocation.distanceTo(kateringLocation)/1000;
        holder.tv_distance.setText(String.valueOf(distance).substring(0,3)+" km");
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

        @BindView(R.id.img_photokatering)
        ImageView img_photokatering;
        @BindView(R.id.tv_fullname)
        TextView tv_fullname;
        @BindView(R.id.tv_address)
        TextView tv_address;
        @BindView(R.id.tv_rating)
        TextView tv_rating;
        @BindView(R.id.tv_distance)
        TextView tv_distance;

        KateringModel katering;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            ButterKnife.bind(this,view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle b=new Bundle();
                    b.putString("detail_katering",new Gson().toJson(katering));
                    Intent i=new Intent(view.getContext(), DetailKateringActivity.class);
                    i.putExtras(b);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }
    }
}
