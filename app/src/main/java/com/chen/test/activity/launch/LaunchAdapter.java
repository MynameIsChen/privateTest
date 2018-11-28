package com.chen.test.activity.launch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.test.OnItemListener;
import com.chen.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxianglin on 2018/1/12.
 * Class note:
 */

public class LaunchAdapter extends RecyclerView.Adapter<LaunchItemHolder> {
    private Context mContext;
    private List<String> mData = new ArrayList<>();
    private OnItemListener mListener;
    private int w;

    public LaunchAdapter(Context context, int w) {
        mContext = context;
        this.w = w;
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

    @Override
    public LaunchItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_launch, parent, false);
        ViewGroup.LayoutParams params = v.getLayoutParams();
        params.width = w;
        v.setLayoutParams(params);
        return new LaunchItemHolder(v);
    }

    @Override
    public void onBindViewHolder(LaunchItemHolder holder, int position) {
//        holder.parent.getLayoutParams().width = w;
        holder.setIndex(position);
        holder.setListener(mListener);
        holder.mName.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
