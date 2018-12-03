package com.chen.test.weight.x5webview;

import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebView;

public class X5WebViewClient {
    //拦截警告框
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        return false;
    }

    //拦截输入框
    public boolean onJsPrompt(WebView webView, String url, String msg, String result, JsPromptResult jsPromptResult) {
        return false;
    }

    //拦截确认框
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return false;
    }
}
