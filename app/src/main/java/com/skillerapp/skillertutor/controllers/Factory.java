package com.skillerapp.skillertutor.controllers;

import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.model.courses.Course;
import com.skillerapp.skillertutor.model.misc.Education;
import com.skillerapp.skillertutor.model.misc.Experience;
import com.skillerapp.skillertutor.model.misc.Feedback;
import com.skillerapp.skillertutor.model.utils.DatabaseAccessibleObject;
import com.skillerapp.skillertutor.viewmodels.CourseViewModel;
import com.skillerapp.skillertutor.viewmodels.EducationViewModel;
import com.skillerapp.skillertutor.viewmodels.ExperienceViewModel;
import com.skillerapp.skillertutor.viewmodels.FeedbackViewModel;
import com.skillerapp.skillertutor.views.courses.CourseNewActivity;
import com.skillerapp.skillertutor.views.more.TutorEducationNewActivity;
import com.skillerapp.skillertutor.views.more.TutorExperiencesNewActivity;

public final class Factory {

    public static Class detectIntent(String className) {
        switch (className) {
            case StringsConstants.Items.EDUCATION:
                return TutorEducationNewActivity.class;
            case StringsConstants.Items.EXPERIENCE:
                return TutorExperiencesNewActivity.class;
            case StringsConstants.Items.COURSE:
                return CourseNewActivity.class;
        }
        return null;
    }

    public static DatabaseAccessibleObject detectObject(String className) {
        switch (className) {
            case StringsConstants.Items.EDUCATION:
                return new Education();
            case StringsConstants.Items.EXPERIENCE:
                return new Experience();
            case StringsConstants.Items.COURSE:
                return new Course();
            case StringsConstants.Items.FEEDBACK:
                return new Feedback();
        }
        return null;
    }

    public static Class detectClass(String className) {
        switch (className) {
            case StringsConstants.Items.EDUCATION:
                return Education.class;
            case StringsConstants.Items.EXPERIENCE:
                return Experience.class;
            case StringsConstants.Items.COURSE:
                return Course.class;
            case StringsConstants.Items.FEEDBACK:
                return Feedback.class;
        }
        return null;
    }

    public static ViewModelInterface detectViewModel(String className) {
        switch (className) {
            case StringsConstants.Items.EDUCATION:
                return new EducationViewModel();
            case StringsConstants.Items.EXPERIENCE:
                return new ExperienceViewModel();
            case StringsConstants.Items.COURSE:
                return new CourseViewModel();
            case StringsConstants.Items.FEEDBACK:
                return new FeedbackViewModel();
        }
        return null;
    }
}
