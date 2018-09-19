package com.donntu.kp.server.datawork;

import com.donntu.kp.server.datawork.creations.interfaces.Bird;
import com.donntu.kp.server.datawork.creations.interfaces.Creation;
import com.donntu.kp.server.datawork.creations.interfaces.Mammal;
import com.donntu.kp.server.logger.Log;

import java.util.ArrayList;
import java.util.List;

public class InstanceCreatorController {
    private static List<InstanceCreator> instanceCreators = new ArrayList<>();
    private static List<String> strings = new ArrayList<>();
    private static List<Creation> creations = new ArrayList<>();

    public static void start() throws InterruptedException {
        for (String line : strings) {
            String[] split;
            try {
                split = Spliter.split(line);
            } catch (Exception e) {
                continue;
            }
            switch (split[0]) {
                case "BIRD":
                    Log.getInstance().log("Получен объект типа Bird");
                    instanceCreators.add(new InstanceCreator(split, Bird.class));
                    break;
                case "MAMMAL":
                    Log.getInstance().log("Получен объект типа Mammal");
                    instanceCreators.add(new InstanceCreator(split, Mammal.class));
                    break;
                default:
                    Log.getInstance().log("Тип объекта неопределен. (" + split[0] + ")");
            }
        }

        for (InstanceCreator instanceCreator : instanceCreators) {
            instanceCreator.start();
            Log.getInstance().log("Поток " + instanceCreator.getName() + " начал свою работу");
        }
        for (InstanceCreator instanceCreator : instanceCreators) {
            instanceCreator.join();
        }

        for (InstanceCreator instanceCreator : instanceCreators) {
            creations.add(instanceCreator.getCreated());
        }
    }

    public static void addString(String string) {
        strings.add(string);
    }

    public static List<Creation> getCreations() {
        return creations;
    }
}
