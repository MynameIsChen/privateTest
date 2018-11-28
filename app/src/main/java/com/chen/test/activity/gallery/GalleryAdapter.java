package com.chen.test.activity.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chen.test.OnItemListener;
import com.chen.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxianglin on 2017/12/18.
 * Class note:
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryHolder> {
    private Context mContext;
    private OnItemListener mListener;
    private List<String> mData = new ArrayList<>();

    public GalleryAdapter(Context context) {
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

    @Override
    public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GalleryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gallery, parent, false));
    }

    @Override
    public void onBindViewHolder(GalleryHolder holder, int position) {
        holder.setIndex(position);
        holder.setListener(mListener);
        String info = mData.get(position);
        if (info == null) {
            info = "";
        }
        holder.mTxt.setText(info);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
