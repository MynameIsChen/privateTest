package com.chen.test.activity.coordinator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.test.R;
import com.chen.test.base.BaseFragment;
import com.chen.test.databinding.FragmentCoordinator5Binding;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/11/28上午11:48
 */
public class CoordinatorFragment5 extends BaseFragment {
    private FragmentCoordinator5Binding mBinding;


    public static CoordinatorFragment5 newInstance() {
        return new CoordinatorFragment5();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_coordinator5, null, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView() {

    }

}
