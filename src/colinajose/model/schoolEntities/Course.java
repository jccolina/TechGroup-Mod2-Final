package colinajose.model.schoolEntities;

import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import colinajose.model.base.BaseEntity;

public class Course extends BaseEntity {
    private String level;
    private String group;
    private String year;
    private String classroom;
    private MyCircularDoublyLinkedList<Subject> subjects;
    private GradingScale gradingScale;

    public Course(String level, String year, String group) {
        this.level = level;
        this.group = group;
        this.year = year;
        this.subjects = new MyCircularDoublyLinkedList<>();
    }


    public MyCircularDoublyLinkedList<Subject> getSubjects() {
        return subjects;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Course) {
            Course courseToCompare = (Course) object;
            if (courseToCompare.getLevel().equals(this.level)
                    && courseToCompare.getYear().equals(this.year)
                    && courseToCompare.getGroup().equals(this.group)) {
                return true;
            }
        }
        return false;
    }
}
