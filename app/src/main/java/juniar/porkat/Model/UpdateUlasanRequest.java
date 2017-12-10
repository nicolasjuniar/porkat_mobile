package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 19/11/2017.
 */

public class UpdateUlasanRequest {
    @SerializedName("id_ulasan")
    private int id_ulasan;
    @SerializedName("ulasan")
    private String ulasan;
    @SerializedName("rating")
    private float rating;

    public UpdateUlasanRequest(int id_ulasan, String ulasan, float rating) {
        this.id_ulasan = id_ulasan;
        this.ulasan = ulasan;
        this.rating = rating;
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
}
