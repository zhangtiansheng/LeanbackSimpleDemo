package com.dasu.activityanimationdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtils {

    // 获取屏幕密度
    static public float getDensity(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）

        return density;
    }

    // dip->px
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    // px->dip
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    // 获取屏幕高度
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(dm);

        return dm.heightPixels;
    }

    // 获取屏幕宽度
    public static int getScreenWidth(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    public  static void showDisplayInfo(Activity activity){
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        // 屏幕宽度（像素）
        int width = metric.widthPixels;
        // 屏幕高度（像素）
        int height = metric.heightPixels;
        // 屏幕密度（1.0 / 1.5 / 2.0）
        float density = metric.density;
        // 屏幕密度DPI（160 / 240 / 320）
        int densityDpi = metric.densityDpi;
        String info = "机顶盒型号: " + android.os.Build.MODEL + ",\nSDK版本:"
                + android.os.Build.VERSION.SDK + ",\n系统版本:"
                + android.os.Build.VERSION.RELEASE  + "\n屏幕宽度（像素）: "
                + width + "\n屏幕高度（像素）: " + height
                + "\n屏幕密度:  "
                + density
                + "\n屏幕密度DPI: " + densityDpi;
        LogUtils.i("tag_ss_system", "System INFO = " + info);
    }
}
