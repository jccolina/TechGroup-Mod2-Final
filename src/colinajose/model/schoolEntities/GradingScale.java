package colinajose.model.schoolEntities;

import colinajose.model.base.BaseEntity;
import colinajose.model.people.Student;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import datastructures.hashmap.MyHashMap;

public class GradingScale extends BaseEntity {
    public static final String SCHOLARSHIP = "SCHOLARSHIP";
    public static final String NOTIFY = "NOTIFY";
    public static final String EXPELLED = "EXPELLED";
    public static final String AVERAGE = "AVERAGE";

    private double scholarshipGrade;
    private double notifyGrade;
    private double expelledGrade;

    public GradingScale(double expelledGrade, double notifyGrade, double scholarshipGrade) {
        this.expelledGrade = expelledGrade;
        this.scholarshipGrade = scholarshipGrade;
        this.notifyGrade = notifyGrade;
    }

    public String computeGrade(double grade) {
        if (grade <= expelledGrade) {
            return EXPELLED;
        } else if (grade <= notifyGrade) {
            return NOTIFY;
        } else if (grade >= scholarshipGrade) {
            return SCHOLARSHIP;
        } else {
            return AVERAGE;
        }
    }

    public void computeStates(MyHashMap<Student,Double> averages, MyCircularDoublyLinkedList<Student> students){
        double higherGrade = 0;
        boolean scholarShipFound = false;
        Student higherGradeStudent = students.get(0);
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            double studentAverage = averages.get(student);
            String state = computeGrade(studentAverage);
            if (!student.setState(state)){
                System.out.println("Student state not updated: " + student.getName() + ", CI: " + student.getCi());
            }
            if (state.equals(SCHOLARSHIP)) {
                scholarShipFound = true;
            }
            if (!scholarShipFound && (studentAverage > higherGrade)) {
                higherGrade = studentAverage;
                higherGradeStudent = student;
            }
        }
        if (!scholarShipFound) {
            higherGradeStudent.setState(SCHOLARSHIP);
        }
    }

    public MyCircularDoublyLinkedList<Student> filterStudentsByState(MyCircularDoublyLinkedList<Student> students, String state){
        MyCircularDoublyLinkedList<Student> result = new MyCircularDoublyLinkedList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if(student.getState().equals(state)){
                result.add(student);
            }
        }
        return result;
    }
    public double getExpelledGrade() {
        return expelledGrade;
    }

    public void setExpelledGrade(double expelledGrade) {
        this.expelledGrade = expelledGrade;
    }

    public double getScholarshipGrade() {
        return scholarshipGrade;
    }

    public void setScholarshipGrade(double scholarshipGrade) {
        this.scholarshipGrade = scholarshipGrade;
    }

    public double getNotifyGrade() {
        return notifyGrade;
    }

    public void setNotifyGrade(double notifyGrade) {
        this.notifyGrade = notifyGrade;
    }
}
