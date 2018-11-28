package com.chen.test.template;

/**
 * Created by chenxianglin on 2018/5/16.
 * Class note:
 */

public interface IView<P extends IPresenter> {
    void setPresenter(P presenter);
}
