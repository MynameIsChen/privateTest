package com.chen.test.activity.coordinator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.chen.test.R;
import com.chen.test.base.BaseFragment;
import com.chen.test.databinding.FragmentCoordinator2Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/11/28上午11:48
 */
public class CoordinatorFragment2 extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnTouchListener,
        AppBarLayout.OnOffsetChangedListener, SwipeRefreshLayout.OnChildScrollUpCallback {
    private FragmentCoordinator2Binding mBinding;
    

    private ViewPagerAdapter mAdapter;
    private List<String> mTitles = new ArrayList<>();
    private int barOffset;

    public static CoordinatorFragment2 newInstance(){
        return new CoordinatorFragment2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_coordinator2,null,false);
        return mBinding.getRoot();
    }
    @Override
    protected void initView() {
        for (int i = 0; i < 5; i++) {
            mTitles.add("I=" + i);
        }
        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), mTitles);
        mBinding.pager.setAdapter(mAdapter);
        mBinding.swipeRefresh.setEnabled(false);
        mBinding.appBarLayout.addOnOffsetChangedListener(this);
        mBinding.swipeRefresh.setOnChildScrollUpCallback(this);
        mBinding.swipeRefresh.setOnRefreshListener(this);
        mBinding.swipeRefresh.setNestedScrollingEnabled(false);
        mBinding.swipeRefresh.setOnTouchListener(this);//只是不出现动画，但是还是要滑动，所以不能在这儿控制
        mBinding.pager.setOnTouchListener(this);
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
