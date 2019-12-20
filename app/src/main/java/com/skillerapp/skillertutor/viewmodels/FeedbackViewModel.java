package com.skillerapp.skillertutor.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.skillerapp.skillertutor.controllers.DataManager;
import com.skillerapp.skillertutor.controllers.ViewModelInterface;
import com.skillerapp.skillertutor.datamanagers.FeedbackDataManager;
import com.skillerapp.skillertutor.model.misc.Feedback;

import java.util.List;

public class FeedbackViewModel extends ViewModel implements ViewModelInterface<Feedback> {

    private DataManager<Feedback> feedbackDataManager;
    private MediatorLiveData<List<Feedback>> feedbackMediator;

    public FeedbackViewModel() {
        feedbackDataManager = new FeedbackDataManager();
        feedbackMediator = new MediatorLiveData<>();
    }

    @Override
    public void writeToDatabase(Feedback feedback) {
        feedbackDataManager.writeToDatabase(feedback);
    }

    @Override
    public void writeToDatabase(Feedback feedback, String databasePath) {
        feedbackDataManager.writeToDatabase(feedback, databasePath);
    }

    @Override
    public void editInDatabase(Feedback feedback, String databasePath) {
        feedbackDataManager.editInDatabase(feedback, databasePath);
    }

    @Override
    public void removeFromDatabase(String databasePath) {
        feedbackDataManager.removeFromDatabase(databasePath);
    }

    @Override
    public LiveData<List<Feedback>> getObjectsList() {
        if (feedbackMediator.getValue() == null) {
            feedbackMediator.addSource(feedbackDataManager.getObjectsListFromDatabase(), new Observer<List<Feedback>>() {
                @Override
                public void onChanged(@Nullable List<Feedback> feedback) {
                    feedbackMediator.setValue(feedback);
                }
            });
        }
        return feedbackMediator;
    }
}
