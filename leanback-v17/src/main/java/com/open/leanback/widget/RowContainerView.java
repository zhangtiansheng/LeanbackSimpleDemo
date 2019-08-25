package com.open.leanback.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.open.leanback.R;

/**
 * RowContainerView wraps header and user defined row view
 */
final class RowContainerView extends LinearLayout {

    private ViewGroup mHeaderDock;
    private Drawable mForeground;
    private boolean mForegroundBoundsChanged = true;

    public RowContainerView(Context context) {
        this(context, null, 0);
    }

    public RowContainerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RowContainerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOrientation(VERTICAL);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.lb_row_container, this);

        mHeaderDock = findViewById(R.id.lb_row_container_header_dock);
        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

    public void addHeaderView(View headerView) {
        if (mHeaderDock.indexOfChild(headerView) < 0) {
            mHeaderDock.addView(headerView, 0);
        }
    }

    public void removeHeaderView(View headerView) {
        if (mHeaderDock.indexOfChild(headerView) >= 0) {
            mHeaderDock.removeView(headerView);
        }
    }

    public void addRowView(View view) {
        addView(view);
    }

    public void showHeader(boolean show) {
        mHeaderDock.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setForeground(Drawable d) {
        mForeground = d;
        setWillNotDraw(mForeground == null);
        invalidate();
    }

    public void setForegroundColor(@ColorInt int color) {
        if (mForeground instanceof ColorDrawable) {
            ((ColorDrawable) mForeground.mutate()).setColor(color);
            invalidate();
        } else {
            setForeground(new ColorDrawable(color));
        }
    }

    @Override
    public Drawable getForeground() {
        return mForeground;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mForegroundBoundsChanged = true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mForeground != null) {
            if (mForegroundBoundsChanged) {
                mForegroundBoundsChanged = false;
                mForeground.setBounds(0, 0, getWidth(), getHeight());
            }
            mForeground.draw(canvas);
        }
    }

    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }
}