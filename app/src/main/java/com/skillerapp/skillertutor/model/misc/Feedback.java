package com.skillerapp.skillertutor.model.misc;

import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.model.users.User;
import com.skillerapp.skillertutor.model.utils.DatabaseAccessibleObject;

import java.io.Serializable;

public class Feedback implements Serializable, DatabaseAccessibleObject {

    private String rating;
    private String review;
    private Date date;
    private User user;

    private String databaseReference;

    public Date getDate() {
        return date;
    }

    public Feedback() {
        this.date = new Date();

    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        /*int rate = Integer.parseInt(rating);
        if (rate >= 0 && rate <= 5)
            this.rating = String.valueOf(rate);*/
        this.rating = rating;
    }


    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getDatabaseReference() {
        return databaseReference;
    }

    @Override
    public void setDatabaseReference(String databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    public String toString() {
        return rating + StringsConstants.Symbols.SPACE
                + review + StringsConstants.Symbols.SPACE
                + date.toString();
    }
}