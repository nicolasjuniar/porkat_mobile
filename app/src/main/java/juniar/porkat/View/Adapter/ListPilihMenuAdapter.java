package juniar.porkat.View.Adapter;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.MenuKateringModel;
import juniar.porkat.Model.TransaksiMenuModel;
import juniar.porkat.R;
import juniar.porkat.View.Fragment.PilihMenuDialog;

import static juniar.porkat.Main.base_url;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class ListPilihMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements PilihMenuDialog.GetMenuCallback {

    List<TransaksiMenuModel> list;
    Context context;
    Bundle bundle;
    int height;
    int width;
    PilihMenuDialog dialog=new PilihMenuDialog();
    private final String TAG=getClass().getSimpleName();
    int hour,minute;
    Calendar calendar;

    private final int ADD_MENU=1;
    private final int GET_MENU=2;
    private int click_position=0;
    public View view;
    private int type;

    public ListPilihMenuAdapter(List<TransaksiMenuModel> list, Context context, Bundle bundle) {
        this.context = context;
        this.list = list;
        this.bundle=bundle;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        calendar=Calendar.getInstance();
        hour=calendar.get(Calendar.HOUR_OF_DAY);
        minute=calendar.get(Calendar.MINUTE);
        if(viewType==ADD_MENU)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pilihmenu, parent, false);
            ListPilihMenuAdapter.ViewHolderAddMenu AddMenuHolder = new ListPilihMenuAdapter.ViewHolderAddMenu(v);
            v.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            width = ViewGroup.LayoutParams.MATCH_PARENT;
            height = v.getMeasuredHeight();
            dialog.setArguments(bundle);
            dialog.setCallback(this);
            return AddMenuHolder;
        }
        else
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_getmenu, parent, false);
            ListPilihMenuAdapter.ViewHolderGetMenu GetMenuHolder = new ListPilihMenuAdapter.ViewHolderGetMenu(v);
            v.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            width = ViewGroup.LayoutParams.MATCH_PARENT;
            height = v.getMeasuredHeight();
            return GetMenuHolder;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case ADD_MENU:
            {
                ViewHolderAddMenu holder1=(ViewHolderAddMenu)holder;
                holder1.position=position;
                if(list.get(position).isVisibility())
                {
                    holder1.setViewHoldervVisible(width,height);
                }else
                {
                    holder1.setViewHoldervVisible(width,0);
                }
                break;
            }
            case GET_MENU:
            {
                final ViewHolderGetMenu holder2=(ViewHolderGetMenu) holder;
                MenuKateringModel model=list.get(position).getMenuKateringModel();
                holder2.position=position;
                holder2.tv_namamenu.setText(list.get(position).getMenuKateringModel().getNama_menu());
                Picasso.with(context).load(base_url+"foto/katering/"+model.getFoto()).fit().into(holder2.img_menu);
                if(list.get(position).isVisibility())
                {
                    holder2.setViewHolderVisible(width,height);
                }else
                {
                    holder2.setViewHolderVisible(width,0);
                }
                holder2.tv_waktu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog timePickerDialog=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                holder2.tv_waktu.setText(hour+":"+minute);
                                list.get(position).setJam_pengantaran(parseDateTime(holder2.tv_waktu.getText().toString()));
                            }
                        },hour,minute,false);
                        timePickerDialog.show();
                    }
                });
                break;
            }
        }
    }

    private DateTime parseDateTime(String input){
        DateTimeFormatter formatter= DateTimeFormat.forPattern("HH:mm");
        DateTime dateTime=formatter.parseDateTime(input);
        return dateTime;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolderAddMenu extends RecyclerView.ViewHolder {
        int position;
        ViewHolderAddMenu(View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                    click_position=position;
                    dialog.show(manager,"pilih menu");
                }
            });
        }

        void setViewHoldervVisible(int width,int height)
        {
            itemView.setLayoutParams(new ViewGroup.LayoutParams(width,height));
        }
    }

    public class ViewHolderGetMenu extends RecyclerView.ViewHolder {
        @BindView(R.id.img_menu)
        ImageView img_menu;
        @BindView(R.id.tv_namamenu)
        TextView tv_namamenu;
        @BindView(R.id.tv_waktu)
        TextView tv_waktu;
        int position;
        ViewHolderGetMenu(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,view);

            img_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                    click_position=position;
                    dialog.show(manager,"pilih menu");
                }
            });
        }

        void setViewHolderVisible(int width,int height)
        {
            itemView.setLayoutParams(new ViewGroup.LayoutParams(width,height));
        }
    }

    @Override
    public void getMenu(MenuKateringModel model) {
        list.get(click_position).setType(GET_MENU);
        list.get(click_position).setMenuKateringModel(model);
        notifyDataSetChanged();
        notifyItemChanged(click_position);
    }
}