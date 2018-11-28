package com.chen.test.longconnect;

import android.util.Log;

import com.chen.core.bean.MinaNetEntity;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * Created by chenxianglin on 2018/5/29.
 * Class note:
 */

public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
    private String tag = "KeepAliveMessageFactoryImpl";
    private static final String ResponseStr = "keepalive_response";
    private static final String RequestStr = "keepalive_request";


    @Override
    public Object getRequest(IoSession session) {
        Log.e(tag, "getRequest");
        return getKeepAliveData();//返回心跳请求数据
    }

    private MinaNetEntity getKeepAliveData() {
        //....你的服务器心跳需要的数据
        return new MinaNetEntity();
    }


    @Override
    public Object getResponse(IoSession session, Object request) {
        Log.e(tag, "getResponse");
        return ResponseStr;//返回心跳请求返回数据，这里将数据进行处理封装成你想要的数据
    }


    //true if and only if the specified message is a keep-alive response message;
    @Override
    public boolean isRequest(IoSession session, Object message) {
        Log.e(tag, "isRequest");
        MinaNetEntity sm = (MinaNetEntity) message;
        if (sm != null && sm.getSendBody() != null && sm.getSendBody().equals(RequestStr)) {
            Log.e(tag, "isRequest : 是请求心跳包");
            return true;
        }
        return false;
    }

    //true if and only if the specified message is a keep-alive request message.
    @Override
    public boolean isResponse(IoSession session, Object message) {
        Log.e(tag, "isResponse");
        MinaNetEntity sm = (MinaNetEntity) message;
        if (sm != null && sm.getResponseBody() != null && sm.getResponseBody().trim().equals(ResponseStr)) {
            Log.e(tag, "isResponse : 是返回心跳包");
            return true;
        }
        return false;
    }
}
