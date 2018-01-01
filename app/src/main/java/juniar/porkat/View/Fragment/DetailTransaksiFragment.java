package juniar.porkat.View.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.R;
import juniar.porkat.View.Activity.TransaksiActivity;
import juniar.porkat.View.Interface.TransaksiCallback;

/**
 * Created by Nicolas Juniar on 02/12/2017.
 */

public class DetailTransaksiFragment extends Fragment{

    @BindView(R.id.tv_tglpesan)
    TextView tv_tglpesan;
    @BindView(R.id.sw_1minggu)
    SwitchCompat sw_1minggu;
    @BindView(R.id.sw_1bulan)
    SwitchCompat sw_1bulan;
    @BindView(R.id.sw_1kali)
    SwitchCompat sw_1kali;
    @BindView(R.id.sw_2kali)
    SwitchCompat sw_2kali;
    @BindView(R.id.sw_3kali)
    SwitchCompat sw_3kali;
    @BindView(R.id.cv_tglmulai)
    CardView cv_tglmulai;
    @BindView(R.id.btn_lanjut)
    Button btn_lanjut;

    Calendar calendar;
    int year,month,day;
    DateTime dateTime;

    int hariMulai;
    int bulanMulai;
    int tahunMulai;
    boolean cek=false;

    setTotalMenu callback;
    TransaksiCallback transaksiCallback;
    private DatePickerDialog datePickerDialog;

    private final String TAG=getClass().getSimpleName();

    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_detailtransaksi, container, false);
        ButterKnife.bind(this,view);
        calendar=Calendar.getInstance();
        dateTime=new DateTime();

        tv_tglpesan.setText(getDateNow());

        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog =new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String bulan=String.valueOf((month+1)/10==0?("0"+(month+1)):(month+1));
                tv_tglpesan.setText(changeDateFormat(String.valueOf(day)+"/"+String.valueOf(bulan)+"/"+String.valueOf(year)));
                hariMulai=day;
                bulanMulai=month;
                tahunMulai=year;
                cek=true;
            }
        },year,month,day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);


        cv_tglmulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cek)
                {
                    datePickerDialog.updateDate(tahunMulai,bulanMulai,hariMulai);
                }
                datePickerDialog.show();
            }
        });

        setSwitchCompatOnClick();

        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearError();
                if(checkInput())
                {
                    DateTime tgl_pesan= parseDateFormat(tv_tglpesan.getText().toString());
                    int lama_pesan=0;
                    if(sw_1minggu.isChecked())
                    {
                        lama_pesan=7;
                    }
                    else
                    {
                        lama_pesan=30;
                    }
                    ((TransaksiActivity)transaksiCallback).nextTransaksi(tgl_pesan,lama_pesan);
                }
            }
        });

        return view;
    }

    private String getDateNow(){
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        DateTime dateTime  = DateTime.parse(String.valueOf(new DateTime()), DateTimeFormat.forPattern(pattern));
        Locale locale=new Locale("in_ID");
        return dateTime.toString("d MMM yyyy",locale);
    }

    private String changeDateFormat(String input){
        DateTimeFormatter oldFormat=DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime oldDateTime=oldFormat.parseDateTime(input);
        DateTimeFormatter newFormat=DateTimeFormat.forPattern("d MMM yyyy");
        DateTime newDateTime=DateTime.parse(newFormat.print(oldDateTime),newFormat);
        return newDateTime.toString("d MMM yyyy");
    }

    private DateTime parseDateFormat(String input){
        String pattern;
        if(Character.isDigit(input.charAt(1)))
        {
            pattern="dd MMM yyyy";
        }
        else
        {
            pattern="d MMM yyyy";
        }
        DateTimeFormatter oldFormat=DateTimeFormat.forPattern(pattern);
        DateTime oldDateTime=oldFormat.parseDateTime(input);
        long time=oldDateTime.getMillis();
        DateTimeFormatter newFormat=DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime newDateTime=DateTime.parse(newFormat.print(oldDateTime),newFormat);
        return newDateTime;
    }

    public void clearError()
    {
        sw_3kali.setError(null);
        sw_1bulan.setError(null);
    }

    public boolean checkInput()
    {
        boolean check=true;
        if(!sw_1minggu.isChecked() && !sw_1bulan.isChecked())
        {
            Toast.makeText(getActivity(), "pilih lama pesanan", Toast.LENGTH_SHORT).show();
            check=false;
        }
        else if(!sw_1kali.isChecked() && !sw_2kali.isChecked() && !sw_3kali.isChecked())
        {
            Toast.makeText(getActivity(), "pilih jumlah pesanan", Toast.LENGTH_SHORT).show();
            check=false;
        }
        return check;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callback = (setTotalMenu) activity;
            transaksiCallback=(TransaksiActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement setTotalMenu");
        }
    }

    public interface setTotalMenu
    {
        void onChangeTotalMenu(int total);
    }

    public void setSwitchCompatOnClick()
    {
        sw_1kali.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    sw_2kali.setChecked(false);
                    sw_3kali.setChecked(false);
                    callback.onChangeTotalMenu(1);
                }
                else
                {
                    checkSetTotal();
                }
            }
        });

        sw_2kali.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    sw_1kali.setChecked(false);
                    sw_3kali.setChecked(false);
                    callback.onChangeTotalMenu(2);
                }
                else
                {
                    checkSetTotal();
                }
            }
        });

        sw_3kali.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    sw_1kali.setChecked(false);
                    sw_2kali.setChecked(false);
                    callback.onChangeTotalMenu(3);
                }
                else
                {
                    checkSetTotal();
                }
            }
        });

        sw_1minggu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    sw_1bulan.setChecked(false);
                }
            }
        });

        sw_1bulan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    sw_1minggu.setChecked(false);
                }
            }
        });
    }

    public void checkSetTotal()
    {
        if(!sw_1kali.isChecked() && !sw_2kali.isChecked() && !sw_3kali.isChecked())
        {
            callback.onChangeTotalMenu(0);
        }
    }
}
