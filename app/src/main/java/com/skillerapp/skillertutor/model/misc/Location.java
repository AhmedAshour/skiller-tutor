package com.skillerapp.skillertutor.model.misc;

import java.io.Serializable;

public class Location implements Serializable {

    private String street;
    private String apartment;
    private String moreInfo;
    private String city;
    private String country;

    public Location() {
    }

    public Location(String street, String apartment, String city, String moreInfo) {
        this.street = street;
        this.apartment = apartment;
        this.moreInfo = moreInfo;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        /*int streetNum = Integer.parseInt(street);
        if (streetNum > 0)
            this.street = String.valueOf(streetNum);*/
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        /*int apartmentNum = Integer.parseInt(apartment);
        if (apartmentNum > 0)
            this.apartment = String.valueOf(apartmentNum);*/
        this.apartment = apartment;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}