package com.chen.test.activity.coordinator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.test.R;
import com.chen.test.base.BaseFragment;
import com.chen.test.databinding.FragmentCoordinator3Binding;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/11/28上午11:48
 */
public class CoordinatorFragment3 extends BaseFragment implements AppBarLayout.OnOffsetChangedListener, SwipeRefreshLayout.OnChildScrollUpCallback {
    private FragmentCoordinator3Binding mBinding;

//    @BindView(R.id.btn)
//    Button mBtn;
//    @BindView(R.id.text)
//    TextView mText;
    private int barOffset;

    public static CoordinatorFragment3 newInstance() {
        return new CoordinatorFragment3();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_coordinator3, null, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView() {
        mBinding.appBarLayout.addOnOffsetChangedListener(this);
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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        barOffset = Math.abs(verticalOffset);
    }

    @Override
    public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {
        return barOffset > 0;//第二种方法
    }
}
