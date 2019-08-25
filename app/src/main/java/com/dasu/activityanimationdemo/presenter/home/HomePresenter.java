package com.dasu.activityanimationdemo.presenter.home;

import android.content.Context;

import com.dasu.activityanimationdemo.mode.mock.MockLayoutMenu;
import com.dasu.activityanimationdemo.presenter.BasePresenter;


public class HomePresenter extends BasePresenter<HomeMvpView> {

    private Context mContext;

    public HomePresenter(Context context, HomeMvpView view) {
        mContext = context;
        attachView(view);
    }

    public void loadData() {
        getView().mvpUpdateHomeLayout(MockLayoutMenu.mock(mContext));
    }
}
