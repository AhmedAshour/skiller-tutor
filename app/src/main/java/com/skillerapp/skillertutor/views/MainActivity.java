package com.skillerapp.skillertutor.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.skillerapp.skillertutor.R;
import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.util.BottomNavigationViewHelper;
import com.skillerapp.skillertutor.views.courses.CoursesFragment;
import com.skillerapp.skillertutor.views.lessons.LessonsFragment;
import com.skillerapp.skillertutor.views.more.MoreFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_lessons:
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new LessonsFragment()).commit();
                    return true;
                case R.id.navigation_messages:
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new MessagesFragment()).commit();
                    return true;
                case R.id.navigation_courses:
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CoursesFragment()).commit();
                    return true;
                case R.id.navigation_more:
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new MoreFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();

        Intent intent = getIntent();
        if (intent.getStringExtra(StringsConstants.Intents.POSITION) != null) {
            switch (intent.getStringExtra(StringsConstants.Intents.POSITION)) {
                case StringsConstants.Intents.FRAGMENT_LESSONS:
                    fragmentManager.beginTransaction().add(R.id.fragment_container, new LessonsFragment()).commit();
                    break;
                case StringsConstants.Intents.FRAGMENT_MESSAGES:
                    fragmentManager.beginTransaction().add(R.id.fragment_container, new MessagesFragment()).commit();
                    break;
                case StringsConstants.Intents.FRAGMENT_COURSES:
                    fragmentManager.beginTransaction().add(R.id.fragment_container, new CoursesFragment()).commit();
                    break;
                case StringsConstants.Intents.FRAGMENT_MORE:
                    fragmentManager.beginTransaction().add(R.id.fragment_container, new MoreFragment()).commit();
                    break;
            }
        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_container, new LessonsFragment()).commit();
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
    }
}
