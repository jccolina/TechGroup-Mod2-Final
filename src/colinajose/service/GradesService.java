package colinajose.service;

import colinajose.model.people.Student;
import colinajose.model.schoolEntities.Course;
import colinajose.model.schoolEntities.Grade;
import colinajose.model.schoolEntities.GradingScale;
import colinajose.model.schoolEntities.Kardex;
import colinajose.model.schoolEntities.Subject;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import datastructures.linkedlist.MyLinkedList;
import datastructures.selfbalancedbst.MyColorNode;
import datastructures.selfbalancedbst.MyRedBlackBST;

public class GradesService {
    private static final MyRedBlackBST gradesTree = new MyRedBlackBST();

    public static void setGradingScale(Course course, double scholarship, double expelled, double notify) {
        GradingScale gradingScale = new GradingScale(expelled, notify, scholarship);
        course.setGradingScale(gradingScale);
    }

    public static void addGrade(double grade, Student student, Subject subject) {
        Grade studentGrade = new Grade(student, grade);
        subject.addGrade(studentGrade);
    }

    public static void computeGrades(Kardex kardex) {
        MyCircularDoublyLinkedList<Student> students = kardex.getStudents();
        MyCircularDoublyLinkedList<Subject> subjects = kardex.getCourse().getSubjects();
        gradesTree.clear();
        if(students.size() > 0) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                Grade grade = computeGrade(subjects, student);
                gradesTree.add(grade.getFinalGrade(), grade.getStudent());
            }
        }
    }

    private static Grade computeGrade(MyCircularDoublyLinkedList<Subject> subjects, Student student) {
        double average = 0;
        if(subjects.size() > 0) {
            for (int i = 0; i < subjects.size(); i++) {
                MyCircularDoublyLinkedList<Grade> grades = subjects.get(i).getGrades();
                if (grades.size() > 0) {
                    for (int j = 0; j < grades.size(); j++) {
                        Grade grade = grades.get(j);
                        if (grade.getStudent().equals(student)) {
                            average += grade.getFinalGrade();
                            break;
                        }
                    }
                }
            }
            average /= subjects.size();
        }
        return new Grade(student, average);
    }

    public static void computeStudentStates(Kardex kardex) {
        GradingScale gradingScale = kardex.getCourse().getGradingScale();
        if(gradingScale == null || gradesTree.getSize() == 0){
            return;
        }
        double expelledGrade = gradingScale.getExpelledGrade();
        double notifyGrade = gradingScale.getNotifyGrade();
        double scholarshipGrade = gradingScale.getScholarshipGrade();
        boolean hasScholarship = setScholarShipStudents(scholarshipGrade);
        setExpelledStudents(expelledGrade);
        setNotifyStudents(expelledGrade, notifyGrade, gradesTree.getRoot());
        setAverageStudents(notifyGrade, scholarshipGrade, gradesTree.getRoot());
        if (!hasScholarship) {
            scholarshipToHigherGrades(expelledGrade);
        }
    }

    private static void scholarshipToHigherGrades(double expelledGrade) {
        MyColorNode currentNode = gradesTree.getRoot();
        if(currentNode == MyRedBlackBST.NULLT){
            return;
        }
        while (currentNode.getRight() != MyRedBlackBST.NULLT){
            currentNode = currentNode.getRight();
        }
        if(currentNode.getValue() > expelledGrade){
            MyLinkedList students = currentNode.getElements();
            for (int i = 0; i < students.size(); i++) {
                Student student = (Student) students.get(i);
                student.setState(Student.State.SCHOLARSHIP);
            }
        }
    }

    public static MyCircularDoublyLinkedList<Grade> sortGrades() {
        MyCircularDoublyLinkedList<Grade> sortedGrades = new MyCircularDoublyLinkedList<>();
        MyColorNode rootGrade = gradesTree.getRoot();
        addSortedGrades(sortedGrades, rootGrade);
        return  sortedGrades;
    }

    private static void addSortedGrades(MyCircularDoublyLinkedList<Grade> sortedGrades, MyColorNode currentNode) {
        if (currentNode == MyRedBlackBST.NULLT) return;

        addSortedGrades(sortedGrades, currentNode.getRight());
        MyLinkedList students = currentNode.getElements();
        for (int i = 0; i < students.size(); i++) {
            Student student = (Student) students.get(i);
            sortedGrades.add(new Grade(student, currentNode.getValue()));
        }
        addSortedGrades(sortedGrades, currentNode.getLeft());
    }

    public static boolean setScholarShipStudents(double scholarshipGrade){
        MyColorNode node = gradesTree.getRoot();
        boolean result = false;
        while(node != MyRedBlackBST.NULLT){
            if(node.getValue() >= scholarshipGrade){
                MyLinkedList students = node.getElements();
                for (int i = 0; i < students.size(); i++) {
                    Student student = (Student) students.get(i);
                    student.setState(Student.State.SCHOLARSHIP);
                }
                result = true;
            }
            node = node.getRight();
        }
        return result;
    }

    public static void setExpelledStudents(double expelledGrade){
        MyColorNode node = gradesTree.getRoot();
        while(node != MyRedBlackBST.NULLT){
            if(node.getValue() <= expelledGrade){
                MyLinkedList students = node.getElements();
                for (int i = 0; i < students.size(); i++) {
                    Student student = (Student) students.get(i);
                    student.setState(Student.State.EXPELLED);
                }
            }
            node = node.getLeft();
        }
    }
    public static void setNotifyStudents(double expelledGrade, double notifyGrade, MyColorNode currentNode){
        if(currentNode == MyRedBlackBST.NULLT) return;
        if(currentNode.getValue() <= expelledGrade){
            setNotifyStudents(expelledGrade, notifyGrade, currentNode.getRight());
        } else if(currentNode.getValue() > notifyGrade){
            setNotifyStudents(expelledGrade, notifyGrade, currentNode.getLeft());
        } else {
            MyLinkedList students = currentNode.getElements();
            for (int i = 0; i < students.size(); i++) {
                Student student = (Student) students.get(i);
                student.setState(Student.State.NOTIFY);
            }
            setNotifyStudents(expelledGrade, notifyGrade, currentNode.getRight());
            setNotifyStudents(expelledGrade, notifyGrade, currentNode.getLeft());
        }
    }
    public static void setAverageStudents(double notifyGrade, double scholarshipGrade, MyColorNode currentNode){
        if(currentNode == MyRedBlackBST.NULLT) return;
        if(currentNode.getValue() <= notifyGrade){
            setAverageStudents(notifyGrade, scholarshipGrade, currentNode.getRight());
        } else if(currentNode.getValue() >= scholarshipGrade){
            setAverageStudents(notifyGrade, scholarshipGrade, currentNode.getLeft());
        } else {
            MyLinkedList students = currentNode.getElements();
            for (int i = 0; i < students.size(); i++) {
                Student student = (Student) students.get(i);
                student.setState(Student.State.AVERAGE);
            }
            setAverageStudents(notifyGrade, scholarshipGrade, currentNode.getRight());
            setAverageStudents(notifyGrade, scholarshipGrade, currentNode.getLeft());
        }
    }
}
