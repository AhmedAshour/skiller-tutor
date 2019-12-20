package com.skillerapp.skillertutor.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.skillerapp.skillertutor.controllers.DataManager;
import com.skillerapp.skillertutor.controllers.ViewModelInterface;
import com.skillerapp.skillertutor.datamanagers.CourseDataManager;
import com.skillerapp.skillertutor.model.courses.Course;

import java.util.List;

public class CourseViewModel extends ViewModel implements ViewModelInterface<Course> {

    private DataManager<Course> courseDataManager;
    private MediatorLiveData<List<Course>> courseMediator;

    public CourseViewModel() {
        courseDataManager = new CourseDataManager();
        courseMediator = new MediatorLiveData<>();
    }

    @Override
    public void writeToDatabase(Course course) {
        courseDataManager.writeToDatabase(course);
    }

    @Override
    public void writeToDatabase(Course course, String databasePath) {
        courseDataManager.writeToDatabase(course, databasePath);
    }

    @Override
    public void editInDatabase(Course course, String databasePath) {
        courseDataManager.editInDatabase(course, databasePath);
    }

    @Override
    public void removeFromDatabase(String databasePath) {
        courseDataManager.removeFromDatabase(databasePath);
    }

    @Override
    public LiveData<List<Course>> getObjectsList() {
        if (courseMediator.getValue() == null) {
            courseMediator.addSource(courseDataManager.getObjectsListFromDatabase(), new Observer<List<Course>>() {
                @Override
                public void onChanged(@Nullable List<Course> courses) {
                    courseMediator.setValue(courses);
                }
            });
        }
        return courseMediator;
    }
}
