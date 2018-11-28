package com.chen.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;

import com.chen.test.R;
import com.chen.test.weight.TouchLayout;
import com.chen.test.weight.TouchLayout1;
import com.chen.test.weight.TouchView;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenxianglin on 2017/12/4.
 * Class note:
 */

public class TouchActivity extends Activity {
    private static final String TAG = "TouchActivity";

    @BindView(R.id.view)
    TouchView mView;
    @BindView(R.id.layout1)
    TouchLayout1 mLayout1;
    @BindView(R.id.layout)
    TouchLayout mLayout;

    private OkHttpClient.Builder mBuilder;
    private OkHttpClient mClient;


    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, TouchActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        DataBindingUtil.setContentView(this, R.layout.activity_touch);
        ButterKnife.bind(this);
//        Looper.prepare();
//        Looper looper = Looper.myLooper();

        People p = new Child();
        p.move(1);
        p.language();
        List l = new ArrayList();
    }

    abstract class People {
        protected abstract void language();

        public void move(int distance) {
            System.out.println(this.getClass()+"=move=" + distance);
        }
    }

    class Child extends People {
        @Override
        protected void language() {
            System.out.println("=language=");
        }

        @Override
        public void move(int distance) {
            super.move(distance);
            System.out.println(this.getClass()+"=move=" + distance);
        }
    }

    interface Interface {
        public final int a = 0;

        public abstract void setA(int i);
    }

//    @OnClick({R.id.view, R.id.layout1, R.id.layout})
//    public void onItemClick(View v) {
//        switch (v.getId()) {
//            case R.id.view:
//                break;
//            case R.id.layout1:
//                break;
//            case R.id.layout:
//                break;
//        }
//    }

    private void init() {
        mBuilder = new OkHttpClient.Builder();
        mBuilder.cache(null);
        mBuilder.sslSocketFactory(null, null);
        mBuilder.hostnameVerifier(new AllowAllHostnameVerifier());
        mBuilder.addInterceptor(null);
        mBuilder.addNetworkInterceptor(new StethoInterceptor());
        mClient = mBuilder.build();

        Request request = new Request.Builder().url("").build();
        try {
            Response response = mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Retrofit retrofit = new Retrofit();

    }

    private void exectue() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onTouchEvent  action：" + ev.getAction());
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent  action：" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
}
