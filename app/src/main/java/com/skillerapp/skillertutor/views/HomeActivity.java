package com.skillerapp.skillertutor.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skillerapp.skillertutor.BuildConfig;
import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.constants.FirebaseKeys;
import com.skillerapp.skillertutor.model.users.Tutor;

import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            HomeActivity.this.startActivity(intent);
        } else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                         /*   .setLogo(R.drawable.logo_with_slogan)
                            .setTheme(R.style.LoginTheme)*/
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.FacebookBuilder().build(),
                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                                    new AuthUI.IdpConfig.PhoneBuilder().build()))
                            .setIsSmartLockEnabled(!BuildConfig.DEBUG, true)
                            .build(),
                    RC_SIGN_IN);

            AuthUI.IdpConfig phoneConfigWithDefaultNumber = new AuthUI.IdpConfig
                    .PhoneBuilder().setDefaultCountryIso("eg").build();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String tutorUid = user.getUid();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference()
                        .child(FirebaseKeys.CHILD_TUTORS).child(tutorUid);

                try {
                    mDatabase.child(FirebaseKeys.CHILD_DATABASE_REFERENCE).setValue(tutorUid);
                    mDatabase.child(FirebaseKeys.Tutor.User.Name.KEY_CHILD_NAME_FIRST_NAME).setValue(user.getDisplayName().toString());
                    mDatabase.child(FirebaseKeys.Tutor.User.Contact.KEY_CHILD_EMAIL).setValue(user.getEmail().toString());
                    mDatabase.child(FirebaseKeys.Tutor.User.Contact.KEY_CHILD_PHONE).setValue(user.getPhoneNumber().toString());
                    mDatabase.child(FirebaseKeys.Tutor.User.CHILD_USER_IMAGE_URL).setValue(user.getPhotoUrl().toString());
                } catch (NullPointerException e) {
                    e.getCause();
                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}
