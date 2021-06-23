package com.myapplicationdev.android.demointernshipassessment;

import java.io.Serializable;
// todo: POJO
public class Customer implements Serializable {
    private int id;
    private String name;
    private String contact;
    private String email;
    private String home;
    private int stars;

    public Customer(int id, String name, String contact, String email, String home, int stars) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.home = home;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}

