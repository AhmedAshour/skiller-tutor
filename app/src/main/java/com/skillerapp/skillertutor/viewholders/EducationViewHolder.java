package com.skillerapp.skillertutor.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.skillerapp.skillertutor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EducationViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_tutor_utils)
    SwipeRevealLayout layout;

    @BindView(R.id.item_secondary_bar)
    LinearLayout secondaryLayout;
    @BindView(R.id.item_tutor_utils_primary)
    LinearLayout primaryLayout;

    @BindView(R.id.item_secondary_bar_btn_edit)
    ImageButton btnEdit;
    @BindView(R.id.item_secondary_bar_btn_delete)
    ImageButton btnDelete;

    @BindView(R.id.item_tutor_utils_title)
    TextView tvInstitutionName;
    @BindView(R.id.item_tutor_utils_sub_title)
    TextView tvDegree;
    @BindView(R.id.item_tutor_utils_description)
    TextView tvDescription;
    @BindView(R.id.item_tutor_utils_date_start)
    TextView tvDateStart;
    @BindView(R.id.item_tutor_utils_date_end)
    TextView tvDateEnd;

    public EducationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public SwipeRevealLayout getLayout() {
        return layout;
    }

    public LinearLayout getSecondaryLayout() {
        return secondaryLayout;
    }

    public LinearLayout getPrimaryLayout() {
        return primaryLayout;
    }

    public ImageButton getBtnEdit() {
        return btnEdit;
    }

    public ImageButton getBtnDelete() {
        return btnDelete;
    }

    public void setInstitutionName(String institutionName) {
        tvInstitutionName.setText(institutionName);
    }

    public void setDegree(String degree) {
        tvDegree.setText(degree);
    }

    public void setDescription(String description) {
        tvDescription.setText(description);
    }

    public void setDateStart(String dateStart) {
        tvDateStart.setText(dateStart);
    }

    public void setDateEnd(String dateEnd) {
        tvDateEnd.setText(dateEnd);
    }
}
