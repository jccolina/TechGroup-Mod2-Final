package colinajose.model.schoolEntities;

import colinajose.model.people.Student;
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

    public void addSingleGrade(Grade grade){
        this.grades.add(grade);
    }

    public void addSingleGrade(Student student, double grade){
        Grade newGrade = new Grade(student, grade);
        this.grades.add(newGrade);
    }

    public void addSingleSchedule(String schedule){
        this.schedule.add(schedule);
    }

    public double getGrade(Student student) {
        double finalGrade = 0;
        for (int i = 0; i < grades.size(); i++) {
            Grade grade = grades.get(i);
            if(grade.getStudent().equals(student)){
                finalGrade = grade.getFinalGrade();
                break;
            }
        }
        return finalGrade;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Subject){
            Subject subjectToCompare = (Subject) object;
            if(this.topic.equals(subjectToCompare.getTopic())){
                return true;
            }
        }
        return false;
    }

}
