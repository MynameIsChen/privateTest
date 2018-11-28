package com.chen.test.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.chen.test.dagger.scope.ApplicationContext;
import com.chen.test.dagger.scope.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by chenxianglin on 2018/5/22.
 * Class note:
 */

@Singleton
public class AppPreferences implements PreferenceHelper {
    private static final String LOGIN_STATE = "login_state";

    private SharedPreferences mSharedPreferences;

    @Inject
    public AppPreferences(@ApplicationContext Context context, @PreferenceInfo String fileName) {
        mSharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setLoginState(boolean state) {
        mSharedPreferences.edit().putBoolean(LOGIN_STATE, state).apply();
    }

    @Override
    public boolean isLogin() {
        return mSharedPreferences.getBoolean(LOGIN_STATE, false);
    }
}
