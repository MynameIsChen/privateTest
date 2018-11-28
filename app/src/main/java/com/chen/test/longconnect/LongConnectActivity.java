package com.chen.test.longconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chen.test.R;
import com.chen.test.base.BaseActivity;
import com.chen.test.service.LongConnectService;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2018/5/29.
 * Class note:
 */

public class LongConnectActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.start)
    TextView mStart;
    @BindView(R.id.address)
    EditText mAddress;
    @BindView(R.id.set)
    TextView mSet;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, LongConnectActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_connect);
        ButterKnife.bind(this);

        mStart.setOnClickListener(this);
        mSet.setOnClickListener(this);

//        replaceFragment(CustomFragment.getInstance("cid"),"fragment");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            startService(new Intent(LongConnectActivity.this, LongConnectService.class));
        } else if (v.getId() == R.id.set) {
            SessionManager.getInstance().writeToServer("发送消息...");
        }
    }
}
