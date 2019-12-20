package com.skillerapp.skillertutor.model.courses;

import com.skillerapp.skillertutor.model.misc.Date;
import com.skillerapp.skillertutor.model.utils.DatabaseAccessibleObject;

import java.io.Serializable;

public class Course implements Serializable, DatabaseAccessibleObject {

    private String courseTitle;
    private String price;
    private String skillName;
    private String timeStart;
    private String timeEnd;
    private Date dateStart;
    private Date dateEnd;
    private String location;
    private String description;
    private String numSessions;
    private String numHours;
    private String numHoursPerSession;
    private String notes;

    private String databaseReference;

    private String tutorUid;

    public Course(String courseTitle, String price, String skillName, String timeStart, String timeEnd,
                  Date dateStart, Date dateEnd, String location, String description, String numSessions,
                  String numHours, String numHoursPerSession, String notes, String tutorUid) {
        this.courseTitle = courseTitle;
        this.price = price;
        this.skillName = skillName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.location = location;
        this.description = description;
        this.numSessions = numSessions;
        this.numHours = numHours;
        this.numHoursPerSession = numHoursPerSession;
        this.notes = notes;
        this.tutorUid = tutorUid;
    }

    public Course(String numHoursPerSession, String courseTitle, String price, String numHours, String numSessions, String tutorUid) {
        this.numHoursPerSession = numHoursPerSession;
        this.courseTitle = courseTitle;
        this.price = price;
        this.numHours = numHours;
        this.numSessions = numSessions;
        this.tutorUid = tutorUid;
    }

    public Course() {
        dateStart = new Date();
        dateEnd = new Date();
    }

    public String getNumSessions() {
        return numSessions;
    }

    public void setNumSessions(String numSessions) {
        this.numSessions = numSessions;
    }

    public String getTutorUid() {
        return tutorUid;
    }

    public void setTutorUid(String tutorUid) {
        this.tutorUid = tutorUid;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumHours() {
        return numHours;
    }

    public void setNumHours(String numHours) {
        this.numHours = numHours;
    }

    public String getNumHoursPerSession() {
        return numHoursPerSession;
    }

    public void setNumHoursPerSession(String numHoursPerSession) {
        this.numHoursPerSession = numHoursPerSession;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String getDatabaseReference() {
        return databaseReference;
    }

    @Override
    public void setDatabaseReference(String databaseReference) {
        this.databaseReference = databaseReference;
    }
}