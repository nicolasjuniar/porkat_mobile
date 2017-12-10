package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 15/11/2017.
 */

public class InsertUlasanResponse {
    @SerializedName("id_ulasan")
    private int id_ulasan;
    @SerializedName("ulasan")
    private String ulasan;
    @SerializedName("rating")
    private float rating;
    @SerializedName("message")
    private String message;

    public InsertUlasanResponse(int id_ulasan, String ulasan, float rating, String message) {
        this.id_ulasan = id_ulasan;
        this.ulasan = ulasan;
        this.rating = rating;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
