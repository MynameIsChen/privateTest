package com.chen.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.SurfaceView;

import com.chen.test.R;
import com.chen.test.base.BaseActivity;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnPreparedListener;

import java.io.IOException;

import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2018/7/12.
 * Class note:
 */

public class PlayerActivity extends BaseActivity {
    private PLMediaPlayer mMediaPlayer = null;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, PlayerActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        mMediaPlayer = new PLMediaPlayer(this);
        mMediaPlayer.setDisplay(new SurfaceView(this).getHolder());
//        PLMediaPlayer mMediaPlayer = new PLMediaPlayer(mContext, mAVOptions);
        try {
            mMediaPlayer.setDataSource("http://10.2.9.130/m10.music.126.net/20180712150729/14a84542b8e15abef45016c51391485e/ymusic/c187/cdf9/7c6c/e6d4f99a146f6c8ae5c4e55146f62f9a.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setOnPreparedListener(new PLOnPreparedListener() {
            @Override
            public void onPrepared(int i) {
                mMediaPlayer.start();
            }
        });
        mMediaPlayer.setOnInfoListener(new PLOnInfoListener() {
            @Override
            public void onInfo(int i, int i1) {
                Log.d("player", "i=" + i + "=i1=" + i1);
                if(i==10005) {
                    Log.d("player", "audio_time=" + i + "=i1=" + i1);
                }
            }
        });
        mMediaPlayer.prepareAsync();
    }

    @Override
    protected void onDestroy() {
        mMediaPlayer.release();
        super.onDestroy();
    }
}
