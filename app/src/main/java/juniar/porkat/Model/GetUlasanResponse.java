package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 15/11/2017.
 */

public class GetUlasanResponse {
    @SerializedName("listulasan")
    ArrayList<UlasanModel> listulasan;
    @SerializedName("ulasanpelanggan")
    UlasanModel ulasanpelanggan;

    public GetUlasanResponse(ArrayList<UlasanModel> listulasan, UlasanModel ulasanpelanggan) {
        this.listulasan = listulasan;
        this.ulasanpelanggan = ulasanpelanggan;
    }

    public ArrayList<UlasanModel> getListulasan() {
        return listulasan;
    }

    public void setListulasan(ArrayList<UlasanModel> listulasan) {
        this.listulasan = listulasan;
    }

    public UlasanModel getUlasanpelanggan() {
        return ulasanpelanggan;
    }

    public void setUlasanpelanggan(UlasanModel ulasanpelanggan) {
        this.ulasanpelanggan = ulasanpelanggan;
    }
}
