package com.chen.test.activity.coordinator;

import android.databinding.DataBindingUtil;

import com.chen.test.R;
import com.chen.test.base.BaseFragment;
import com.chen.test.databinding.FragmentCoordinator3Binding;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/11/28上午11:48
 */
public class CoordinatorFragment3 extends BaseFragment {
    private FragmentCoordinator3Binding mBinding;

    public static CoordinatorFragment3 newInstance(){
        return new CoordinatorFragment3();
    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_coordinator3);
    }
}
