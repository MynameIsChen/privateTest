package com.chen.test.template.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.test.R;
import com.chen.test.base.BaseFragment;

/**
 * Created by chenxianglin on 2018/5/17.
 * Class note:
 */

public class TestFragment extends BaseFragment implements TestMvpView {
    private TestPresenter mPresenter;
    protected View mRoot;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onAttach(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_test, null);
        return mRoot;
    }

    @Override
    public void setPresenter(TestPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
