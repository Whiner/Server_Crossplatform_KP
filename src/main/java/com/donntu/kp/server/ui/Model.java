package com.donntu.kp.server.ui;

import com.donntu.kp.server.Server;
import com.donntu.kp.server.datawork.InstanceCreatorController;
import com.donntu.kp.server.datawork.creations.interfaces.Creation;
import com.donntu.kp.server.observer.ListViewObserver;
import javafx.scene.control.ListView;

import java.util.List;


public class Model {
    private Server server = null;
    private InstanceCreatorController instanceCreatorController = new InstanceCreatorController();

    public void startServer(int port) {
        if (server == null) {
            server = new Server(port);
            server.subscribe(instanceCreatorController);
        }
        if (!server.isStart()) {
            server.start();
            server.setStart();
        }
    }

    public int getCreatedCount() {
        return instanceCreatorController.getCreations().size();
    }

    public void stopServer() {
        if (server != null && server.isStart()) {
            server.stopListen();
        }
        server = null;
    }

    public void subscribeListView(ListView<String> listView) {
        instanceCreatorController.subscribe(new ListViewObserver(listView));
    }

    public List<Creation> getCreations() {
        return instanceCreatorController.getCreations();
    }
}
