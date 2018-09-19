package com.donntu.kp.server.ui;

import com.donntu.kp.server.Server;

public class Model {
    private Server server = null;

    public void startServer(int port) {
        if (server == null) {
            server = new Server(port);
        }
        if (!server.isStart()) {
            server.start();
            server.setStart();
        }
    }

    public void stopServer() {
        if (server != null && server.isStart()) {
            server.stopListen();
        }
        server = null;
    }
}
