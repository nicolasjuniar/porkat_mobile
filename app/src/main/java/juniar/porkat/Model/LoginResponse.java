package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 15/08/2017.
 */

public class LoginResponse {
    @SerializedName("status")
    private boolean status;
    @SerializedName("role")
    private String role;
    @SerializedName("message")
    private String message;
    @SerializedName("datapelanggan")
    private PelangganModel datapelanggan;
    @SerializedName("datakatering")
    private KateringModel datakatering;

    public LoginResponse(boolean status, String role, String message, PelangganModel datapelanggan, KateringModel datakatering) {
        this.status = status;
        this.role = role;
        this.message = message;
        this.datapelanggan = datapelanggan;
        this.datakatering = datakatering;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PelangganModel getDatapelanggan() {
        return datapelanggan;
    }

    public void setDatapelanggan(PelangganModel datapelanggan) {
        this.datapelanggan = datapelanggan;
    }

    public KateringModel getDatakatering() {
        return datakatering;
    }

    public void setDatakatering(KateringModel datakatering) {
        this.datakatering = datakatering;
    }
}
