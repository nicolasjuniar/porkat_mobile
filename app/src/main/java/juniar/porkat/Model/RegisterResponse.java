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
    @SerializedName("datapelanggan")
    PelangganModel datapelanggan;
    @SerializedName("datakatering")
    KateringModel datakatering;

    public RegisterResponse(boolean status, String message, PelangganModel datapelanggan, KateringModel datakatering) {
        this.status = status;
        this.message = message;
        this.datapelanggan = datapelanggan;
        this.datakatering = datakatering;
    }

    public KateringModel getDatakatering() {
        return datakatering;
    }

    public void setDatakatering(KateringModel datakatering) {
        this.datakatering = datakatering;
    }

    public PelangganModel getDatapelanggan() {
        return datapelanggan;
    }

    public void setDatapelanggan(PelangganModel datapelanggan) {
        this.datapelanggan = datapelanggan;
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
