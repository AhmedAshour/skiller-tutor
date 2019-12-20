package com.skillerapp.skillertutor.model.courses;


import com.skillerapp.skillertutor.model.misc.Date;
import com.skillerapp.skillertutor.model.misc.Location;
import com.skillerapp.skillertutor.model.misc.Time;
import com.skillerapp.skillertutor.model.users.Student;
import com.skillerapp.skillertutor.model.users.Tutor;
import com.skillerapp.skillertutor.model.utils.DatabaseAccessibleObject;

import java.io.Serializable;

public class Lesson implements Serializable, DatabaseAccessibleObject {

    private String studentUid;
    private Location location;
    private Date date;
    private Time startTime;
    private Time endTime;

    private String tutorUid;
    private Tutor tutor;
    private Student student;

    private String hashId;
    private String uid;

    private Course course;

    private boolean isDoneTutor;
    private boolean isDoneStudent;


    private String databaseReference;

    public Lesson(String studentUid, Location location,
                  Date date, Time startTime, Time endTime,
                  String tutorUid, Tutor tutor, Student student,
                  String hashId, String uid, Course course,
                  boolean isDoneTutor, boolean isDoneStudent, String databaseReference) {
        this.studentUid = studentUid;
        this.location = location;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tutorUid = tutorUid;
        this.tutor = tutor;
        this.student = student;
        this.hashId = hashId;
        this.uid = uid;
        this.course = course;
        this.isDoneTutor = isDoneTutor;
        this.isDoneStudent = isDoneStudent;
        this.databaseReference = databaseReference;
    }

    public Lesson() {
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getTutorUid() {
        return tutorUid;
    }

    public void setTutorUid(String tutorUid) {
        this.tutorUid = tutorUid;
    }


    public String getStudentUid() {
        return studentUid;
    }

    public void setStudentUid(String studentUid) {
        this.studentUid = studentUid;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isDoneTutor() {
        return isDoneTutor;
    }

    public void setDoneTutor(boolean doneTutor) {
        isDoneTutor = doneTutor;
    }

    public boolean isDoneStudent() {
        return isDoneStudent;
    }

    public void setDoneStudent(boolean doneStudent) {
        isDoneStudent = doneStudent;
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
