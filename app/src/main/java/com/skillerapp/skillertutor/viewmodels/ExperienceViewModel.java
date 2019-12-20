package com.skillerapp.skillertutor.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.skillerapp.skillertutor.controllers.DataManager;
import com.skillerapp.skillertutor.controllers.ViewModelInterface;
import com.skillerapp.skillertutor.datamanagers.ExperienceDataManager;
import com.skillerapp.skillertutor.model.misc.Experience;

import java.util.List;

public class ExperienceViewModel extends ViewModel implements ViewModelInterface<Experience> {

    private DataManager<Experience> experienceDataManager;
    private MediatorLiveData<List<Experience>> experienceMediator;

    public ExperienceViewModel() {
        experienceDataManager = new ExperienceDataManager();
        experienceMediator = new MediatorLiveData<>();
    }

    @Override
    public void writeToDatabase(Experience experience) {
        experienceDataManager.writeToDatabase(experience);
    }

    @Override
    public void writeToDatabase(Experience experience, String databasePath) {
        experienceDataManager.writeToDatabase(experience, databasePath);
    }

    @Override
    public void editInDatabase(Experience experience, String databasePath) {
        experienceDataManager.editInDatabase(experience, databasePath);
    }

    @Override
    public void removeFromDatabase(String databasePath) {
        experienceDataManager.removeFromDatabase(databasePath);
    }

    @Override
    public LiveData<List<Experience>> getObjectsList() {
        if (experienceMediator.getValue() == null) {
            experienceMediator.addSource(experienceDataManager.getObjectsListFromDatabase(), new Observer<List<Experience>>() {
                @Override
                public void onChanged(@Nullable List<Experience> experience) {
                    experienceMediator.setValue(experience);
                }
            });
        }
        return experienceMediator;
    }
}
