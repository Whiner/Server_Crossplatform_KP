package com.donntu.kp.server;

import com.donntu.kp.server.logger.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private boolean start = false;
    private DatagramSocket datagramSocket;
    private int port;
    private int buffer = 1024;
    private List<String> caughtStrings = new ArrayList<>();


    public Server(int port) {
        this.port = port;
    }

    public synchronized void startListen() throws SocketException {
        if (datagramSocket == null) {
            datagramSocket = new DatagramSocket(port);
        }
        start = true;
        while (start) {
            DatagramPacket pack = new DatagramPacket(new byte[buffer], buffer);
            try {
                datagramSocket.receive(pack);
            } catch (IOException e) {
                if (start) {
                    Log.getInstance().log("Ошибка работы с сокетом: " + e.getMessage());
                    start = false;
                }
            }
            String x = new String(pack.getData()).replaceAll("\u0000", "");
            if (!x.equals("")) {
                Log.getInstance().log("Принята строка (" + x + ")");
                caughtStrings.add(x);
            }
        }
    }

    public List<String> getCaughtStrings() {
        return caughtStrings;
    }

    public void stopListen() {
        start = false;
        datagramSocket.close();
    }

    public boolean isStart() {
        return start;
    }

    @Override
    public void run() {
        try {
            startListen();
        } catch (IOException e) {
            Log.getInstance().log("Ошибка работы сервера: " + e.getMessage());
        }
    }

    public void setStart() {
        this.start = true;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }
}
