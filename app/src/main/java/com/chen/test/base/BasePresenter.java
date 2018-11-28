package com.chen.test.base;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by chenxianglin on 2018/5/15.
 * Class note:
 */

public class BasePresenter implements IPresenter {
    private boolean loading;
    private CompositeDisposable mDisposable;

    @Inject
    public BasePresenter(CompositeDisposable disposable){
        mDisposable = disposable;
    }

    @Override
    public void destroy() {

    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
