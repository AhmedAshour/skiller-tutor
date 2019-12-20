package com.skillerapp.skillertutor.controllers;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.model.utils.DatabaseAccessibleObject;

import java.util.ArrayList;
import java.util.List;

public abstract class DataManager<T extends DatabaseAccessibleObject> {

    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private MutableLiveData<List<T>> liveData;
    private List<T> objectsList;
    private String objectType;
    private DatabaseAccessibleObject object;

    public DataManager(String T) {
        objectType = T;
        object = Factory.detectObject(T);
        liveData = new MutableLiveData<>();
        objectsList = new ArrayList<>();
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public void setDatabaseReference(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public MutableLiveData<List<T>> getLiveData() {
        return liveData;
    }

    public List<T> getObjectsList() {
        return objectsList;
    }

    public DatabaseAccessibleObject getObject() {
        return object;
    }

    public void writeToDatabase(T object) {
        databaseReference.push().setValue(object);
        Log.d(StringsConstants.LogMessages.DATA_MANAGER, StringsConstants.LogMessages.Database.CHILD_ADDED);
    }

    public void writeToDatabase(T object, String databasePath) {
        databaseReference.child(databasePath).setValue(object);
        Log.d(StringsConstants.LogMessages.DATA_MANAGER, StringsConstants.LogMessages.Database.CHILD_RE_ADDED);
    }

    public void editInDatabase(T object, String databasePath) {
        databaseReference.child(databasePath).setValue(object);
        Log.d(StringsConstants.LogMessages.DATA_MANAGER, StringsConstants.LogMessages.Database.CHILD_EDITED);
    }

    public void removeFromDatabase(String databasePath) {
        databaseReference.child(databasePath).removeValue();
        Log.d(StringsConstants.LogMessages.DATA_MANAGER, StringsConstants.LogMessages.Database.CHILD_REMOVED);
    }

    public MutableLiveData<List<T>> getObjectsListFromDatabase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                objectsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    object = (DatabaseAccessibleObject) snapshot.getValue(Factory.detectClass(objectType));
                    object.setDatabaseReference(snapshot.getKey());
                    objectsList.add((T) object);
                }
                liveData.setValue(objectsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return liveData;
    }
}
