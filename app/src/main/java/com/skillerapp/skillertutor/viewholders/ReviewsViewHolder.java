package com.skillerapp.skillertutor.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skillerapp.skillertutor.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_tutor_feedback)
    LinearLayout layout;

    @BindView(R.id.item_review_civ_reviewer_profile_pic)
    CircleImageView civReviewsProfilePic;
    @BindView(R.id.item_review_tv_reviewer_name)
    TextView tvReviewsName;
    @BindView(R.id.item_review_tv_date)
    TextView tvReviewsDate;
    @BindView(R.id.item_review_content)
    TextView tvReviewsContent;

    public ReviewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public void setProfilePic(String imgUrl) {
        Glide.with(itemView.getContext()).load(imgUrl).into(civReviewsProfilePic);
    }

    public void setName(String name) {
        tvReviewsName.setText(name);
    }

    public void setDate(String date) {
        tvReviewsDate.setText(date);
    }

    public void setContent(String content) {
        tvReviewsContent.setText(content);
    }
}
