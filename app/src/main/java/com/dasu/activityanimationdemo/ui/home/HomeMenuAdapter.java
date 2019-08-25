package com.dasu.activityanimationdemo.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dasu.activityanimationdemo.R;
import com.dasu.activityanimationdemo.mode.home.LayoutMenu;
import com.dasu.activityanimationdemo.utils.LogUtils;

import java.util.List;
import java.util.Random;

public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.ViewHolder>{
    private static final String TAG = HomeMenuAdapter.class.getSimpleName();
    private Context mContext;
    private List<LayoutMenu> mLayoutMenus;
    private onMenuItemClickListener mItemClickListener;

    public HomeMenuAdapter(List<LayoutMenu> menuList) {
        mLayoutMenus = menuList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtils.v(TAG, "onCreateViewHolder(), parent children count: " + parent.getChildCount());
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        LogUtils.v(TAG, "onViewRecycled(), holder:" + holder);
        super.onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        LogUtils.v(TAG, "onViewAttachedToWindow(), holder:" + holder);
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        LogUtils.v(TAG, "onViewDetachedFromWindow(), holder:" + holder);
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LogUtils.v(TAG, "onBindViewHolder(), position: " + position + ", holder:" + holder);
        LayoutMenu menu = mLayoutMenus.get(position);
        //设置标题
        if (!TextUtils.isEmpty(menu.getMenuName())) {
            holder.mMenuLayout.setTitleVisible(View.VISIBLE);
            holder.mMenuLayout.setTitleText(menu.getMenuName());
        } else {
            holder.mMenuLayout.setTitleVisible(View.GONE);
        }

        //设置卡位栏
        if (menu.getElementList().size() > 0) {
            holder.mMenuLayout.setMenusLayout(menu.getElementList());
            holder.mMenuLayout.setOnItemClickListener(onItemClickListener(position));
        } else {
            holder.mMenuLayout.hideMenusGridView();
        }
    }

    private HomeContentView.onItemClickListener onItemClickListener(final int menuPosition) {
        return new HomeContentView.onItemClickListener() {
            @Override
            public void onItemClick(View view, LayoutMenu.CardElement data, int elementPosition, int cardPosition) {
                LogUtils.v(TAG, "onMenuItemClick(), menuPos:" + menuPosition + ", elementPos:" + elementPosition + ", cardPos:" + cardPosition);
                if (mItemClickListener != null) {
                    mItemClickListener.onMenuItemClick(view, data, menuPosition, elementPosition, cardPosition);
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return mLayoutMenus != null ? mLayoutMenus.size() : 0;
    }

    public void setOnMenuItemClickListener(onMenuItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface onMenuItemClickListener {
        void onMenuItemClick(View view, LayoutMenu.CardElement data, int menuPosition, int elementPosition, int cardPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        HomeContentView mMenuLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mMenuLayout = (HomeContentView) itemView.findViewById(R.id.hcv_item_home_menu);
        }
    }

}
