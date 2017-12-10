package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;

/**
 * Created by Nicolas Juniar on 15/11/2017.
 */

public class UlasanModel {
    @SerializedName("id_ulasan")
    private int id_ulasan;
    @SerializedName("ulasan")
    private String ulasan;
    @SerializedName("rating")
    private float rating;
    @SerializedName("waktu_ulasan")
    private String waktu_ulasan;
    @SerializedName("id_pengguna")
    private String id_pengguna;
    @SerializedName("nama_lengkap")
    private String nama_lengkap;

    public UlasanModel() {
    }

    public UlasanModel(int id_ulasan, String ulasan, float rating, String waktu_ulasan, String id_pengguna, String nama_lengkap) {
        this.id_ulasan = id_ulasan;
        this.ulasan = ulasan;
        this.rating = rating;
        this.waktu_ulasan = waktu_ulasan;
        this.id_pengguna = id_pengguna;
        this.nama_lengkap = nama_lengkap;
    }

    public int getId_ulasan() {
        return id_ulasan;
    }

    public void setId_ulasan(int id_ulasan) {
        this.id_ulasan = id_ulasan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getWaktu_ulasan() {
        return waktu_ulasan;
    }

    public void setWaktu_ulasan(String waktu_ulasan) {
        this.waktu_ulasan = waktu_ulasan;
    }

    public String getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(String id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UlasanModel)) return false;

        UlasanModel that = (UlasanModel) o;

        if (id_ulasan != that.id_ulasan) return false;
        if (Float.compare(that.rating, rating) != 0) return false;
        if (ulasan != null ? !ulasan.equals(that.ulasan) : that.ulasan != null) return false;
        if (waktu_ulasan != null ? !waktu_ulasan.equals(that.waktu_ulasan) : that.waktu_ulasan != null)
            return false;
        if (id_pengguna != null ? !id_pengguna.equals(that.id_pengguna) : that.id_pengguna != null)
            return false;
        return nama_lengkap != null ? nama_lengkap.equals(that.nama_lengkap) : that.nama_lengkap == null;
    }

    @Override
    public int hashCode() {
        int result = id_ulasan;
        result = 31 * result + (ulasan != null ? ulasan.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        result = 31 * result + (waktu_ulasan != null ? waktu_ulasan.hashCode() : 0);
        result = 31 * result + (id_pengguna != null ? id_pengguna.hashCode() : 0);
        result = 31 * result + (nama_lengkap != null ? nama_lengkap.hashCode() : 0);
        return result;
    }
}
