package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 17/10/2017.
 */

public class KateringModel {
    @SerializedName("id_katering")
    int id_katering;
    @SerializedName("id_pengguna")
    String id_pengguna;
    @SerializedName("katasandi")
    String katasandi;
    @SerializedName("nama_katering")
    String nama_katering;
    @SerializedName("no_telp")
    String no_telp;
    @SerializedName("alamat")
    String alamat;
    @SerializedName("foto")
    String foto;
    @SerializedName("rating")
    float rating;
    @SerializedName("longitude")
    double longitude;
    @SerializedName("latitude")
    double latitude;
    @SerializedName("no_verifikasi")
    String no_verifikasi;
    double distance;

    public KateringModel(int id_katering, String id_pengguna, String katasandi, String nama_katering, String no_telp, String alamat, String foto, float rating, double longitude, double latitude, String no_verifikasi) {
        this.id_katering = id_katering;
        this.id_pengguna = id_pengguna;
        this.katasandi = katasandi;
        this.nama_katering = nama_katering;
        this.no_telp = no_telp;
        this.alamat = alamat;
        this.foto = foto;
        this.rating = rating;
        this.longitude = longitude;
        this.latitude = latitude;
        this.no_verifikasi = no_verifikasi;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getId_katering() {
        return id_katering;
    }

    public void setId_katering(int id_katering) {
        this.id_katering = id_katering;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getNo_verifikasi() {
        return no_verifikasi;
    }

    public void setNo_verifikasi(String no_verifikasi) {
        this.no_verifikasi = no_verifikasi;
    }
}
