package com.donntu.kp.server.ui;

import com.donntu.kp.server.Main;
import com.donntu.kp.server.logger.Log;
import com.donntu.kp.server.observer.TextAreaObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML
    private Circle serverIndicator;

    @FXML
    private TextField portTF;

    @FXML
    private TextArea logTA;

    @FXML
    private ListView<String> objectsLV;

    @FXML
    private Button startBT;

    private Model model = new Model();


    private int port = 1050;

    private void setOnButtonAction() {
        startBT.setOnAction(event -> {
            if (startBT.getText().equals("Запустить сервер")) {
                if (portTF.getText().equals("")) {
                    portTF.setText(String.valueOf(port));
                }
                model.startServer(Integer.parseInt(portTF.getText()));
                Log.getInstance().log("Сервер запущен");
                serverIndicator.setFill(new Color(0.5, 1, 0.5, 1));
                startBT.setText("Остановить");
            } else {
                model.stopServer();
                Log.getInstance().log("Сервер остановлен");
                Log.getInstance().log("За сессию создано " + model.getCreatedCount() + " объектов");
                serverIndicator.setFill(new Color(1, 0.3, 0.3, 1));
                startBT.setText("Запустить сервер");
            }

        });
    }

    @FXML
    void initialize() {
        Main.setModel(model);
        objectsLV.setStyle("-fx-font-size: 16px");
        model.subscribeListView(objectsLV);
        setOnButtonAction();
        Log.getInstance().subscribe(new TextAreaObserver(logTA));
        portTF.setText(String.valueOf(port));
    }

}