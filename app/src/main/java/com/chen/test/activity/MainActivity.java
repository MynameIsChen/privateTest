package com.chen.test.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.common.util.UIUtils;
import com.chen.test.R;
import com.chen.core.bean.UserEntity;
import com.chen.test.activity.list.GridActivity;
import com.chen.core.bean.CountryEntity;
import com.chen.test.activity.morelist.MoreListActivity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements QRCodeView.Delegate {
    public final String TAG = getClass().getName();
    public static final int REQUEST_CODE_CAMERA = 11;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.zxingview)
    ZXingView mZxingview;
    @BindView(R.id.zbarview)
    ZBarView mZbarview;
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.constraint)
    TextView mConstraint;
    @BindView(R.id.tv_permissions)
    TextView mTvPermissions;
    @BindView(R.id.list)
    TextView mList;
    @BindView(R.id.create)
    TextView mCreate;

    private Bitmap mBitmap;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initView();
    }

    private void initView() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScrollActivity.class));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toConstraint();
            }
        });

        mZxingview.setDelegate(this);
        jackson();
    }

    @OnClick({R.id.tv_permissions, R.id.list, R.id.create})
    public void onItemClick(View v) {
        switch (v.getId()) {
            case R.id.tv_permissions:
                MoreListActivity.launch(this);
                break;
            case R.id.list:
                GridActivity.launch(this);
                break;
            case R.id.create:
                createImg();
                break;
        }
    }

    private void toConstraint() {
        startActivity(new Intent(MainActivity.this, ConstraintActivity.class));
    }

    private void dbFlow() {
        UserEntity user = new UserEntity();
        user.setName("jack");
        user.setSex(1);
        user.setAge(30);
        user.save();
        user.setName("jackJeans");
        user.update();
        user.setName("rose");
        user.setSex(0);
        user.setAge(17);
        user.save();
        for (int i = 0; i < 1000; i++) {
            UserEntity temp = new UserEntity();
            temp.setName("temp" + i);
            temp.setSex(i % 2);
            temp.setAge((i + 1) % 3 * 10 + 1);
            temp.save();
        }
        List<UserEntity> list = SQLite.select().from(UserEntity.class).queryList();
//        SQLite.select().from(UserEntity.class).where(UserEntity_Table.age.greaterTen(10));
//        SQLite.select().from(UserEntity.class);
    }

    private void jackson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        CountryEntity item = new CountryEntity("1", "中国");
        try {
            Log.d("test", "start");
            String s = mapper.writeValueAsString(item);
            Log.d("test", "parse=" + s);
        } catch (JsonProcessingException e) {
            Log.d("test", "parse=end=" + e.getMessage());
            e.printStackTrace();
        }
        List<CountryEntity> list = new ArrayList<>();
        for (int i = 0; i < 235; i++) {
            list.add(item);
        }
        try {
            String i = mapper.writeValueAsString(list);
            Log.d("test", "parse=array=" + i);
        } catch (JsonProcessingException e) {
            Log.d("test", "parse=array=end=" + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            createImg();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createImg() {
        String qrCode = "www.baidu.com";
        int size = (int) UIUtils.dpToPx(this, 100);
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        setQrCode(qrCode, size, logo);
    }

    private void seek() {

    }

    private void setQrCode(final String url, final int size, final Bitmap logo) {
        Observable<Bitmap> observable = Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                mBitmap = QRCodeEncoder.syncEncodeQRCode(url, size, getResources().getColor(R.color.black), getResources().getColor(R.color.white), logo);
                e.onNext(mBitmap);
            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        mImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onStart() {
        mZxingview.startCamera();
        mZxingview.startSpot();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mZxingview.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZxingview.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        //Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
//        vibrate();//震动
        mZxingview.stopSpot();

        if (!TextUtils.isEmpty(result)) {
            mZxingview.stopCamera();
            mZxingview.onDestroy();

            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(ScanCodeActivity.this, ServiceManagerDianZhuNewActivity.class);
//            intent.putExtra("url", result);
//            //intent.setData(Uri.parse(result));
//            startActivity(intent);
//            finish();
        } else {
            Toast.makeText(this, "链接无效,请重新扫描", Toast.LENGTH_SHORT).show();
            mZxingview.startSpot();
        }
        Log.e(TAG, "扫码:06");
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "无相机权限,打开相机出错");

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
        Log.e(TAG, "扫码:07");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_CAMERA) {
            mZxingview.startCamera();
            mZxingview.startSpot();
        }
    }
}
