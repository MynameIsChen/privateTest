package com.chen.test.activity.stick;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.test.R;
import com.jay.widget.StickyHeaders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxianglin on 2017/12/6.
 * Class note:
 */

public class StickAdapter extends RecyclerView.Adapter<StickHolder> implements StickyHeaders,StickyHeaders.ViewSetup {
    private Context mContext;
    private List<String> mData = new ArrayList<>();

    public StickAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<String> list) {
        mData.clear();
        if (list != null && list.size() > 0) {
            mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public StickHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StickHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_stick, parent, false));
    }

    @Override
    public void onBindViewHolder(StickHolder holder, int position) {
        String item = mData.get(position);
        if (item == null || item.equals("")) {
            item = "";
        }
        holder.mTitle.setText(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public boolean isStickyHeader(int i) {
        if (i % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setupStickyHeaderView(View stickyHeader) {
        ViewCompat.setElevation(stickyHeader, 10);
    }

    @Override
    public void teardownStickyHeaderView(View stickyHeader) {
        ViewCompat.setElevation(stickyHeader, 0);
    }
}
