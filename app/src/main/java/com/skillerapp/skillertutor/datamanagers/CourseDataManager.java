package com.skillerapp.skillertutor.datamanagers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.skillerapp.skillertutor.constants.FirebaseKeys;
import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.controllers.DataManager;
import com.skillerapp.skillertutor.model.courses.Course;

public class CourseDataManager extends DataManager<Course> {

    public CourseDataManager() {
        super(StringsConstants.Items.COURSE);
        super.setUser(FirebaseAuth.getInstance().getCurrentUser());
        super.setDatabaseReference(FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_TUTORS).child(getUser().getUid())
                .child(FirebaseKeys.Tutor.User.CHILD_USER_COURSES_LIST));
    }
}
