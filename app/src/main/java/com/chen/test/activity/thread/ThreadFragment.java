package com.chen.test.activity.thread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.test.R;
import com.chen.test.base.BaseFragment;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2018/1/4.
 * Class note:
 */

public class ThreadFragment extends BaseFragment {
    public static final String INFO = "info";

    @BindView(R.id.info)
    TextView mInfo;
    private View mRoot;
    private String info;

    public static ThreadFragment getInstance(String info) {
        ThreadFragment fragment = new ThreadFragment();
        Bundle bundle = new Bundle();
        bundle.putString(INFO, info);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.fragment_thread, null);
            mUnbinder = ButterKnife.bind(this, mRoot);
        }
        initView();
        toThread();
        return mRoot;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            info = bundle.getString(INFO, "");
        }
        mInfo.setText(info);
    }

    private void toThread() {
        Callable<Integer> callable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Thread thread = new Thread(futureTask);   //FutureTask对象作为Thread对象的target创建新的线程
                thread.start();                      //线程进入到就绪状态
                thread.isDaemon();//是否是后台线程
                try {
                    Thread.sleep(1);//
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("主线程for循环执行完毕..");

        try {
            int sum = futureTask.get();            //取得新创建的新线程中的call()方法返回的结果
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class MyCallable implements Callable<Integer> {
        private int i;

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                sum += i;
            }
            return sum;
        }
    }

}
