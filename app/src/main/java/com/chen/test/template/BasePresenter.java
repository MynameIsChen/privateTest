package com.chen.test.template;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by chenxianglin on 2018/5/16.
 * Class note:
 */

public class BasePresenter<V extends IView> implements IPresenter<V> {
    private V v;
    private CompositeDisposable mDisposable;

    @Inject
    public BasePresenter(CompositeDisposable disposable){
        mDisposable = disposable;
    }

    @Override
    public void onAttach(V view) {
        v = view;
        v.setPresenter(this);
    }

    @Override
    public void onDetach() {
        v = null;
    }

    public V getV() {
        return v;
    }
}
