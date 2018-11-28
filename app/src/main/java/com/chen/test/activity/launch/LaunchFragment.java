package com.chen.test.activity.launch;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chen.test.R;
import com.chen.test.activity.coordinator.CoordinatorActivity;
import com.chen.test.activity.MainActivity;
import com.chen.test.activity.PlayerActivity;
import com.chen.test.activity.RxActivity;
import com.chen.test.activity.TouchActivity;
import com.chen.test.activity.gallery.GalleryActivity;
import com.chen.test.activity.stick.StickActivity;
import com.chen.test.activity.thread.ThreadActivity;
import com.chen.test.activity.viewpager.ViewPagerActivity;
import com.chen.test.activity.webin.WebInListActivity;
import com.chen.test.base.BaseFragment;
import com.chen.test.longconnect.LongConnectActivity;
import com.chen.test.websocket.SocketActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.PermissionUtils;

/**
 * Created by chenxianglin on 2018/5/24.
 * Class note:
 */

public class LaunchFragment extends BaseFragment {
    public static final int REQUEST_CODE_CAMERA = 11;

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.viewTouch)
    TextView mTouch;
    @BindView(R.id.stick)
    TextView mStick;
    private View mRoot;

    public static LaunchFragment newInstance() {
        LaunchFragment fragment = new LaunchFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            mRoot = inflater.inflate(R.layout.fragment_launch, container, false);
            ButterKnife.bind(this, mRoot);
        }
        return mRoot;
    }


    @OnClick({R.id.title, R.id.viewTouch, R.id.stick, R.id.coordinator, R.id.pager, R.id.rx, R.id.thread, R.id.web,
            R.id.router, R.id.dagger, R.id.recyclerView, R.id.camera, R.id.longConnect, R.id.socket, R.id.player,R.id.viewPager})
    public void onItemClick(View v) {
        switch (v.getId()) {
            case R.id.title:
                MainActivity.launch(getActivity());
                break;
            case R.id.viewTouch:
                TouchActivity.launch(getActivity());
                break;
            case R.id.stick:
                StickActivity.launch(getActivity());
                break;
            case R.id.coordinator:
                CoordinatorActivity.launch(getActivity());
                break;
            case R.id.pager:
                GalleryActivity.launch(getActivity());
                break;
            case R.id.rx:
                RxActivity.launch(getActivity());
                break;
            case R.id.thread:
                ThreadActivity.launch(getActivity());
                break;
            case R.id.web:
                WebInListActivity.launch(getActivity());
                break;
            case R.id.router:
                Bundle bundle = new Bundle();
                bundle.putString("title", "This is a title!");
                ARouter.getInstance().build("/router/routerActivity").with(bundle).navigation();
                break;
            case R.id.dagger:
                ARouter.getInstance().build("/pro/proActivity").navigation();
                break;
            case R.id.recyclerView:
                LaunchGridActivity.launch(getActivity());
                break;
            case R.id.camera:
//                toCamera();
                break;
            case R.id.longConnect:
                LongConnectActivity.launch(getActivity());
                break;
            case R.id.socket:
                SocketActivity.launch(getActivity());
                break;
            case R.id.player:
                PlayerActivity.launch(getActivity());
                break;
            case R.id.viewPager:
                ViewPagerActivity.launch(getActivity());
                break;
        }
    }

    private void toCamera() {
        if (PermissionUtils.hasSelfPermissions(getContext(), Manifest.permission.CAMERA)) {
            String parentPath = Environment.getExternalStorageDirectory() + File.separator + "test" + File.separator;
            File cameraFile = new File(parentPath, System.currentTimeMillis() + ".jpg");
            startActivityForResult(
                    new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile)),
                    REQUEST_CODE_CAMERA);
        }
    }
}
