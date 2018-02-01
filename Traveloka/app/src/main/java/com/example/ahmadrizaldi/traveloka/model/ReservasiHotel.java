package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 12/28/17.
 */

public class ReservasiHotel {

    private String idReservasi, email, bookingdate, idHotel, idRoom, bank, total, kodePembayaran, poinTerpakai, poinPending, permintaanKhusus, status, durasi, jumlahKamar, namaHotel, namaRoom, dataPemesan, voucher, url, tanggalCheckIn, tanggalCheckOut;

    public ReservasiHotel() {
    }


    public ReservasiHotel(String idReservasi, String email, String bookingdate, String idHotel, String idRoom, String bank, String total, String kodePembayaran, String poinTerpakai, String poinPending, String permintaanKhusus, String status, String durasi, String jumlahKamar, String namaHotel, String namaRoom, String dataPemesan, String voucher, String url, String tanggalCheckIn, String tanggalCheckOut) {
        this.idReservasi = idReservasi;
        this.email = email;
        this.bookingdate = bookingdate;
        this.idHotel = idHotel;
        this.idRoom = idRoom;
        this.bank = bank;
        this.total = total;
        this.kodePembayaran = kodePembayaran;
        this.poinTerpakai = poinTerpakai;
        this.poinPending = poinPending;
        this.permintaanKhusus = permintaanKhusus;
        this.status = status;
        this.durasi = durasi;
        this.jumlahKamar = jumlahKamar;
        this.namaHotel = namaHotel;
        this.namaRoom = namaRoom;
        this.dataPemesan = dataPemesan;
        this.voucher = voucher;
        this.url = url;
        this.tanggalCheckIn = tanggalCheckIn;
        this.tanggalCheckOut = tanggalCheckOut;
    }

    public String getIdReservasi() {
        return idReservasi;
    }

    public void setIdReservasi(String idReservasi) {
        this.idReservasi = idReservasi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(String bookingdate) {
        this.bookingdate = bookingdate;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getKodePembayaran() {
        return kodePembayaran;
    }

    public void setKodePembayaran(String kodePembayaran) {
        this.kodePembayaran = kodePembayaran;
    }

    public String getPoinTerpakai() {
        return poinTerpakai;
    }

    public void setPoinTerpakai(String poinTerpakai) {
        this.poinTerpakai = poinTerpakai;
    }

    public String getPoinPending() {
        return poinPending;
    }

    public void setPoinPending(String poinPending) {
        this.poinPending = poinPending;
    }

    public String getPermintaanKhusus() {
        return permintaanKhusus;
    }

    public void setPermintaanKhusus(String permintaanKhusus) {
        this.permintaanKhusus = permintaanKhusus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getJumlahKamar() {
        return jumlahKamar;
    }

    public void setJumlahKamar(String jumlahKamar) {
        this.jumlahKamar = jumlahKamar;
    }

    public String getNamaHotel() {
        return namaHotel;
    }

    public void setNamaHotel(String namaHotel) {
        this.namaHotel = namaHotel;
    }

    public String getNamaRoom() {
        return namaRoom;
    }

    public void setNamaRoom(String namaRoom) {
        this.namaRoom = namaRoom;
    }

    public String getDataPemesan() {
        return dataPemesan;
    }

    public void setDataPemesan(String dataPemesan) {
        this.dataPemesan = dataPemesan;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTanggalCheckIn() {
        return tanggalCheckIn;
    }

    public void setTanggalCheckIn(String tanggalCheckIn) {
        this.tanggalCheckIn = tanggalCheckIn;
    }

    public String getTanggalCheckOut() {
        return tanggalCheckOut;
    }

    public void setTanggalCheckOut(String tanggalCheckOut) {
        this.tanggalCheckOut = tanggalCheckOut;
    }
}
