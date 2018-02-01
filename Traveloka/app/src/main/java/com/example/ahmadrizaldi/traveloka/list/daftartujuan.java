package com.example.ahmadrizaldi.traveloka.list;

/**
 * Created by ahmadrizaldi on 10/11/17.
 */

public class daftartujuan {

    private String namaLokasi, namaBandara, kodeBandara;

    public daftartujuan(String namaLokasi, String namaBandara, String kodeBandara) {
        this.namaLokasi = namaLokasi;
        this.namaBandara = namaBandara;
        this.kodeBandara = kodeBandara;
    }

    public String getNamaLokasi() {
        return namaLokasi;
    }

    public void setNamaLokasi(String namaLokasi) {
        this.namaLokasi = namaLokasi;
    }

    public String getNamaBandara() {
        return namaBandara;
    }

    public void setNamaBandara(String namaBandara) {
        this.namaBandara = namaBandara;
    }

    public String getKodeBandara() {
        return kodeBandara;
    }

    public void setKodeBandara(String kodeBandara) {
        this.kodeBandara = kodeBandara;
    }
}
