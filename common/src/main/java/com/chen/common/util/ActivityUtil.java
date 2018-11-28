package com.chen.common.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.chen.common.R;

import java.util.List;

public class ActivityUtil {
    public static final int REQUESTCODE_TABACTIVITY_BASE = 1000;

    /**
     * 跳转到下一个页面
     *
     * @param curActivity
     * @param nextActivity
     */
    public static void next(Activity curActivity, Class nextActivity) {
        next(curActivity, nextActivity, null, -1, R.anim.in_from_right, R.anim.out_to_right);
    }

    public static void next(Activity curActivity, Class nextActivity, int reqCode) {
        next(curActivity, nextActivity, null, reqCode, R.anim.in_from_right, R.anim.out_to_right);
    }

    public static void next(Activity curActivity, Class nextActivity, Bundle extras) {
        next(curActivity, nextActivity, extras, -1, R.anim.in_from_right, R.anim.out_to_right);
    }

    public static void next(Activity curActivity, Class nextActivity, Bundle extras, int reqCode) {
        next(curActivity, nextActivity, extras, reqCode, R.anim.in_from_right, R.anim.out_to_right);
    }


    /**
     * 跳转到下一个页面
     *
     * @param curActivity
     * @param nextActivity
     * @param inAnimId
     * @param outAnimId
     */
    public static void next(Activity curActivity, Class nextActivity, int inAnimId, int outAnimId) {
        next(curActivity, nextActivity, null, -1, inAnimId, outAnimId);
    }

    /**
     * 跳转到下一个页面
     *
     * @param curActivity
     * @param nextActivity
     * @param extras
     * @param reqCode
     * @param inAnimId
     * @param outAnimId
     */
    public static void next(Activity curActivity, Class nextActivity, Bundle extras, int reqCode, int inAnimId,
                            int outAnimId) {
        Intent intent = new Intent(curActivity, nextActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//保持一个实例
        if (null != extras) {
            intent.putExtras(extras);
        }
        if (reqCode < 0) {
            curActivity.startActivity(intent);
        } else {
            curActivity.startActivityForResult(intent, reqCode);
        }
        /**
         * 2015-8-8 注释  系统所以打开都从right -left
         */
        curActivity.overridePendingTransition(inAnimId, outAnimId);
    }


    public static void next(@NonNull Fragment fragment, Class nextActivity, Bundle extras, int reqCode) {
        next(fragment, nextActivity, extras, reqCode, R.anim.in_from_right, R.anim.out_to_right);
    }

    /**
     * 跳转到下一个页面
     *
     * @param fragment
     * @param nextActivity
     * @param extras
     * @param reqCode
     * @param inAnimId
     * @param outAnimId
     */
    public static void next(@NonNull Fragment fragment, Class nextActivity, Bundle extras, int reqCode, int inAnimId,
                            int outAnimId) {
        Intent intent = new Intent(fragment.getActivity(), nextActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//保持一个实例
        if (null != extras) {
            intent.putExtras(extras);
        }
        if (reqCode < 0) {
            fragment.startActivity(intent);
        } else {
            fragment.startActivityForResult(intent, reqCode);
        }
        /**
         * 2015-8-8 注释  系统所以打开都从right -left
         */
        fragment.getActivity().overridePendingTransition(inAnimId, outAnimId);
    }

    /**
     * 返回到上一个页面
     *
     * @param curActivity
     */
    public static void goBack(Activity curActivity) {
        goBack(curActivity, R.anim.in_from_left, R.anim.out_to_right);
    }

    /**
     * 返回到上一个页面
     *
     * @param curActivity
     * @param inAnimId
     * @param outAnimId
     */
    public static void goBack(Activity curActivity, int inAnimId, int outAnimId) {
        curActivity.finish();
        curActivity.overridePendingTransition(inAnimId, outAnimId);
    }

    /**
     * 返回到上一个页面并返回值
     *
     * @param curActivity
     * @param retCode
     * @param retData
     */
    public static void goBackWithResult(Activity curActivity, int retCode, Bundle retData) {
        goBackWithResult(curActivity, retCode, retData, R.anim.in_from_left, R.anim.out_to_right);
    }

    /**
     * 返回到上一个页面并返回值
     *
     * @param curActivity
     * @param retCode
     * @param retData
     * @param inAnimId
     * @param outAnimId
     */
    public static void goBackWithResult(Activity curActivity, int retCode, Bundle retData, int inAnimId, int outAnimId) {
        Intent intent = null;
        intent = new Intent();
        if (null != retData) {
            intent.putExtras(retData);
        }
        curActivity.setResult(retCode, intent);
        curActivity.finish();
        curActivity.overridePendingTransition(inAnimId, outAnimId);
    }

    public static void goBackWithResult(Activity curActivity, int retCode) {
        goBackWithResult(curActivity, retCode, null, R.anim.in_from_left, R.anim.out_to_right);
    }

    /**
     * 直接返回到指定的某个页面
     *
     * @param curActivity
     * @param backActivity
     */
    public static void backActivityWithClearTop(Activity curActivity, Class backActivity) {
        backActivityWithClearTop(curActivity, backActivity, null, R.anim.in_from_left, R.anim.out_to_right);
    }


    /**
     * 直接返回到指定的某个页面
     *
     * @param curActivity
     * @param backActivity
     * @param inAnimId
     * @param outAnimId
     */
    public static void backActivityWithClearTop(Activity curActivity, Class backActivity, Bundle extras, int inAnimId,
                                                int outAnimId) {
        Intent intent = new Intent(curActivity, backActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (null != extras) {
            intent.putExtras(extras);
        }
        curActivity.startActivity(intent);
        curActivity.overridePendingTransition(inAnimId, outAnimId);
    }

    /**
     * 栈顶的activity
     *
     * @param context
     * @return
     */
    public static String getTopActivity(Activity context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        if (manager != null) {
            List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
            if (runningTaskInfos != null && runningTaskInfos.size() > 0) {
                if ((runningTaskInfos.get(0).topActivity) != null) {
                    return (runningTaskInfos.get(0).topActivity).getShortClassName();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
