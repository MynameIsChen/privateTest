package com.chen.test.longconnect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.common.util.DateFormatUtils;
import com.chen.test.R;
import com.chen.test.base.BaseFragment;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2018/6/1.
 * Class note:
 */

public class CustomFragment extends BaseFragment implements OnItemListener {
    @BindView(R.id.type)
    RecyclerView mType;
    @BindView(R.id.info)
    RecyclerView mInfo;

    private String cid = "";
    private View mRoot;
    private CustomTypeAdapter mTypeAdapter;
    private CustomTypeAdapter mInfoAdapter;
    private List<String> mInfos = new ArrayList<>();

    public static CustomFragment getInstance(String id) {
        CustomFragment fragment = new CustomFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cid", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.fragment_custom, container, false);
        }
        mUnbinder = ButterKnife.bind(this, mRoot);
        initView();
        return mRoot;
    }

    protected void initView() {
        initAdapter();
    }

    private void initAdapter() {
        mTypeAdapter = new CustomTypeAdapter(0);
        mInfoAdapter = new CustomTypeAdapter(1);
        mTypeAdapter.setListener(this);

        mType.setLayoutManager(new LinearLayoutManager(getContext()));
        mInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        mType.setAdapter(mTypeAdapter);
        mInfo.setAdapter(mInfoAdapter);
        if (getArguments() != null && getArguments().containsKey("cid")) {
            cid = getArguments().getString("cid");
        }
    }

    @Override
    public void onItem(int position) {
        switch (position) {
            default:
            case 0:
                String info = "client：" + mTypeAdapter.getItem(position);
                ConnectEvent event = new ConnectEvent(position, info, System.currentTimeMillis());
                SessionManager.getInstance().writeToServer(new Gson().toJson(event));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConnectEvent(ConnectEvent event) {
        if (event != null) {
            String time = DateFormatUtils.format(event.getTime());
            String info = "TYPE：" + event.getType() + " info：" + event.getInfo() + "\ntime：" + time;
            mInfos.add(info);
            mInfoAdapter.addData(info);
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
