package com.chen.test.activity.morelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chen.test.OnItemListener;
import com.chen.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2017/11/24.
 * Class note:
 */

public class MoreListHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView mTitle;
    View parent;

    OnItemListener mListener;
    int index;
    int parentIndex;

    public MoreListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        parent = itemView;

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItem(getParentIndex(), getIndex());
                }
            }
        });
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
    }

    public void setListener(OnItemListener listener) {
        mListener = listener;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
