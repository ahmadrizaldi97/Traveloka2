package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 10/18/17.
 */

public class Reservasi {

    private String Idreservasi ,Flights_id, Kelas, BookingDate, Total, PembayaranKe, Status, Url, PNR, Email, PoinPending, PotongPoin, JumlahPenumpang, KodePembayaran, reschedule, IDBaru;

    public Reservasi() {
    }



    public Reservasi(String idreservasi, String flights_id, String kelas, String bookingDate, String total, String pembayaranKe, String status, String Url_, String pnr, String emailsss, String poins, String PotongPoin_, String JumlahPenumpang_, String KodePembayaran_ , String resss, String IDBaru) {
        Idreservasi = idreservasi;
        Flights_id = flights_id;
        Kelas = kelas;
        BookingDate = bookingDate;
        Total = total;
        PembayaranKe = pembayaranKe;
        Status = status;
        this.Url = Url_;
        this.PNR = pnr;
        this.Email = emailsss;
        this.PoinPending = poins;
        this.IDBaru = IDBaru;

        //String PotongPoin_, String JumlahPenumpang_, String KodePembayaran_
        this.PotongPoin = PotongPoin_;
        this.JumlahPenumpang = JumlahPenumpang_;
        this.KodePembayaran = KodePembayaran_;
        this.reschedule = resss;
    }

    public String getIdreservasi() {
        return Idreservasi;
    }

    public void setIdreservasi(String idreservasi) {
        Idreservasi = idreservasi;
    }

    public String getFlights_id() {
        return Flights_id;
    }

    public void setFlights_id(String flights_id) {
        Flights_id = flights_id;
    }

    public String getKelas() {
        return Kelas;
    }

    public void setKelas(String kelas) {
        Kelas = kelas;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getPembayaranKe() {
        return PembayaranKe;
    }

    public void setPembayaranKe(String pembayaranKe) {
        PembayaranKe = pembayaranKe;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String PNR) {
        this.PNR = PNR;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPoinPending() {
        return PoinPending;
    }

    public void setPoinPending(String poinPending) {
        PoinPending = poinPending;
    }

    public String getPotongPoin() {
        return PotongPoin;
    }

    public String getReschedule() {
        return reschedule;
    }

    public void setReschedule(String reschedule) {
        this.reschedule = reschedule;
    }

    public void setPotongPoin(String potongPoin) {
        PotongPoin = potongPoin;
    }

    public String getJumlahPenumpang() {
        return JumlahPenumpang;
    }

    public void setJumlahPenumpang(String jumlahPenumpang) {
        JumlahPenumpang = jumlahPenumpang;
    }

    public String getKodePembayaran() {
        return KodePembayaran;
    }

    public void setKodePembayaran(String kodePembayaran) {
        KodePembayaran = kodePembayaran;
    }

    public String getIDBaru() {
        return IDBaru;
    }

    public void setIDBaru(String IDBaru) {
        this.IDBaru = IDBaru;
    }
}
