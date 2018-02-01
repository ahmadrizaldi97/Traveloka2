package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 10/19/17.
 */

public class passangerlist {

    private String Id_Reservasi, nama;


    public passangerlist() {
    }

    public passangerlist(String id_Reservasi, String nama) {
        Id_Reservasi = id_Reservasi;
        this.nama = nama;
    }

    public String getId_Reservasi() {
        return Id_Reservasi;
    }

    public void setId_Reservasi(String id_Reservasi) {
        Id_Reservasi = id_Reservasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
