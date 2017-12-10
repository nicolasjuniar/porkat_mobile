package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 19/11/2017.
 */

public class InsertUlasanRequest {
    @SerializedName("ulasan")
    private String ulasan;
    @SerializedName("rating")
    private float rating;
    @SerializedName("id_pelanggan")
    private int id_pelanggan;
    @SerializedName("id_katering")
    private int id_katering;

    public InsertUlasanRequest(String ulasan, float rating, int id_pelanggan, int id_katering) {
        this.ulasan = ulasan;
        this.rating = rating;
        this.id_pelanggan = id_pelanggan;
        this.id_katering = id_katering;
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

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public int getId_katering() {
        return id_katering;
    }

    public void setId_katering(int id_katering) {
        this.id_katering = id_katering;
    }
}
