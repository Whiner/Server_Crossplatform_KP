package com.donntu.kp.server.observer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class ListViewObserver implements IObserver {
    private ListView<String> listView;

    public ListViewObserver(ListView<String> listView) {
        this.listView = listView;
    }

    @Override
    public synchronized void update(String string) {
        Platform.runLater(() -> listView.getItems().add(string));
    }
}
