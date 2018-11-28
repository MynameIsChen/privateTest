package com.chen.common.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.common.R;

import org.json.JSONObject;

public class ToastUtil {
    private static Context sContext;
    private static int resInfo = R.drawable.tost_ic_svstatus_info;
    private static int resSuccess = R.drawable.tost_ic_svstatus_success;
    private static int resError = R.drawable.tost_ic_svstatus_error;
    public static final int SHORT_DELAY = 1500; // 1.5 seconds

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    /**
     * 1秒 错误信息提示
     */
    public static void showErrorMsg(Context context, JSONObject errorResponse) {
        showErrorMsg(errorResponse);
    }

    /**
     * 1秒 错误信息提示
     */
    public static void showErrorMsg(JSONObject errorResponse) {
        if (errorResponse != null) {
            int code = errorResponse.optInt("status");
            showMessage(errorResponse.optString("msg"), resError, View.VISIBLE, SHORT_DELAY);
        }
    }

    /**
     * 1秒 错误信息提示
     */
    public static void showErrorMsg(int str) {
        showMessage(str, resError, View.VISIBLE, Gravity.CENTER, SHORT_DELAY);
    }

    /**
     * 1秒 错误信息提示
     */
    public static void showErrorMsg(Context context, int str) {
        showErrorMsg(str);
    }


    /**
     * 1秒 错误信息提示
     */
    public static void showErrorMsg(Context context, String str) {
        showErrorMsg(str);

    }

    /**
     * 1秒 错误信息提示
     */
    public static void showErrorMsg(String str) {
        showMessage(str, resError, View.VISIBLE, SHORT_DELAY);
    }

    /**
     * 1秒 信息提示
     */
    public static void showInfoMsg(Context context, String str) {
        showInfoMsg(str);
    }

    /**
     * 1秒 信息提示
     */
    public static void showInfoMsg(String str) {
        showMessage(str, resInfo, View.VISIBLE, SHORT_DELAY);
    }

    /**
     * 1秒 错误信息提示
     */
    public static void showSuccessMsg(JSONObject res) {
        if (res != null) {
            int code = res.optInt("status");
            showMessage(res.optString("msg"), resSuccess, View.VISIBLE, SHORT_DELAY);
        }
    }

    /**
     * 1秒 成功信息提示
     */
    public static void showSuccessMsg(Context context, String str) {
        showSuccessMsg(str);

    }

    /**
     * 1秒 成功信息提示
     */
    public static void showSuccessMsg(String str) {
        showMessage(str, resSuccess, View.VISIBLE, SHORT_DELAY);
    }

    /**
     * 1秒 成功信息提示
     */
    public static void showSuccessMsg(int str) {
        showMessage(str, resSuccess, View.VISIBLE, Gravity.CENTER, SHORT_DELAY);

    }

    private static Handler handler = new Handler();
    private static Runnable runnable;

