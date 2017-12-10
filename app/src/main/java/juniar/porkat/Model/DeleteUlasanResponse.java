package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 22/11/2017.
 */

public class DeleteUlasanResponse {
    @SerializedName("message")
    private String message;

    public DeleteUlasanResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
