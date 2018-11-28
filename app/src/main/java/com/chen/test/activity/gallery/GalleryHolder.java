package com.chen.test.activity.gallery;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chen.test.OnItemListener;
import com.chen.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2017/12/18.
 * Class note:
 */

public class GalleryHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt)
    TextView mTxt;
    View parent;

    int index;
    OnItemListener mListener;

    public GalleryHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        parent = itemView;

        parent.setOnClickListener(new View.OnClickListener() {
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
