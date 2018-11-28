package com.chen.test.activity.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chen.common.util.UIUtils;
import com.chen.core.Event.ServiceEvent;
import com.chen.test.OnItemListener;
import com.chen.test.R;
import com.chen.test.base.BaseActivity;
import com.chen.test.service.InitService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenxianglin on 2018/5/24.
 * Class note:
 */

public class LaunchGridActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    TextView mBack;
    @BindView(R.id.list)
    RecyclerView mList;

    private LaunchAdapter mAdapter;
    private GridLayoutManager mManager;
    private GridItemDecoration mDecoration;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, LaunchGridActivity.class));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_launch_grid);
        ButterKnife.bind(this);

        initView();
    }

    protected void initView() {
        EventBus.getDefault().register(this);
        int h = (int) UIUtils.dpToPx(this, 10);
        float item = UIUtils.dpToPx(this, 65);
        int spanCount = 4;
        float sw = UIUtils.getScreenWidth(this) - (item * spanCount);
        int w = (int) (sw / (spanCount + 1));
        mAdapter = new LaunchAdapter(this, (int) item);
        mManager = new GridLayoutManager(this, spanCount);
        mDecoration = new GridItemDecoration();
        mList.addItemDecoration(mDecoration);
        mList.setLayoutManager(mManager);
        mList.setAdapter(mAdapter);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(String.valueOf(i + 1));
        }
        mAdapter.setData(list);
        mAdapter.setListener(new OnItemListener() {
            @Override
            public void onItem(int type, int position) {
                Log.d("onitem", "position=" + position);
                switch (position) {
                    case 0:
                        InitService.startService(LaunchGridActivity.this);
                        break;
                    case 1:
                        InitService.stopService(LaunchGridActivity.this);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.back, R.id.title})
    public void onItemClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.title:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onProgressEvent(ServiceEvent event) {
        if (event != null) {
            String title = event.getState();
            if (event.getState().equals("onHandleIntent")) {
                title += (": " + event.getProgress());
            }
            mTitle.setText(title);
        }
    }
}
