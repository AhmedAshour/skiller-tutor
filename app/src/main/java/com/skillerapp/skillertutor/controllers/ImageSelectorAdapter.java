package com.skillerapp.skillertutor.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;

public class ImageSelectorAdapter {

    public static final int TAKE_PHOTO = 0;
    public static final int GALLERY = 1;

    private static final String STRING_TAKE_PHOTO = "Take Photo";
    private static final String STRING_GALLERY = "Gallery";

    private Context context;

    public ImageSelectorAdapter(Context context) {
        this.context = context;
    }

    public void selectImage() {
        final Context context = this.context;
        final CharSequence[] options = {STRING_TAKE_PHOTO, STRING_GALLERY};

        AlertDialog.Builder builder = new AlertDialog.Builder(
                this.context);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals(STRING_TAKE_PHOTO)) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    ((Activity) context).startActivityForResult(intent, TAKE_PHOTO);

                } else if (options[item].equals(STRING_GALLERY)) {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    ((Activity) context).startActivityForResult(intent, GALLERY);
                }
            }
        });
        builder.show();
    }
}
