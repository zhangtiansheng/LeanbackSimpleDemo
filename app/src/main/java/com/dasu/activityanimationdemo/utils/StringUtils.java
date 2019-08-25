package com.dasu.activityanimationdemo.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class StringUtils {

    /**
     * 读取assets文件
     *
     * @param fileName
     * @param context
     * @return
     */
    public static String readAssetsFile(String fileName, Context context) {
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer, "utf-8");
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
