package com.chen.test.activity.coordinator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxianglin on 2018/5/17.
 * Class note:
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return SingleFragment.getInstance(mTitles.get(position));
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }
}
