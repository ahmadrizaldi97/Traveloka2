package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 11/12/17.
 */

public class RoomHotel {

    private String ID_Room, ID_Hotel, NamaRoom, Harga, Fasilitas, UkuranKamar, tipeTempatTidur,Kebijakan;

    public RoomHotel() {
    }

    public RoomHotel(String ID_Room, String ID_Hotel, String namaRoom, String harga, String fasilitas, String ukuranKamar, String tipeTempatTidur, String kebijakan) {
        this.ID_Room = ID_Room;
        this.ID_Hotel = ID_Hotel;
        NamaRoom = namaRoom;
        Harga = harga;
        Fasilitas = fasilitas;
        UkuranKamar = ukuranKamar;
        this.tipeTempatTidur = tipeTempatTidur;
        Kebijakan = kebijakan;
    }

    public String getID_Room() {
        return ID_Room;
    }

    public void setID_Room(String ID_Room) {
        this.ID_Room = ID_Room;
    }

    public String getID_Hotel() {
        return ID_Hotel;
    }

    public void setID_Hotel(String ID_Hotel) {
        this.ID_Hotel = ID_Hotel;
    }

    public String getNamaRoom() {
        return NamaRoom;
    }

    public void setNamaRoom(String namaRoom) {
        NamaRoom = namaRoom;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getFasilitas() {
        return Fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        Fasilitas = fasilitas;
    }

    public String getUkuranKamar() {
        return UkuranKamar;
    }

    public void setUkuranKamar(String ukuranKamar) {
        UkuranKamar = ukuranKamar;
    }

    public String getTipeTempatTidur() {
        return tipeTempatTidur;
    }

    public void setTipeTempatTidur(String tipeTempatTidur) {
        this.tipeTempatTidur = tipeTempatTidur;
    }

    public String getKebijakan() {
        return Kebijakan;
    }

    public void setKebijakan(String kebijakan) {
        Kebijakan = kebijakan;
    }
}
