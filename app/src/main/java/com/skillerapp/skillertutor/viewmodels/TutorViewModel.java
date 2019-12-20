package com.skillerapp.skillertutor.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.skillerapp.skillertutor.datamanagers.TutorDataManager;
import com.skillerapp.skillertutor.model.users.Tutor;

public class TutorViewModel extends ViewModel {

    private TutorDataManager tutorDataManager;
    private MediatorLiveData<Tutor> tutorLiveData;

    public TutorViewModel() {
        tutorDataManager = new TutorDataManager();
        tutorLiveData = new MediatorLiveData<>();
    }

    public LiveData<Tutor> getTutor() {
        if (tutorLiveData.getValue() == null) {
            tutorLiveData.addSource(tutorDataManager.getTutorFromDatabase(), new Observer<Tutor>() {
                @Override
                public void onChanged(@Nullable Tutor tutor) {
                    tutorLiveData.setValue(tutor);
                }
            });
        }
        return tutorLiveData;
    }

    public void editInDatabase(Tutor updatedTutor) {
        tutorDataManager.editInDatabase(updatedTutor);
    }
}
