package com.chen.test.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chen.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/11/28下午2:19
 */
public class CoordinatorHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text)
    public TextView mText;

    public CoordinatorHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
