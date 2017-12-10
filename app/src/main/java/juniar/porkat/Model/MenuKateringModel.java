package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 01/11/2017.
 */

public class MenuKateringModel {
    @SerializedName("id_menu")
    private int id_menu;
    @SerializedName("nama_menu")
    private String nama_menu;
    @SerializedName("foto")
    private String foto;
    @SerializedName("harga")
    private int harga;
    @SerializedName("id_katering")
    private int id_katering;

    public MenuKateringModel() {
    }

    public MenuKateringModel(int id_menu, String nama_menu, String foto, int harga, int id_katering) {
        this.id_menu = id_menu;
        this.nama_menu = nama_menu;
        this.foto = foto;
        this.harga = harga;
        this.id_katering = id_katering;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public void setNama_menu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getId_katering() {
        return id_katering;
    }

    public void setId_katering(int id_katering) {
        this.id_katering = id_katering;
    }
}
