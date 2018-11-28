package com.chen.common.util;

import android.content.Context;
import android.util.Log;

/**
 * Created by chenxianglin on 2018/6/1.
 * Class note:
 */

public class Lg {
    private static boolean mDebug = true;
    private static String TAG = "tag";

    public void init(Context context) {
        if (context != null) {
            TAG = context.getApplicationContext().getPackageName();
        }
    }

    public static void setTag(String tag) {
        TAG = tag;
    }

    public static void setDebug(boolean debug) {
        mDebug = debug;
    }

    public void d(String info) {
        if (mDebug) {
            Log.d(TAG, info);
        }
    }

    public void w(String info) {
        if (mDebug) {
            Log.w(TAG, info);
        }
    }

    public void e(String info) {
        if (mDebug) {
            Log.e(TAG, info);
        }
    }

    public void i(String info) {
        if (mDebug) {
            Log.i(TAG, info);
        }
    }

    public void v(String info) {
        if (mDebug) {
            Log.v(TAG, info);
        }
    }
}
