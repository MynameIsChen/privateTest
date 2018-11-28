package com.chen.common.util;

import android.util.Log;

public class Lg {
    private static boolean ISSHOW = false;
    private static String APP_TAG = "网信贷";
    public Lg() {
    }

    public static void setDebugMode(boolean isShow) {
        ISSHOW = isShow;
    }

    public static void d(String msg) {
        d("", msg);
    }

    public static void e(String msg) {
        e("", msg);
    }

    public static void i(String msg) {
        i("", msg);
    }

    public static void w(String msg) {
        w("", msg);
    }

    public static void d(String tag, String msg) {
        if(ISSHOW) {
            Log.d(">>>" + APP_TAG + "<<< "+tag, " >> " + msg);
        }
    }

    public static void e(String tag, String msg) {
        if(ISSHOW) {
            Log.e(">>>" + APP_TAG + "<<< "+tag, " >> " + msg);
        }

    }

    public static void i(String tag, String msg) {
        if(ISSHOW) {
            Log.i(">>>" + APP_TAG + "<<< "+tag, " >> " + msg);
        }

    }

    public static void w(String tag, String msg) {
        if(ISSHOW) {
            Log.w(">>>" + APP_TAG + "<<< "+tag, " >> " + msg);
        }

    }

    public static void w(String tag, String msg, Throwable exc) {
        if(ISSHOW) {
            Log.w(">>>" + APP_TAG + "<<< "+tag, " >> " + msg, exc);
        }
    }

    public static void e(String tag, String msg, Throwable exc) {
        if(ISSHOW) {
            Log.e(">>>" + APP_TAG + "<<< "+tag, " >> " + msg, exc);
        }
    }

    public static void i(String tag, String msg, Throwable exc) {
        if(ISSHOW) {
            Log.i(">>>" + APP_TAG + "<<< "+tag, " >> " + msg, exc);
        }
    }

    public static void d(String tag, String msg, Object... args) {
        if(ISSHOW) {
            Log.d(">>>" + APP_TAG + "<<< " + tag, " >> " + String.format(msg, args));
        }
    }
}
