package com.chen.test;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chen.test.base.PreferenceHelper;
import com.chen.test.dagger.component.ApplicationComponent;
import com.chen.test.dagger.component.DaggerApplicationComponent;
import com.chen.test.dagger.module.ApplicationModule;
import com.chen.test.dagger.module.NetModule;
import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import javax.inject.Inject;

/**
 * Created by chenxianglin on 2017/11/6.
 * Class note:
 */

public class App extends Application {
    private ApplicationComponent mApplicationComponent;

    /**
     * @Inject 成员对象要求包级可见，对象不能为private
     * */
    @Inject
    PreferenceHelper mHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
        //chrome调试程序
        Stetho.initializeWithDefaults(this);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .netModule(new NetModule(this, "baseUrl"))
                .applicationModule(new ApplicationModule())
                .build();
        /**
         * 实现类的注入
         * */
        mApplicationComponent.inject(this);

        initRouter();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void initRouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    protected void setLogin(boolean state) {
//        mApplicationComponent.dbManager();
//        mApplicationComponent.application();
//        mApplicationComponent.context();
        mHelper.setLoginState(state);
    }

}
