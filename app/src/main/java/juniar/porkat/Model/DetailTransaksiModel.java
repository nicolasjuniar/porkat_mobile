package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.sql.Date;

/**
 * Created by Nicolas Juniar on 09/12/2017.
 */

public class DetailTransaksiModel {
    @SerializedName("id_detailpesan")
    Integer id_detailpesan;
    @SerializedName("id_pesan")
    Integer id_pesan;
    @SerializedName("waktu_pengantaran")
    String waktu_pengantaran;
    @SerializedName("id_menu")
    int id_menu;

    public DetailTransaksiModel() {
    }

    public DetailTransaksiModel(Integer id_detailpesan, Integer id_pesan, String waktu_pengantaran, int id_menu) {
        this.id_detailpesan = id_detailpesan;
        this.id_pesan = id_pesan;
        this.waktu_pengantaran = waktu_pengantaran;
        this.id_menu = id_menu;
    }

    public Integer getId_detailpesan() {
        return id_detailpesan;
    }

    public void setId_detailpesan(Integer id_detailpesan) {
        this.id_detailpesan = id_detailpesan;
    }

    public Integer getId_pesan() {
        return id_pesan;
    }

    public void setId_pesan(Integer id_pesan) {
        this.id_pesan = id_pesan;
    }

    public String getWaktu_pengantaran() {
        return waktu_pengantaran;
    }

    public void setWaktu_pengantaran(String waktu_pengantaran) {
        this.waktu_pengantaran = waktu_pengantaran;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }
}
