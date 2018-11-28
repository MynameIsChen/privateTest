package com.chen.test.weight.PageTransformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by chenxianglin on 2018/10/12.
 * Class note: 画廊效果Transformer（半透明+缩放）
 */

public class GalleryTransformer implements ViewPager.PageTransformer {
    private static final float MAX_ALPHA = 0.8f;
    private static final float MAX_SCALE = 0.95f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {
            //不可见区域
            page.setAlpha(MAX_ALPHA);
            page.setScaleX(MAX_SCALE);
            page.setScaleY(MAX_SCALE);
        } else {
            //可见区域，透明度效果
            if (position <= 0) {
                //pos区域[-1,0)
                page.setAlpha(MAX_ALPHA + MAX_ALPHA * (1 + position));
            } else {
                //pos区域[0,1]
                page.setAlpha(MAX_ALPHA + MAX_ALPHA * (1 - position));
            }
            //可见区域，缩放效果
            float scale = Math.max(MAX_SCALE, 1 - Math.abs(position));
            page.setScaleX(scale);
            page.setScaleY(scale);
        }
    }

    private void v(View page, float position) {
        int mOffset = 10;
        //移动X轴左边，使得卡片在同一坐标
        page.setTranslationX(-position * page.getWidth());
        //缩放卡片并调整位置
        float scale = (page.getWidth() - mOffset * position) / page.getWidth();
        page.setScaleX(scale);
        page.setScaleY(scale);
        //移动Y轴坐标
        page.setTranslationY(position * mOffset);
    }

    private void v1(View page, float position) {
        int mOffset = 10;
        //移动X轴左边，使得卡片在同一坐标
        page.setTranslationX(-position * page.getWidth());
        if (position <= 0) {
            //页面滑动的时候
            page.setTranslationX(0f);
            page.setRotation(45 * position);
            page.setTranslationX((page.getWidth() / 2 * position));
        } else {
            //页面不滑动的时候
            //移动X轴坐标，使得卡片在同一坐标
            page.setTranslationX(-position * page.getWidth());
            //缩放卡片并调整位置
            float scale = (page.getWidth() - mOffset * position) / page.getWidth();
            page.setScaleX(scale);
            page.setScaleY(scale);
            //移动Y轴坐标
            page.setTranslationY(position * mOffset);
        }
    }
}
