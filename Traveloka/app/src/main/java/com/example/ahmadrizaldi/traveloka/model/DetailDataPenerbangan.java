package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 10/15/17.
 */

public class DetailDataPenerbangan {


    private String id ,Flights_id, From, To, Dates, JamBerangkat, JamTiba , Kuota_E, Kuota_B, Kuota_F, Harga_E, Harga_B, Harga_F, MaxReschedule;

    public DetailDataPenerbangan() {
    }


    public DetailDataPenerbangan(String id, String flights_id, String from, String to, String dates, String jamBerangkat, String jamTiba, String kuota_E, String kuota_B, String kuota_F, String harga_E, String harga_B, String harga_F, String maxReschedule) {
        this.id = id;
        Flights_id = flights_id;
        From = from;
        To = to;
        Dates = dates;
        JamBerangkat = jamBerangkat;
        JamTiba = jamTiba;
        Kuota_E = kuota_E;
        Kuota_B = kuota_B;
        Kuota_F = kuota_F;
        Harga_E = harga_E;
        Harga_B = harga_B;
        Harga_F = harga_F;
        MaxReschedule = maxReschedule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlights_id() {
        return Flights_id;
    }

    public void setFlights_id(String flights_id) {
        Flights_id = flights_id;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getDates() {
        return Dates;
    }

    public void setDates(String dates) {
        Dates = dates;
    }

    public String getJamBerangkat() {
        return JamBerangkat;
    }

    public void setJamBerangkat(String jamBerangkat) {
        JamBerangkat = jamBerangkat;
    }

    public String getJamTiba() {
        return JamTiba;
    }

    public void setJamTiba(String jamTiba) {
        JamTiba = jamTiba;
    }

    public String getKuota_E() {
        return Kuota_E;
    }

    public void setKuota_E(String kuota_E) {
        Kuota_E = kuota_E;
    }

    public String getKuota_B() {
        return Kuota_B;
    }

    public void setKuota_B(String kuota_B) {
        Kuota_B = kuota_B;
    }

    public String getKuota_F() {
        return Kuota_F;
    }

    public void setKuota_F(String kuota_F) {
        Kuota_F = kuota_F;
    }

    public String getHarga_E() {
        return Harga_E;
    }

    public void setHarga_E(String harga_E) {
        Harga_E = harga_E;
    }

    public String getHarga_B() {
        return Harga_B;
    }

    public void setHarga_B(String harga_B) {
        Harga_B = harga_B;
    }

    public String getHarga_F() {
        return Harga_F;
    }

    public void setHarga_F(String harga_F) {
        Harga_F = harga_F;
    }

    public String getMaxReschedule() {
        return MaxReschedule;
    }

    public void setMaxReschedule(String maxReschedule) {
        MaxReschedule = maxReschedule;
    }
}
