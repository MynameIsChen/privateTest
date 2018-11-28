package com.chen.test.activity.coordinator;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.chen.test.R;
import com.chen.test.base.BaseActivity;
import com.chen.test.base.BaseFragment;
import com.chen.test.databinding.ActivityCoordinatorBinding;
import com.jakewharton.rxbinding2.view.RxView;

/**
 * Created by chenxianglin on 2017/12/11.
 * Class note:
 */

public class CoordinatorActivity extends BaseActivity {
    private ActivityCoordinatorBinding mBinding;

    private CoordinatorFragment1 mFragment1;
    private CoordinatorFragment2 mFragment2;
    private CoordinatorFragment3 mFragment3;
    private int mCurrentIndex;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, CoordinatorActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_coordinator);
        addDisposable(RxView.clicks(mBinding.testA).subscribe(o -> loadFragment(0)));
        addDisposable(RxView.clicks(mBinding.testB).subscribe(o -> loadFragment(1)));
        addDisposable(RxView.clicks(mBinding.testC).subscribe(o -> loadFragment(2)));
    }

    private void loadFragment(int index) {
        mBinding.testA.setSelected(index == 0);
        mBinding.testB.setSelected(index == 1);
        mBinding.testC.setSelected(index == 2);
        if (index == mCurrentIndex) return;
        showHideFragment(getFragment(index));
        mCurrentIndex = index;
    }

    private BaseFragment getFragment(int index) {
        if (index == 0) {
            if (mFragment1 == null) {
                mFragment1 = CoordinatorFragment1.newInstance();
            }
            addFragment(mFragment1);
            return mFragment1;
        } else if (index == 1) {
            if (mFragment2 == null) {
                mFragment2 = CoordinatorFragment2.newInstance();
            }
            addFragment(mFragment2);
            return mFragment2;
        } else if (index == 2) {
            if (mFragment3 == null) {
                mFragment3 = CoordinatorFragment3.newInstance();
            }
            addFragment(mFragment3);
            return mFragment3;
        }

        return null;
    }

    private void addFragment(BaseFragment fragment) {
        if (!fragment.isAdded()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment, fragment);
            transaction.commitAllowingStateLoss();
        }
    }

}
