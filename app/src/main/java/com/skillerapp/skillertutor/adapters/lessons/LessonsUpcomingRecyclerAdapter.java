package com.skillerapp.skillertutor.adapters.lessons;

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
import com.skillerapp.skillertutor.model.courses.Lesson;
import com.skillerapp.skillertutor.viewholders.lessons.LessonsUpcomingViewHolder;

import java.util.List;

public class LessonsUpcomingRecyclerAdapter extends RecyclerView.Adapter<LessonsUpcomingViewHolder> {

    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Lesson> upcomingList;

    public LessonsUpcomingRecyclerAdapter(Context context, List<Lesson> upcomingList) {
        recyclerViewAdapter = new RecyclerViewAdapter(context, StringsConstants.Items.COURSE);
        this.upcomingList = upcomingList;
    }

    @NonNull
    @Override
    public LessonsUpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson_upcoming, parent, false);
        return new LessonsUpcomingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsUpcomingViewHolder holder, int position) {
        Lesson lesson = upcomingList.get(position);

        try {
            holder.setProfilePicture(lesson.getStudent().getImageURL());
            holder.setDate(lesson.getDate().toString());
            holder.setTimeStart(lesson.getStartTime().toString());
            holder.setTimeEnd(lesson.getEndTime().toString());
            holder.setAddress(lesson.getLocation().getCity());//TODO set address probably
            holder.setCourseTitle(lesson.getCourse().getCourseTitle());
            holder.setPrice(lesson.getCourse().getPrice());
            holder.setStudentName(lesson.getStudent().getFullName());
            holder.setRate(lesson.getStudent().getRating());

            if (position == upcomingList.size() - 1)
                recyclerViewAdapter.addMarginBottom(holder.getLayout(), DimensionsConstants.Margins.MARGIN_MEDIUM);

            recyclerViewAdapter.addFoldingCellToggleOption(holder.getFoldingCell());
        } catch (Exception e) {
            e.getCause();
        }
    }

    @Override
    public int getItemCount() {
        if (upcomingList != null) return upcomingList.size();
        else return 0;
    }
}
