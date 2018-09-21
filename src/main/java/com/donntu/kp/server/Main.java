package com.donntu.kp.server;

import com.donntu.kp.server.datawork.Serializer;
import com.donntu.kp.server.ui.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void setModel(Model model) {
        Main.model = model;
    }

    private static Model model;

    public static void main(String[] args) {
        launch(args);
        //Server.startListen();
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml/"));
        primaryStage.setTitle("Сервер");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> {
            model.stopServer();
            Serializer.serialize(model.getCreations(), "serialized.bin");
        });
    }
}
