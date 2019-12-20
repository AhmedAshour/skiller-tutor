package com.skillerapp.skillertutor.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.ramotion.foldingcell.FoldingCell;
import com.skillerapp.skillertutor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.item_course)
    SwipeRevealLayout layout;

    @BindView(R.id.item_course_title_view)
    RelativeLayout primaryLayout;
    @BindView(R.id.item_course_fc)
    FoldingCell foldingCell;
    @BindView(R.id.item_secondary_bar_btn_edit)
    ImageButton btnEdit;

    @BindView(R.id.item_course_title_view_tv_course_name)
    TextView tvTitleViewCourseName;
    @BindView(R.id.item_course_title_view_tv_course_price)
    TextView tvTitleViewPrice;
    @BindView(R.id.item_course_title_view_tv_course_currency)
    TextView tvTitleViewCurrency;
    @BindView(R.id.item_course_title_view_tv_course_num_sessions)
    TextView tvTitleViewNumSessions;
    @BindView(R.id.item_course_title_view_tv_course_num_hours)
    TextView tvTitleViewNumHours;

    @BindView(R.id.item_course_title_view_tv_course_num_hours_per_session)
    TextView tvTitleViewNumHoursPerSession;
    @BindView(R.id.item_course_content_view_cv_info_tv_skill)
    TextView tvSkillName;
    @BindView(R.id.item_course_content_view_cv_info_tv_time_start)
    TextView tvTimeStart;
    @BindView(R.id.item_course_content_view_cv_info_tv_time_end)
    TextView tvTimeEnd;
    @BindView(R.id.item_course_content_view_cv_info_tv_date_start)
    TextView tvDateStart;
    @BindView(R.id.item_course_content_view_cv_info_tv_date_end)
    TextView tvDateEnd;
    @BindView(R.id.item_course_content_view_cv_info_tv_location)
    TextView tvLocation;
    @BindView(R.id.item_course_content_view_cv_description_tv_description)
    TextView tvDescription;
    @BindView(R.id.item_cv_notes_tv_notes)
    TextView tvNotes;
    @BindView(R.id.item_secondary_bar_btn_delete)
    ImageButton btnDelete;
    @BindView(R.id.item_secondary_bar)
    LinearLayout secondaryLayout;

    public CourseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public SwipeRevealLayout getLayout() {
        return layout;
    }

    public LinearLayout getSecondaryLayout() {
        return secondaryLayout;
    }

    public RelativeLayout getPrimaryLayout() {
        return primaryLayout;
    }

    public FoldingCell getFoldingCell() {
        return foldingCell;
    }

    public void setTitleViewName(String courseName) {
        this.tvTitleViewCourseName.setText(courseName);
    }

    public void setTitleViewPrice(String coursePrice) {
        this.tvTitleViewPrice.setText(coursePrice);
    }

    public void setTitleViewNumSessions(String numSessions) {
        this.tvTitleViewNumSessions.setText(numSessions);
    }

    public void setTitleViewNumHours(String numHoursPerSession, String numSessions) {
        int calcNumHours = Integer.valueOf(numSessions) * Integer.valueOf(numHoursPerSession);
        this.tvTitleViewNumHours.setText(String.valueOf(calcNumHours));
        this.tvTitleViewNumHoursPerSession.setText(numHoursPerSession);
    }

    public void setSkillName(String skillName) {
        this.tvSkillName.setText(skillName);
    }

    public void setTimeStart(String timeStart) {
        this.tvTimeStart.setText(timeStart);
    }

    public void setTimeEnd(String timeEnd) {
        this.tvTimeEnd.setText(timeEnd);
    }

    public void setDateStart(String dateStart) {
        this.tvDateStart.setText(dateStart);
    }

    public void setDateEnd(String dateEnd) {
        this.tvDateEnd.setText(dateEnd);
    }

    public void setLocation(String location) {
        this.tvLocation.setText(location);
    }

    public void setDescription(String description) {
        this.tvDescription.setText(description);
    }

    public void setNotes(String notes) {
        this.tvNotes.setText(notes);
    }

    public ImageButton getBtnEdit() {
        return btnEdit;
    }

    public ImageButton getBtnDelete() {
        return btnDelete;
    }

    @Override
    public void onClick(View v) {
    }
}