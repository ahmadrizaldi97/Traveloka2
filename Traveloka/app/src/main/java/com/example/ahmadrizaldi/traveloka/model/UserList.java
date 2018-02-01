package com.example.ahmadrizaldi.traveloka.model;

/**
 * Created by ahmadrizaldi on 10/22/17.
 */

public class UserList {

    private String email, poin;

    public UserList() {
    }

    public UserList(String email, String poin) {
        this.email = email;
        this.poin = poin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }
}
