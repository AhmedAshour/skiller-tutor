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

import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.adapters.lessons.LessonsFinishedRecyclerAdapter;
import com.skillerapp.skillertutor.model.courses.Lesson;
import com.skillerapp.skillertutor.viewmodels.LessonsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LessonsTakenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LessonsTakenFragment extends Fragment {

    private String TAG = LessonsTakenFragment.class.getSimpleName();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.fragment_lessons_taken_rv)
    RecyclerView rvFinished;

    private LessonsViewModel lessonsViewModel;
    private LessonsFinishedRecyclerAdapter recyclerAdapter;
    private List<Lesson> finishedList;


    public LessonsTakenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LessonsTakenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LessonsTakenFragment newInstance(String param1, String param2) {
        LessonsTakenFragment fragment = new LessonsTakenFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lessons_taken, container, false);
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
        lessonsViewModel.getFinishedLessons().observe(this, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(@Nullable List<Lesson> lessons) {
                Log.d(TAG, lessons+"");

                finishedList = lessons;
                recyclerAdapter = new LessonsFinishedRecyclerAdapter(getActivity(), lessons);
                rvFinished.setAdapter(recyclerAdapter);
            }
        });
    }

    private void initRv() {
        rvFinished.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFinished.setHasFixedSize(true);
        //rvFinished.setAdapter(new LessonsFinishedRecyclerAdapter(getActivity(), finishedList));
    }

}
