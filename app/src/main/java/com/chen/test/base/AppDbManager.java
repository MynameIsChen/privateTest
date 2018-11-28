package com.chen.test.base;

import android.content.Context;

import com.chen.test.dagger.scope.ApplicationContext;

import javax.inject.Inject;

/**
 * Created by chenxianglin on 2018/5/22.
 * Class note:
 */

public class AppDbManager implements DbManager {
    private Context mContext;
    private PreferenceHelper mPreferencesHelper;

    @Inject
    public AppDbManager(@ApplicationContext Context context, PreferenceHelper preferencesHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public void setLoginState(boolean state) {
        mPreferencesHelper.setLoginState(state);
    }

    @Override
    public boolean isLogin() {
        return mPreferencesHelper.isLogin();
    }

    @Override
    public Context context() {
        return mContext;
    }
}
