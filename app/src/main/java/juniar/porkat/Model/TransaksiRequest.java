package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 09/12/2017.
 */

public class TransaksiRequest {
    @SerializedName("pesan")
    TransaksiModel pesan;
    @SerializedName("detailpesan")
    ArrayList<DetailTransaksiModel> detailpesan;

    public TransaksiRequest() {
        this.pesan=new TransaksiModel();
        this.detailpesan=new ArrayList<>();
    }

    public TransaksiRequest(TransaksiModel pesan, ArrayList<DetailTransaksiModel> detailpesan) {
        this.pesan = pesan;
        this.detailpesan = detailpesan;
    }

    public TransaksiModel getPesan() {
        return pesan;
    }

    public void setPesan(TransaksiModel pesan) {
        this.pesan = pesan;
    }

    public ArrayList<DetailTransaksiModel> getDetailpesan() {
        return detailpesan;
    }

    public void setDetailpesan(ArrayList<DetailTransaksiModel> detailpesan) {
        this.detailpesan = detailpesan;
    }
}
