package com.chen.test.activity.thread;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxianglin on 2018/1/4.
 * Class note:
 */

public class ThreadAdapter extends FragmentPagerAdapter {
    private List<String> mTitles = new ArrayList<>();

    public ThreadAdapter(FragmentManager manager, List<String> titles) {
        super(manager);
        mTitles.addAll(titles);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        if (position >= 0 && mTitles.size() > position) {
            return ThreadFragment.getInstance(mTitles.get(position));
        } else {
            return null;
        }
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
