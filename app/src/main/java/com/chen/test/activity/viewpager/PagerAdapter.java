package com.chen.test.activity.viewpager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chen.common.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxianglin on 2018/5/17.
 * Class note:
 */

public class PagerAdapter extends android.support.v4.view.PagerAdapter {
    private List<String> mData = new ArrayList<>();
    private List<ImageView> mViews = new ArrayList<>();
    private Context mContext;

    public PagerAdapter(Context context) {
        mContext = context;
    }

    public PagerAdapter(Context context, List<String> list) {
        mContext = context;
        mData.clear();
        mData.addAll(list);
    }

    public void setData(List<String> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView;
        if (mViews != null && mViews.size() > position) {
            imageView = mViews.get(position);
        } else {
            imageView = new ImageView(mContext);
            int margin = (int) UIUtils.dpToPx(mContext, 100);
            int w = UIUtils.getScreenWidth(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(w, margin));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(mData.get(position)).into(imageView);
            container.addView(imageView);
        }
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "标题" + position;
    }
}
