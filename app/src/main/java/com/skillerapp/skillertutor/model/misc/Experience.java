package com.skillerapp.skillertutor.model.misc;

import com.skillerapp.skillertutor.model.utils.DatabaseAccessibleObject;

import java.io.Serializable;

public class Experience implements Serializable, DatabaseAccessibleObject {

    private String title;
    private String company;

    private Date startDate;
    private Date endDate;

    private String description;

    private String databaseReference;

    public Experience() {
        this.startDate = new Date();
        this.endDate = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return company + " "
                + title + " "
                + startDate.toString() + " "
                + endDate.toString();
    }
}