package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 1/1/18.
 */

public class Admin {

    private String nama, email;

    public Admin() {
    }

    public Admin(String nama, String email) {
        this.nama = nama;
        this.email = email;
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
