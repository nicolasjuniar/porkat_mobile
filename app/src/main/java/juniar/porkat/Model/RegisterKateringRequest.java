package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 19/11/2017.
 */

public class RegisterKateringRequest {
    @SerializedName("id_pengguna")
    private String id_pengguna;
    @SerializedName("katasandi")
    private String katasandi;
    @SerializedName("nama_katering")
    private String nama_katering;
    @SerializedName("no_telp")
    private String no_telp;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("no_verifikasi")
    private String no_verifikasi;

    public RegisterKateringRequest(String id_pengguna, String katasandi, String nama_katering, String no_telp, String alamat, String no_verifikasi) {
        this.id_pengguna = id_pengguna;
        this.katasandi = katasandi;
        this.nama_katering = nama_katering;
        this.no_telp = no_telp;
        this.alamat = alamat;
        this.no_verifikasi = no_verifikasi;
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

    public String getNama_katering() {
        return nama_katering;
    }

    public void setNama_katering(String nama_katering) {
        this.nama_katering = nama_katering;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_verifikasi() {
        return no_verifikasi;
    }

    public void setNo_verifikasi(String no_verifikasi) {
        this.no_verifikasi = no_verifikasi;
    }
}
