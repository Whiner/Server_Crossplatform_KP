package com.donntu.kp.server.datawork;


import com.donntu.kp.server.datawork.creations.interfaces.Creation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
    public static void serialize(List<Creation> creations, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename)) {
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (Creation creation : creations) {
                out.writeObject(creation);
                //Log.getInstance().log("Объект " + creation + " успешно серилизован");
            }
            //Log.getInstance().log("Сериализация прошла успешно");
        } catch (IOException e) {
            //Log.getInstance().log("Ошибка сериализации: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Creation> deserialize(String filename) {
        try (FileInputStream fileIn = new FileInputStream(filename)) {
            List<Creation> creations = new ArrayList<>();
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Creation c;
            try {
                while ((c = (Creation) in.readObject()) != null) {
                    creations.add(c);
                    //Log.getInstance().log("Объект " + c + " успешно десерилизован");
                }
            } catch (EOFException ignored) {
            }
            in.close();
            //Log.getInstance().log("Десериализация прошла успешно");
            return creations;
        } catch (IOException | ClassNotFoundException e) {
            //Log.getInstance().log("Ошибка десериализации: " + e.getMessage());
            e.printStackTrace();
        }
        return null;

    }
}
