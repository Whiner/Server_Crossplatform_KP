package com.donntu.kp.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerLogic {
    public static void startListen() {
        try {
            DatagramSocket ds = new DatagramSocket(1050);
            while (true) {
                DatagramPacket pack = new DatagramPacket(new byte[1024], 1024);
                ds.receive(pack);
                String x = new String(pack.getData());
                System.out.println(x.replaceAll("\u0000", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
