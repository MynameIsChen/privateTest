package com.chen.test.test.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by chenxianglin on 2018/5/18.
 * Class note:
 */

public class GreetingClient {

    public static void main(String[] arg) {
        String serverName = arg[0];
        int port = Integer.parseInt(arg[1]);
        try {
            Socket socket = new Socket(serverName, port);
            System.out.println("远程主机地址：" + socket.getRemoteSocketAddress());
            OutputStream out = socket.getOutputStream();
            DataOutputStream dataOut = new DataOutputStream(out);

            dataOut.writeUTF("Hello from " + socket.getLocalSocketAddress());
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            System.out.println("服务器响应：" + dataInputStream.readUTF());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
