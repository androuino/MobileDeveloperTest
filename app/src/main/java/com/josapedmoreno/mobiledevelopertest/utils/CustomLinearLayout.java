package com.josapedmoreno.mobiledevelopertest.utils;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import timber.log.Timber;

public class CustomLinearLayout extends LinearLayoutManager {
    private static final String TAG = CustomLinearLayout.class.getName();

    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            Timber.tag(TAG).e("Error on " + TAG + "->onLayoutChildren()");
        }
    }
}
