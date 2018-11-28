package com.chen.test.dagger.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.chen.test.dagger.scope.ActivityContext;
import com.chen.test.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by chenxianglin on 2018/5/14.
 * Class note:
 */

/**
 * 注明本类是Module
 * */
@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

}
