package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 10/12/2017.
 */

public class GetTransaksiModel {
    @SerializedName("id_pesan")
    int id_pesan;
    @SerializedName("nama_katering")
    String nama_katering;
    @SerializedName("tgl_mulai")
    String tgl_mulai;
    @SerializedName("tgl_selesai")
    String tgl_selesai;
    @SerializedName("alamat")
    String alamat;
    @SerializedName("catatan")
    String catatan;
    @SerializedName("nota")
    String nota;
    @SerializedName("total")
    int total;
    @SerializedName("status")
    String status;

    public GetTransaksiModel(int id_pesan, String nama_katering, String tgl_mulai, String tgl_selesai, String alamat, String catatan, String nota, int total, String status) {
        this.id_pesan = id_pesan;
        this.nama_katering = nama_katering;
        this.tgl_mulai = tgl_mulai;
        this.tgl_selesai = tgl_selesai;
        this.alamat = alamat;
        this.catatan = catatan;
        this.nota = nota;
        this.total = total;
        this.status = status;
    }

    public int getId_pesan() {
        return id_pesan;
    }

    public void setId_pesan(int id_pesan) {
        this.id_pesan = id_pesan;
    }

    public String getNama_katering() {
        return nama_katering;
    }

    public void setNama_katering(String nama_katering) {
        this.nama_katering = nama_katering;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
