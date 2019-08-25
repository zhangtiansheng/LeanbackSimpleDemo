package com.dasu.activityanimationdemo.presenter;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseMvpView> {

    private WeakReference<V> mViewReference = null;

    protected void attachView(V view) {
        mViewReference = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewReference == null ? null : mViewReference.get();
    }
}
