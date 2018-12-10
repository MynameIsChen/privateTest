package com.chen.test.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by chenxianglin on 2018/1/4.
 * Class note:
 */

public class BaseFragment extends SupportFragment {
    protected Unbinder mUnbinder;
    public String TAG = this.getClass().getSimpleName();

    private CompositeDisposable mCompositeDisposable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected void initView() {

    }

    protected void addDisposable(Disposable d) {
        if (mCompositeDisposable == null) mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(d);
    }

    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            try {
                mUnbinder.unbind();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onDestroyView();
    }
}
