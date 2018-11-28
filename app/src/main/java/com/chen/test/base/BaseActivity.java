package com.chen.test.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import com.chen.test.App;
import com.chen.test.R;
import com.chen.test.dagger.component.ActivityComponent;
import com.chen.test.dagger.component.DaggerActivityComponent;
import com.chen.test.dagger.module.ActivityModule;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.SupportActivity;
import permissions.dispatcher.PermissionRequest;

/**
 * Created by chenxianglin on 2017/12/26.
 * Class note:
 */

public class BaseActivity extends SupportActivity {
    public String TAG = this.getClass().getSimpleName();

    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject();
    }

    protected void initView() {

    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    protected void initInject() {

    }

    protected void addDisposable(Disposable d) {
        if (mCompositeDisposable == null) mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(d);
    }

    protected void replaceFragment(Fragment fragment, String name) {
        FragmentManager mManager = getSupportFragmentManager();
        FragmentTransaction mTransaction = mManager.beginTransaction();
        mTransaction.replace(R.id.fragment, fragment).addToBackStack(name).commitAllowingStateLoss();
    }

    protected void showRationaleDialog(@StringRes int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }
}
