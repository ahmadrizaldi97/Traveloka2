package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 11/9/17.
 */

public class Hotel {

    private String ID_Hotel , NamaHotel, Reputasi, Lokasi, Fasilitas, Deskripsi, Kebijakan;
    private Double lat, lang;
    private String GM1, GM2, GM3, GM4, GM5;
    private String Alamat;
    private String Id_Pemilik;

    public Hotel() {
    }

    public Hotel(String ID_Hotel, String namaHotel, String reputasi, String lokasi, String fasilitas, String deskripsi, String kebijakan, Double lat, Double lang, String GM1, String GM2, String GM3, String GM4, String GM5, String alamat, String id_Pemilik) {
        this.ID_Hotel = ID_Hotel;
        NamaHotel = namaHotel;
        Reputasi = reputasi;
        Lokasi = lokasi;
        Fasilitas = fasilitas;
        Deskripsi = deskripsi;
        Kebijakan = kebijakan;
        this.lat = lat;
        this.lang = lang;
        this.GM1 = GM1;
        this.GM2 = GM2;
        this.GM3 = GM3;
        this.GM4 = GM4;
        this.GM5 = GM5;
        Alamat = alamat;
        Id_Pemilik = id_Pemilik;
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

    public String getReputasi() {
        return Reputasi;
    }

    public void setReputasi(String reputasi) {
        Reputasi = reputasi;
    }

    public String getLokasi() {
        return Lokasi;
    }

    public void setLokasi(String lokasi) {
        Lokasi = lokasi;
    }

    public String getFasilitas() {
        return Fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        Fasilitas = fasilitas;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getKebijakan() {
        return Kebijakan;
    }

    public void setKebijakan(String kebijakan) {
        Kebijakan = kebijakan;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLang() {
        return lang;
    }

    public void setLang(Double lang) {
        this.lang = lang;
    }

    public String getGM1() {
        return GM1;
    }

    public void setGM1(String GM1) {
        this.GM1 = GM1;
    }

    public String getGM2() {
        return GM2;
    }

    public void setGM2(String GM2) {
        this.GM2 = GM2;
    }

    public String getGM3() {
        return GM3;
    }

    public void setGM3(String GM3) {
        this.GM3 = GM3;
    }

    public String getGM4() {
        return GM4;
    }

    public void setGM4(String GM4) {
        this.GM4 = GM4;
    }

    public String getGM5() {
        return GM5;
    }

    public void setGM5(String GM5) {
        this.GM5 = GM5;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getId_Pemilik() {
        return Id_Pemilik;
    }

    public void setId_Pemilik(String id_Pemilik) {
        Id_Pemilik = id_Pemilik;
    }
}
