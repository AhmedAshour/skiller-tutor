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
import com.skillerapp.skillertutor.model.misc.Education;
import com.skillerapp.skillertutor.viewmodels.EducationViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TutorEducationNewActivity extends AppCompatActivity {

    @BindView(R.id.activity_tutor_education_new_et_name)
    TextInputEditText etInstitutionName;
    @BindView(R.id.activity_tutor_education_new_et_degree)
    TextInputEditText etDegree;
    @BindView(R.id.activity_tutor_education_new_et_description)
    TextInputEditText etDescription;
    @BindView(R.id.activity_tutor_education_new_et_year_from)
    TextInputEditText etYearFrom;
    @BindView(R.id.activity_tutor_education_new_et_year_to)
    TextInputEditText etYearTo;

    private boolean onEditMode;

    private Education education;

    private DateAdapter dateAdapterYearFrom;
    private DateAdapter dateAdapterYearTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_education_new);
        ButterKnife.bind(this);

        onEditMode = false;

        dateAdapterYearFrom = new DateAdapter(this);
        dateAdapterYearFrom.addDatePicker(etYearFrom);

        dateAdapterYearTo = new DateAdapter(this);
        dateAdapterYearTo.addDatePicker(etYearTo);

        Intent intent = getIntent();
        if (intent != null
                && intent.getSerializableExtra(StringsConstants.Items.EDUCATION) instanceof Education) {
            education = (Education) intent.getSerializableExtra(StringsConstants.Items.EDUCATION);
            populateViews(education);
            onEditMode = true;
        }
    }

    private void initViewModel() {
        EducationViewModel educationViewModel = ViewModelProviders.of(this).get(EducationViewModel.class);
        if (onEditMode) {
            educationViewModel.editInDatabase(education, education.getDatabaseReference());
            Toast.makeText(this, StringsConstants.Items.EDUCATION + StringsConstants.Toasts.EDITED_SUCCESSFULY, Toast.LENGTH_SHORT).show();
        } else {
            educationViewModel.writeToDatabase(education);
            Toast.makeText(this, StringsConstants.Items.EDUCATION + StringsConstants.Toasts.ADDED_SUCCESSFULY, Toast.LENGTH_SHORT).show();
        }
    }

    private void populateViews(Education education) {
        if (education.getInstitutionName() != null)
            etInstitutionName.setText(education.getInstitutionName());
        if (education.getDegree() != null)
            etDegree.setText(education.getDegree());
        if (education.getDescription() != null)
            etDescription.setText(education.getDescription());
        if (education.getStartDate() != null)
            etYearFrom.setText(education.getStartDate().toString());
        if (education.getEndDate() != null)
            etYearTo.setText(education.getEndDate().toString());
    }

    @OnClick(R.id.activity_education_new_btn_save)
    public void createEducation(View view) {
        if (education == null)
            education = new Education();

        if (etInstitutionName.getText() != null)
            education.setInstitutionName(etInstitutionName.getText().toString());
        if (etDegree.getText() != null)
            education.setDegree(etDegree.getText().toString());
        if (etDescription.getText() != null)
            education.setDescription(etDescription.getText().toString());
        if (etYearFrom.getText() != null && !dateAdapterYearFrom.getDateArray().isEmpty()) {
            education.getStartDate().setDay(dateAdapterYearFrom.getDateArray().get(DateAdapter.DAY));
            education.getStartDate().setMonth(dateAdapterYearFrom.getDateArray().get(DateAdapter.MONTH));
            education.getStartDate().setYear(dateAdapterYearFrom.getDateArray().get(DateAdapter.YEAR));
        }
        if (etYearTo.getText() != null && !dateAdapterYearTo.getDateArray().isEmpty()) {
            education.getEndDate().setDay(dateAdapterYearTo.getDateArray().get(DateAdapter.DAY));
            education.getEndDate().setDay(dateAdapterYearTo.getDateArray().get(DateAdapter.DAY));
            education.getEndDate().setYear(dateAdapterYearTo.getDateArray().get(DateAdapter.YEAR));
        }

        initViewModel();

        Intent intent = new Intent(TutorEducationNewActivity.this, TutorEducationActivity.class);
        TutorEducationNewActivity.this.startActivity(intent);
    }
}
