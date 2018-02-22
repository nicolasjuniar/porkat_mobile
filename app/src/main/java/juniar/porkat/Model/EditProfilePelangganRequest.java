package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 19/11/2017.
 */

public class EditProfilePelangganRequest {
    @SerializedName("id_pelanggan")
    private int id_pelanggan;
    @SerializedName("no_telp")
    private String no_telp;
    @SerializedName("nama_lengkap")
    private String nama_lengkap;
    @SerializedName("alamat")
    private String alamat;

    public EditProfilePelangganRequest(int id_pelanggan, String no_telp, String nama_lengkap, String alamat) {
        this.id_pelanggan = id_pelanggan;
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
