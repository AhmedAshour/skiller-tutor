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
import com.skillerapp.skillertutor.adapters.ExperienceRecyclerAdapter;
import com.skillerapp.skillertutor.model.misc.Experience;
import com.skillerapp.skillertutor.viewmodels.ExperienceViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TutorExperiencesActivity extends AppCompatActivity {

    @BindView(R.id.activity_tutor_experiences_rv)
    RecyclerView rvExperiences;

    private ExperienceRecyclerAdapter experienceRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_experiences);
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {
        ExperienceViewModel experienceViewModel = ViewModelProviders.of(this).get(ExperienceViewModel.class);
        experienceViewModel.getObjectsList().observe(this, new Observer<List<Experience>>() {
            @Override
            public void onChanged(@Nullable List<Experience> experience) {
                experienceRecyclerAdapter = new ExperienceRecyclerAdapter(TutorExperiencesActivity.this, experience);
                rvExperiences.setAdapter(experienceRecyclerAdapter);
            }
        });
    }

    private void initRecyclerView() {
        rvExperiences.setLayoutManager(new LinearLayoutManager(this));
        rvExperiences.setHasFixedSize(true);
    }

    @OnClick(R.id.activity_tutor_experiences_fab_new_experience)
    public void addExperience(View view) {
        Intent intent = new Intent(TutorExperiencesActivity.this, TutorExperiencesNewActivity.class);
        TutorExperiencesActivity.this.startActivity(intent);
    }
}