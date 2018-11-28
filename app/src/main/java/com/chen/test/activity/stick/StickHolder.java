package com.chen.test.activity.stick;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chen.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2017/12/6.
 * Class note:
 */

public class StickHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView mTitle;

    public StickHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
