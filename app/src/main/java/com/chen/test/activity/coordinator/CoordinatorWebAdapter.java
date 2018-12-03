package com.chen.test.activity.coordinator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import com.chen.test.R;
import com.chen.test.holder.CoordinatorWebHolder;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/12/3下午1:24
 */
public class CoordinatorWebAdapter extends RecyclerView.Adapter<CoordinatorWebHolder> {

    @Override
    public CoordinatorWebHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CoordinatorWebHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coordinator_web, parent, false));
    }

    @Override
    public void onBindViewHolder(CoordinatorWebHolder holder, int position) {
        initWeb(holder);
        holder.mWebView.loadUrl("http://gunuoapi.weiyingjia.org/gunuo/details.html?uid=17&g_id=59");
    }

    private void initWeb(CoordinatorWebHolder holder) {
        WebSettings webSetting = holder.mWebView.getSettings();
//        //支持缩放，默认为true。
//        //webSetting.setSupportZoom(false);
//        //调整图片至适合webview的大小
//        webSetting.setUseWideViewPort(true);
//        // 缩放至屏幕的大小
//        webSetting.setLoadWithOverviewMode(true);
//        //设置默认编码
//        webSetting.setDefaultTextEncodingName("utf-8");
//        //设置自动加载图片
//        //webSetting.setLoadsImagesAutomatically(true);
//
//        //允许访问文件
//        //webSetting.setAllowFileAccess(true);
//        //开启javascript
        //todo 部分url的内容需要js扩展出来，所以这个需要打开，再使用warp_content可以使内容完全展开，在recyclerView中
        webSetting.setJavaScriptEnabled(true);
//        //支持通过JS打开新窗口
//        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
//        //提高渲染的优先级
//        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        //支持内容重新布局
//        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//        //关闭webview中缓存
//        //webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        holder.mWebView.setWebChromeClient(new WebChromeClient());
//        holder.mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                int height = view.getContentHeight();
//                Lg.d("CoordinatorWebAdapter", "height=" + height);
//                super.onPageFinished(view, url);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
