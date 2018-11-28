package com.chen.common.util;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by Administrator on 2018/3/27.
 */

public class ProcessUtils
{
  /**
   * 包名判断是否为主进程
   */
  public static boolean isMainProcess(Context context)
  {
    return context.getPackageName().equals(getCurProcessName(context));
  }

  public static String getCurProcessName(Context context)
  {
    int pid = android.os.Process.myPid();
    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    if (activityManager != null)
      for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses())
        if (appProcess.pid == pid)
          return appProcess.processName;
    return null;
  }
}
