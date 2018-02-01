package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 12/7/17.
 */

public class RoomHotel_Fix {


    private String IDHotel, ID_Room;
    private String namaRoom, Harga;
    private String gm1, gm2, gm3, gm4, gm5, gm6;
    private String ukuranKamar, tipeTempatTidur;
    private String fasilitasKamar, fasilitasKamarMandi, deskripsiKamar;
    private String stok;

    public RoomHotel_Fix() {
    }

    public RoomHotel_Fix(String IDHotel, String ID_Room, String namaRoom, String harga, String gm1, String gm2, String gm3, String gm4, String gm5, String gm6, String ukuranKamar, String tipeTempatTidur, String fasilitasKamar, String fasilitasKamarMandi, String deskripsiKamar, String stok) {
        this.IDHotel = IDHotel;
        this.ID_Room = ID_Room;
        this.namaRoom = namaRoom;
        Harga = harga;
        this.gm1 = gm1;
        this.gm2 = gm2;
        this.gm3 = gm3;
        this.gm4 = gm4;
        this.gm5 = gm5;
        this.gm6 = gm6;
        this.ukuranKamar = ukuranKamar;
        this.tipeTempatTidur = tipeTempatTidur;
        this.fasilitasKamar = fasilitasKamar;
        this.fasilitasKamarMandi = fasilitasKamarMandi;
        this.deskripsiKamar = deskripsiKamar;
        this.stok = stok;
    }

    public String getIDHotel() {
        return IDHotel;
    }

    public void setIDHotel(String IDHotel) {
        this.IDHotel = IDHotel;
    }

    public String getID_Room() {
        return ID_Room;
    }

    public void setID_Room(String ID_Room) {
        this.ID_Room = ID_Room;
    }

    public String getNamaRoom() {
        return namaRoom;
    }

    public void setNamaRoom(String namaRoom) {
        this.namaRoom = namaRoom;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getGm1() {
        return gm1;
    }

    public void setGm1(String gm1) {
        this.gm1 = gm1;
    }

    public String getGm2() {
        return gm2;
    }

    public void setGm2(String gm2) {
        this.gm2 = gm2;
    }

    public String getGm3() {
        return gm3;
    }

    public void setGm3(String gm3) {
        this.gm3 = gm3;
    }

    public String getGm4() {
        return gm4;
    }

    public void setGm4(String gm4) {
        this.gm4 = gm4;
    }

    public String getGm5() {
        return gm5;
    }

    public void setGm5(String gm5) {
        this.gm5 = gm5;
    }

    public String getGm6() {
        return gm6;
    }

    public void setGm6(String gm6) {
        this.gm6 = gm6;
    }

    public String getUkuranKamar() {
        return ukuranKamar;
    }

    public void setUkuranKamar(String ukuranKamar) {
        this.ukuranKamar = ukuranKamar;
    }

    public String getTipeTempatTidur() {
        return tipeTempatTidur;
    }

    public void setTipeTempatTidur(String tipeTempatTidur) {
        this.tipeTempatTidur = tipeTempatTidur;
    }

    public String getFasilitasKamar() {
        return fasilitasKamar;
    }

    public void setFasilitasKamar(String fasilitasKamar) {
        this.fasilitasKamar = fasilitasKamar;
    }

    public String getFasilitasKamarMandi() {
        return fasilitasKamarMandi;
    }

    public void setFasilitasKamarMandi(String fasilitasKamarMandi) {
        this.fasilitasKamarMandi = fasilitasKamarMandi;
    }

    public String getDeskripsiKamar() {
        return deskripsiKamar;
    }

    public void setDeskripsiKamar(String deskripsiKamar) {
        this.deskripsiKamar = deskripsiKamar;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }
}
