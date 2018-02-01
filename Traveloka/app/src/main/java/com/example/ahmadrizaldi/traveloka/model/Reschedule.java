package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 10/26/17.
 */

public class Reschedule {


    private String Id_Reservasi, TotalBayar, Flight_id, kelas, jumlahPenumpang, daftarPenumpang, Status, id, id_baru;

    public Reschedule() {
    }

    public Reschedule(String id_Reservasi, String totalBayar, String flight_id, String kelas, String jumlahPenumpang, String daftarPenumpang, String status, String id, String id_baru) {
        Id_Reservasi = id_Reservasi;
        TotalBayar = totalBayar;
        Flight_id = flight_id;
        this.kelas = kelas;
        this.jumlahPenumpang = jumlahPenumpang;
        this.daftarPenumpang = daftarPenumpang;
        Status = status;
        this.id = id;
        this.id_baru = id_baru;
    }

    public String getId_Reservasi() {
        return Id_Reservasi;
    }

    public void setId_Reservasi(String id_Reservasi) {
        Id_Reservasi = id_Reservasi;
    }

    public String getTotalBayar() {
        return TotalBayar;
    }

    public void setTotalBayar(String totalBayar) {
        TotalBayar = totalBayar;
    }

    public String getFlight_id() {
        return Flight_id;
    }

    public void setFlight_id(String flight_id) {
        Flight_id = flight_id;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getJumlahPenumpang() {
        return jumlahPenumpang;
    }

    public void setJumlahPenumpang(String jumlahPenumpang) {
        this.jumlahPenumpang = jumlahPenumpang;
    }

    public String getDaftarPenumpang() {
        return daftarPenumpang;
    }

    public void setDaftarPenumpang(String daftarPenumpang) {
        this.daftarPenumpang = daftarPenumpang;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_baru() {
        return id_baru;
    }

    public void setId_baru(String id_baru) {
        this.id_baru = id_baru;
    }
}
