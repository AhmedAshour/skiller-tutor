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
import com.skillerapp.skillertutor.model.misc.Experience;
import com.skillerapp.skillertutor.viewholders.ExperienceViewHolder;

import java.util.List;

public class ExperienceRecyclerAdapter extends RecyclerView.Adapter<ExperienceViewHolder> {

    private Context context;
    private List<Experience> experiencesList;

    public ExperienceRecyclerAdapter(Context context, List<Experience> experiencesList) {
        this.context = context;
        this.experiencesList = experiencesList;
    }

    @NonNull
    @Override
    public ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tutor_utils, parent, false);
        return new ExperienceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewHolder holder, int position) {
        Experience experience = experiencesList.get(position);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context,
                StringsConstants.Items.EXPERIENCE, experience);

        try {
            holder.setTitle(experience.getTitle());
            holder.setCompany(experience.getCompany());
            holder.setDescription(experience.getDescription());
            holder.setDateStart(experience.getStartDate().toString());
            holder.setDateEnd(experience.getEndDate().toString());

        } catch (Exception e) {
            e.getCause();
        }

        recyclerViewAdapter.adaptHeight(holder.getSecondaryLayout(), holder.getPrimaryLayout());
        recyclerViewAdapter.addUndoOption(holder.getBtnDelete());
        recyclerViewAdapter.addEditOption(holder.getBtnEdit());
        if (position == experiencesList.size() - 1)
            recyclerViewAdapter.addMarginBottom(holder.getLayout(), DimensionsConstants.Margins.MARGIN_MEDIUM);
    }

    @Override
    public int getItemCount() {
        if (experiencesList != null) return experiencesList.size();
        else return 0;
    }
}