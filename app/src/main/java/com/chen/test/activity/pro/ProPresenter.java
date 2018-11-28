package com.chen.test.activity.pro;

import com.chen.core.bean.DaggerEntity;
import com.chen.test.template.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ProPresenter<V extends ProMvpView> extends BasePresenter<V> implements ProMvpPresenter<V> {

    @Inject
    public ProPresenter(CompositeDisposable disposable, DaggerEntity entity) {
        super(disposable);
    }
}