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
import android.support.v7.app.AppCompatActivity;

import com.chen.test.App;
import com.chen.test.R;
import com.chen.test.dagger.component.ActivityComponent;
import com.chen.test.dagger.component.DaggerActivityComponent;
import com.chen.test.dagger.module.ActivityModule;

import permissions.dispatcher.PermissionRequest;

/**
 * Created by chenxianglin on 2017/12/26.
 * Class note:
 */

public class BaseActivity extends AppCompatActivity {
    public String TAG = this.getClass().getSimpleName();

    protected FragmentManager mManager;
    protected FragmentTransaction mTransaction;

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

    protected void replaceFragment(Fragment fragment, String name) {
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        mTransaction.replace(R.id.fragment, fragment)
                .addToBackStack(name)
                .commitAllowingStateLoss();
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
