package com.chen.test.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by chenxianglin on 2018/1/4.
 * Class note:
 */

public class BaseFragment extends SupportFragment {
    protected Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void initView() {

    }

    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            try {
                mUnbinder.unbind();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onDestroyView();
    }
}
