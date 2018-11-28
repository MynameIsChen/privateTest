package com.chen.test.activity.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chen.common.util.UIUtils;
import com.chen.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxianglin on 2017/11/27.
 * Class note:
 */

public class ListAdapter extends RecyclerView.Adapter<ItemHolder> {
    private Context mContext;
    private List<String> mData = new ArrayList<>();
    private OnItemListener mListener;
    private boolean mStagger;

    public ListAdapter(Context context) {
        mContext = context;
    }

    public void setListener(OnItemListener listener) {
        mListener = listener;
    }

    public void setData(List<String> list) {
        mData.clear();
        if (list != null && list.size() > 0) {
            mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setStagger(boolean stagger) {
        mStagger = stagger;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.setIndex(position);
        holder.setListener(mListener);

        String title = mData.get(position);
        int size = (int) UIUtils.dpToPx(mContext, 100);
        int h = (int) UIUtils.dpToPx(mContext, 50);

        ViewGroup.LayoutParams params = holder.mImg.getLayoutParams();
        if (!mStagger) {
            params.height = size + h * ((position + 1) % 2);
        } else {
            params.height = size;
        }
        holder.mImg.setLayoutParams(params);
        holder.mImg.setImageResource(R.drawable.ic_launcher_background);
        if (!TextUtils.isEmpty(title)) {
            holder.mTitle.setText(title);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
