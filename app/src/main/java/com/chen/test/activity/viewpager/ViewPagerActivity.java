package com.chen.test.activity.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.chen.common.util.UIUtils;
import com.chen.test.R;
import com.chen.test.base.BaseActivity;
import com.chen.test.weight.PageTransformer.GalleryTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2018/10/12.
 * Class note:
 */

public class ViewPagerActivity extends BaseActivity {
    @BindView(R.id.pager)
    ViewPager mViewPager;
    private PagerAdapter mAdapter;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, ViewPagerActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        mAdapter = new PagerAdapter(this, getImgs());
        mViewPager.setOffscreenPageLimit(3);
        int pageMargin = (int) UIUtils.dpToPx(this, 0);//page边距
        int visibleWidth = (int) UIUtils.dpToPx(this, 28);//重叠部分可视区域
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mViewPager.getLayoutParams();
        params.width = UIUtils.getScreenWidth(this) - 2 * pageMargin - 2 * visibleWidth;//重设viewPager宽度
        params.height = (int) UIUtils.dpToPx(this, 200);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageMargin(pageMargin);
        mViewPager.setPageTransformer(true, new GalleryTransformer());//滑动效果
    }

    private List<String> getImgs() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("http://d.hiphotos.baidu.com/image/pic/item/4034970a304e251f569f36f6aa86c9177f3e5350.jpg");
            list.add("http://d.hiphotos.baidu.com/image/pic/item/c995d143ad4bd113904f7de451afa40f4afb0562.jpg");
            list.add("http://g.hiphotos.baidu.com/image/pic/item/8718367adab44aed9808c6edbf1c8701a18bfb71.jpg");
            list.add("http://f.hiphotos.baidu.com/image/h%3D300/sign=1ee0b4c4dc58ccbf04bcb33a29d8bcd4/aa18972bd40735fa4bf353cf93510fb30f240840.jpg");
            list.add("http://h.hiphotos.baidu.com/image/h%3D300/sign=bdb1172ebd7eca800d053fe7a1229712/5fdf8db1cb134954068359695b4e9258d1094a19.jpg");
        }
        return list;
    }
}
