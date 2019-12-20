package com.skillerapp.skillertutor.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;

import com.ramotion.foldingcell.FoldingCell;
import com.skillerapp.skillertutor.constants.StringsConstants;
import com.skillerapp.skillertutor.model.utils.DatabaseAccessibleObject;

import java.io.Serializable;

public final class RecyclerViewAdapter {

    private Context context;
    private String itemName;
    private DatabaseAccessibleObject object;
    private ViewModelInterface viewModel;
    private int layoutHeight;

    public RecyclerViewAdapter(Context context, String itemName) {
        this.context = context;
        this.itemName = itemName;
    }

    public RecyclerViewAdapter(Context context, String itemName, DatabaseAccessibleObject object) {
        this.context = context;
        this.itemName = itemName;
        this.object = object;
    }

    public void adaptHeight(final ViewGroup toBeAdaptedLayout, final ViewGroup adaptedLayout) {
        ViewTreeObserver vtoPrimary = adaptedLayout.getViewTreeObserver();
        vtoPrimary.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                layoutHeight = adaptedLayout.getMeasuredHeight();
                ViewGroup.LayoutParams paramsSecondary = toBeAdaptedLayout.getLayoutParams();
                paramsSecondary.height = layoutHeight;
                toBeAdaptedLayout.setLayoutParams(paramsSecondary);

                adaptedLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                toBeAdaptedLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void addUndoOption(ImageButton button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                viewModel = Factory.detectViewModel(itemName);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(StringsConstants.Deletion.ASK_TO_DELETE);
                builder.setPositiveButton(StringsConstants.YES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar
                                .make(view, itemName + StringsConstants.Deletion.MSG_DELETED, Snackbar.LENGTH_LONG)
                                .setAction(StringsConstants.UNDO, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (viewModel != null)
                                            viewModel.writeToDatabase(object, object.getDatabaseReference());
                                    }
                                })
                                .show();
                        if (viewModel != null)
                            viewModel.removeFromDatabase(object.getDatabaseReference());
                    }
                });
                builder.setNegativeButton(StringsConstants.CANCEL, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void addEditOption(ImageButton button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Factory.detectIntent(itemName));
                if (object != null)
                    intent.putExtra(itemName, (Serializable) object);
                context.startActivity(intent);
            }
        });
    }

    public void addFoldingCellToggleOption(final FoldingCell foldingCell) {
        foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell.toggle(false);
            }
        });
    }

    public void addMargin(ViewGroup item, int marginSize) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) item.getLayoutParams();
        int marginSizeDP = convertToDP(marginSize);
        marginLayoutParams.setMargins(marginSizeDP, marginSizeDP, marginSizeDP, marginSizeDP);
        item.setLayoutParams(marginLayoutParams);
    }

    public void addMarginStart(ViewGroup item, int marginSize) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) item.getLayoutParams();
        marginLayoutParams.setMarginStart(convertToDP(marginSize));
        item.setLayoutParams(marginLayoutParams);
    }

    public void addMarginEnd(ViewGroup item, int marginSize) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) item.getLayoutParams();
        marginLayoutParams.setMarginEnd(convertToDP(marginSize));
        item.setLayoutParams(marginLayoutParams);
    }

    public void addMarginTop(ViewGroup item, int marginSize) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) item.getLayoutParams();
        marginLayoutParams.topMargin = convertToDP(marginSize);
        item.setLayoutParams(marginLayoutParams);
    }

    public void addMarginBottom(ViewGroup item, int marginSize) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) item.getLayoutParams();
        marginLayoutParams.bottomMargin = convertToDP(marginSize);
        item.setLayoutParams(marginLayoutParams);
    }

    private int convertToDP(int dimension) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimension, context.getResources().getDisplayMetrics());
    }
}
