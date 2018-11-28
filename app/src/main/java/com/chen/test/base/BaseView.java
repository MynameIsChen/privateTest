package com.chen.test.base;

/**
 * Created by chenxianglin on 2018/5/15.
 * Class note:
 */

public interface BaseView<T extends IPresenter> extends IView<T> {

    @Override
    void setPresenter(T presenter);

    boolean isLogin();

    void showLoading();

    void hideLoading();
}
