package colinajose.model.schoolEntities;

import datastructures.arraylist.MyArrayList;
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

    public Course(String grade, String year, String group) {
        this.grade = grade;
        this.group = group;
        this.year = year;
        this.subjects = new MyCircularDoublyLinkedList<>();
    }

    public void computeStudentsState(MyHashMap averages, MyCircularDoublyLinkedList<Student> students) {
        gradingScale.computeStates(averages, students);
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

    public void setGradingScale(double expelled, double notify, double scholarship) {
        GradingScale gradingScale = new GradingScale(expelled, notify, scholarship);
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

    @Override
    public boolean equals(Object object) {
        if (object instanceof Course) {
            Course courseToCompare = (Course) object;
            if (courseToCompare.getGrade().equals(this.grade)
                    && courseToCompare.getYear().equals(this.year)
                    && courseToCompare.getGroup().equals(this.group)) {
                return true;
            }
        }
        return false;
    }

    public MyCircularDoublyLinkedList<Student> filterStudents(MyCircularDoublyLinkedList<Student> students, String state) {
        return gradingScale.filterStudentsByState(students, state);
    }

    public MyHashMap<Student, Double> computeAverage(MyCircularDoublyLinkedList<Student> students) {
        MyHashMap<Student, Double> result = new MyHashMap<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            double average = 0;
            for (int j = 0; j < subjects.size(); j++) {
                Subject subject = subjects.get(j);
                average += subject.getGrade(student);
            }
            result.put(student, average / subjects.size());
        }
        return result;
    }
}
