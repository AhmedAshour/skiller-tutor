package com.skillerapp.skillertutor.views.more;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.model.users.Tutor;
import com.skillerapp.skillertutor.viewmodels.TutorViewModel;
import com.skillerapp.skillertutor.views.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MoreFragment extends Fragment {

    @BindView(R.id.btn_more_personal_info)
    Button btnPersonalInfo;
    @BindView(R.id.btn_more_education)
    Button btnEducation;
    @BindView(R.id.btn_more_experiences)
    Button btnExperiences;
    @BindView(R.id.btn_more_reviews)
    Button btnReviews;
    @BindView(R.id.fragment_more_btn_privacy_settings)
    Button btnPrivacySettings;
    @BindView(R.id.fragment_more_btn_sign_out)
    Button btnSignOut;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tv_more_country)
    TextView tvCountry;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String TAG = MoreFragment.class.getSimpleName();

    @BindView(R.id.civ_more_profile_pic)
    CircleImageView civMoreProfilePic;
    @BindView(R.id.tv_more_name)
    TextView tvName;
    @BindView(R.id.tv_more_city)
    TextView tvCity;
    private TutorViewModel tutorViewModel;
    @BindView(R.id.tv_more_hourly_price)
    TextView tvPrice;
    @BindView(R.id.tv_more_experience_hours)
    TextView tvHours;
    @BindView(R.id.tv_more_rating)
    TextView tvRating;

    public MoreFragment() {
        // Required empty public constructor
    }

    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, v);

        btnPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        btnEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TutorEducationActivity.class);
                startActivity(intent);
            }
        });

        btnExperiences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TutorExperiencesActivity.class);
                startActivity(intent);
            }
        });

        btnReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TutorFeedbackActivity.class);
                startActivity(intent);
            }
        });

        btnPrivacySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PrivacySettingsActivity.class);
                startActivity(intent);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        initViewModel();
    }

    private void initViewModel() {
        tutorViewModel = ViewModelProviders.of(this).get(TutorViewModel.class);
        tutorViewModel.getTutor().observe(this, new Observer<Tutor>() {
            @Override
            public void onChanged(@Nullable Tutor tutor) {
                Log.d(TAG, tutor + "");
                populateTutorProfile(tutor);

            }
        });
    }

    private void populateTutorProfile(Tutor tutor) {
        try {
            if (tutor.getImageURL() != null)
                Glide.with(getActivity()).load(tutor.getImageURL()).into(civMoreProfilePic);
            tvName.setText(tutor.getFullName());
            tvCity.setText(tutor.getLocation().getCity());
            tvCountry.setText(tutor.getLocation().getCountry());
            tvHours.setText(tutor.getNumExperienceHours());
            tvPrice.setText(tutor.getPricePerHour());
            tvRating.setText(tutor.getRating());
        } catch (Exception e) {
        }
    }
}
