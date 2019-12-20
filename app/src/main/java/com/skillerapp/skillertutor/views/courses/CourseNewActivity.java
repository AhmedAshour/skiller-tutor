package com.skillerapp.skillertutor.views.courses;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.controllers.DateAdapter;
import com.skillerapp.skillertutor.model.courses.Course;
import com.skillerapp.skillertutor.viewmodels.CourseViewModel;
import com.skillerapp.skillertutor.views.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseNewActivity extends AppCompatActivity {

    @BindView(R.id.et_new_course_title)
    TextInputEditText etTitle;
    @BindView(R.id.activity_course_new_et_course_description)
    TextInputEditText etDescription;
    @BindView(R.id.et_new_course_num_sessions)
    TextInputEditText etNumSessions;
    @BindView(R.id.et_new_course_num_hours_per_session)
    TextInputEditText etNumHours;
    @BindView(R.id.et_new_course_price)
    TextInputEditText etPrice;
    @BindView(R.id.activity_course_new_et_location)
    TextInputEditText etLocation;
    @BindView(R.id.activity_course_new_et_time_from)
    TextInputEditText etTimeFrom;
    @BindView(R.id.activity_course_new_et_time_to)
    TextInputEditText etTimeTo;
    @BindView(R.id.activity_course_new_et_date_from)
    TextInputEditText etDateFrom;
    @BindView(R.id.activity_course_new_et_date_to)
    TextInputEditText etDateTo;
    @BindView(R.id.et_new_course_skill)
    TextInputEditText etSkill;
    @BindView(R.id.activity_course_new_et_notes)
    TextInputEditText etNotes;
    @BindView(R.id.activity_course_new_btn_create)
    Button btnCreateCourse;

    private boolean onEditMode;

    private Course course;

    private DateAdapter dateAdapterYearFrom;
    private DateAdapter dateAdapterYearTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_new);
        ButterKnife.bind(this);

        onEditMode = false;

        dateAdapterYearFrom = new DateAdapter(this);
        dateAdapterYearFrom.addDatePicker(etDateFrom);

        dateAdapterYearTo = new DateAdapter(this);
        dateAdapterYearTo.addDatePicker(etDateTo);

        Intent intent = getIntent();
        if (intent != null
                && intent.getSerializableExtra(StringsConstants.Items.COURSE) instanceof Course) {
            course = (Course) intent.getSerializableExtra(StringsConstants.Items.COURSE);
            populateViews(course);
            onEditMode = true;
        }
    }

    private void initViewModel() {
        CourseViewModel courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);
        if (onEditMode) {
            courseViewModel.editInDatabase(course, course.getDatabaseReference());
            Toast.makeText(this, StringsConstants.Items.COURSE + StringsConstants.Toasts.EDITED_SUCCESSFULY, Toast.LENGTH_SHORT).show();
        } else {
            courseViewModel.writeToDatabase(course);
            Toast.makeText(this, StringsConstants.Items.COURSE + StringsConstants.Toasts.ADDED_SUCCESSFULY, Toast.LENGTH_SHORT).show();
        }
    }

    private void populateViews(Course course) {
        if (course.getCourseTitle() != null)
            etTitle.setText(course.getCourseTitle());
        if (course.getDescription() != null)
            etDescription.setText(course.getDescription());
        if (course.getNumSessions() != null)
            etNumSessions.setText(course.getNumSessions());
        if (course.getNumHoursPerSession() != null)
            etNumHours.setText(course.getNumHoursPerSession());
        if (course.getPrice() != null)
            etPrice.setText(course.getPrice());
        if (course.getSkillName() != null)
            etSkill.setText(course.getSkillName());
        if (course.getLocation() != null)
            etLocation.setText(course.getLocation());
        if (course.getTimeStart() != null)
            etTimeFrom.setText(course.getTimeStart());
        if (course.getTimeEnd() != null)
            etTimeTo.setText(course.getTimeEnd());
        if (course.getDateStart() != null)
            etDateFrom.setText(course.getDateStart().toString());
        if (course.getDateEnd() != null)
            etDateTo.setText(course.getDateEnd().toString());
        if (course.getNotes() != null)
            etNotes.setText(course.getNotes());
    }

    @OnClick(R.id.activity_course_new_btn_create)
    public void createCourse(View view) {
        if (course == null)
            course = new Course();

        if (etTitle.getText() != null)
            course.setCourseTitle(etTitle.getText().toString());
        if (etDescription.getText() != null)
            course.setDescription(etDescription.getText().toString());
        if (etNumSessions.getText() != null)
            course.setNumSessions(etNumSessions.getText().toString());
        if (etNumHours.getText() != null)
            course.setNumHoursPerSession(etNumHours.getText().toString());
        if (etPrice.getText() != null)
            course.setPrice(etPrice.getText().toString());
        if (etSkill.getText() != null)
            course.setSkillName(etSkill.getText().toString());
        if (etLocation.getText() != null)
            course.setLocation(etLocation.getText().toString());
        if (etDateFrom.getText() != null && !dateAdapterYearFrom.getDateArray().isEmpty()) {
            course.getDateStart().setDay(dateAdapterYearFrom.getDateArray().get(DateAdapter.DAY));
            course.getDateStart().setMonth(dateAdapterYearFrom.getDateArray().get(DateAdapter.MONTH));
            course.getDateStart().setYear(dateAdapterYearFrom.getDateArray().get(DateAdapter.YEAR));
        }
        if (etDateTo.getText() != null && !dateAdapterYearTo.getDateArray().isEmpty()) {
            course.getDateEnd().setDay(dateAdapterYearTo.getDateArray().get(DateAdapter.DAY));
            course.getDateEnd().setMonth(dateAdapterYearTo.getDateArray().get(DateAdapter.MONTH));
            course.getDateEnd().setYear(dateAdapterYearTo.getDateArray().get(DateAdapter.YEAR));
        }
        if (etTimeFrom.getText() != null)
            course.setTimeStart(etTimeFrom.getText().toString());
        if (etTimeTo.getText() != null)
            course.setTimeEnd(etTimeTo.getText().toString());
        if (etNotes.getText() != null)
            course.setNotes(etNotes.getText().toString());

        initViewModel();
        Intent intent = new Intent(CourseNewActivity.this, MainActivity.class);
        intent.putExtra(StringsConstants.Intents.POSITION, StringsConstants.Intents.FRAGMENT_COURSES);
        CourseNewActivity.this.startActivity(intent);
    }
}
