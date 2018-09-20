package com.donntu.kp.server.datawork;


import com.donntu.kp.server.datawork.creations.Sex;
import com.donntu.kp.server.datawork.creations.interfaces.Bird;
import com.donntu.kp.server.datawork.creations.interfaces.Creation;
import com.donntu.kp.server.datawork.creations.interfaces.Mammal;
import com.donntu.kp.server.logger.Log;

import java.util.Objects;

public class InstanceCreator extends Thread {
    private String string;
    private Creation created;

    public InstanceCreator(String string) {
        this.string = string;
    }

    public Creation getCreated() {
        return created;
    }

    @Override
    public void run() {
        String[] split;
        try {
            split = parse(string);
        } catch (Exception e) {
            Log.getInstance().log("Ошибка разбиения строки: " + e.getMessage());
            return;
        }
        try {
            Creation creation = Objects.requireNonNull(checkClass(split[0])).newInstance();
            if (creation instanceof Bird) {
                Log.getInstance().log("Поток " + getName() + " создал BIRD");
                created = new Bird(
                        split[1],
                        split[2],
                        Integer.valueOf(split[3]),
                        Sex.valueOf(split[4]),
                        Boolean.valueOf(split[5]),
                        split[6],
                        Boolean.valueOf(split[7]),
                        Boolean.valueOf(split[8]),
                        split[9]);
            } else if (creation instanceof Mammal) {
                Log.getInstance().log("Поток " + getName() + " создал MAMMAL");
                created = new Mammal(
                        split[1],
                        split[2],
                        Integer.valueOf(split[3]),
                        Sex.valueOf(split[4]),
                        Boolean.valueOf(split[5]),
                        Boolean.valueOf(split[6]),
                        Boolean.valueOf(split[7]),
                        Boolean.valueOf(split[8]),
                        Boolean.valueOf(split[9]),
                        split[10]);
            } else {
                created = null;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Class<? extends Creation> checkClass(String string) {
        switch (string) {
            case "BIRD":
                return Bird.class;
            case "MAMMAL":
                return Mammal.class;
            default:
                return null;
        }
    }

    private String[] parse(String string) throws Exception {
        return Spliter.split(string);

    }
}
