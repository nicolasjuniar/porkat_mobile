package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 15/11/2017.
 */

public class CekUlasanResponse {
    @SerializedName("ulas")
    private boolean ulas;

    public CekUlasanResponse(boolean ulas) {
        this.ulas = ulas;
    }

    public boolean isUlas() {
        return ulas;
    }

    public void setUlas(boolean ulas) {
        this.ulas = ulas;
    }
}
