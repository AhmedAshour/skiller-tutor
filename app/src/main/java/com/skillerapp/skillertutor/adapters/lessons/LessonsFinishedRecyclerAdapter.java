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
import com.skillerapp.skillertutor.viewholders.lessons.LessonsFinishedViewHolder;

import java.util.List;

public class LessonsFinishedRecyclerAdapter extends RecyclerView.Adapter<LessonsFinishedViewHolder> {

    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Lesson> finishedList;

    public LessonsFinishedRecyclerAdapter(Context context, List<Lesson> finishedList) {
        recyclerViewAdapter = new RecyclerViewAdapter(context, StringsConstants.Items.COURSE);
        this.finishedList = finishedList;
    }

    @NonNull
    @Override
    public LessonsFinishedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson_finished, parent, false);
        return new LessonsFinishedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsFinishedViewHolder holder, int position) {
        Lesson lesson = finishedList.get(position);

        try {
            holder.setProfilePicture(lesson.getStudent().getImageURL());
            holder.setDate(lesson.getDate().toString());
            holder.setAddress(lesson.getLocation().getCity());//TODO set address probably
            holder.setCourseTitle(lesson.getCourse().getCourseTitle());
            holder.setPrice(lesson.getCourse().getPrice());
            holder.setStudentName(lesson.getStudent().getFullName());
            holder.setRate(lesson.getStudent().getRating());

            recyclerViewAdapter.addFoldingCellToggleOption(holder.getFoldingCell());
            if (position == finishedList.size() - 1)
                recyclerViewAdapter.addMarginBottom(holder.getLayout(), DimensionsConstants.Margins.MARGIN_MEDIUM);

        } catch (Exception e) {
            e.getCause();
        }
    }

    @Override
    public int getItemCount() {
        if (finishedList != null) return finishedList.size();
        else return 0;
    }
}
