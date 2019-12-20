package com.skillerapp.skillertutor.model.users;


import com.skillerapp.skillertutor.model.courses.Course;
import com.skillerapp.skillertutor.model.misc.Contact;
import com.skillerapp.skillertutor.model.misc.Date;
import com.skillerapp.skillertutor.model.misc.Education;
import com.skillerapp.skillertutor.model.misc.Experience;
import com.skillerapp.skillertutor.model.misc.Feedback;
import com.skillerapp.skillertutor.model.misc.Location;
import com.skillerapp.skillertutor.model.misc.Name;
import com.skillerapp.skillertutor.model.misc.Skill;
import com.skillerapp.skillertutor.model.utils.DatabaseAccessibleObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable, DatabaseAccessibleObject {

    private String bio;
    private String imageURL;
    private String gender;
    private String userType;
    private ArrayList<Skill> skillsList;
    private ArrayList<String> skillsTagsList;
    private ArrayList<Feedback> feedBacksList;
    private ArrayList<Course> coursesList;
    private ArrayList<Experience> experienceList;
    private Name name;
    private Contact contact;
    private Education education;
    private Date birthday;
    private String numExperienceHours;
    private Location location;
    private String rating;

    private String databaseReference;

    public User(String bio, String imageURL, String gender, String userType,
                ArrayList<Skill> skillsList, ArrayList<String> skillsTagsList,
                ArrayList<Feedback> feedBacksList, ArrayList<Course> coursesList,
                ArrayList<Experience> experienceList, Name name, Contact contact,
                Education education, Date birthday, String numExperienceHours,
                Location location, String databasePath) {
        this.bio = bio;
        this.imageURL = imageURL;
        this.gender = gender;
        this.userType = userType;
        this.skillsList = skillsList;
        this.skillsTagsList = skillsTagsList;
        this.feedBacksList = feedBacksList;
        this.coursesList = coursesList;
        this.experienceList = experienceList;
        this.name = name;
        this.contact = contact;
        this.education = education;
        this.birthday = birthday;
        this.numExperienceHours = numExperienceHours;
        this.location = location;
        this.databaseReference = databasePath;
    }

    public User() {
        skillsList = new ArrayList<>();
        skillsTagsList = new ArrayList<>();
        feedBacksList = new ArrayList<>();
        experienceList = new ArrayList<>();
        name = new Name();
        contact = new Contact();
        education = new Education();
        birthday = new Date();
        location = new Location();
        coursesList = new ArrayList<>();
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getFullName() {
        if (name.getFirstName() != null && name.getLastName() != null)
            return name.getFirstName() + " " + name.getLastName();
        else if (name.getLastName() == null && name.getFirstName() != null)
            return name.getFirstName();
        else
            return " ";
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public ArrayList<Feedback> getFeedBacksList() {
        return feedBacksList;
    }

    public void setFeedBacksList(ArrayList<Feedback> feedBacksList) {
        this.feedBacksList = feedBacksList;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserType() {
        return userType;
    }

    protected void setUserType(String userType) {
        this.userType = userType;
    }

    public ArrayList<Skill> getSkillsList() {
        return skillsList;
    }

    public void setSkillsList(ArrayList<Skill> skillsList) {
        this.skillsList = skillsList;
    }

    public ArrayList<String> getSkillsTagsList() {
        return skillsTagsList;
    }

    public void setSkillsTagsList(ArrayList<String> skillsTagsList) {
        this.skillsTagsList = skillsTagsList;
    }

    public ArrayList<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(ArrayList<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(ArrayList<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    public String getNumExperienceHours() {
        if (numExperienceHours != null)
            return numExperienceHours;
        else
            return "0";
    }

    public void setNumExperienceHours(String numExperienceHours) {
        this.numExperienceHours = numExperienceHours;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getRating() {
        if (rating != null)
            return rating;
        else
            return "0";
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("gender", this.gender);
        map.put("name", this.name);
        map.put("contact", this.contact);
        map.put("education", this.education);
        map.put("birthday", this.birthday);
        return map;
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