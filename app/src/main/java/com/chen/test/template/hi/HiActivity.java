package com.chen.test.template.hi;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chen.test.R;
import com.chen.test.base.BaseActivity;

public class HiActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi);
    }
}

