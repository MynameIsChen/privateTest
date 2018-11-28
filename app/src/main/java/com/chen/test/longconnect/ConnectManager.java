package com.chen.test.longconnect;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;

/**
 * Created by chenxianglin on 2018/5/29.
 * Class note: 连接的配置文件
 */

public class ConnectManager {
    //    public static final String BROADCAST_ACTION = "com.example.lyf.longconnect";
    public static final String MESSAGE = "message";

    private ConnectConfig mConfig;//配置文件
    private WeakReference<Context> mContext;
    private NioSocketConnector mConnection;
    private IoSession mSession;
    private InetSocketAddress mAddress;

    public ConnectManager(ConnectConfig mConfig) {
        this.mConfig = mConfig;
        this.mContext = new WeakReference<Context>(mConfig.getContext());
        init();
    }

    private void init() {
        EventBus.getDefault().post(new ConnectEvent("ConnectManager-init"));
        mAddress = new InetSocketAddress(mConfig.getIp(), mConfig.getPort());
        //创建连接对象
        mConnection = new NioSocketConnector();
        //设置连接地址
        mConnection.setDefaultRemoteAddress(mAddress);
        //设置接收缓存区大小
        mConnection.getSessionConfig().setReadBufferSize(mConfig.getReadBufferSize());
//        mConnection.getSessionConfig().setReceiveBufferSize(65536);//读取缓冲去大小最大65536，64KB
//        mConnection.getSessionConfig().setMinReadBufferSize(1024*64);//读取缓冲去大小最大65536，64KB
        //设置日志
        mConnection.getFilterChain().addLast("logger", new LoggingFilter());
        //配置解码器与编码器
        mConnection.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
//        mConnection.getFilterChain().addLast("codec", new ProtocolCodecFilter(new AppProtocolCodecFactory()));

        //设置连接监听/配置数据返回处理
        mConnection.setHandler(new DefaultHandler(mContext.get()));

        //设置心跳工程
        KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
        //// 当读操作空闲时发送心跳
        KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory);
        // 设置心跳包请求后超时无反馈情况下的处理机制，默认为关闭连接,在此处设置为输出日志提醒
        heartBeat.setRequestTimeoutHandler(KeepAliveRequestTimeoutHandler.LOG);
        /** 是否回发 */
        heartBeat.setForwardEvent(false);
        /** 发送频率 */
        heartBeat.setRequestInterval(60);// 单位应该是秒
        /** 设置心跳包请求后 等待反馈超时时间。 超过该时间后则调用KeepAliveRequestTimeoutHandler.CLOSE */
        heartBeat.setRequestTimeout(15);//  单位应该是秒
        mConnection.getSessionConfig().setKeepAlive(true);
        mConnection.getFilterChain().addLast("heartbeat", heartBeat);

    }

    private static class DefaultHandler extends IoHandlerAdapter {
        private Context context;

        public DefaultHandler(Context context) {
            this.context = context;
        }

        /**
         * 连接成功时回调的方法
         *
         * @param session
         * @throws Exception
         */
        @Override
        public void sessionOpened(IoSession session) throws Exception {
            //当与服务器连接成功时,将我们的session保存到我们的sesscionmanager类中,从而可以发送消息到服务器
            EventBus.getDefault().post(new ConnectEvent("与服务器连接成功"));
            SessionManager.getInstance().setIoSession(session);
        }

        /**
         * 接收到消息时回调的方法
         *
         * @param session
         * @param message
         * @throws Exception
         */
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            EventBus.getDefault().post(new ConnectEvent("接收到消息"));
            if (context != null) {
                //将接收到的消息利用广播发送出去
                String info = message.toString();
                ConnectEvent event = new ConnectEvent(-1, info);
                try {
                    event = new Gson().fromJson(info, ConnectEvent.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
                EventBus.getDefault().post(event);
            }
        }
    }

    /**
     * 与服务器连接的方法
     *
     * @return
     */
    public boolean connect() {
        EventBus.getDefault().post(new ConnectEvent("connect"));
        try {
            ConnectFuture future = mConnection.connect();
//            ConnectFuture future = mConnection.connect(new InetSocketAddress());
            future.awaitUninterruptibly();//等待连接创建完成
            mSession = future.getSession();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
//        mSession.getCloseFuture().awaitUninterruptibly();//等待断开连接
        return mSession == null ? false : true;
    }

    /**
     * 断开连接的方法
     */
    public void disConnect() {
        EventBus.getDefault().post(new ConnectEvent("disConnect"));
        if (mConnection != null) {
            mConnection.dispose();
            mConnection = null;
        }
        mSession = null;
        mAddress = null;
        mContext = null;
    }

    /**
     * 连接的配置文件
     * Created by lyf on 2017/5/20.
     */

    public static class ConnectConfig {
        private Context mContext;
        private String ip;
        private int port;
        private int readBufferSize; //缓存大小
        private long connectionTimeout;//连接超时时间

        public Context getContext() {
            return mContext;
        }


        public String getIp() {
            return ip;
        }


        public int getPort() {
            return port;
        }


        public int getReadBufferSize() {
            return readBufferSize;
        }


        public long getConnectionTimeout() {
            return connectionTimeout;
        }


        public static class Builder {
            private Context mContext;
            private String ip = "";
            private int port = 9123;
            private int readBufferSize = 10240; //缓存大小
            private long connectionTimeout = 10000;//连接超时时间


            public Builder(Context mContext) {
                this.mContext = mContext;
            }

            public Builder setConnectionTimeout(long connectionTimeout) {
                this.connectionTimeout = connectionTimeout;
                return this;
            }

            public Builder setIp(String ip) {
                this.ip = ip;
                return this;
            }

            public Builder setmContext(Context mContext) {
                this.mContext = mContext;
                return this;
            }

            public Builder setPort(int port) {
                this.port = port;
                return this;
            }

            public Builder setReadBufferSize(int readBufferSize) {
                this.readBufferSize = readBufferSize;
                return this;
            }

            public ConnectConfig build() {
                ConnectConfig connectConfig = new ConnectConfig();
                connectConfig.connectionTimeout = this.connectionTimeout;
                connectConfig.ip = this.ip;
                connectConfig.port = this.port;
                connectConfig.mContext = this.mContext;
                connectConfig.readBufferSize = this.readBufferSize;
                return connectConfig;
            }
        }
    }
}
