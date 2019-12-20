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
import com.skillerapp.skillertutor.viewholders.lessons.LessonsRequestsViewHolder;

import java.util.List;

public class LessonsRequestsRecyclerAdapter extends RecyclerView.Adapter<LessonsRequestsViewHolder> {

    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Lesson> requestsList;
    private LessonsRequestsViewHolder.RecyclerViewClickListener mListener;

    public LessonsRequestsRecyclerAdapter(Context context, List<Lesson> requestsList,
                                          LessonsRequestsViewHolder.RecyclerViewClickListener mListener) {
        recyclerViewAdapter = new RecyclerViewAdapter(context, StringsConstants.Items.COURSE);
        this.requestsList = requestsList;
        this.mListener = mListener;
    }

    public LessonsRequestsRecyclerAdapter() {
    }

    @NonNull
    @Override
    public LessonsRequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson_request, parent, false);
        return new LessonsRequestsViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsRequestsViewHolder holder, int position) {
        final Lesson lesson = requestsList.get(position);

        try {
            holder.setProfilePicture(lesson.getStudent().getImageURL());
            holder.setDate(lesson.getDate().toString());
            holder.setTimeStart(lesson.getStartTime().toString());
            holder.setTimeEnd(lesson.getEndTime().toString());
            holder.setAddress(lesson.getLocation().getCity()); //TODO set address probably
            holder.setCourseTitle(lesson.getCourse().getCourseTitle());
            holder.setPrice(lesson.getCourse().getPrice());
            holder.setStudentName(lesson.getStudent().getFullName());
            holder.setRate(lesson.getStudent().getRating());

            recyclerViewAdapter.addFoldingCellToggleOption(holder.getFoldingCell());
            if (position == requestsList.size() - 1)
                recyclerViewAdapter.addMarginBottom(holder.getLayout(), DimensionsConstants.Margins.MARGIN_MEDIUM);

        } catch (Exception e) {
            e.getCause();
        }
    }

    @Override
    public int getItemCount() {
        if (requestsList != null) return requestsList.size();
        else return 0;
    }
}
