package com.chen.router;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by chenxianglin on 2018/5/21.
 * Class note:
 */

@Route(path = "/service/hello", name = "路由服务器")
public class RouterService implements IService {
    @Override
    public void sayHello(Context context) {
        Log.d("RouterService", "sayHello");
        Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init(Context context) {
        Log.d("RouterService", "init");
    }
}
