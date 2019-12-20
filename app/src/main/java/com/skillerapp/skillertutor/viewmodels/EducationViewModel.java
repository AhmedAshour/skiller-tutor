package com.skillerapp.skillertutor.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.skillerapp.skillertutor.controllers.DataManager;
import com.skillerapp.skillertutor.controllers.ViewModelInterface;
import com.skillerapp.skillertutor.datamanagers.EducationDataManager;
import com.skillerapp.skillertutor.model.misc.Education;

import java.util.List;

public class EducationViewModel extends ViewModel implements ViewModelInterface<Education> {

    private DataManager<Education> educationDataManager;
    private MediatorLiveData<List<Education>> educationMediator;

    public EducationViewModel() {
        educationDataManager = new EducationDataManager();
        educationMediator = new MediatorLiveData<>();
    }

    @Override
    public void writeToDatabase(Education education) {
        educationDataManager.writeToDatabase(education);
    }

    @Override
    public void writeToDatabase(Education education, String databasePath) {
        educationDataManager.writeToDatabase(education, databasePath);
    }

    @Override
    public void editInDatabase(Education education, String databasePath) {
        educationDataManager.editInDatabase(education, databasePath);
    }

    @Override
    public void removeFromDatabase(String databasePath) {
        educationDataManager.removeFromDatabase(databasePath);
    }

    @Override
    public LiveData<List<Education>> getObjectsList() {
        if (educationMediator.getValue() == null) {
            educationMediator.addSource(educationDataManager.getObjectsListFromDatabase(), new Observer<List<Education>>() {
                @Override
                public void onChanged(@Nullable List<Education> education) {
                    educationMediator.setValue(education);
                }
            });
        }
        return educationMediator;
    }
}
