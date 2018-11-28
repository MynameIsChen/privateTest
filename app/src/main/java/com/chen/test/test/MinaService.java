package com.chen.test.test;

import android.util.Log;

import com.chen.common.util.DateFormatUtils;
import com.chen.test.longconnect.ConnectEvent;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;

/**
 * Created by chenxianglin on 2018/5/29.
 */

public class MinaService {
    private static InetSocketAddress mAddress = new InetSocketAddress("192.168.2.30", 9123);

    public static void main(String[] args) {
        IoAcceptor acceptor = new NioSocketAcceptor();
        //添加日志过滤
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        //设置回调监听
        acceptor.setHandler(new DemoServerHandler());
        //设置读取大小
        acceptor.getSessionConfig().setReadBufferSize(2048);
        //设置超时时间
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        try {
            //开启监听
            acceptor.setDefaultLocalAddress(mAddress);
            acceptor.bind(mAddress);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class DemoServerHandler extends IoHandlerAdapter {

        @Override
        public void sessionCreated(IoSession session) throws Exception {
            super.sessionCreated(session);
        }

        /**
         * 一个客户端连接时回调的方法
         */
        @Override
        public void sessionOpened(IoSession session) throws Exception {
            super.sessionOpened(session);
        }

        /**
         * 接收到消息时回调的方法
         */
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            super.messageReceived(session, message);
            try {
                session.write("准备发送消息", mAddress);
                ConnectEvent readyEvent = new ConnectEvent(-1, "准备发送消息", System.currentTimeMillis());
                session.write(new Gson().toJson(readyEvent), mAddress);
                //接收客户端的消息
                Log.d("MinaService", "messageReceived:" + DateFormatUtils.format(System.currentTimeMillis()));
                ConnectEvent event = new ConnectEvent(-1, message.toString(), System.currentTimeMillis());
                try {
                    ConnectEvent receivedEvent = new Gson().fromJson(message.toString(), ConnectEvent.class);
                    String info = "service：" + receivedEvent.getInfo();
                    event = new ConnectEvent(receivedEvent.getType(), info, System.currentTimeMillis());
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }

                //发送消息给客户端
                session.write(new Gson().toJson(event), mAddress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 发送消息时回调的方法
         */
        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            super.messageSent(session, message);
        }

        /**
         * 一个客户端断开时回调的方法
         */
        @Override
        public void sessionClosed(IoSession session) throws Exception {
            super.sessionClosed(session);
        }
    }
}
