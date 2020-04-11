package colinajose.model.schoolEntities;

import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import colinajose.model.base.BaseEntity;
import colinajose.model.people.Student;
import datastructures.hashmap.MyHashMap;

public class Course extends BaseEntity {
    private String grade;
    private String group;
    private String year;
    private MyCircularDoublyLinkedList<Subject> subjects;
    private GradingScale gradingScale;
    private MyHashMap<Student, Integer> averages;

    public Course(String grade, String year, String group) {
        this.grade = grade;
        this.group = group;
        this.year = year;
        this.subjects = new MyCircularDoublyLinkedList<>();
        this.averages =  new MyHashMap<>();
    }

    public MyCircularDoublyLinkedList<Subject> getSubjects() {
        return subjects;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSubjects(MyCircularDoublyLinkedList<Subject> subjects) {
        this.subjects = subjects;
    }

    public GradingScale getGradingScale() {
        return gradingScale;
    }

    public void setGradingScale(GradingScale gradingScale) {
        this.gradingScale = gradingScale;
    }

    public MyHashMap<Student, Integer> getAverages() {
        return averages;
    }

    public void setAverages(MyHashMap<Student, Integer> averages) {
        this.averages = averages;
    }

    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }

    public void addAverage(Student student, Integer average){
        this.averages.put(student, average);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    @Override
    public boolean equals(Object object){
        if(object instanceof Course){
            Course courseToCompare = (Course) object;
            if(courseToCompare.getGrade().equals(this.grade)
                    && courseToCompare.getYear().equals(this.year)
                    && courseToCompare.getGroup().equals(this.group)){
                return true;
            }
        }
        return false;
    }
}
