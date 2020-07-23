package colinajose.service;

import colinajose.model.people.Student;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;

public class ObservableService implements Observable {

    private MyCircularDoublyLinkedList<Observer> devices;

    @Override
    public void registerObserver(Observer observer) {
        this.devices.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.devices.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < this.devices.size(); i++) {
            this.devices.get(i).update();
        }
    }

    @Override
    public Object getState(Object owner) {
        if (owner instanceof Student) {
            Student student = (Student) owner;
            return student.getState();
        } else {
            return "";
        }
    }
}
