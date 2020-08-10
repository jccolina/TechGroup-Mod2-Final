package colinajose.model.schoolEntities;

import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import colinajose.model.base.BaseEntity;
import colinajose.model.people.Teacher;

public class Subject extends BaseEntity {
    private String topic;
    private Teacher teacher;
    private MyCircularDoublyLinkedList<Grade> grades;
    private MyCircularDoublyLinkedList<String> schedule;

    public Subject(String topic) {
        this.topic = topic;
        this.grades = new MyCircularDoublyLinkedList<>();
        this.schedule = new MyCircularDoublyLinkedList<>();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.addSubject(this);
    }

    public MyCircularDoublyLinkedList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(MyCircularDoublyLinkedList<Grade> grades) {
        this.grades = grades;
    }

    public MyCircularDoublyLinkedList<String> getSchedule() {
        return schedule;
    }

    public void setSchedules(MyCircularDoublyLinkedList<String> schedule) {
        this.schedule = schedule;
    }

    public void addGrade(Grade grade){
        this.grades.add(grade);
    }

    public void addSchedule(String schedule){
        this.schedule.add(schedule);
    }

    @Override
    public String toString(){
        return getTopic();
    }

}
