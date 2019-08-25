package com.dasu.activityanimationdemo.utils;

public class LogUtils {
    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;

    public static final int LEVEL = VERBOSE;
    public static boolean logVerbose = false;

    public static void v(String tag, String msg) {
        if (logVerbose) {
            android.util.Log.v("verbose/" + tag, "" + msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG >= LEVEL) {
            android.util.Log.d(tag, "" + msg);
        }
    }

    public static void i(String tag, String msg) {
        if (INFO >= LEVEL) {
            android.util.Log.i(tag, "" + msg);
        }
    }

    public static void w(String tag, String msg) {
        if (WARN >= LEVEL) {
            android.util.Log.w(tag, "" + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (ERROR >= LEVEL) {
            android.util.Log.e(tag, "" + msg);
        }
    }
}
