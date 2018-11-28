package com.chen.test.template;

/**
 * Created by chenxianglin on 2018/5/16.
 * Class note:
 */

public interface IPresenter<V extends IView> {

    void onAttach(V view);

    void onDetach();
}
