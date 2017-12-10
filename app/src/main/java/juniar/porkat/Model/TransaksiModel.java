package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.sql.Date;

/**
 * Created by Nicolas Juniar on 09/12/2017.
 */

public class TransaksiModel {
    @SerializedName("id_pesan")
    Integer id_pesan;
    @SerializedName("id_pelanggan")
    int id_pelanggan;
    @SerializedName("id_katering")
    int id_katering;
    @SerializedName("tgl_mulai")
    String tgl_mulai;
    @SerializedName("tgl_selesai")
    String tgl_selesai;
    @SerializedName("catatan")
    String catatan;
    @SerializedName("nota")
    String nota;
    @SerializedName("alamat")
    String alamat;
    @SerializedName("longitude")
    double longitude;
    @SerializedName("latitude")
    double latitude;
    @SerializedName("total")
    int total;

    public TransaksiModel() {
    }

    public TransaksiModel(Integer id_pesan, int id_pelanggan, int id_katering, String tgl_mulai, String tgl_selesai, String catatan, String nota, String alamat, double longitude, double latitude, int total) {
        this.id_pesan = id_pesan;
        this.id_pelanggan = id_pelanggan;
        this.id_katering = id_katering;
        this.tgl_mulai = tgl_mulai;
        this.tgl_selesai = tgl_selesai;
        this.catatan = catatan;
        this.nota = nota;
        this.alamat = alamat;
        this.longitude = longitude;
        this.latitude = latitude;
        this.total = total;
    }

    public Integer getId_pesan() {
        return id_pesan;
    }

    public void setId_pesan(Integer id_pesan) {
        this.id_pesan = id_pesan;
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public int getId_katering() {
        return id_katering;
    }

    public void setId_katering(int id_katering) {
        this.id_katering = id_katering;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public void setTgl_selesai(String tgl_selesai) {
        this.tgl_selesai = tgl_selesai;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
