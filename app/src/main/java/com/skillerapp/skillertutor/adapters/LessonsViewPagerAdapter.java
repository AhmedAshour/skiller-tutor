package com.skillerapp.skillertutor.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.views.lessons.LessonsRequestsFragment;
import com.skillerapp.skillertutor.views.lessons.LessonsTakenFragment;
import com.skillerapp.skillertutor.views.lessons.LessonsUpcomingFragment;

public class LessonsViewPagerAdapter extends FragmentPagerAdapter {

    public LessonsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LessonsRequestsFragment();
            case 1:
                return new LessonsUpcomingFragment();
            case 2:
                return new LessonsTakenFragment();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return StringsConstants.Headers.REQUESTS;
            case 1:
                return StringsConstants.Headers.UPCOMING;
            case 2:
                return StringsConstants.Headers.FINISHED;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
