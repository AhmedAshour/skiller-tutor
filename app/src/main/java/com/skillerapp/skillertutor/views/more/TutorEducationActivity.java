package com.skillerapp.skillertutor.views.more;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.adapters.EducationRecyclerAdapter;
import com.skillerapp.skillertutor.model.misc.Education;
import com.skillerapp.skillertutor.viewmodels.EducationViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TutorEducationActivity extends AppCompatActivity {

    @BindView(R.id.activity_tutor_education_rv)
    RecyclerView rvEducation;

    private EducationViewModel educationViewModel;
    private EducationRecyclerAdapter educationRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_education);
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {
        educationViewModel = ViewModelProviders.of(this).get(EducationViewModel.class);
        educationViewModel.getObjectsList().observe(this, new Observer<List<Education>>() {
            @Override
            public void onChanged(@Nullable List<Education> education) {
                educationRecyclerAdapter = new EducationRecyclerAdapter(TutorEducationActivity.this, education);
                rvEducation.setAdapter(educationRecyclerAdapter);
            }
        });
    }

    private void initRecyclerView() {
        rvEducation.setLayoutManager(new LinearLayoutManager(this));
        rvEducation.setHasFixedSize(true);
    }

    @OnClick(R.id.activity_tutor_education_fab_new_education)
    public void addEducation(View view) {
        Intent intent = new Intent(TutorEducationActivity.this, TutorEducationNewActivity.class);
        TutorEducationActivity.this.startActivity(intent);
    }
}