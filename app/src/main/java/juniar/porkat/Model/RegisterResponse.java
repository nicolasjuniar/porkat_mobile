package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public class RegisterResponse {
    @SerializedName("status")
    boolean status;
    @SerializedName("message")
    String message;

    public RegisterResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
