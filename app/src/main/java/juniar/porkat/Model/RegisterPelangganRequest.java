package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 19/11/2017.
 */

public class RegisterPelangganRequest {
    @SerializedName("id_pengguna")
    private String id_pengguna;
    @SerializedName("katasandi")
    private String katasandi;
    @SerializedName("no_telp")
    private String no_telp;
    @SerializedName("nama_lengkap")
    private String nama_lengkap;
    @SerializedName("alamat")
    private String alamat;

    public RegisterPelangganRequest(String id_pengguna, String katasandi, String no_telp, String nama_lengkap, String alamat) {
        this.id_pengguna = id_pengguna;
        this.katasandi = katasandi;
        this.no_telp = no_telp;
        this.nama_lengkap = nama_lengkap;
        this.alamat = alamat;
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
