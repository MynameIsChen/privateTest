package com.chen.test.activity.morelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.test.OnItemListener;
import com.chen.test.R;
import com.chen.core.bean.TitleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxianglin on 2017/11/24.
 * Class note:
 */

public class MoreListAdapter extends RecyclerView.Adapter<MoreListHolder> {
    private Context mContext;
    private OnItemListener mListener;
    private List<TitleEntity> mData = new ArrayList<>();
    private int width = 0;
    private int parentIndex;

    public MoreListAdapter(Context context, int w, int parentIndex) {
        mContext = context;
        width = w;
        this.parentIndex = parentIndex;
    }

    public void setListener(OnItemListener listener) {
        mListener = listener;
    }

    public void setData(List<TitleEntity> list) {
        mData.clear();
        if (list != null && list.size() > 0) {
            mData.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public MoreListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_more_list, parent, false);
        if (width > 0) {
            v.getLayoutParams().width = width;
        }
        return new MoreListHolder(v);
    }

    @Override
    public void onBindViewHolder(MoreListHolder holder, int position) {
        holder.setIndex(position);
        holder.setListener(mListener);
        holder.setParentIndex(parentIndex);
        TitleEntity item = mData.get(position);
        if (item != null) {
            holder.mTitle.setText(item.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
