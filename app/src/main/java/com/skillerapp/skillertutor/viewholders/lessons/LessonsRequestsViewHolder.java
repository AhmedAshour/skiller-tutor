package com.skillerapp.skillertutor.viewholders.lessons;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;
import com.skillerapp.skillertutor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LessonsRequestsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_lesson_request_fc)
    FoldingCell foldingCell;

    @BindView(R.id.item_lesson_title_view_cv_iv_photo)
    ImageView ivTitleProfilePic;
    @BindView(R.id.item_lesson_title_view_cv_tv_student_name)
    TextView tvTitleStudentName;
    @BindView(R.id.item_lesson_title_view_cv_tv_price)
    TextView tvTitleViewPrice;
    @BindView(R.id.item_lesson_title_view_cv_tv_currency)
    TextView tvTitleViewCurrency;
    @BindView(R.id.item_lesson_title_view_cv_tv_skill)
    TextView tvTitleViewSkill;
    @BindView(R.id.item_lesson_title_view_cv_tv_date)
    TextView tvTitleViewDate;
    @BindView(R.id.item_lesson_title_view_cv_tv_location)
    TextView tvTitleViewLocation;
    @BindView(R.id.item_lesson_content_view_cv_info_student_iv_photo)
    ImageView ivProfilePicture;
    @BindView(R.id.item_lesson_content_view_cv_info_student_tv_name)
    TextView tvName;
    @BindView(R.id.item_lesson_content_view_cv_info_student_tv_rate)
    TextView tvRate;
    @BindView(R.id.item_lesson_content_view_cv_info_student_img_btn_enter_profile)
    ImageButton btnEnterProfile;
    @BindView(R.id.item_lesson_content_view_cv_info_lesson_tv_skill)
    TextView tvSkillName;
    @BindView(R.id.item_lesson_content_view_cv_info_lesson_tv_time_start)
    TextView tvTimeStart;
    @BindView(R.id.item_lesson_content_view_cv_info_lesson_tv_time_end)
    TextView tvTimeEnd;
    @BindView(R.id.item_lesson_content_view_cv_info_lesson_tv_date)
    TextView tvDate;
    @BindView(R.id.item_lesson_content_view_cv_info_lesson_tv_location)
    TextView tvLocation;
    @BindView(R.id.item_lesson_content_view_cv_info_payment_tv_price)
    TextView tvPrice;
    @BindView(R.id.item_lesson_content_view_cv_info_payment_tv_currency)
    TextView tvCurrency;
    @BindView(R.id.item_lesson_content_view_cv_info_payment_tv_payment_method)
    TextView tvPaymentMethod;
    @BindView(R.id.item_cv_notes_tv_notes)
    TextView tvNotes;
    @BindView(R.id.item_lesson_content_view_acceptance_btn_accept)
    Button btnAccept;
    @BindView(R.id.item_lesson_content_view_acceptance_btn_deny)
    Button btnReject;

    public LessonsRequestsViewHolder(final View itemView, final RecyclerViewClickListener mListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
//        itemView.setOnClickListener(this);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onBtnAcceptClick(itemView, getAdapterPosition());
            }
        });
        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onBtnRejectClick(itemView, getAdapterPosition());

            }
        });
//        btnEnterProfile.setOnClickListener(this);
    }

    public FoldingCell getLayout() {
        return getFoldingCell();
    }

    public FoldingCell getFoldingCell() {
        return foldingCell;
    }

    public void setProfilePicture(String imgUrl) {
        Glide.with(itemView.getContext()).load(imgUrl).into(ivTitleProfilePic);
        Glide.with(itemView.getContext()).load(imgUrl).into(ivProfilePicture);
    }

    public void setStudentName(String name) {
        tvTitleStudentName.setText(name);
        tvName.setText(name);
    }

    public void setPrice(String price) {
        tvTitleViewPrice.setText(price);
        tvPrice.setText(price);
    }

    public void setCurrency(String currency) {
        tvTitleViewCurrency.setText(currency);
        tvCurrency.setText(currency);
    }

    public void setCourseTitle(String courseTitle) {
        tvTitleViewSkill.setText(courseTitle);
        tvSkillName.setText(courseTitle);
    }

    public void setTimeStart(String dateStart) {
        tvTimeStart.setText(dateStart);
    }

    public void setTimeEnd(String dateEnd) {
        tvTimeEnd.setText(dateEnd);
    }

    public void setDate(String date) {
        tvTitleViewDate.setText(date);
        tvDate.setText(date);
    }

    public void setAddress(String address) {
        //TODO get location object and format it here
        tvTitleViewLocation.setText(address);
        tvLocation.setText(address);
    }

    public void setRate(String rate) {
        tvRate.setText(rate);
    }

    public void setTvTimeStart(String timeStart) {
        tvTimeStart.setText(timeStart);
    }

    public void setTvTimeEnd(String timeEnd) {
        tvTimeEnd.setText(timeEnd);
    }

    public void setTvPaymentMethod(String paymentMethod) {
        tvPaymentMethod.setText(paymentMethod);
    }

    public void setTvNotes(String notes) {
        tvNotes.setText(notes);
    }

//    public void setOnItemClickListener(ItemClickListener itemClickListener) {
//        this.itemClickListener = itemClickListener;
//    }

    public interface RecyclerViewClickListener {
        void onBtnAcceptClick(View v, int position);

        void onBtnRejectClick(View v, int position);
    }
}