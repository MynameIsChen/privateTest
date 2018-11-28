package com.chen.test.dagger.module;

import com.chen.core.bean.DaggerEntity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxianglin on 2018/5/22.
 * Class note:
 */

@Module
public class DaggerModule {
    private DaggerEntity mEntity;

    public DaggerModule(DaggerEntity entity) {
        mEntity = entity;
    }

    @Provides
    public DaggerEntity provideDagger() {
        return mEntity;
    }
}
