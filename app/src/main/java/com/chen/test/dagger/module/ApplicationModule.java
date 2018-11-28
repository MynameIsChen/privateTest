package com.chen.test.dagger.module;

import com.chen.test.base.AppDbManager;
import com.chen.test.base.AppPreferences;
import com.chen.test.base.DbManager;
import com.chen.test.base.PreferenceHelper;
import com.chen.test.dagger.scope.PreferenceInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxianglin on 2018/5/22.
 * Class note:
 */

@Module
public class ApplicationModule {
    private static final String KEY_PREFERENCE = "PREFERENCE";

    /**
     * 用来提供依赖对象的方法
     * 因为注解中所依赖的对象需要提供参数，需要通过Provides来实现，否则会提示异常
     *
     * @Scope 会指定使用区域，生命周期是其作用的区域的生命周期(Activity,Application)
     * @Singleton 指定该方法只会使用一次，否则会出现异常，也是 @Scope 的一种
     */
    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return KEY_PREFERENCE;
    }

    @Provides
    @Singleton
    DbManager provideDbManager(AppDbManager dbManager) {
        return dbManager;
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferenceHelper(AppPreferences appPreferences) {
        return appPreferences;
    }
}
