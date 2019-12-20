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

import java.io.Serializable;
import java.util.ArrayList;

public class Tutor extends User implements Serializable {

    private String title;
    private String pricePerHour;

    public Tutor(String bio, String imageUrl, String gender, String userType,
                 ArrayList<Skill> skillsList, ArrayList<String> skillsTagsList,
                 ArrayList<Feedback> feedBacksList, ArrayList<Course> coursesList,
                 ArrayList<Experience> experienceList, Name name, Contact contact,
                 Education education, Date birthday, String numExperienceHours,
                 Location location, String databasePath, String title, String pricePerHour) {
        super(bio, imageUrl, gender, userType, skillsList, skillsTagsList, feedBacksList,
                coursesList, experienceList, name, contact, education, birthday,
                numExperienceHours, location, databasePath);
        this.title = title;
        this.pricePerHour = pricePerHour;
    }

    public Tutor() {
        super();
    }

    public String getPricePerHour() {
        if (pricePerHour != null)
            return pricePerHour;/* + " LE/Hr";*/
        else
            return "0";
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "title='" + title + '\'' +
                ", pricePerHour='" + pricePerHour + '\'' +
                ", courses='" + getCoursesList().isEmpty() + '\'' +
                '}';
    }
}