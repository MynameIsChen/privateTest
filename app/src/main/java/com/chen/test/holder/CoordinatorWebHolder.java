package com.chen.test.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;

import com.chen.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/11/28下午2:19
 */
public class CoordinatorWebHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.web)
    public WebView mWebView;

    public CoordinatorWebHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
