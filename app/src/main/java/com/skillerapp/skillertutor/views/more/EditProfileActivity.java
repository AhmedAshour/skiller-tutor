package com.skillerapp.skillertutor.views.more;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.controllers.DateAdapter;
import com.skillerapp.skillertutor.controllers.ImageSelectorAdapter;
import com.skillerapp.skillertutor.model.users.Tutor;
import com.skillerapp.skillertutor.viewmodels.TutorViewModel;
import com.skillerapp.skillertutor.views.MainActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    private String TAG = EditProfileActivity.class.getSimpleName();

    @BindView(R.id.activity_edit_profile_iv_profile_pic)
    CircleImageView civProfilePic;
    @BindView(R.id.activity_edit_profile_et_name_first)
    TextInputEditText etNameFirst;
    @BindView(R.id.activity_edit_profile_et_name_last)
    TextInputEditText etNameLast;
    @BindView(R.id.activity_edit_profile_et_city)
    TextInputEditText etCity;
    @BindView(R.id.activity_edit_profile_et_country)
    TextInputEditText etCountry;
    @BindView(R.id.activity_edit_profile_et_birthday)
    TextInputEditText etBirthday;
    @BindView(R.id.activity_edit_profile_et_email)
    TextInputEditText etEmail;
    @BindView(R.id.activity_edit_profile_et_phone_num)
    TextInputEditText etPhoneNumber;
    @BindView(R.id.activity_edit_profile_et_price_per_hour)
    TextInputEditText etPricePerHour;
    @BindView(R.id.activity_edit_profile_et_bio)
    TextInputEditText etBio;
    @BindView(R.id.activity_edit_profile_radio_group_gender_radio_btn_male)
    RadioButton cbMale;
    @BindView(R.id.activity_edit_profile_radio_group_gender_radio_btn_female)
    RadioButton cbFemale;
    @BindView(R.id.activity_edit_profile_btn_save)
    Button btnSave;

    private TutorViewModel tutorViewModel;
    private String downloadURL;
    private Tutor tutor;

    private DateAdapter dateAdapterBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        initViewModel();

        dateAdapterBirthday = new DateAdapter(this);
        dateAdapterBirthday.addDatePicker(etBirthday);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case ImageSelectorAdapter.TAKE_PHOTO:
                // TODO
                break;

            case ImageSelectorAdapter.GALLERY:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    civProfilePic.setImageURI(selectedImage);

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (selectedImage != null) {
                        final StorageReference storageReference =
                                FirebaseStorage.getInstance().getReference().child(Objects.requireNonNull(user).getUid());
                        storageReference.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        downloadURL = uri.toString();
                                    }
                                });
                            }
                        });
                    }
                }
                break;
        }
    }

    private void initViewModel() {
        tutorViewModel = ViewModelProviders.of(EditProfileActivity.this).get(TutorViewModel.class);
        tutorViewModel.getTutor().observe(EditProfileActivity.this, new Observer<Tutor>() {
            @Override
            public void onChanged(@Nullable Tutor tutor) {
                EditProfileActivity.this.tutor = tutor;
                populateViews(tutor);
            }
        });
    }


    private void populateViews(Tutor tutor) {
        if (tutor.getImageURL() != null)
            Glide.with(this).load(tutor.getImageURL()).into(civProfilePic);
        etNameFirst.setText(tutor.getName().getFirstName());
        etNameLast.setText(tutor.getName().getLastName());
        etCity.setText(tutor.getLocation().getCity());
        etCountry.setText(tutor.getLocation().getCountry());
        etBirthday.setText(tutor.getBirthday().toString());
        etEmail.setText(tutor.getContact().getEmail());
        etPhoneNumber.setText(tutor.getContact().getPhoneNumber());
        etPricePerHour.setText(tutor.getPricePerHour());
        etBio.setText(tutor.getBio());
        try {
            if (tutor.getGender().toLowerCase().equals(StringsConstants.Gender.MALE))
                cbMale.setChecked(true);
            else if (tutor.getGender().toLowerCase().equals(StringsConstants.Gender.FEMALE))
                cbFemale.setChecked(true);
        } catch (NullPointerException e) {
            e.getCause();
        }
    }

    @OnClick(R.id.activity_edit_profile_btn_save)
    public void onClickBtnSave(View view) {
        Tutor updatedTutor = new Tutor();

        if (etNameFirst.getText() != null)
            updatedTutor.getName().setFirstName(etNameFirst.getText().toString());
        if (etNameLast.getText() != null)
            updatedTutor.getName().setLastName(etNameLast.getText().toString());
        if (downloadURL != null)
            updatedTutor.setImageURL(downloadURL);
        if (etCity.getText() != null)
            updatedTutor.getLocation().setCity(etCity.getText().toString());
        if (etCountry.getText() != null)
            updatedTutor.getLocation().setCountry(etCountry.getText().toString());
        if (etBirthday.getText() != null && !dateAdapterBirthday.getDateArray().isEmpty()) {
            updatedTutor.getBirthday().setDay(dateAdapterBirthday.getDateArray().get(DateAdapter.DAY));
            updatedTutor.getBirthday().setMonth(dateAdapterBirthday.getDateArray().get(DateAdapter.MONTH));
            updatedTutor.getBirthday().setYear(dateAdapterBirthday.getDateArray().get(DateAdapter.YEAR));
        }
        if (etPhoneNumber.getText() != null)
            updatedTutor.getContact().setPhoneNumber(etPhoneNumber.getText().toString());
        if (etEmail.getText() != null)
            updatedTutor.getContact().setEmail(etEmail.getText().toString());
        if (etPricePerHour.getText() != null)
            updatedTutor.setPricePerHour(etPricePerHour.getText().toString());
        if (etBio.getText() != null)
            updatedTutor.setBio(etBio.getText().toString());

        if (cbMale.isChecked())
            updatedTutor.setGender(StringsConstants.Gender.MALE);
        else if (cbFemale.isChecked())
            updatedTutor.setGender(StringsConstants.Gender.FEMALE);

        tutorViewModel.editInDatabase(updatedTutor);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(StringsConstants.Intents.POSITION, StringsConstants.Intents.FRAGMENT_MORE);
        startActivity(intent);
    }

    @OnClick(R.id.activity_edit_profile_iv_profile_pic)
    public void onClickProfilePic(View view) {
        ImageSelectorAdapter adapter = new ImageSelectorAdapter(this);
        adapter.selectImage();
    }
}
