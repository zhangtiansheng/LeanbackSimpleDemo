package com.dasu.activityanimationdemo.ui.home;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dasu.activityanimationdemo.R;
import com.dasu.activityanimationdemo.mode.home.LayoutMenu;
import com.dasu.activityanimationdemo.ui.view.base.AbsItemRecyclerAdapter;
import com.dasu.activityanimationdemo.utils.LogUtils;

import java.util.List;

public class HomeMenuItemAdapter extends AbsItemRecyclerAdapter<LayoutMenu.CardElement> {


    public HomeMenuItemAdapter(List<LayoutMenu.CardElement> data) {
        super(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtils.v(TAG, "onCreateViewHolder(), parent children count: " + parent.getChildCount());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_menu_item, parent, false);
        view.setOnFocusChangeListener(onItemFocusChanged());
        return new ViewHolder(view);
    }

    @Override
    protected void onBindView(RecyclerView.ViewHolder holder, LayoutMenu.CardElement data, int position) {

        final ViewHolder vh = (ViewHolder) holder;

        vh.mTextName.setText(data.getElementName());

        Glide.with(vh.mImagePic.getContext())
                .load(data.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .placeholder(R.drawable.a2)
                .fallback(R.drawable.a2)
                .error(R.mipmap.ic_launcher)
                .into(vh.mImagePic);
    }

    private View.OnFocusChangeListener onItemFocusChanged() {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    onItemFocus(v);
                } else {
                    onItemUnFocus(v);
                }
            }
        };
    }

    private void onItemFocus(View v) {

    }

    private void onItemUnFocus(View v) {

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImagePic;
        public TextView mTextName;

        public ViewHolder(View itemView) {
            super(itemView);
            mImagePic = (ImageView) itemView.findViewById(R.id.mImagePic);
            mTextName = (TextView) itemView.findViewById(R.id.mTextName);
        }
    }
}
