package com.chen.test.activity.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2017/11/27.
 * Class note:
 */

public class ItemHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.title)
    TextView mTitle;

    int index;
    View parent;
    OnItemListener mListener;

    public ItemHolder(View itemView) {
        super(itemView);
        parent = itemView;
        ButterKnife.bind(this, itemView);

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItem(getIndex());
                }
            }
        });
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
