package com.chen.test.activity.webin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chen.test.R;
import com.chen.test.base.BaseActivity;
import com.chen.core.bean.ListItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2018/1/10.
 * Class note:
 */

public class WebInListActivity extends BaseActivity {
    @BindView(R.id.list)
    RecyclerView mList;

    private LinearLayoutManager mManager;
    private WebInListAdapter mAdapter;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, WebInListActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_in_list);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void initView() {
        mAdapter = new WebInListAdapter(this);
        mManager = new LinearLayoutManager(this);
        mList.setLayoutManager(mManager);
        mList.setAdapter(mAdapter);

        setData();
    }

    private void setData() {
        List<ListItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i == 1) {
                list.add(new ListItemEntity(WebInListAdapter.TYPE_WEB, "http://gunuoapi.weiyingjia.org/gunuo/details.html?uid=17&g_id=52"));
            } else if (i == 2) {
                list.add(new ListItemEntity(WebInListAdapter.TYPE_WEB, "https://zhidao.baidu.com/question/2011310508679097908.html"));
            } else {
                list.add(new ListItemEntity(WebInListAdapter.TYPE_ITEM, "i=" + i));
            }
        }
        mAdapter.setData(list);
    }
}
