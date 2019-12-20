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
import com.skillerapp.skillertutor.model.misc.Feedback;
import com.skillerapp.skillertutor.viewholders.ReviewsViewHolder;

import java.util.List;

public class FeedbackRecyclerAdapter extends RecyclerView.Adapter<ReviewsViewHolder> {

    private Context context;
    private List<Feedback> feedbackList;

    public FeedbackRecyclerAdapter(Context context, List<Feedback> feedbackList) {
        this.context = context;
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tutor_feedback, parent, false);
        return new ReviewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {
        Feedback feedback = feedbackList.get(position);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context, StringsConstants.Items.FEEDBACK);

        try {
            holder.setName(feedback.getUser().getFullName());
            holder.setProfilePic(feedback.getUser().getImageURL());
            holder.setDate(feedback.getDate().toString());
            holder.setContent(feedback.getReview());

        } catch (Exception e) {
            e.getCause();
        }

        if (position == feedbackList.size() - 1)
            recyclerViewAdapter.addMarginBottom(holder.getLayout(), DimensionsConstants.Margins.MARGIN_MEDIUM);
    }

    @Override
    public int getItemCount() {
        if (feedbackList != null) return feedbackList.size();
        else return 0;
    }
}
