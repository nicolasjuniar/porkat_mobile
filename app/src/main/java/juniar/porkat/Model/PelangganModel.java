package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 17/10/2017.
 */

public class PelangganModel {
    @SerializedName("id_pelanggan")
    int id_pelanggan;
    @SerializedName("id_pengguna")
    String id_pengguna;
    @SerializedName("katasandi")
    String katasandi;
    @SerializedName("no_telp")
    String no_telp;
    @SerializedName("nama_lengkap")
    String nama_lengkap;
    @SerializedName("alamat")
    String alamat;

    public PelangganModel(int id_pelanggan, String id_pengguna, String katasandi, String no_telp, String nama_lengkap, String alamat) {
        this.id_pelanggan = id_pelanggan;
        this.id_pengguna = id_pengguna;
        this.katasandi = katasandi;
        this.no_telp = no_telp;
        this.nama_lengkap = nama_lengkap;
        this.alamat = alamat;
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(String id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getKatasandi() {
        return katasandi;
    }

    public void setKatasandi(String katasandi) {
        this.katasandi = katasandi;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
