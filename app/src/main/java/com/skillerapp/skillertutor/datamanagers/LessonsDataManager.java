package com.skillerapp.skillertutor.datamanagers;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.skillerapp.skillertutor.constants.FirebaseKeys;
import com.skillerapp.skillertutor.model.courses.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonsDataManager {

    private String TAG = LessonsDataManager.class.getSimpleName();

    private DatabaseReference mDatabaseRequests;
    private DatabaseReference mDatabaseUpcoming;
    private DatabaseReference mDatabaseFinished;
    private MutableLiveData<List<Lesson>> requestsLiveData;
    private MutableLiveData<List<Lesson>> upcomingLiveData;
    private MutableLiveData<List<Lesson>> finishedLiveData;
    private List<Lesson> tempLessons;
    private FirebaseUser user;
    private String tutorUid;

    public LessonsDataManager() {
        mDatabaseRequests = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_LESSONS_REQUESTS);
        mDatabaseUpcoming = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_LESSONS_UPCOMING);
        mDatabaseFinished = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseKeys.CHILD_LESSONS_FINISHED);

        requestsLiveData = new MutableLiveData<>();
        upcomingLiveData = new MutableLiveData<>();
        finishedLiveData = new MutableLiveData<>();

        tempLessons = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d(TAG, user.getUid());
            tutorUid = user.getUid();
        }
    }

    public MutableLiveData<List<Lesson>> getRequestsLessonsFromDatabase() {
        Query query = mDatabaseRequests.orderByChild(FirebaseKeys.Lesson.KEY_CHILD_TUTOR_UID).equalTo(tutorUid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tempLessons.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        Lesson lesson = snapshot.getValue(Lesson.class);
                        tempLessons.add(lesson);
                        Log.d(TAG + " REQUESTS", lesson + "");
                    } catch (Exception e) {
                    }

                }
                requestsLiveData.setValue(tempLessons);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return requestsLiveData;
    }

    public MutableLiveData<List<Lesson>> getUpcomingLessonsFromDatabase() {
        Query query = mDatabaseUpcoming.orderByChild(FirebaseKeys.Lesson.KEY_CHILD_TUTOR_UID).equalTo(tutorUid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tempLessons.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        Lesson lesson = snapshot.getValue(Lesson.class);
                        tempLessons.add(lesson);
                        Log.d(TAG + " UPCOMING", lesson + "");
                    } catch (Exception e) {
                    }
                }

                upcomingLiveData.setValue(tempLessons);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return upcomingLiveData;
    }

    public MutableLiveData<List<Lesson>> getFinishedLessonsFromDatabase() {
        Query query = mDatabaseFinished.orderByChild(FirebaseKeys.Lesson.KEY_CHILD_TUTOR_UID).equalTo(tutorUid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tempLessons.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        Lesson lesson = snapshot.getValue(Lesson.class);
                        tempLessons.add(lesson);
                        Log.d(TAG + " FINISHED", lesson + "");
                    } catch (Exception e) {
                    }
                }
                finishedLiveData.setValue(tempLessons);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return finishedLiveData;
    }

    public void pushLessonToUpcomingDatabase(Lesson lesson) {
        Log.d(TAG, lesson + "");
        mDatabaseUpcoming.push().setValue(lesson);
    }

    public void deleteRequestLessonFromDatabase(String hashId) {
        mDatabaseRequests.child(hashId).removeValue();
    }
}
