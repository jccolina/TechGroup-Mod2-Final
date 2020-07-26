package colinajose.service;

import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;

public class ObservableService implements Observable {

    private MyCircularDoublyLinkedList<Observer> serviceDevices;

    public ObservableService(){
        this.serviceDevices = new MyCircularDoublyLinkedList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.serviceDevices.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.serviceDevices.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < this.serviceDevices.size(); i++) {
            this.serviceDevices.get(i).sendNotification();
        }
    }
}
