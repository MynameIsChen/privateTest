package com.chen.test.service;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.chen.core.Event.ServiceEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chenxianglin on 2018/5/24.
 * Class note:
 */

public class InitService extends IntentService {
    public static final String ACTION_INIT = "action_init";
    public static final String ACTION_STOP = "action_stop";
    public final String TAG = this.getClass().getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */

    public InitService() {
        super("InitService");
    }

    public static void startService(Context context) {
        Intent intent = new Intent(context, InitService.class);
        intent.setAction(ACTION_INIT);
        // 2018/4/4  Android8.0后不允许在后台开启服务
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            context.startForegroundService(intent);
        else
            context.startService(intent);
    }

    public static void stopService(Context context) {
        Intent intent = new Intent(context, InitService.class);
        intent.setAction(ACTION_STOP);
        context.stopService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent thread:" + Thread.currentThread() + "=action=" + intent.getAction());
        if (intent != null) {
            if (TextUtils.equals(intent.getAction(), ACTION_INIT)) {
                // 2018/4/4 在service里再调用startForeground方法，不然就会出现ANR
                startForeground(0, new Notification());
                //todo init something
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100);
                        sendEvent("onHandleIntent", i + 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "end");
            }
        }
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        sendEvent("onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        Log.d(TAG, "onStart");
        sendEvent("onStart");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        sendEvent("onDestroy");
        super.onDestroy();
    }

    private void sendEvent(String state) {
        sendEvent(state, 0);
    }

    private void sendEvent(String state, int progress) {
        EventBus.getDefault().postSticky(new ServiceEvent(state, progress));
    }
}
