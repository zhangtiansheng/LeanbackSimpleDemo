package com.dasu.activityanimationdemo.ui.home;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dasu.activityanimationdemo.R;
import com.dasu.activityanimationdemo.mode.home.LayoutMenu;
import com.dasu.activityanimationdemo.ui.view.base.AbsRecyclerAdapter;
import com.dasu.activityanimationdemo.utils.ScreenUtils;
import com.open.leanback.widget.HorizontalGridView;

import java.util.List;


public class HomeContentView extends LinearLayout {

    private Context mContext;
    private TextView mTitleTv;
    private onItemClickListener mItemClickListener;
    private SparseArray<HorizontalGridView> mMenusGridView = new SparseArray<>();
    private int mVerticalSpacing = 30;

    public HomeContentView(Context context) {
        super(context);
        initView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        setOrientation(VERTICAL);
        setClipChildren(false);
        setClipToPadding(false);
    }

    public HomeContentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        setClipChildren(false);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        mTitleTv = new TextView(mContext);
        mTitleTv.setPadding(0, 0, 0, 20);
        mTitleTv.setTextColor(mContext.getResources().getColor(R.color.lb_tv_white));
        mTitleTv.setTextSize(20);
        addView(mTitleTv);
        removeView(mTitleTv);
        addView(mTitleTv);
    }

    private AbsRecyclerAdapter.OnItemClickListener<LayoutMenu.CardElement> onMenuItemClick(final int elementPos) {
        return new AbsRecyclerAdapter.OnItemClickListener<LayoutMenu.CardElement>() {
            @Override
            public void onItemClick(View view, int position, LayoutMenu.CardElement data) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(view, data, elementPos, position);
                }
            }
        };
    }

    public void setTitleText(String text) {
        if (mTitleTv != null) {
            mTitleTv.setText(text);
        }
    }

    public void setTitleVisible(int visibility) {
        if (mTitleTv == null) {
            return;
        }
        mTitleTv.setVisibility(visibility);
    }

    private boolean isElementEmpty(LayoutMenu.Element element) {
        if (element == null || element.getCardList() == null || element.getCardList().size() == 0) {
            return true;
        }
        return false;
    }

    public void setMenusLayout(List<LayoutMenu.Element> menusLayout) {
        if (menusLayout == null || menusLayout.size() == 0) {
            return;
        }
        int menusCount = menusLayout.size();
        for (int i = 0; i < menusCount; i++) {
            LayoutMenu.Element element = menusLayout.get(i);
            if (isElementEmpty(element)) {
                continue;
            }
            HorizontalGridView menuGridView = getMenuGridView(i);
            HomeMenuItemAdapter menuItemAdapter = new HomeMenuItemAdapter(element.getCardList());
            menuItemAdapter.setOnItemClickListener(onMenuItemClick(i));
            menuGridView.setAdapter(menuItemAdapter);
            if (i < (menusCount - 1)) {
                menuGridView.setPadding(0, 0, 0, mVerticalSpacing);
            }
            addView(menuGridView);
        }
    }

    private HorizontalGridView getMenuGridView(int position) {
        HorizontalGridView menuGridView = mMenusGridView.get(position);
        if (menuGridView == null) {
            menuGridView = new HorizontalGridView(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = ScreenUtils.dip2px(mContext, 200f);
            menuGridView.setLayoutParams(params);
            menuGridView.setClipChildren(false);
            menuGridView.setClipToPadding(false);
            menuGridView.setHorizontalSpacing(15);
            mMenusGridView.put(position, menuGridView);
        } else {
            if (menuGridView.getParent() != null) {
                ((ViewGroup)menuGridView.getParent()).removeView(menuGridView);
            }
        }
        return menuGridView;
    }

    public void setVerticalMenuSpacing(int spacing) {
        mVerticalSpacing = spacing;
    }

    public void hideMenusGridView() {
        this.removeViews(1, getChildCount() - 1);
    }


    public void setOnItemClickListener(onItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface onItemClickListener {
        void onItemClick(View view, LayoutMenu.CardElement data, int elementPosition, int cardPosition);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
