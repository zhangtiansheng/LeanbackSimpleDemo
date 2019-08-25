package com.open.leanback.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.open.leanback.R;

class NonOverlappingFrameLayout extends FrameLayout {

    public NonOverlappingFrameLayout(Context context) {
        this(context, null);
    }

    public NonOverlappingFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public NonOverlappingFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Avoid creating hardware layer when Transition is animating alpha.
     */
    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }
}