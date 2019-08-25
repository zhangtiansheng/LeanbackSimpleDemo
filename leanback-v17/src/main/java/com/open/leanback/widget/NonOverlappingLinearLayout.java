package com.open.leanback.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class NonOverlappingLinearLayout extends LinearLayout {

    boolean mFocusableViewAvailableFixEnabled = false;
    boolean mDeferFocusableViewAvailableInLayout;
    final ArrayList<ArrayList<View>> mSortedAvailableViews = new ArrayList();


    public NonOverlappingLinearLayout(Context context) {
        this(context, null);
    }

    public NonOverlappingLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NonOverlappingLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Avoids creating a hardware layer when animating alpha.
     */
    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }

    public void setFocusableViewAvailableFixEnabled(boolean enabled) {
        mFocusableViewAvailableFixEnabled = enabled;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        try {
            mDeferFocusableViewAvailableInLayout = mFocusableViewAvailableFixEnabled
                    && getOrientation() == HORIZONTAL
                    && getLayoutDirection() == LAYOUT_DIRECTION_RTL;
            if (mDeferFocusableViewAvailableInLayout) {
                while (mSortedAvailableViews.size() > getChildCount()) {
                    mSortedAvailableViews.remove(mSortedAvailableViews.size() - 1);
                }
                while (mSortedAvailableViews.size() < getChildCount()) {
                    mSortedAvailableViews.add(new ArrayList());
                }
            }
            super.onLayout(changed, l, t, r, b);
            if (mDeferFocusableViewAvailableInLayout) {
                for (int i = 0; i < mSortedAvailableViews.size(); i++) {
                    for (int j = 0; j < mSortedAvailableViews.get(i).size(); j++) {
                        super.focusableViewAvailable(mSortedAvailableViews.get(i).get(j));
                    }
                }
            }
        } finally {
            if (mDeferFocusableViewAvailableInLayout) {
                mDeferFocusableViewAvailableInLayout = false;
                for (int i = 0; i < mSortedAvailableViews.size(); i++) {
                    mSortedAvailableViews.get(i).clear();
                }
            }
        }
    }

    @Override
    public void focusableViewAvailable(View v) {
        if (mDeferFocusableViewAvailableInLayout) {
            View i = v;
            int index = -1;
            while (i != this && i != null) {
                if (i.getParent() == this) {
                    index = indexOfChild(i);
                    break;
                }
                i = (View) i.getParent();
            }
            if (index != -1) {
                mSortedAvailableViews.get(index).add(v);
            }
        } else {
            super.focusableViewAvailable(v);
        }
    }
}