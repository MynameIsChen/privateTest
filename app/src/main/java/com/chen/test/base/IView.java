package com.chen.test.base;

/**
 * Created by chenxianglin on 2018/5/14.
 * Class note:
 */

public interface IView<T extends IPresenter> {
    void setPresenter(T presenter);
}
