package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 11/20/17.
 */

public class PemilikHotel {

    private String ID_Hotel, NamaHotel, Email, reputasi;



    public PemilikHotel() {
    }

    public PemilikHotel(String ID_Hotel, String namaHotel, String email, String reputasi) {
        this.ID_Hotel = ID_Hotel;
        NamaHotel = namaHotel;
        Email = email;
        this.reputasi = reputasi;
    }

    public String getID_Hotel() {
        return ID_Hotel;
    }

    public void setID_Hotel(String ID_Hotel) {
        this.ID_Hotel = ID_Hotel;
    }

    public String getNamaHotel() {
        return NamaHotel;
    }

    public void setNamaHotel(String namaHotel) {
        NamaHotel = namaHotel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getReputasi() {
        return reputasi;
    }

    public void setReputasi(String reputasi) {
        this.reputasi = reputasi;
    }
}
