package colinajose.model.people;

import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;

public class Student extends Person {
    public enum State {SCHOLARSHIP, EXPELLED, NOTIFY, AVERAGE, NONE};
    private State state;
    private MyCircularDoublyLinkedList<Parent> parents;

    public Student(String name, String CI, int age, String gender, Contact contact) {
        super(name, CI, age, gender, contact);
        this.parents = new MyCircularDoublyLinkedList<>();
        this.state = State.NONE;
    }

    public Student(String name, String CI, int age, String gender) {
        super(name, CI, age, gender);
        this.parents = new MyCircularDoublyLinkedList<>();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addParent(Parent parent){
        this.parents.add(parent);
    }

    public void removeParent(Parent parent){
        this.parents.remove(parent);
    }

    public MyCircularDoublyLinkedList getParents(){
        return this.parents;
    }

    public void setParents(MyCircularDoublyLinkedList parents){
        this.parents = parents;
    }

}
