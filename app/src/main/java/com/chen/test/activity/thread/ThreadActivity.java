package com.chen.test.activity.thread;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chen.test.R;
import com.chen.test.base.BaseActivity;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by chenxianglin on 2018/1/4.
 * Class note:
 */

public class ThreadActivity extends BaseActivity {
    @BindView(R.id.tabs)
    PagerTabStrip mTabs;
    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.turn)
    TextView mButton;
    @BindView(R.id.change)
    TextView mInfo;
    @BindView(R.id.progress)
    TextView mProgress;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.et1)
    EditText mEditText1;
    @BindView(R.id.et2)
    EditText mEditText2;

    private ThreadAdapter mAdapter;
    private FragmentManager mManager;
    private AsyncTask<Integer, Integer, String> mAsyncTask;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, ThreadActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        mManager = getSupportFragmentManager();
        mAdapter = new ThreadAdapter(mManager, Arrays.asList(new String[]{"1", "2", "3"}));
        mPager.setAdapter(mAdapter);
        mTabs.setTabIndicatorColorResource(R.color.black);
        mButton.setText("start");

        initAsync();
        getValue();
        loadBitmap();
    }

    private Bitmap mBitmap;
    private Bitmap mBlurBitmap;

    private void loadBitmap() {
        blur = false;
        String url = "http://g.hiphotos.baidu.com/image/pic/item/f3d3572c11dfa9ec8c2d744c6ed0f703918fc16d.jpg";
        getBitmap(this, url, false, new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                mBitmap = resource;
                changeImg();
            }
        });

        getBitmap(this, url, true, new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                mBlurBitmap = resource;
//                changeImg();
            }
        });
    }

    public void getBitmap(Context context, String url, boolean isBlur, SimpleTarget<Bitmap> target) {
        BitmapRequestBuilder builder = Glide.with(context).load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background);
        if (isBlur) {
            builder.transform(new BlurTransformation(context, radius, sampling));
        }
        builder.into(target);
    }

    @OnClick({R.id.turn, R.id.change})
    public void onItemClick(View v) {
        switch (v.getId()) {
            case R.id.turn:
//                changeState(1);
                changeImg();
                break;
            case R.id.change:
                getValue();
                loadBitmap();
                break;
        }
    }

    private boolean blur;
    private int radius = 20;
    private int sampling = 1;

    private void getValue() {
        try {
            radius = Integer.parseInt(mEditText1.getText().toString());
            sampling = Integer.parseInt(mEditText2.getText().toString());
        } catch (NumberFormatException e) {
            radius = 20;
            sampling = 1;
            e.printStackTrace();
        }
        radius = radius <= 25 ? radius : 25;
        sampling = sampling > 0 ? sampling : 1;
    }

    private void changeImg() {
        if (blur && mBlurBitmap != null) {
            mIv.setImageBitmap(mBlurBitmap);
        } else if (!blur && mBitmap != null) {
            mIv.setImageBitmap(mBitmap);
        }
        blur = !blur;
    }

    private void changeState(int type) {
        switch (type) {
            case 1:
                startThreadHandler();
                break;
            case 2:
                startAsync();
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    private void startAsync() {
        if (mButton.getText().equals("start")) {
            mButton.setText("stop");
            start();
        } else {
            mButton.setText("start");
            stop();
        }
    }

    private void start() {
        if (mAsyncTask != null) {
            if (mAsyncTask.getStatus() != AsyncTask.Status.RUNNING) {
                if (mAsyncTask.getStatus() == AsyncTask.Status.FINISHED) {
                    initAsync();
                }
                mAsyncTask.execute();
            }
        }
    }

    private void stop() {
        if (mAsyncTask != null && !mAsyncTask.isCancelled()) {
            mAsyncTask.cancel(true);
        }
    }

    private void startThreadHandler() {
        new Thread(new TestRunnable()).start();
    }

    private void startPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //todo test
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                1, TimeUnit.SECONDS, null);


    }

    private void startService() {

    }

    private void initAsync() {
        mAsyncTask = new AsyncTask<Integer, Integer, String>() {
            @Override
            protected void onPreExecute() {
                mInfo.setText("ready!");
                mProgress.setText("go!");
                super.onPreExecute();
                //预处理
            }

            @Override
            protected void onPostExecute(String s) {
                mInfo.setText(s);
                super.onPostExecute(s);
                //在UI线程上运行，得到结果并操作，如果任务被取消，此方法将不会被调用。
            }

            @Override
            protected void onProgressUpdate(final Integer... values) {
                Message msg = new Message();
                msg.what = HANDLER_ASYNC;
                msg.arg1 = values[0];
                mHandler.sendMessage(msg);
                //进度更新
            }

            @Override
            protected void onCancelled(String s) {
                mInfo.setText("onCancelled=s=" + s);
                super.onCancelled(s);
                //doInBackground后，调用完成
            }

            @Override
            protected void onCancelled() {
                mInfo.setText("onCancelled");
                super.onCancelled();
                //doInBackground后，调用完成
            }

            @Override
            protected String doInBackground(Integer... integers) {
                //后台处理，并返回值
                try {
                    for (int i = 10; i > 0; i--) {
                        Thread.sleep(1000);
                        onProgressUpdate((11 - i) * 10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "boom";
            }
        };
    }

    public static final int HANDLER_THREAD = 1;
    public static final int HANDLER_ASYNC = 2;
    public static final int HANDLER_POOL = 3;
    public static final int HANDLER_SERVICE = 4;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_THREAD:
                    mInfo.setText(msg.obj.toString());
                    break;
                case HANDLER_ASYNC:
                    mProgress.setText("当前进度：" + msg.arg1 + "%");
                    break;
                case HANDLER_POOL:

                    break;
                case HANDLER_SERVICE:

                    break;
            }
        }
    };

    class TestRunnable implements Runnable {
        @Override
        public void run() {
            try {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        String info = "ready update about thread.";
//                        mInfo.setText(info);

                        Message msg = new Message();
                        msg.what = HANDLER_THREAD;
                        msg.obj = (info + "handler");
                        mHandler.sendMessage(msg);
                    }
                });

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    String info = "end Thread and Handler.";
//                    mInfo.setText(info);

                    Message msg = new Message();
                    msg.what = HANDLER_THREAD;
                    msg.obj = (info + "handler");
                    mHandler.sendMessage(msg);
                }
            });
        }
    }
}
