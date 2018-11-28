package com.chen.router;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chen.router.databinding.ActivityRouterBinding;

/**
 * Created by chenxianglin on 2018/5/21.
 * Class note:
 */

@Route(path = "/router/routerActivity")
public class RouterActivity extends AppCompatActivity {
    ActivityRouterBinding mBinding = null;
    @Autowired
    String title;

    @Autowired(name = "/service/hello")
    RouterService mService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_router);
        ARouter.getInstance().inject(this);

        Bundle bundle = getIntent().getExtras();
        String title1 = bundle.getString("title");
        RouteBean bean = new RouteBean();
        bean.setTitle("title:" + title + "title1:" + title1);
        mBinding.setRouter(bean);
        mBinding.executePendingBindings();

        mService.sayHello(this);
    }
}
