package com.skillerapp.skillertutor.model.misc;

import java.io.Serializable;

public class Contact implements Serializable{

    private String email;
    private String phoneNumber;
    private Location location;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact() {
        location = new Location();
    }

    public Contact(String email, String phoneNumber, Location location) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    /*public void setEmail(String email) {
        if (Validation.validateEmail(email))
            this.email = email;
    }*/

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /*public void setPhoneNumber(String phoneNumber) {
        if (Validation.validatePhoneNumber(phoneNumber))
            this.phoneNumber = phoneNumber;
    }*/

    public void setLocation(Location location) {
        this.location = location;
    }
}