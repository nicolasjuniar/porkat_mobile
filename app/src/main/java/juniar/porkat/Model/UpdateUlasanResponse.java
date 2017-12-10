package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 15/11/2017.
 */

public class UpdateUlasanResponse {
    @SerializedName("ulasan")
    private String ulasan;
    @SerializedName("rating")
    private float rating;
    @SerializedName("message")
    private String message;

    public UpdateUlasanResponse(String ulasan, float rating, String message) {
        this.ulasan = ulasan;
        this.rating = rating;
        this.message = message;
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
