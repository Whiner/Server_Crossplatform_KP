package com.donntu.kp.server.datawork;


import com.donntu.kp.server.datawork.creations.Sex;
import com.donntu.kp.server.datawork.creations.interfaces.Bird;
import com.donntu.kp.server.datawork.creations.interfaces.Creation;
import com.donntu.kp.server.datawork.creations.interfaces.Mammal;
import com.donntu.kp.server.logger.Log;

public class InstanceCreator extends Thread {

    private String[] split;
    private Class<? extends Creation> creationType;
    private Creation created;

    public InstanceCreator(String[] split, Class<? extends Creation> creationType) {
        this.split = split;
        this.creationType = creationType;
    }

    public Creation getCreated() {
        return created;
    }

    @Override
    public void run() {

        try {
            if (creationType.newInstance() instanceof Bird) {
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
            } else if (creationType.newInstance() instanceof Mammal) {
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
}
