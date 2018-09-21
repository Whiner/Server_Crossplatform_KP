package com.donntu.kp.server.datawork;

import com.donntu.kp.server.datawork.creations.interfaces.Creation;
import com.donntu.kp.server.logger.Log;
import com.donntu.kp.server.observer.IObserver;

import java.util.ArrayList;
import java.util.List;

public class InstanceCreatorController implements IObserver {
    private List<Creation> creations = new ArrayList<>();
    private List<IObserver> observers = new ArrayList<>();

    public void subscribe(IObserver observer) {
        observers.add(observer);
    }

    private synchronized void notifyObservers(Creation creation) {
        for (IObserver observer : observers) {
            observer.update(creation.getName() + " из \'" + creation.getArial() + "\'");

        }
    }

    public Creation start(InstanceCreator instanceCreator) throws InterruptedException {
        instanceCreator.start();
        Log.getInstance().log("Поток " + instanceCreator.getName() + " начал свою работу");
        instanceCreator.join();
        return instanceCreator.getCreated();
    }

    public List<Creation> getCreations() {
        return creations;
    }

    public synchronized void addString(String string) {
        try {
            Creation creation = start(new InstanceCreator(string));
            notifyObservers(creation);
            creations.add(creation);
        } catch (InterruptedException e) {
            Log.getInstance().log("Ошибка в потоке: " + e.getMessage());
        }
    }

    @Override
    public void update(String string) {
        addString(string);
    }
}
