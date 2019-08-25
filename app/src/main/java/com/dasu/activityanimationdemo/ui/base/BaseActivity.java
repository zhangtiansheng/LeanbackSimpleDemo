package com.dasu.activityanimationdemo.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.dasu.activityanimationdemo.utils.LogUtils;

import java.util.Random;

public abstract class BaseActivity extends Activity {

    protected final String TAG = this.getClass().getSimpleName() + ":" + (new Random().nextInt(100));
    private boolean isInterceptKeyEventHandleSelf = false;
    private boolean isInterceptKeyEvent = false;
    private boolean isStopDispatchKeyEvent = false;
    private long mCreateTime = 0;
    protected Context mContext;

    static {
        LogUtils.logVerbose = true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mCreateTime = System.currentTimeMillis();
        LogUtils.v(TAG, "onCreate(), start time: " + mCreateTime);

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.v(TAG, "onStart(), current time: " + System.currentTimeMillis() + ", differ onCreate: " + (System.currentTimeMillis() - mCreateTime));
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.v(TAG, "onResume(), current time: " + System.currentTimeMillis() + ", differ onCreate: " + (System.currentTimeMillis() - mCreateTime));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.v(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.v(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.v(TAG, "onDestroy()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.v(TAG, "onNewIntent()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.v(TAG, "onRestart()");
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtils.v(TAG, "onKeyDown(), event: " + event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        LogUtils.v(TAG, "onKeyUp(), event: " + event);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        LogUtils.v(TAG, "onKeyLongPress(), event: " + event);
        return super.onKeyLongPress(keyCode, event);
    }

    protected final void interceptKeyEvent() {
        interceptKeyEvent(true);
    }

    protected final void interceptKeyEvent(boolean handleSelf) {
        isInterceptKeyEventHandleSelf = handleSelf;
        isInterceptKeyEvent = true;
        LogUtils.v(TAG, "interceptKeyEvent(), handle self:" + handleSelf);
    }

    protected void onDispatchKeyEvent(KeyEvent event) {}

    public void stopDispatchKeyEvent(boolean stop) {
        isStopDispatchKeyEvent = stop;
    }

    /**
     * 增加KeyEvent的拦截：一次性拦截所有后续KeyEvent or 只拦截一次KeyEvent
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        LogUtils.v(TAG, "dispatchKeyEvent(), event: " + event);
        if (isStopDispatchKeyEvent) {
            return true;
        }
        onDispatchKeyEvent(event);
        int keyAction = event.getAction();
        if (keyAction == KeyEvent.ACTION_DOWN) {
            if (isInterceptKeyEventHandleSelf) {
                return onKeyDown(event.getKeyCode(), event);
            } else if (isInterceptKeyEvent) {
                return true;
            }
        } else if (keyAction == KeyEvent.ACTION_UP) {
            boolean isIntercept = false;
            if (isInterceptKeyEventHandleSelf) {
                isIntercept = onKeyUp(event.getKeyCode(), event);
            } else if (isInterceptKeyEvent) {
                isIntercept = true;
            }
            isInterceptKeyEvent = false;
            isInterceptKeyEventHandleSelf = false;
            if (isIntercept) {
                return isIntercept;
            }
        }
        return super.dispatchKeyEvent(event);
    }

}
