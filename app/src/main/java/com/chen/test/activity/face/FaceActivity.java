package com.chen.test.activity.face;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chen.test.base.BaseActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FaceActivity extends BaseActivity implements FaceContract.View {
    private FaceContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setPresenter(FaceContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
