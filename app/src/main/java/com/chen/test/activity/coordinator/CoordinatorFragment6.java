package com.chen.test.activity.coordinator;

import android.animation.LayoutTransition;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.chen.common.util.Lg;
import com.chen.test.R;
import com.chen.test.base.BaseFragment;
import com.chen.test.databinding.FragmentCoordinator6Binding;
import com.chen.test.holder.CoordinatorHolder;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/11/28上午11:48
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class CoordinatorFragment6 extends BaseFragment implements View.OnLayoutChangeListener, View.OnScrollChangeListener, View.OnTouchListener, SwipeRefreshLayout.OnRefreshListener {
    private FragmentCoordinator6Binding mBinding;
    private LayoutTransition mTransition;


    public static CoordinatorFragment6 newInstance() {
        return new CoordinatorFragment6();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_coordinator6, null, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView() {
        List<String> list = new ArrayList<>();
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

        addDisposable(RxView.clicks(mBinding.back).subscribe(o -> showHide()));
        mBinding.list.setAdapter(adapter);
        mBinding.list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.swipeRefresh.setEnabled(false);
        mBinding.swipeRefresh.setOnRefreshListener(this);

        mTransition = new LayoutTransition();
        mTransition.enableTransitionType(LayoutTransition.CHANGING);
        mBinding.coordinatorLayout.setLayoutTransition(mTransition);

        mBinding.coordinatorLayout.addOnLayoutChangeListener(this);
        mBinding.coordinatorLayout.setOnTouchListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mBinding.coordinatorLayout.setOnScrollChangeListener(this);
        }
    }

    private void showHide() {
        mBinding.coordinatorLayout.setLayoutTransition(mTransition);//控制view变化的动画显示
        mBinding.head.setVisibility(View.VISIBLE);
        mBinding.back.setVisibility(View.INVISIBLE);
        mBinding.add.setVisibility(View.GONE);
        mBinding.swipeRefresh.setEnabled(false);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        Lg.d(this.getClass().getName(), "onLayoutChange : left=" + left + "=top=" + top + "=right=" + right + "=bottom=" + bottom
                + "=oldLeft=" + oldLeft + "=oldTop=" + oldTop + "=oldRight=" + oldRight + "=oldBottom=" + oldBottom);
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Lg.d(this.getClass().getName(), "onScrollChange : scrollX=" + scrollX + "=scrollY=" + scrollY
                + "=oldScrollX=" + oldScrollX + "=oldScrollY=" + oldScrollY);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Lg.d(this.getClass().getName(), "onTouch: y=" + v.getTranslationY() + "=ScrollY=" + v.getScrollY()
                + "=Y=" + v.getY() + "=TranslationY=" + v.getTranslationY() + "=PivotY=" + v.getPivotY()
                + "=RotationY=" + v.getRotationY());
        return true;
    }

    @Override
    public void onRefresh() {
        mBinding.swipeRefresh.setRefreshing(false);
    }
}
