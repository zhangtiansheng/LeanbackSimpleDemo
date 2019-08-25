package com.dasu.activityanimationdemo.mode.mock;


import android.content.Context;
import android.text.TextUtils;

import com.dasu.activityanimationdemo.mode.home.LayoutMenu;
import com.dasu.activityanimationdemo.utils.LogUtils;
import com.dasu.activityanimationdemo.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MockLayoutMenu {

    private static final String TAG = MockLayoutMenu.class.getSimpleName();

    public static List<LayoutMenu> mock(Context mContext) {
        String json = StringUtils.readAssetsFile("home1.json", mContext);
        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<LayoutMenu>>(){}.getType();
            ArrayList<LayoutMenu> layoutMenus1 = gson.fromJson(json, type);
            return layoutMenus1;
        } else {
            return null;
        }
    }

}
