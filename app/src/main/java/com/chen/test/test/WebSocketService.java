package com.chen.test.test;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Collection;

/**
 * Created by chenxianglin on 2018/6/2.
 * Class note:
 */

public class WebSocketService extends WebSocketServer {

    public WebSocketService(int port) {
        super(new InetSocketAddress(port));
    }

    public WebSocketService(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        String address = conn.getRemoteSocketAddress().getAddress().getHostAddress();
        String message = String.format("(%s) <进入房间！>", address);
        sendAll(message);
        print(message);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        String address = conn.getRemoteSocketAddress().getAddress().getHostAddress();
        String message = String.format("(%s) <退出房间！>", address);
        sendAll(message);
        print(message);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        String address = conn.getRemoteSocketAddress().getAddress().getHostAddress();
        String msg = String.format("(%s) %s", address, message);
        sendAll(msg);
        print(message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        if (conn != null) {
            conn.close(0);
        }
        ex.printStackTrace();
    }

    private static void print(String msg) {
        System.out.println(String.format("[%d] %s", System.currentTimeMillis(), msg));
    }

    public void sendAll(String msg) {
        Collection<WebSocket> connections = connections();
        for (WebSocket webSocket : connections) {
            webSocket.send(msg);
        }
    }
}
