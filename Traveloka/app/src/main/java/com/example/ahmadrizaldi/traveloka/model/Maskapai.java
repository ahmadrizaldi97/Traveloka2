package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 12/19/17.
 */

public class Maskapai {

    private String id, nama, email;

    public Maskapai() {
    }

    public Maskapai(String id, String nama, String email) {
        this.id = id;
        this.nama = nama;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}