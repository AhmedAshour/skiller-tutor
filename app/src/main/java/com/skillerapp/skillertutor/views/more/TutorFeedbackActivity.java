package com.skillerapp.skillertutor.views.more;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.adapters.FeedbackRecyclerAdapter;
import com.skillerapp.skillertutor.model.misc.Feedback;
import com.skillerapp.skillertutor.viewmodels.FeedbackViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorFeedbackActivity extends AppCompatActivity {

    @BindView(R.id.activity_tutor_reviews_rv)
    RecyclerView rvReviews;

    private FeedbackRecyclerAdapter feedbackRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_feedback);
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {
        FeedbackViewModel tutorViewModel = ViewModelProviders.of(this).get(FeedbackViewModel.class);
        tutorViewModel.getObjectsList().observe(this, new Observer<List<Feedback>>() {
            @Override
            public void onChanged(@Nullable List<Feedback> feedback) {
                feedbackRecyclerAdapter = new FeedbackRecyclerAdapter(TutorFeedbackActivity.this, feedback);
                rvReviews.setAdapter(feedbackRecyclerAdapter);
            }
        });
    }

    private void initRecyclerView() {
        rvReviews.setLayoutManager(new LinearLayoutManager(this));
        rvReviews.setHasFixedSize(true);
    }
}
