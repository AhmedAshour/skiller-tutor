package com.skillerapp.skillertutor.views.lessons;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skillerapp.skillertutor.DummyData;
import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.adapters.lessons.LessonsRequestsRecyclerAdapter;
import com.skillerapp.skillertutor.model.courses.Lesson;
import com.skillerapp.skillertutor.viewholders.lessons.LessonsRequestsViewHolder;
import com.skillerapp.skillertutor.viewmodels.LessonsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LessonsRequestsFragment extends Fragment {

    private String TAG = LessonsRequestsFragment.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.fragment_lessons_requests_rv)
    RecyclerView rvRequests;

    private LessonsViewModel lessonsViewModel;
    private LessonsRequestsRecyclerAdapter recyclerAdapter;
    private List<Lesson> requestsList;


    public LessonsRequestsFragment() {
        // Required empty public constructor
    }

    public static LessonsRequestsFragment newInstance(String param1, String param2) {
        LessonsRequestsFragment fragment = new LessonsRequestsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lessons_requests, container, false);
        ButterKnife.bind(this,v);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        initRv();
        initViewModel();
    }

    private void initViewModel() {
        lessonsViewModel = ViewModelProviders.of(this).get(LessonsViewModel.class);
        lessonsViewModel.getRequestsLessons().observe(this, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(@Nullable List<Lesson> lessons) {
                Log.d(TAG, lessons+"");

                requestsList = lessons;
                recyclerAdapter = new LessonsRequestsRecyclerAdapter(getActivity(), lessons,
                        new LessonsRequestsViewHolder.RecyclerViewClickListener() {
                    @Override
                    public void onBtnAcceptClick(View v, int position) {
                        lessonsViewModel.putLessonInUpcoming(requestsList.get(position));
                        lessonsViewModel.deleteLessonFromRequests(requestsList.get(position).getHashId());
                    }

                    @Override
                    public void onBtnRejectClick(View v, int position) {
                        String hashId = requestsList.get(position).getHashId();
                        if (hashId != null)
                            lessonsViewModel.deleteLessonFromRequests(hashId);
                    }
                });
                rvRequests.setAdapter(recyclerAdapter);
            }
        });
    }

    private void initRv() {
        rvRequests.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRequests.setHasFixedSize(true);
        rvRequests.setAdapter(new LessonsRequestsRecyclerAdapter());
    }

}
