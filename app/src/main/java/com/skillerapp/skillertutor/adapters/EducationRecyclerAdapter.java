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
import com.skillerapp.skillertutor.model.misc.Education;
import com.skillerapp.skillertutor.viewholders.EducationViewHolder;

import java.util.List;

public class EducationRecyclerAdapter extends RecyclerView.Adapter<EducationViewHolder> {

    private Context context;
    private List<Education> educationsList;

    public EducationRecyclerAdapter(Context context, List<Education> educationList) {
        this.context = context;
        this.educationsList = educationList;
    }

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tutor_utils, parent, false);
        return new EducationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {
        Education education = educationsList.get(position);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context,
                StringsConstants.Items.EDUCATION, education);

        try {
            holder.setInstitutionName(education.getInstitutionName());
            holder.setDegree(education.getDegree());
            holder.setDescription(education.getDescription());
            holder.setDateStart(education.getStartDate().toString());
            holder.setDateEnd(education.getEndDate().toString());

        } catch (Exception e) {
            e.getCause();
        }

        recyclerViewAdapter.adaptHeight(holder.getSecondaryLayout(), holder.getPrimaryLayout());
        recyclerViewAdapter.addUndoOption(holder.getBtnDelete());
        recyclerViewAdapter.addEditOption(holder.getBtnEdit());
        if (position == educationsList.size() - 1)
            recyclerViewAdapter.addMarginBottom(holder.getLayout(), DimensionsConstants.Margins.MARGIN_MEDIUM);
    }

    @Override
    public int getItemCount() {
        if (educationsList != null) return educationsList.size();
        else return 0;
    }
}
