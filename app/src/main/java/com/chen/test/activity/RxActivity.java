package com.chen.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chen.test.R;
import com.chen.test.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by chenxianglin on 2017/12/26.
 * Class note:
 */

public class RxActivity extends BaseActivity {
    private static String TAG = "RxActivity";

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, RxActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        init();
    }

    private void init() {
//        try {
//            Process process = new ProcessBuilder("pm","uninstall","").start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onSubscribe=value=" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError=e=" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
        observable.subscribe(observer);
        observable.flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {
                List<Integer> list = new ArrayList<>();
                for (int i=0;i<3;i++){
                    list.add(i);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        });
    }
}
