package com.skillerapp.skillertutor.controllers;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.skillerapp.skillertutor.model.misc.Date;
import com.skillerapp.skillertutor.model.users.Tutor;

import java.util.ArrayList;
import java.util.Calendar;

public final class DateAdapter {

    public static final int DAY = 0;
    public static final int MONTH = 1;
    public static final int YEAR = 2;

    private Context context;
    private Tutor tutor;
    private ArrayList<String> dateArray;
    private String dateString;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public DateAdapter(Context context) {
        this.context = context;
        dateArray = new ArrayList<>(3);
    }

    public void addDatePicker(final TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        context,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                Date date = new Date();
                date.setDay(String.valueOf(day));
                date.setMonth(String.valueOf(month));
                date.setYear(String.valueOf(year));
                dateString = date.toString();
                textView.setText(dateString);

                dateArray.add(DateAdapter.DAY, String.valueOf(day));
                dateArray.add(DateAdapter.MONTH, String.valueOf(month));
                dateArray.add(DateAdapter.YEAR, String.valueOf(year));
            }
        };
    }

    public ArrayList<String> getDateArray() {
        return dateArray;
    }

    public String getDateString() {
        return dateString;
    }
}
