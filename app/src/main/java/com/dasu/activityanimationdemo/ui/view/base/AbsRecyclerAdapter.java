package com.dasu.activityanimationdemo.ui.view.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dasu.activityanimationdemo.utils.LogUtils;

import java.util.List;
import java.util.Random;

public abstract class AbsRecyclerAdapter<T> extends RecyclerView.Adapter {
    protected final String TAG = this.getClass().getSimpleName() + ":" + (new Random().nextInt(100));
    protected List<T> mDataList;
    private  OnItemClickListener mItemClickListener;

    public AbsRecyclerAdapter(List<T> data) {
        mDataList = data;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LogUtils.v(TAG, "onBindViewHolder(), position: " + position + ", holder:" + holder);
        final int pos = position;
        final RecyclerView.ViewHolder vholer = holder;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, pos, mDataList.get(pos));
                }
            }
        });
        onBindView(holder, mDataList.get(pos), position);
    }

    protected abstract void onBindView(RecyclerView.ViewHolder holder,T data, int position);

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        mItemClickListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T data);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        LogUtils.v(TAG, "onViewRecycled(), holder:" + holder);
        super.onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        LogUtils.v(TAG, "onViewAttachedToWindow(), holder:" + holder);
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        LogUtils.v(TAG, "onViewDetachedFromWindow(), holder:" + holder);
        super.onViewDetachedFromWindow(holder);
    }

}
