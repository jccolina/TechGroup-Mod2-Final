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
    private static final MyRedBlackBST<Student> gradesTree = new MyRedBlackBST<>();

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
        setNotifyStudents(expelledGrade, notifyGrade);
        setAverageStudents(notifyGrade, scholarshipGrade);
        if (!hasScholarship) {
            scholarshipToHigherGrades(expelledGrade);
        }
    }

    private static void scholarshipToHigherGrades(double expelledGrade){
        MyLinkedList<Student> higherStudents = gradesTree.getHigherValuesOver(expelledGrade);
        for (int i = 0; i < higherStudents.size(); i++) {
            Student student = higherStudents.get(i);
            student.setState(Student.State.SCHOLARSHIP);
        }
    }

    public static MyCircularDoublyLinkedList<Grade> sortGrades() {
        MyCircularDoublyLinkedList<Grade> sortedGrades = new MyCircularDoublyLinkedList<>();
        MyColorNode<Student> rootGrade = gradesTree.getRoot();
        addSortedGrades(sortedGrades, rootGrade);
        return  sortedGrades;
    }

    private static void addSortedGrades(MyCircularDoublyLinkedList<Grade> sortedGrades, MyColorNode<Student> currentNode) {
        if (currentNode == MyRedBlackBST.NULLT) return;

        addSortedGrades(sortedGrades, currentNode.getRight());
        MyLinkedList<Student> students = currentNode.getElements();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            sortedGrades.add(new Grade(student, currentNode.getValue()));
        }
        addSortedGrades(sortedGrades, currentNode.getLeft());
    }

    public static boolean setScholarShipStudents(double scholarshipGrade){
        MyLinkedList<Student> scholarshipStudents = gradesTree.getValuesGreaterThan(scholarshipGrade);
        if(scholarshipStudents.isEmpty()) return false;
        for (int i = 0; i < scholarshipStudents.size(); i++) {
            Student student = scholarshipStudents.get(i);
            student.setState(Student.State.SCHOLARSHIP);
        }
        return true;
    }
    public static void setExpelledStudents(double expelledGrade){
        MyLinkedList<Student> expelledStudents = gradesTree.getValuesLessThan(expelledGrade);
        if(expelledStudents.isEmpty()) return;
        for (int i = 0; i < expelledStudents.size(); i++) {
            Student student = expelledStudents.get(i);
            student.setState(Student.State.EXPELLED);
        }
    }
    public static void setNotifyStudents(double expelledGrade, double notifyGrade){
        MyLinkedList<Student> notifyStudents = gradesTree.getValuesLeftOpenInterval(expelledGrade, notifyGrade);
        if(notifyStudents.isEmpty()) return;
        for (int i = 0; i < notifyStudents.size(); i++) {
            Student student = notifyStudents.get(i);
            student.setState(Student.State.NOTIFY);
        }
    }
    public static void setAverageStudents(double notifyGrade, double scholarshipGrade){
        MyLinkedList<Student> averageStudents = gradesTree.getValuesOpenInterval(notifyGrade, scholarshipGrade);
        if(averageStudents.isEmpty()) return;
        for (int i = 0; i < averageStudents.size(); i++) {
            Student student = averageStudents.get(i);
            student.setState(Student.State.AVERAGE);
        }
    }

}
