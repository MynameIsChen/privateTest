package com.chen.test.activity.pro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chen.test.R;
import com.chen.test.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = "/pro/proActivity")
public class ProActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView mTitle;

    @Inject
    ProPresenter<ProMvpView> mProPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        initView();
    }

    protected void initView() {
        mTitle.setText("title");
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
//        getActivityComponent().compositeDisposable().add(null);
    }

    @Override
    protected void onDestroy() {
        mProPresenter.onDetach();
        super.onDestroy();
    }
}

