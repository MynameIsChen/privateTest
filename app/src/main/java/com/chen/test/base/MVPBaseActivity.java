package com.chen.test.base;

/**
 * Created by chenxianglin on 2018/5/16.
 * Class note:
 */

public class MVPBaseActivity<T extends BaseView, C extends IPresenter>{
    private T mT;
    private C mC;

    public T getT() {
        return mT;
    }

    public void setT(T t) {
        mT = t;
    }

    public C getC() {
        return mC;
    }

    public void setC(C c) {
        mC = c;
    }
}
