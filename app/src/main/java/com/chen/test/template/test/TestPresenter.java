package com.chen.test.template.test;

import com.chen.test.template.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by chenxianglin on 2018/5/16.
 * Class note:
 */

public class TestPresenter<V extends TestMvpView> extends BasePresenter<V> implements TestMvpPresenter<V> {

    public TestPresenter(CompositeDisposable disposable) {
        super(disposable);
    }
}
