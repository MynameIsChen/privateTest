package com.chen.test.template.hi;

import com.chen.test.template.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

public class HiPresenter<V extends HiMvpView> extends BasePresenter<V> implements HiMvpPresenter<V> {

    public HiPresenter(CompositeDisposable disposable) {
        super(disposable);
    }
}