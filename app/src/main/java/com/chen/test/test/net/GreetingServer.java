package com.chen.test.test.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by chenxianglin on 2018/5/18.
 * Class note:
 */

public class GreetingServer extends Thread {

    private ServerSocket mServerSocket;

    public GreetingServer(int port) throws IOException {
        mServerSocket = new ServerSocket(port);
        mServerSocket.setSoTimeout(10000);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("等待远程连接，端口号为：" + mServerSocket.getLocalPort() + "...");
                Socket server = mServerSocket.accept();
                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
                DataInputStream dataInputStream = new DataInputStream(server.getInputStream());
                System.out.println(dataInputStream.readUTF());
                DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
                dataOutputStream.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodBye!");
                server.close();
            } catch (SocketTimeoutException e) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } finally {

            }
        }
    }

    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            Thread t = new GreetingServer(port);
            t.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
