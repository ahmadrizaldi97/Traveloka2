package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 10/17/17.
 */

public class imageuploadd {

    private String url,nama;

    public imageuploadd(String nama, String url) {
        this.url = url;
        this.nama = nama;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
