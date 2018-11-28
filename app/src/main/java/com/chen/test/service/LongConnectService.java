package com.chen.test.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.chen.test.longconnect.ConnectManager;

/**
 * Created by chenxianglin on 2018/5/29.
 * Class note:
 */

public class LongConnectService extends Service {
    public static final String TAG = "LongConnectService";

    public LongConnectService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private ConnectThread connectThread;

    @Override
    public void onCreate() {
        super.onCreate();
        //使用子线程开启连接
        connectThread = new ConnectThread("mina", getApplicationContext());
        connectThread.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        connectThread.disConnection();
        connectThread = null;
    }

    /**
     * 负责调用connectmanager类来完成与服务器的连接
     */
    class ConnectThread extends HandlerThread {

        boolean isConnection;
        ConnectManager mManager;

        public ConnectThread(String name, Context context) {
            super(name);
            //创建配置文件类
            ConnectManager.ConnectConfig config = new ConnectManager.ConnectConfig.Builder(context)
                    .setIp("192.168.2.30")
                    .setPort(9123)
                    .setReadBufferSize(10240)
                    .setConnectionTimeout(10000)
                    .build();
            //创建连接的管理类
            mManager = new ConnectManager(config);
        }

        @Override
        protected void onLooperPrepared() {
            //利用循环请求连接
            while (true) {
                isConnection = mManager.connect();
                if (isConnection) {
                    //当请求成功的时候,跳出循环
                    break;
                }
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {

                }
            }
        }

        /**
         * 断开连接
         */
        public void disConnection() {
            mManager.disConnect();
        }
    }
}
