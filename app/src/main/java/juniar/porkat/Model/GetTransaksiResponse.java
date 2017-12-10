package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 10/12/2017.
 */

public class GetTransaksiResponse {
    @SerializedName("listtransaksi")
    ArrayList<GetTransaksiModel> listtransaksi;

    public GetTransaksiResponse(ArrayList<GetTransaksiModel> listtransaksi) {
        this.listtransaksi = listtransaksi;
    }

    public ArrayList<GetTransaksiModel> getListtransaksi() {
        return listtransaksi;
    }

    public void setListtransaksi(ArrayList<GetTransaksiModel> listtransaksi) {
        this.listtransaksi = listtransaksi;
    }
}
