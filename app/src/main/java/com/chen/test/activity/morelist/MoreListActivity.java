package com.chen.test.activity.morelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chen.common.util.UIUtils;
import com.chen.core.bean.TitleEntity;
import com.chen.test.OnItemListener;
import com.chen.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2017/11/24.
 * Class note:
 */

public class MoreListActivity extends Activity {
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.root)
    RelativeLayout mRoot;
    @BindView(R.id.content)
    RelativeLayout mContent;

    private MoreListAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<View> mContents = new ArrayList<>();
    private int w;
    private int h;
    private int index;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, MoreListActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_list);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        w = (int) UIUtils.dpToPx(this, 50);
        h = (int) UIUtils.dpToPx(this, 200);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                showPop(0, 0, 0);
                onItemClick(-1);
            }
        });

        mContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContents.clear();
                mRoot.removeAllViews();
            }
        });
    }

    private List<TitleEntity> getData() {
        List<TitleEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new TitleEntity("title" + i));
        }
        return list;
    }

    private void showPop(int parent, int x, int y) {
        View contentView = null;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w, h);
        if (mContents.size() > parent) {
            contentView = mContents.get(parent);
        }
        if (contentView == null) {
            contentView = View.inflate(this, R.layout.pop_list, null);
            params.leftMargin = x;
            params.topMargin = y;
            mContents.add(contentView);
            mAdapter = new MoreListAdapter(this, w, parent);
            mManager = new LinearLayoutManager(this);
            mRoot.addView(contentView, params);
            RecyclerView list = contentView.findViewById(R.id.list);
            list.setLayoutManager(mManager);
            list.setAdapter(mAdapter);

            mAdapter.setListener(new OnItemListener() {
                @Override
                public void onItem(int type, int position) {
                    onItemClick(type);
                }
            });
            mAdapter.setData(getData());
        }
    }

    private void onItemClick(int type) {
        if (index > type) {
            for (int i = mRoot.getChildCount() - 1; i > type + 1; i--) {
                if (mContents.size() > i) {
                    mContents.remove(i);
                }
                mRoot.removeViewAt(i);
            }
            index = type;
        }
        index++;
        if (type > -1) {
            showPop(index, w * index, 0);//h * index + top
        }
    }
}