    /**
     * 延迟2秒关闭Activity
     */
    public static void closeHandlerActivity(final Activity context) {
        if (runnable == null) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    ActivityUtil.goBack(context);
                    if (handler != null && runnable != null) {
                        handler.removeCallbacks(runnable);
                        runnable = null;
                    }
                }
            };
            handler.postDelayed(runnable, SHORT_DELAY + 500);
        }
    }

    /**
     * 延迟2秒关闭Activity
     */
    public static void closeHandlerActivity(final Activity context, final int code) {
        if (runnable == null) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    ActivityUtil.goBackWithResult(context, code);
                    if (handler != null && runnable != null) {
                        handler.removeCallbacks(runnable);
                        runnable = null;
                    }
                }
            };
            handler.postDelayed(runnable, SHORT_DELAY + 500);
        }
    }

    private static Handler mhandler = new Handler(Looper.getMainLooper());

    private static Toast toast = null;
    private static View view = null;
    private static TextView tv = null;
    private static ImageView ic = null;

    private static Object synObj = new Object();

    /**
     * 显示1.5秒消息在底部显示，没有图标
     */
    public static void showMsg(final String msg) {
        showMessage(msg, resSuccess, View.GONE, Gravity.BOTTOM, SHORT_DELAY);
    }

    public static void showMsg(final String msg, int time) {
        showMessage(msg, resSuccess, View.GONE, Gravity.BOTTOM, time);
    }

    /**
     * 显示1.5秒消息在底部显示，没有图标
     */
    public static void showMsg(final JSONObject errorResponse) {
        if (errorResponse != null) {
            int code = errorResponse.optInt("status");
            if (code != 125 || code != 107) {
                showMessage(errorResponse.optString("msg"), resSuccess, View.GONE, Gravity.BOTTOM, SHORT_DELAY);
            }
        }
    }

    /**
     * 显示1.5秒消息在底部显示，没有图标
     */
    public static void showMsg(final int msg) {
        showMessage(msg, resSuccess, View.GONE, Gravity.BOTTOM, SHORT_DELAY);
    }

    public static void showMessage(final CharSequence msg, final int res, final int isvis, final int len) {
        showMessage(msg, res, isvis, Gravity.CENTER, len);
    }

    /**
     * 资源文件方式显示文本
     *
     * @param msg   提示信息
     * @param res   提示图片
     * @param isvis 是否显示图片
     * @param len   显示时间长度
     */
    public static void showMessage(final CharSequence msg, final int res, final int isvis, final int gravity, final int len) {
        if (msg == null || msg.equals("")) {
            return;
        }
        mhandler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (synObj) { //加上同步是为了每个toast只要有机会显示出来
                    if (toast != null) {
                        //toast.cancel();
                        ic.setImageResource(res);
                        ic.setVisibility(isvis);
                        if (isvis == View.GONE) {
                            toast.setGravity(gravity, 0, sContext.getResources().getDimensionPixelSize(
                                    R.dimen.dp_64));
                        } else {
                            toast.setGravity(gravity, 0, 0);
                        }
                        tv.setText(msg);
                        toast.setView(view);
                        toast.setDuration(len);
                    } else {
                        //toast = Toast.makeText(sContext, msg, len);
                        toast = new Toast(sContext);
                        view = View.inflate(sContext, R.layout.common_toast, null);
                        tv = (TextView) view.findViewById(R.id.tvMsg);
                        ic = (ImageView) view.findViewById(R.id.ivBigLoading);
                        if (isvis == View.GONE) {
                            toast.setGravity(gravity, 0, sContext.getResources().getDimensionPixelSize(
                                    R.dimen.dp_64));
                        } else {
                            toast.setGravity(gravity, 0, 0);
                        }
                        ic.setVisibility(isvis);
                        ic.setImageResource(res);
                        tv.setText(msg);
                        toast.setView(view);
                    }
                    toast.show();
                }
            }
        });
    }


    /**
     * 资源文件方式显示文本
     *
     * @param msg   提示信息
     * @param res   提示图片
     * @param isvis 是否显示图片
     * @param len   显示时间长度
     */
    private static void showMessage(final int msg, final int res, final int isvis, final int gravity, final int len) {
        mhandler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (synObj) {
                    if (toast != null) {
                        //toast.cancel();
                        tv.setText(msg);
                        ic.setVisibility(isvis);
                        if (isvis == View.GONE) {
                            toast.setGravity(gravity, 0, sContext.getResources().getDimensionPixelSize(
                                    R.dimen.dp_64));
                        } else {
                            toast.setGravity(gravity, 0, 0);
                        }
                        ic.setImageResource(res);
                        toast.setView(view);
                        toast.setDuration(len);
                    } else {
                        toast = new Toast(sContext);
                        view = View.inflate(sContext, R.layout.common_toast, null);
                        tv = (TextView) view.findViewById(R.id.tvMsg);
                        ic = (ImageView) view.findViewById(R.id.ivBigLoading);
                        if (isvis == View.GONE) {
                            toast.setGravity(gravity, 0, sContext.getResources().getDimensionPixelSize(
                                    R.dimen.dp_64));
                        } else {
                            toast.setGravity(gravity, 0, 0);
                        }
                        ic.setVisibility(isvis);
                        tv.setText(msg);
                        toast.setView(view);
                        //toast = Toast.makeText(sContext, msg, len);
                    }
                    toast.show();
                }
            }
        });
    }

}
