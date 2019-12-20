package com.skillerapp.skillertutor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.constants.DimensionsConstants;
import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.controllers.RecyclerViewAdapter;
import com.skillerapp.skillertutor.model.courses.Course;
import com.skillerapp.skillertutor.viewholders.CourseViewHolder;

import java.util.List;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseViewHolder> {

    private Context context;
    private List<Course> coursesList;

    public CourseRecyclerAdapter(Context context, List<Course> coursesList) {
        this.context = context;
        this.coursesList = coursesList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = coursesList.get(position);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context,
                StringsConstants.Items.COURSE, course);

        try {
            holder.setTitleViewName(course.getCourseTitle());
            holder.setTitleViewPrice(course.getPrice());
            holder.setTitleViewNumSessions(course.getNumSessions());
            holder.setTitleViewNumHours(course.getNumSessions(), course.getNumHoursPerSession());

            holder.setSkillName(course.getSkillName());
            holder.setTimeStart(course.getTimeStart());
            holder.setTimeEnd(course.getTimeEnd());
            holder.setDateStart(course.getDateStart().toString());
            holder.setDateEnd(course.getDateEnd().toString());
            holder.setLocation(course.getLocation());
            holder.setDescription(course.getDescription());
            holder.setNotes(course.getNotes());

        } catch (Exception e) {
            e.getCause();
        }

        recyclerViewAdapter.addFoldingCellToggleOption(holder.getFoldingCell());
        recyclerViewAdapter.adaptHeight(holder.getSecondaryLayout(), holder.getPrimaryLayout());
        recyclerViewAdapter.addUndoOption(holder.getBtnDelete());
        recyclerViewAdapter.addEditOption(holder.getBtnEdit());
        if (position == coursesList.size() - 1)
            recyclerViewAdapter.addMarginBottom(holder.getLayout(), DimensionsConstants.Margins.MARGIN_MEDIUM);
    }

    @Override
    public int getItemCount() {
        if (coursesList != null) return coursesList.size();
        else return 0;
    }
}
