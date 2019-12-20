package com.skillerapp.skillertutor.controllers;

import android.arch.lifecycle.LiveData;

import com.skillerapp.skillertutor.model.utils.DatabaseAccessibleObject;

import java.util.List;

public interface ViewModelInterface<T extends DatabaseAccessibleObject> {

    void writeToDatabase(T object);

    void writeToDatabase(T object, String databasePath);

    void editInDatabase(T object, String databasePath);

    void removeFromDatabase(String databasePath);

    LiveData<List<T>> getObjectsList();
}
