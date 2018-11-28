package com.chen.test.activity.pro;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.core.bean.DaggerEntity;
import com.chen.test.R;
import com.chen.test.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProFragment extends BaseFragment implements ProMvpView {
    private ProPresenter mPresenter;
    private View mRoot;

    @BindView(R.id.title)
    TextView mTitle;

    @Inject
    DaggerEntity mEntity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    @SuppressLint("InflateParams")
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.fragment_dagger, null);
        }

        ButterKnife.bind(this, mRoot);
        initData();
        return mRoot;
    }

    @Override
    public void setPresenter(ProPresenter presenter) {
        mPresenter = presenter;
        mPresenter.onAttach(this);
    }

    private void initData() {
        if (mEntity != null && !TextUtils.isEmpty(mEntity.getTitle())) {
            mTitle.setText(mEntity.getTitle());
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

}