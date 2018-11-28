package com.chen.test.template.saw;

import com.chen.test.template.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

public class SawPresenter<V extends SawMvpView> extends BasePresenter<V> implements SawMvpPresenter<V> {

    public SawPresenter(CompositeDisposable disposable) {
        super(disposable);
    }
}