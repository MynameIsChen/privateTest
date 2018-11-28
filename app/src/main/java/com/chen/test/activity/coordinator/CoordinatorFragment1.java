package com.chen.test.activity.coordinator;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chen.test.R;
import com.chen.test.base.BaseFragment;
import com.chen.test.databinding.FragmentCoordinator1Binding;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/11/28上午11:48
 */
public class CoordinatorFragment1 extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnTouchListener,
        AppBarLayout.OnOffsetChangedListener, SwipeRefreshLayout.OnChildScrollUpCallback {
    private FragmentCoordinator1Binding mBinding;

    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mBarLayout;
    @BindView(R.id.behavior_demo_swipe_refresh)
    SwipeRefreshLayout mRefreshLayout;
//    @BindView(R.id.btn)
//    Button mBtn;
//    @BindView(R.id.text)
//    TextView mText;

    private ViewPagerAdapter mAdapter;
    private List<String> mTitles = new ArrayList<>();


    //实现stick+head+viewPager+RecyclerView效果
    public static CoordinatorFragment1 newInstance(){
        return new CoordinatorFragment1();
    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_coordinator1);

        for (int i = 0; i < 5; i++) {
            mTitles.add("I=" + i);
        }
        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), mTitles);
        mPager.setAdapter(mAdapter);
        mRefreshLayout.setEnabled(false);
//        mBarLayout.addOnOffsetChangedListener(this);
//        mRefreshLayout.setOnChildScrollUpCallback(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setNestedScrollingEnabled(false);
        mRefreshLayout.setOnTouchListener(this);//只是不出现动画，但是还是要滑动，所以不能在这儿控制
        mPager.setOnTouchListener(this);
    }


    private void test1() {
//        mBtn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_MOVE:
//                        v.setX(event.getRawX() - v.getWidth() / 2);
//                        v.setY(event.getRawY() - v.getHeight() / 2);
//                        break;
//                }
//                return false;
//            }
//        });
    }
    private int barOffset;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        barOffset = Math.abs(verticalOffset);
        mRefreshLayout.setEnabled(barOffset == 0);//第一种方法
        Log.d("AppBarLayout", "verticalOffset=" + verticalOffset + "=refresh=" + mRefreshLayout.isRefreshing());
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.setRefreshing(false);
                    mRefreshLayout.stopNestedScroll();
                }
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
