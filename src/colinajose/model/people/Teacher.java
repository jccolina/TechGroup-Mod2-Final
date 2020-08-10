package colinajose.model.people;

import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import colinajose.model.schoolEntities.Subject;

public class Teacher extends Employee {
    private MyCircularDoublyLinkedList<Subject> subjects;
    public Teacher(String name, String CI, int age, String gender, Contact contact) {
        super(name, CI, age, gender, contact);
        this.subjects = new MyCircularDoublyLinkedList<>();
    }
    public Teacher(String name, String CI, int age, String gender) {
        super(name, CI, age, gender);
        this.subjects = new MyCircularDoublyLinkedList<>();
    }
    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }

    public MyCircularDoublyLinkedList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(MyCircularDoublyLinkedList<Subject> subjects) {
        this.subjects = subjects;
    }
}
