package com.chen.test.activity.launch;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.common.util.ToastUtil;
import com.chen.test.R;
import com.chen.test.activity.camera.CameraPreviewFragment;
import com.chen.test.activity.contacts.ContactsFragment;
import com.chen.test.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by chenxianglin on 2017/11/22.
 * Class note:
 */

@RuntimePermissions
public class LaunchActivity extends BaseActivity {

    @BindView(R.id.camera)
    TextView mCamera;
    @BindView(R.id.contacts)
    TextView mContacts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);

        initView();
    }

    protected void initView() {
        if (!PermissionUtils.hasSelfPermissions(this, Manifest.permission.CAMERA)) {
            LaunchActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
        }
        if (!PermissionUtils.hasSelfPermissions(this, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)) {
            LaunchActivityPermissionsDispatcher.showContactsWithPermissionCheck(this);
        }
        if (!PermissionUtils.hasSelfPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            LaunchActivityPermissionsDispatcher.showStorageWithPermissionCheck(this);
        }
        replaceFragment(LaunchFragment.newInstance(), "launch");
    }

    @OnClick({R.id.camera, R.id.contacts})
    public void OnItemClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                showCamera();
                break;
            case R.id.contacts:
                showContacts();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        LaunchActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
        PermissionUtils.hasSelfPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        PermissionUtils.hasSelfPermissions(this, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS);
        PermissionUtils.hasSelfPermissions(this, Manifest.permission.CAMERA);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        // NOTE: Perform action that requires the permission. If this is run by PermissionsDispatcher, the permission will have been granted
        replaceFragment(CameraPreviewFragment.newInstance(), "camera");
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        // or disabling certain functionality
        Toast.makeText(this, R.string.permission_camera_denied, Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(PermissionRequest request) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        showRationaleDialog(R.string.permission_camera_rationale, request);
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNeverAskAgain() {
        Toast.makeText(this, R.string.permission_camera_neverask, Toast.LENGTH_SHORT).show();
    }

    @NeedsPermission({Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS})
    void showContacts() {
        // NOTE: Perform action that requires the permission.
        // If this is run by PermissionsDispatcher, the permission will have been granted
        replaceFragment(ContactsFragment.newInstance(), "contacts");
    }

    @OnPermissionDenied({Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS})
    void onContactsDenied() {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        // or disabling certain functionality
        Toast.makeText(this, R.string.permission_contacts_denied, Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale({Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS})
    void showRationaleForContact(PermissionRequest request) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        showRationaleDialog(R.string.permission_contacts_rationale, request);
    }

    @OnNeverAskAgain({Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS})
    void onContactsNeverAskAgain() {
        Toast.makeText(this, R.string.permission_contacts_never_ask_again, Toast.LENGTH_SHORT).show();
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showStorage() {
        ToastUtil.showMsg("存储权限已获取");
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onStorageDenied() {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        // or disabling certain functionality
        Toast.makeText(this, R.string.permission_storage_denied, Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationaleForStorage(PermissionRequest request) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        showRationaleDialog(R.string.permission_storage_rationale, request);
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onStorageNeverAskAgain() {
        Toast.makeText(this, R.string.permission_storage_neverask, Toast.LENGTH_SHORT).show();
    }
}
