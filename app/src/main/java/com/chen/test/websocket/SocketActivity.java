package com.chen.test.websocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chen.common.util.ProcessUtils;
import com.chen.test.R;
import com.chen.test.base.BaseActivity;
import com.chen.test.websocket.socket.WsManager;
import com.chen.test.websocket.socket.WsStatusListener;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.ByteString;

/**
 * Created by chenxianglin on 2018/6/2.
 * Class note:
 */

public class SocketActivity extends BaseActivity implements View.OnClickListener, WsStatusListener {
    @BindView(R.id.send)
    TextView mSend;
    @BindView(R.id.input)
    EditText mInput;
    @BindView(R.id.content)
    TextView mContent;

    private WsManager mWsManager;
    private OkHttpClient okHttpClient;
    private Disposable mDisposable;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, SocketActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        initSocket();
        mSend.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send) {
            String content = mInput.getText().toString();
            sendString(content);
            mInput.setText("");
        }
    }

    private void initSocket() {
        if (mWsManager != null)
            mWsManager.stopConnect();

        okHttpClient = new OkHttpClient().newBuilder()
                .pingInterval(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        mWsManager = new WsManager.Builder(this)
                .wsUrl("ws://" + SocketConstants.SOCKET_IP + ":" + SocketConstants.SOCKET_PORT+"/websocket")
                .needReconnect(true)
                .client(okHttpClient)
                .build();
        mWsManager.setWsStatusListener(this);
    }

    public void sendString(String sendMsg) {
        if (!isSendHeartBeat(sendMsg)) startTimer();
        if (!mWsManager.isWsConnected()) {
            connect();
        }
        mWsManager.sendMessage(sendMsg);
        if (!isCurProcessName() || isSendHeartBeat(sendMsg))
            return;
        Log.d(TAG, "Socket 发送信息\n发送的信息 - " + sendMsg);
    }

    public void connect() {
        this.mWsManager.startConnect();
    }

    public void disConnect() {
        closeTimer();
        if (this.mWsManager != null) {
            this.mWsManager.stopConnect();
            this.okHttpClient.dispatcher().executorService().shutdown();
            this.okHttpClient = null;
            this.mWsManager = null;
        }
    }

    private void startTimer() {
        if (!isCurProcessName()) return;
        closeTimer();
        Observable.timer(15, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        if (!isCurProcessName()) return;
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //取消订阅
                        if (!isCurProcessName()) return;
                        closeTimer();
                    }

                    @Override
                    public void onComplete() {
                        //取消订阅
                        if (!isCurProcessName()) return;
                        closeTimer();
                        //连接超时
                        Log.d(TAG, "Socket 接口链接超时");
                    }
                });
    }

    /**
     * 关闭定时器
     */
    private void closeTimer() {
        if (!isCurProcessName()) return;
        if (mDisposable != null)
            mDisposable.dispose();
    }


    private boolean isCurProcessName() {
        return ProcessUtils.isMainProcess(this);
    }

    private boolean isSendHeartBeat(String text) {
        return TextUtils.equals(text, SocketConstants.SOCKET_SEND_HEARTBEAT);
    }

    private boolean isReceiveHeartBeat(String text) {
        return TextUtils.equals(text, SocketConstants.SOCKET_RECEIVE_HEARTBEAT);
    }

    @Override
    public void onOpen(Response response) {
        if (!isCurProcessName()) return;
        Log.d(TAG, "Socket 链接成功");
    }

    @Override
    public void onMessage(String text) {
        Log.d(TAG, "Socket onMessage" + text.toString());
        mContent.setText(text.toString() + "\n" + mContent.getText());
    }

    @Override
    public void onMessage(ByteString bytes) {
        Log.d(TAG, "Socket onMessage" + bytes.toString());
    }

    @Override
    public void onReconnect() {
        Log.d(TAG, "Socket 重新链接中");
    }

    @Override
    public void onClosing(int code, String reason) {
        Log.d(TAG, "Socket 关闭中");
    }

    @Override
    public void onClosed(int code, String reason) {
        Log.d(TAG, "Socket onClosed");
    }

    @Override
    public void onFailure(Throwable t, Response response) {
        Log.d(TAG, "Socket onFailure");
    }
}
