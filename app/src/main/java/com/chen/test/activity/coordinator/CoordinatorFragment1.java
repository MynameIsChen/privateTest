package com.chen.test.activity.coordinator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.chen.test.R;
import com.chen.test.base.BaseFragment;
import com.chen.test.databinding.FragmentCoordinator1Binding;
import com.chen.test.holder.CoordinatorHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/11/28上午11:48
 */
public class CoordinatorFragment1 extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnTouchListener,
        AppBarLayout.OnOffsetChangedListener, SwipeRefreshLayout.OnChildScrollUpCallback {
    private FragmentCoordinator1Binding mBinding;
    private List<String> list = new ArrayList<>();
    private int barOffset;

    //实现stick+head+viewPager+RecyclerView效果
    public static CoordinatorFragment1 newInstance() {
        return new CoordinatorFragment1();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_coordinator1, null, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView() {
        mBinding.swipeRefresh.setEnabled(false);
        mBinding.swipeRefresh.setOnRefreshListener(this);
        mBinding.swipeRefresh.setNestedScrollingEnabled(false);
        mBinding.swipeRefresh.setOnTouchListener(this);//只是不出现动画，但是还是要滑动，所以不能在这儿控制

        for (int i = 0; i < 20; i++) {
            list.add("test" + i);
        }

        RecyclerView.Adapter adapter = new RecyclerView.Adapter<CoordinatorHolder>() {
            @Override
            public CoordinatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new CoordinatorHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false));
            }

            @Override
            public void onBindViewHolder(CoordinatorHolder holder, int position) {
                holder.mText.setText(list.get(position));
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        };
        mBinding.list.setAdapter(adapter);
        mBinding.list.setLayoutManager(new LinearLayoutManager(getActivity()));

//        test1();
    }


    private void test1() {
        mBinding.fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        v.setX(event.getRawX() - v.getWidth() / 2);
                        v.setY(event.getRawY() - v.getHeight() / 2);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        barOffset = Math.abs(verticalOffset);
        mBinding.swipeRefresh.setEnabled(barOffset == 0);//第一种方法
        Log.d("AppBarLayout", "verticalOffset=" + verticalOffset + "=refresh=" + mBinding.swipeRefresh.isRefreshing());
    }

    @Override
    public void onRefresh() {
        mBinding.swipeRefresh.postDelayed(() -> {
            if (mBinding.swipeRefresh.isRefreshing()) {
                mBinding.swipeRefresh.setRefreshing(false);
                mBinding.swipeRefresh.stopNestedScroll();
            }
        }, 1000);
    }

    @Override
    public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {
        return barOffset > 0;//第二种方法
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.pager) {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                int x = v.getScrollX();
                int y = v.getScrollY();
                return x > y / 2;
            }
        }

        return false;
    }
}
