package com.chen.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by chenxianglin on 2018/5/21.
 * Class note:
 */

public interface IService extends IProvider {
    void sayHello(Context context);
}
