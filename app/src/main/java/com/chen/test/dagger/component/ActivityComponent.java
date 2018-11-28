package com.chen.test.dagger.component;

import com.chen.test.activity.pro.ProActivity;
import com.chen.test.dagger.module.ActivityModule;
import com.chen.test.dagger.scope.ActivityScope;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by chenxianglin on 2018/5/14.
 * Class note:
 */


/**
 * ProPresenter在Inject的时候构造参数中有CompositeDisposable，Activity和Application是常用参数
 * 所以：以上三个参数需要放入ActivityModule中Provides声明
 * 结果：实现注入
 * 其他：有其他注入则需要在对应的Component中Module声明方法提供注入源头
 *
 * @ActivityScope dependencies于一个父类时，当前类需要自定义一个Scope
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(ProActivity proActivity);

    CompositeDisposable compositeDisposable();
}
