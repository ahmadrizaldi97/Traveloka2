package com.example.ahmadrizaldi.traveloka;

/**
 * Created by ahmadrizaldi on 11/23/17.
 */

public class Rekening {

    private String NamaRekening, Nomor, atasNama;

    public Rekening() {
    }

    public Rekening(String namaRekening, String nomor, String atasNama) {
        NamaRekening = namaRekening;
        Nomor = nomor;
        this.atasNama = atasNama;
    }

    public String getNamaRekening() {
        return NamaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        NamaRekening = namaRekening;
    }

    public String getNomor() {
        return Nomor;
    }

    public void setNomor(String nomor) {
        Nomor = nomor;
    }

    public String getAtasNama() {
        return atasNama;
    }

    public void setAtasNama(String atasNama) {
        this.atasNama = atasNama;
    }
}
