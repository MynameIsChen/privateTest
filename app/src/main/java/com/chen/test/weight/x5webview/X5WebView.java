package com.chen.test.weight.x5webview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chen.test.R;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class X5WebView extends LinearLayout {
    @BindView(R.id.x5webview_pg)
    ProgressBar mProgressBar;
    @BindView(R.id.x5webview)
    WebView mWebView;
    @BindView(R.id.x5webview_host)
    TextView mTextView;

    private X5WebViewClient x5WebViewClient = null;

    private boolean isAnimStart = false;
    private int currentProgress;

    private String webTitle = null;
    private Context mContext;

    public X5WebView(Context context) {
        this(context, null);
    }

    public X5WebView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public X5WebView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private WebViewClient client = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setAlpha(1.0f);

            setHostTitle(url);
        }

        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    private WebChromeClient chromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            currentProgress = mProgressBar.getProgress();
            if (newProgress >= 100 && !isAnimStart) {
                // 防止调用多次动画
                isAnimStart = true;
                mProgressBar.setProgress(newProgress);
                // 开启属性动画让进度条平滑消失
                startDismissAnimation(mProgressBar.getProgress());
            } else {
                // 开启属性动画让进度条平滑递增
                startProgressAnimation(newProgress);
            }
            //super.onProgressChanged(webView, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            webTitle = title;
        }

        @Override
        public boolean onJsPrompt(WebView webView, String s, String s1, String s2, JsPromptResult jsPromptResult) {
            if (x5WebViewClient != null) {
                return x5WebViewClient.onJsPrompt(webView, s, s1, s2, jsPromptResult);
            }
            return super.onJsPrompt(webView, s, s1, s2, jsPromptResult);
        }

        @Override
        public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
            if (x5WebViewClient != null) {
                return x5WebViewClient.onJsAlert(webView, s, s1, jsResult);
            }
            return super.onJsAlert(webView, s, s1, jsResult);
        }

        @Override
        public boolean onJsConfirm(WebView webView, String s, String s1, JsResult jsResult) {
            if (x5WebViewClient != null) {
                return x5WebViewClient.onJsConfirm(webView, s, s1, jsResult);
            }
            return super.onJsConfirm(webView, s, s1, jsResult);
        }
    };

    public void init(Context context, @Nullable AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.x5webview_layout, this);
        ButterKnife.bind(this);
        mContext = context;

        mWebView.setWebViewClient(client);
        mWebView.setWebChromeClient(chromeClient);
        initWebViewSettings();
        mWebView.getView().setClickable(true);
    }

    private void initWebViewSettings() {
        WebSettings webSetting = mWebView.getSettings();
        //支持缩放，默认为true。
        //webSetting.setSupportZoom(false);
        //调整图片至适合webview的大小
        //webSetting.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        //webSetting.setLoadWithOverviewMode(true);
        //设置默认编码
        //webSetting.setDefaultTextEncodingName("utf-8");
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
        //webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        //关闭webview中缓存
        //webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    //获得webview
    public WebView getWebView() {
        return mWebView;
    }

    //获得当前网址title
    public String getWebTitle() {
        return webTitle;
    }

    /**
     * progressBar递增动画
     */
    private void startProgressAnimation(int newProgress) {
        ObjectAnimator animator = ObjectAnimator.ofInt(mProgressBar, "progress", currentProgress, newProgress);
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    /**
     * progressBar消失动画
     */
    private void startDismissAnimation(final int progress) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(mProgressBar, "alpha", 1.0f, 0.0f);
        anim.setDuration(1500);  // 动画时长
        anim.setInterpolator(new DecelerateInterpolator());     // 减速
        // 关键, 添加动画进度监听器
        anim.addUpdateListener(valueAnimator -> {
            float fraction = valueAnimator.getAnimatedFraction();      // 0.0f ~ 1.0f
            int offset = 100 - progress;
            mProgressBar.setProgress((int) (progress + offset * fraction));
        });

        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束
                mProgressBar.setProgress(0);
                mProgressBar.setVisibility(View.GONE);
                isAnimStart = false;
            }
        });
        anim.start();
    }

    /*
     * 设置域名提示
     * */
    private void setHostTitle(String url) {
        Uri uri = Uri.parse(url).buildUpon().build();
        String host = uri.getHost();
        String hostTitle = getResources().getString(R.string.server_connect_error);//webview_host_title
        mTextView.setText(hostTitle);
    }

    /**
     * @param url 加载网址
     */
    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }


    /**
     * @param mothodName 方法名称
     */
    public void callJS(String mothodName) {
        callJS(mothodName, null);
    }

    /**
     * @param methodName 方法名称
     * @param data       方法参数 字符串
     */
    public void callJS(String methodName, String data) {
        callJS(methodName, data, null);
    }

    /**
     * @param methodName    方法名称
     * @param data          方法参数 字符串
     * @param valueCallback 回调
     */
    public void callJS(String methodName, String data, ValueCallback<String> valueCallback) {
        //拼接方法
        String method = methodName + "(";
        if (data != null) {
            method = method + data;
        }
        method = method + ")";
        mWebView.evaluateJavascript(method, valueCallback);
    }

    /**
     * @param x5WebViewClient
     */
    public void setX5WebViewClient(X5WebViewClient x5WebViewClient) {
        this.x5WebViewClient = x5WebViewClient;
    }
}
