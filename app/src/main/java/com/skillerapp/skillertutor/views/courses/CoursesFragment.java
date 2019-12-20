package com.skillerapp.skillertutor.views.courses;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.adapters.CourseRecyclerAdapter;
import com.skillerapp.skillertutor.model.courses.Course;
import com.skillerapp.skillertutor.viewmodels.CourseViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CoursesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @BindView(R.id.fragment_courses_fab_new_course)
    FloatingActionButton fabNewCourse;
    @BindView(R.id.rv_courses)
    RecyclerView rvCourses;

    private CourseRecyclerAdapter courseRecyclerAdapter;


    public CoursesFragment() {
        // Required empty public constructor
    }

    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();
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
        View v = inflater.inflate(R.layout.fragment_courses, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {
        CourseViewModel courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);
        courseViewModel.getObjectsList().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(@Nullable List<Course> courses) {
                courseRecyclerAdapter = new CourseRecyclerAdapter(getActivity(), courses);
                rvCourses.setAdapter(courseRecyclerAdapter);
            }
        });
    }

    private void initRecyclerView() {
        rvCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCourses.setHasFixedSize(true);
    }

    @OnClick(R.id.fragment_courses_fab_new_course)
    public void addCourse(View view) {
        Intent intent = new Intent(getActivity(), CourseNewActivity.class);
        startActivity(intent);
    }
}
