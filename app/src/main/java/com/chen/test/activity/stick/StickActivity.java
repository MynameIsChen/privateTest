package com.chen.test.activity.stick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.chen.test.R;
import com.jay.widget.StickyHeaders;
import com.jay.widget.StickyHeadersLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2017/12/6.
 * Class note:
 */

public class StickActivity extends AppCompatActivity {
    @BindView(R.id.list)
    RecyclerView mList;

    private StickAdapter mAdapter;
    private StickyHeadersLinearLayoutManager mManager;
    private StickyHeaders.ViewSetup mSetup;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, StickActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stick);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mManager = new StickyHeadersLinearLayoutManager<StickAdapter>(this);
        mAdapter = new StickAdapter(this);
        mList.setLayoutManager(mManager);
        mList.setAdapter(mAdapter);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "title");
        }
        mAdapter.setData(list);

    }
}
