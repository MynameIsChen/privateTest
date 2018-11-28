package com.chen.test.activity.launch;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chen.test.OnItemListener;
import com.chen.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2018/1/12.
 * Class note:
 */

public class LaunchItemHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.name)
    TextView mName;
    View parent;

    int index;
    OnItemListener mListener;

    public LaunchItemHolder(View itemView) {
        super(itemView);
        parent = itemView;
        ButterKnife.bind(this, itemView);
        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItem(0, getIndex());
                }
            }
        });
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public OnItemListener getListener() {
        return mListener;
    }

    public void setListener(OnItemListener listener) {
        mListener = listener;
    }
}
