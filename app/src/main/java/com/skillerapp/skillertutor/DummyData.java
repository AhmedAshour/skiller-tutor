package com.skillerapp.skillertutor;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skillerapp.skillertutor.constants.FirebaseKeys;
import com.skillerapp.skillertutor.model.courses.Course;
import com.skillerapp.skillertutor.model.courses.Lesson;
import com.skillerapp.skillertutor.model.misc.Contact;
import com.skillerapp.skillertutor.model.misc.Date;
import com.skillerapp.skillertutor.model.misc.Location;
import com.skillerapp.skillertutor.model.misc.Name;
import com.skillerapp.skillertutor.model.misc.Time;
import com.skillerapp.skillertutor.model.users.Student;

public class DummyData {
    private DatabaseReference mDatabaseRequests;
    private DatabaseReference mDatabaseUpcoming;
    private DatabaseReference mDatabaseFinished;

    private Lesson lesson;
    private Location location;
    private Date date;
    private Student student;
    private Contact contact;
    private Name name;
    private Time timeStart;
    private Time timeEnd;
    private Course course;

    public DummyData() {
        lesson = new Lesson();
        location = new Location();
        date = new Date();
        student = new Student();
        contact = new Contact();
        name = new Name();
        timeStart = new Time();
        timeEnd = new Time();
        course = new Course();

        setDummyCourse();
        setDummyName();
        setDummyTime();
        setDummyContact();
        setDummyDate();
        setDummyAddress();
        setDummyStudent();
        setDummyLesson();

        mDatabaseRequests = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_LESSONS_REQUESTS);
        mDatabaseUpcoming = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_LESSONS_UPCOMING);
        mDatabaseFinished = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_LESSONS_FINISHED);

        for (int i = 0; i < 3; i++) {
            mDatabaseRequests.push().setValue(lesson);
            mDatabaseUpcoming.push().setValue(lesson);
            mDatabaseFinished.push().setValue(lesson);
        }
    }

    private void setDummyCourse() {
        course.setNumSessions("1");
        course.setPrice("100");
        course.setNumHoursPerSession("2");
        course.setNumHours("2");
        course.setCourseTitle("Single");

        // new
        course.setSkillName("Programming");
        course.setTimeStart("12 PM");
        course.setTimeEnd("3 PM");
        course.setLocation("AAST");
        course.setNotes("Please do not forget to bring your laptop with you next session");
    }

    private void setDummyTime() {
        timeStart.setHour("2");
        timeStart.setMinute("30");
        timeEnd.setHour("4");
        timeEnd.setHour("30");
    }

    private void setDummyName() {
        name.setFirstName("First");
        name.setLastName("Last");
    }

    private void setDummyContact() {
        contact.setLocation(location);
        contact.setEmail("skiller@skillerapp.com");
        contact.setPhoneNumber("011111111111");
    }

    private void setDummyStudent() {
        student.setRating("4.8");
        student.setBio("This is just me");
//        student.setBirthday(date);
        student.setContact(contact);
        student.setImageURL("https://firebasestorage.googleapis.com/v0/b/skiller-app.appspot.com/o/tutors_photos%2Fphoto-1528673639901-e2d773e396ff.jpg?alt=media&token=ae676629-0d3e-46b2-a86b-0b7df4e530dc");
        student.setName(name);
        student.setDatabaseReference("N2AsZm8bHOgkmO3cy12FJ9LCvpz1");
    }

    private void setDummyDate() {
        date.setDay("1");
        date.setMonth("2");
        date.setYear("2018");
    }

    private void setDummyAddress() {
        location.setApartment("5");
        location.setCity("Alexandria");
        location.setMoreInfo("No more info");
        location.setStreet("1");
    }

    private void setDummyLesson() {
        lesson.setCourse(course);
        lesson.setLocation(location);
        lesson.setDate(date);
        lesson.setStudent(student);
        lesson.setStudentUid("N2AsZm8bHOgkmO3cy12FJ9LCvpz1");
        lesson.setTutorUid("N2AsZm8bHOgkmO3cy12FJ9LCvpz1");
        lesson.setStartTime(timeStart);
        lesson.setEndTime(timeEnd);
    }
}
