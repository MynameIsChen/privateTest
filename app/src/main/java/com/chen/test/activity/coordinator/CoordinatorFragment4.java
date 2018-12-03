package com.chen.test.activity.coordinator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chen.common.util.Lg;
import com.chen.test.R;
import com.chen.test.base.BaseFragment;
import com.chen.test.databinding.FragmentCoordinator4Binding;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/11/28上午11:48
 */
public class CoordinatorFragment4 extends BaseFragment {
    private FragmentCoordinator4Binding mBinding;


    public static CoordinatorFragment4 newInstance() {
        return new CoordinatorFragment4();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_coordinator4, null, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView() {
        initWeb();
    }

    private void initWeb() {
        WebSettings webSetting = mBinding.web.getSettings();
        //支持缩放，默认为true。
        //webSetting.setSupportZoom(false);
        //调整图片至适合webview的大小
        webSetting.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSetting.setLoadWithOverviewMode(true);
        //设置默认编码
        webSetting.setDefaultTextEncodingName("utf-8");
        //设置自动加载图片
        //webSetting.setLoadsImagesAutomatically(true);

        //允许访问文件
        //webSetting.setAllowFileAccess(true);
        //开启javascript
        webSetting.setJavaScriptEnabled(true);
        //支持通过JS打开新窗口
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        //提高渲染的优先级
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //支持内容重新布局
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        //关闭webview中缓存
        //webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mBinding.web.loadUrl("http://gunuoapi.weiyingjia.org/gunuo/details.html?uid=17&g_id=59");
        mBinding.web.setWebChromeClient(new WebChromeClient());
        mBinding.web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                int height = view.getContentHeight();
                Lg.d("CoordinatorFragment4", "height=" + height);
                super.onPageFinished(view, url);
            }
        });
    }

}
