package com.chen.test.test;

import com.chen.test.websocket.SocketConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by chenxianglin on 2018/6/2.
 * Class note:
 */

public class WebsocketMain {

    public static void main(String[] args) {
        WebSocketService service = new WebSocketService(SocketConstants.SOCKET_PORT);
        service.start();

        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = service.getPort();
            System.out.println(String.format("服务器已启动：%s:%d", ip, port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        while (true){
            try {
                String msg = reader.readLine();
                service.sendAll(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
