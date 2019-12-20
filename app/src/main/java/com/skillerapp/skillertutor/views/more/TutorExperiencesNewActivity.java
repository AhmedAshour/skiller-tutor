package com.skillerapp.skillertutor.views.more;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.controllers.DateAdapter;
import com.skillerapp.skillertutor.model.misc.Experience;
import com.skillerapp.skillertutor.viewmodels.ExperienceViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TutorExperiencesNewActivity extends AppCompatActivity {

    @BindView(R.id.activity_tutor_experiences_new_et_title)
    TextInputEditText etTitle;
    @BindView(R.id.activity_tutor_experiences_new_et_company_name)
    TextInputEditText etCompanyName;
    @BindView(R.id.activity_tutor_experiences_new_et_description)
    TextInputEditText etDescription;
    @BindView(R.id.activity_tutor_experiences_new_et_year_from)
    TextInputEditText etYearFrom;
    @BindView(R.id.activity_tutor_experiences_new_et_year_to)
    TextInputEditText etYearTo;

    private boolean onEditMode;

    private Experience experience;

    private DateAdapter dateAdapterYearFrom;
    private DateAdapter dateAdapterYearTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_experiences_new);
        ButterKnife.bind(this);

        onEditMode = false;

        dateAdapterYearFrom = new DateAdapter(this);
        dateAdapterYearFrom.addDatePicker(etYearFrom);

        dateAdapterYearTo = new DateAdapter(this);
        dateAdapterYearTo.addDatePicker(etYearTo);

        Intent intent = getIntent();
        if (intent != null
                && intent.getSerializableExtra(StringsConstants.Items.EXPERIENCE) instanceof Experience) {
            experience = (Experience) intent.getSerializableExtra(StringsConstants.Items.EXPERIENCE);
            populateViews(experience);
            onEditMode = true;
        }
    }

    private void initViewModel() {
        ExperienceViewModel experienceViewModel = ViewModelProviders.of(this).get(ExperienceViewModel.class);
        if (onEditMode) {
            experienceViewModel.editInDatabase(experience, experience.getDatabaseReference());
            Toast.makeText(this, StringsConstants.Items.EDUCATION + StringsConstants.Toasts.EDITED_SUCCESSFULY, Toast.LENGTH_SHORT).show();
        } else {
            experienceViewModel.writeToDatabase(experience);
            Toast.makeText(this, StringsConstants.Items.EDUCATION + StringsConstants.Toasts.ADDED_SUCCESSFULY, Toast.LENGTH_SHORT).show();
        }
    }

    private void populateViews(Experience experience) {
        if (experience.getTitle() != null)
            etTitle.setText(experience.getTitle());
        if (experience.getCompany() != null)
            etCompanyName.setText(experience.getCompany());
        if (experience.getDescription() != null)
            etDescription.setText(experience.getDescription());
        if (experience.getStartDate() != null)
            etYearFrom.setText(experience.getStartDate().toString());
        if (experience.getEndDate() != null)
            etYearTo.setText(experience.getEndDate().toString());
    }

    @OnClick(R.id.activity_experiences_new_btn_save)
    public void createExperience(View view) {
        if (experience == null)
            experience = new Experience();

        if (etTitle.getText() != null)
            experience.setTitle(etTitle.getText().toString());
        if (etCompanyName.getText() != null)
            experience.setCompany(etCompanyName.getText().toString());
        if (etDescription.getText() != null)
            experience.setDescription(etDescription.getText().toString());
        if (etYearFrom.getText() != null && !dateAdapterYearFrom.getDateArray().isEmpty()) {
            experience.getStartDate().setDay(dateAdapterYearFrom.getDateArray().get(DateAdapter.DAY));
            experience.getStartDate().setMonth(dateAdapterYearFrom.getDateArray().get(DateAdapter.MONTH));
            experience.getStartDate().setYear(dateAdapterYearFrom.getDateArray().get(DateAdapter.YEAR));
        }
        if (etYearTo.getText() != null && !dateAdapterYearTo.getDateArray().isEmpty()) {
            experience.getEndDate().setDay(dateAdapterYearTo.getDateArray().get(DateAdapter.DAY));
            experience.getEndDate().setMonth(dateAdapterYearTo.getDateArray().get(DateAdapter.MONTH));
            experience.getEndDate().setYear(dateAdapterYearTo.getDateArray().get(DateAdapter.YEAR));
        }

        initViewModel();
        Intent intent = new Intent(TutorExperiencesNewActivity.this, TutorExperiencesActivity.class);
        TutorExperiencesNewActivity.this.startActivity(intent);
    }
}
