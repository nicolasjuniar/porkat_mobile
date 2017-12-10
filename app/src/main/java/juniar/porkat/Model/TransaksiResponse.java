package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 09/12/2017.
 */

public class TransaksiResponse {
    @SerializedName("message")
    String message;

    public TransaksiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
