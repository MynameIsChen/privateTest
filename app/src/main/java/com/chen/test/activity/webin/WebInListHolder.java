package com.chen.test.activity.webin;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chen.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2018/1/10.
 * Class note:
 */

public class WebInListHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView mTitle;
    View parent;

    public WebInListHolder(View itemView) {
        super(itemView);
        parent = itemView;
        ButterKnife.bind(this, itemView);
    }
}
