package com.chen.test.activity.webin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.common.util.UIUtils;
import com.chen.core.bean.ListItemEntity;
import com.chen.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxianglin on 2018/1/10.
 * Class note:
 */

public class WebInListAdapter extends RecyclerView.Adapter {
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_WEB = 2;

    private Context mContext;
    private List<ListItemEntity> mData = new ArrayList<>();

    public WebInListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<ListItemEntity> temp) {
        if (temp != null && temp.size() > 0) {
            mData.clear();
            mData.addAll(temp);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_web_in_list, parent, false);
            v.getLayoutParams().height = (int) UIUtils.dpToPx(mContext, 44);
            return new WebInListHolder(v);
        } else if (viewType == TYPE_WEB) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_web, parent, false);
            ViewGroup.LayoutParams params = layout.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            layout.setLayoutParams(params);
            return new WebHolder(layout);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItemEntity item = mData.get(position);
        if (item != null) {
            if (item.getType() == TYPE_ITEM) {
                WebInListHolder listHolder = (WebInListHolder) holder;
                listHolder.mTitle.setText("position==" + item.getValue());
                listHolder.parent.getLayoutParams().height = (int) UIUtils.dpToPx(mContext, 44);
            } else if (item.getType() == TYPE_WEB) {
                WebHolder webHolder = (WebHolder) holder;
                webHolder.mWeb.loadUrl(item.getValue());
                webHolder.parent.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        ListItemEntity item = mData.get(position);
        if (item != null) {
            return item.getType();
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
