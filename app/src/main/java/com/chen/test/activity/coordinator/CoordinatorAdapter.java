package com.chen.test.activity.coordinator;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chen.test.R;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/12/6下午1:26
 */
public class CoordinatorAdapter extends BaseQuickAdapter<String, CoordinatorItemHolder> {
    private int currentIndex = -1;

    public CoordinatorAdapter() {
        super(R.layout.adapter_coordinator_item);
    }

    public void setCurrentIndex(int index) {
        currentIndex = index;
        notifyDataSetChanged();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    @Override
    protected void convert(CoordinatorItemHolder helper, String item) {
        TextView textView = helper.getView(R.id.text);
        textView.setText(item);
        textView.setTextColor(mContext.getResources().getColor(currentIndex == helper.getAdapterPosition() ? R.color.red : R.color.black));
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        return super.getItemView(layoutResId, parent);
    }
}
