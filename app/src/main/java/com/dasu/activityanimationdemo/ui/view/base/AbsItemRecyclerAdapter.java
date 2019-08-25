package com.dasu.activityanimationdemo.ui.view.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract  class AbsItemRecyclerAdapter<T> extends AbsRecyclerAdapter<T> {

    public AbsItemRecyclerAdapter(List<T> data) {
        super(data);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener();
        onFocusChangeListener.mChainedListener = holder.itemView.getOnFocusChangeListener();
        holder.itemView.setOnFocusChangeListener(onFocusChangeListener);
    }

    final class OnFocusChangeListener implements View.OnFocusChangeListener {
        View.OnFocusChangeListener mChainedListener;

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
//            LogUtils.v(TAG, "onFocusChange(), hasFocus: " + hasFocus + ", view:" + v);
            if (mChainedListener != null) {
                mChainedListener.onFocusChange(v, hasFocus);
            }
            onItemFocused(v, hasFocus);
        }

        private void onItemFocused(View v, boolean hasFocus) {
            v.animate().cancel();
            if (hasFocus) {
                v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start();
            } else {
                v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
            }
        }
    }
}
