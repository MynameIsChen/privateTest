package com.chen.test.activity.coordinator;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chen.test.R;
import com.chen.test.base.BaseActivity;
import com.chen.test.base.BaseFragment;
import com.chen.test.databinding.ActivityCoordinatorBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxianglin on 2017/12/11.
 * Class note:
 */

public class CoordinatorActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    private ActivityCoordinatorBinding mBinding;

    private CoordinatorAdapter mAdapter;
    private CoordinatorFragment1 mFragment1;
    private CoordinatorFragment2 mFragment2;
    private CoordinatorFragment3 mFragment3;
    private CoordinatorFragment4 mFragment4;
    private CoordinatorFragment5 mFragment5;
    private CoordinatorFragment6 mFragment6;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, CoordinatorActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_coordinator);
        mAdapter = new CoordinatorAdapter();
        mAdapter.setOnItemClickListener(this);
        mBinding.menu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mBinding.menu.setAdapter(mAdapter);
        mAdapter.setNewData(getData());
        loadFragment(0);
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        data.add("Fragment1");
        data.add("Fragment2");
        data.add("Fragment3");
        data.add("Fragment4");
        data.add("Fragment5");
        data.add("Fragment6");
        return data;
    }

    private void loadFragment(int index) {
        if (index == mAdapter.getCurrentIndex()) return;
        showHideFragment(getFragment(index));
        mAdapter.setCurrentIndex(index);
    }

    private BaseFragment getFragment(int index) {
        if (index == 0) {
            if (mFragment1 == null) {
                mFragment1 = CoordinatorFragment1.newInstance();
                addFragment(mFragment1);
            }
            return mFragment1;
        } else if (index == 1) {
            if (mFragment2 == null) {
                mFragment2 = CoordinatorFragment2.newInstance();
                addFragment(mFragment2);
            }
            return mFragment2;
        } else if (index == 2) {
            if (mFragment3 == null) {
                mFragment3 = CoordinatorFragment3.newInstance();
                addFragment(mFragment3);
            }
            return mFragment3;
        } else if (index == 3) {
            if (mFragment4 == null) {
                mFragment4 = CoordinatorFragment4.newInstance();
                addFragment(mFragment4);
            }
            return mFragment4;
        } else if (index == 4) {
            if (mFragment5 == null) {
                mFragment5 = CoordinatorFragment5.newInstance();
                addFragment(mFragment5);
            }
            return mFragment5;
        } else if (index == 5) {
            if (mFragment6 == null) {
                mFragment6 = CoordinatorFragment6.newInstance();
                addFragment(mFragment6);
            }
            return mFragment6;
        }

        return null;
    }

    private void addFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        loadFragment(position);
    }
}
