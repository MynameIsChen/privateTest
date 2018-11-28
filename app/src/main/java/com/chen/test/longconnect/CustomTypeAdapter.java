package com.chen.test.longconnect;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.test.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2018/6/1.
 * Class note:
 */

public class CustomTypeAdapter extends RecyclerView.Adapter<CustomTypeAdapter.TypeHolder> {
    private OnItemListener mListener;
    private int mType;
    private List<String> mData = new ArrayList<>();

    public static String[] types = new String[]{"加入", "离开", "切换", "观众", "准备", "开始", "答题", "判定", "语音", "结束", "礼物"};

    public CustomTypeAdapter(int type) {
        mType = type;
        if (mType == 0) {
            mData = Arrays.asList(types);
        }
    }

    public void setData(List<String> data) {
        if (mData != null && mData.size() > 0) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addData(String info) {
        if (mData == null || mData.size() == 0) {
            mData = new ArrayList<>();
        }
        mData.add(info);
        notifyDataSetChanged();
    }

    public String getItem(int position) {
        if (mData != null && mData.size() > 0) {
            return mData.get(position);
        }
        return "";
    }

    public void setListener(OnItemListener listener) {
        mListener = listener;
    }

    @Override
    public TypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (mType == 1) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_custom_info, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_custom_item, parent, false);
        }
        return new TypeHolder(v);
    }

    @Override
    public void onBindViewHolder(TypeHolder holder, int position) {
        holder.mInfo.setText(TextUtils.isEmpty(mData.get(position)) ? "" : mData.get(position));
        if (mType == 0)
            holder.setListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class TypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.info)
        TextView mInfo;
        View mRoot;

        OnItemListener mListener;

        public TypeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mRoot = itemView;
            itemView.setOnClickListener(this);
        }

        public OnItemListener getListener() {
            return mListener;
        }

        public void setListener(OnItemListener listener) {
            mListener = listener;
        }

        @Override
        public void onClick(View v) {
            if (v == mRoot)
                mListener.onItem(getAdapterPosition());
        }
    }
}
