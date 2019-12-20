package com.skillerapp.skillertutor.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.util.Log;

import com.skillerapp.skillertutor.datamanagers.LessonsDataManager;
import com.skillerapp.skillertutor.model.courses.Lesson;

import java.util.List;

public class LessonsViewModel extends ViewModel {

    private String TAG = LessonsViewModel.class.getSimpleName();

    private MediatorLiveData<List<Lesson>> requestsLiveData;
    private MediatorLiveData<List<Lesson>> upcomingLiveData;
    private MediatorLiveData<List<Lesson>> finishedLiveData;

    private LessonsDataManager lessonsDataManager;


    public LessonsViewModel() {
        requestsLiveData = new MediatorLiveData<>();
        upcomingLiveData = new MediatorLiveData<>();
        finishedLiveData = new MediatorLiveData<>();

        lessonsDataManager = new LessonsDataManager();
    }

    public LiveData<List<Lesson>> getRequestsLessons() {
        if (requestsLiveData.getValue() == null)
            requestsLiveData.addSource(lessonsDataManager.getRequestsLessonsFromDatabase(), new Observer<List<Lesson>>() {
                @Override
                public void onChanged(@Nullable List<Lesson> lessons) {
                    requestsLiveData.setValue(lessons);
                    Log.d(TAG, "REQUEST LESSONS: " + lessons + "");

                }
            });
        return requestsLiveData;
    }

    public LiveData<List<Lesson>> getUpcomingLessons() {
        if (upcomingLiveData.getValue() == null)
            upcomingLiveData.addSource(lessonsDataManager.getUpcomingLessonsFromDatabase(), new Observer<List<Lesson>>() {
                @Override
                public void onChanged(@Nullable List<Lesson> lessons) {
                    upcomingLiveData.setValue(lessons);
                    Log.d(TAG, "UPCOMING LESSONS: " + lessons + "");

                }
            });
        return upcomingLiveData;
    }

    public LiveData<List<Lesson>> getFinishedLessons() {
        if (finishedLiveData.getValue() == null)
            finishedLiveData.addSource(lessonsDataManager.getFinishedLessonsFromDatabase(), new Observer<List<Lesson>>() {
                @Override
                public void onChanged(@Nullable List<Lesson> lessons) {
                    finishedLiveData.setValue(lessons);
                    Log.d(TAG, "FINISHED LESSONS: " + lessons + "");

                }
            });
        return finishedLiveData;
    }

    public void putLessonInUpcoming(Lesson lesson) {
        lessonsDataManager.pushLessonToUpcomingDatabase(lesson);
    }

    public void deleteLessonFromRequests(String hashId) {
        lessonsDataManager.deleteRequestLessonFromDatabase(hashId);
    }
}
