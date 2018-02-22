package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 19/11/2017.
 */

public class ChangePasswordPelangganRequest {
    @SerializedName("id_pelanggan")
    private int id_pelanggan;
    @SerializedName("katasandi")
    private String katasandi;

    public ChangePasswordPelangganRequest(int id_pelanggan, String katasandi) {
        this.id_pelanggan = id_pelanggan;
        this.katasandi = katasandi;
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getKatasandi() {
        return katasandi;
    }

    public void setKatasandi(String katasandi) {
        this.katasandi = katasandi;
    }
}
