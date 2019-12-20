package com.skillerapp.skillertutor.model.misc;

import java.io.Serializable;

public class Name implements Serializable{

    private String firstName;
    private String middleName;
    private String lastName;

    public Name(String firstName) {
        this.firstName = firstName;
    }

    public Name(String firstName, String middleName) {
        this.firstName = firstName;
        this.middleName = middleName;
    }


    public Name(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Name() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName;
    }
}