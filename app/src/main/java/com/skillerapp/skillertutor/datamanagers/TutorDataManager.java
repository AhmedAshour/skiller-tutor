package com.skillerapp.skillertutor.datamanagers;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.skillerapp.skillertutor.constants.FirebaseKeys;
import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.model.users.Tutor;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TutorDataManager {
    private String TAG = TutorDataManager.class.getSimpleName();

    private DatabaseReference databaseReference;
    private MutableLiveData<Tutor> tutorMutableLiveData;
    private Tutor tutor;

    public TutorDataManager() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_TUTORS).child(user.getUid());

        tutorMutableLiveData = new MutableLiveData<>();
    }

    private void getMapReady(Map<String, Object> map, String key) {
        List<Object> expList = Collections.singletonList(map.get(key));
        String listString = TextUtils.join(", ", expList);
        if (listString.charAt(0) != '[')
            map.put(key, expList);

        Log.d(TAG, StringsConstants.LogMessages.ON_DATA_CHANGE + "String" + listString);
        Log.d(TAG, StringsConstants.LogMessages.ON_DATA_CHANGE + "Map after" + map.get(key));
    }

    private Tutor retrieveTutorFromDatabase(DataSnapshot dataSnapshot) {
        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
        Log.d(TAG, StringsConstants.LogMessages.ON_DATA_CHANGE + map.toString());

        getMapReady(map, FirebaseKeys.Tutor.User.CHILD_USER_EXPERIENCE_LIST);
        getMapReady(map, FirebaseKeys.Tutor.User.CHILD_USER_FEEDBACK_LIST);
        getMapReady(map, FirebaseKeys.Tutor.User.CHILD_USER_COURSES_LIST);

        String jsonResponse = null;
        try {
            jsonResponse = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        tutor = gson.fromJson(jsonResponse, Tutor.class);

        return tutor;
    }

    public void editInDatabase(Tutor tutor) {
        if (tutor.getImageURL() != null)
            databaseReference.child(FirebaseKeys.Tutor.User.CHILD_USER_IMAGE_URL).setValue(tutor.getImageURL());

        databaseReference.child(FirebaseKeys.Tutor.User.Name.CHILD_NAME)
                .child(FirebaseKeys.Tutor.User.Name.KEY_CHILD_NAME_FIRST_NAME).setValue(tutor.getName().getFirstName());

        databaseReference.child(FirebaseKeys.Tutor.User.Name.CHILD_NAME)
                .child(FirebaseKeys.Tutor.User.Name.KEY_CHILD_NAME_LAST_NAME).setValue(tutor.getName().getLastName());

        databaseReference.child(FirebaseKeys.Tutor.User.Address.CHILD_ADDRESS)
                .child(FirebaseKeys.Tutor.User.Address.KEY_CHILD_ADDRESS_CITY).setValue(tutor.getLocation().getCity());

        databaseReference.child(FirebaseKeys.Tutor.User.Address.CHILD_ADDRESS)
                .child(FirebaseKeys.Tutor.User.Address.KEY_CHILD_ADDRESS_COUNTRY).setValue(tutor.getLocation().getCountry());

        databaseReference.child(FirebaseKeys.Tutor.User.Birthday.CHILD_BIRTHDAY)
                .child(FirebaseKeys.Tutor.User.Birthday.KEY_CHILD_DATE_DAY).setValue(tutor.getBirthday().getDay());

        databaseReference.child(FirebaseKeys.Tutor.User.Birthday.CHILD_BIRTHDAY)
                .child(FirebaseKeys.Tutor.User.Birthday.KEY_CHILD_DATE_MONTH).setValue(tutor.getBirthday().getMonth());

        databaseReference.child(FirebaseKeys.Tutor.User.Birthday.CHILD_BIRTHDAY)
                .child(FirebaseKeys.Tutor.User.Birthday.KEY_CHILD_DATE_YEAR).setValue(tutor.getBirthday().getYear());

        databaseReference.child(FirebaseKeys.Tutor.User.Contact.CHILD_CONTACT)
                .child(FirebaseKeys.Tutor.User.Contact.KEY_CHILD_EMAIL).setValue(tutor.getContact().getEmail());

        databaseReference.child(FirebaseKeys.Tutor.User.Contact.CHILD_CONTACT)
                .child(FirebaseKeys.Tutor.User.Contact.KEY_CHILD_PHONE).setValue(tutor.getContact().getPhoneNumber());

        databaseReference.child(FirebaseKeys.Tutor.KEY_CHILD_TUTORS_BIO).setValue(tutor.getBio());

        databaseReference.child(FirebaseKeys.Tutor.User.KEY_CHILD_USER_GENDER).setValue(tutor.getGender());

        databaseReference.child(FirebaseKeys.Tutor.KEY_CHILD_TUTORS_PRICE_PER_HOUR).setValue(tutor.getPricePerHour());
    }

    public MutableLiveData<Tutor> getTutorFromDatabase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, dataSnapshot.toString());
                tutor = retrieveTutorFromDatabase(dataSnapshot);
                tutorMutableLiveData.setValue(tutor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return tutorMutableLiveData;
    }
}
