package com.chen.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by chenxianglin on 2018/1/17.
 * Class note:
 */

public class NetUtil {
    /**
     * isConnectNet
     *
     * @return Object 返回对象描述
     * @Exception 异常描述
     */
    public static boolean isConnectNet(Context context) {
        if (context == null)
            return false;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo.isConnected();

        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn;
        if (null != networkInfo) {
            isMobileConn = networkInfo.isConnected();
        } else {
            isMobileConn = false;
        }
        return isWifiConn || isMobileConn;
    }

}
