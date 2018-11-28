package com.chen.test.activity.webin;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.chen.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2018/1/10.
 * Class note:
 */

public class WebHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.web)
    WebView mWeb;
    View parent;

    public WebHolder(View itemView) {
        super(itemView);
        parent = itemView;
        ButterKnife.bind(this, itemView);

        if (Build.VERSION.SDK_INT >= 19) {
            mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }
}
