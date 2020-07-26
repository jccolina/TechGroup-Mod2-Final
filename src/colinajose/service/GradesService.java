package colinajose.service;

import colinajose.model.people.Student;
import colinajose.model.schoolEntities.Course;
import colinajose.model.schoolEntities.Grade;
import colinajose.model.schoolEntities.GradingScale;
import colinajose.model.schoolEntities.Kardex;
import colinajose.model.schoolEntities.Subject;
import datastructures.arraylist.MyArrayList;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;

public class GradesService {

    public static void setGradingScale(Course course, double scholarship, double expelled, double notify) {
        GradingScale gradingScale = new GradingScale(expelled, notify, scholarship);
        course.setGradingScale(gradingScale);
    }

    public static void addGrade(double grade, Student student, Subject subject) {
        Grade studentGrade = new Grade(student, grade);
        subject.addGrade(studentGrade);
    }

    public static MyCircularDoublyLinkedList<Grade> computeGrades(Kardex kardex) {
        MyCircularDoublyLinkedList<Student> students = kardex.getStudents();
        MyCircularDoublyLinkedList<Subject> subjects = kardex.getCourse().getSubjects();
        MyCircularDoublyLinkedList<Grade> averageGrades = new MyCircularDoublyLinkedList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            averageGrades.add(computeGrade(subjects, student));
        }
        return averageGrades;
    }

    private static Grade computeGrade(MyCircularDoublyLinkedList<Subject> subjects, Student student) {
        double average = 0;
        for (int i = 0; i < subjects.size(); i++) {
            MyCircularDoublyLinkedList<Grade> grades = subjects.get(i).getGrades();
            for (int j = 0; j < grades.size(); j++) {
                Grade grade = grades.get(j);
                if (grade.getStudent().equals(student)) {
                    average += grade.getFinalGrade();
                    break;
                }
            }
        }
        average /= subjects.size();
        return new Grade(student, average);
    }

    public static void computeStudentStates(Kardex kardex, MyCircularDoublyLinkedList<Grade> averageGrades) {
        GradingScale gradingScale = kardex.getCourse().getGradingScale();
        double expelledGrade = gradingScale.getExpelledGrade();
        double notifyGrade = gradingScale.getNotifyGrade();
        double scholarshipGrade = gradingScale.getScholarshipGrade();
        boolean hasScholarship = false;
        for (int i = 0; i < averageGrades.size(); i++) {
            Grade grade = averageGrades.get(i);
            if (grade.getFinalGrade() <= expelledGrade) {
                grade.getStudent().setState(Student.State.EXPELLED);
            } else if (grade.getFinalGrade() <= notifyGrade) {
                grade.getStudent().setState(Student.State.NOTIFY);
            } else if (grade.getFinalGrade() <= scholarshipGrade) {
                grade.getStudent().setState(Student.State.AVERAGE);
            } else {
                grade.getStudent().setState(Student.State.SCHOLARSHIP);
                hasScholarship = true;
            }
        }
        if (!hasScholarship) {
            scholarshipToHigherGrades(averageGrades);
        }
    }

    private static void scholarshipToHigherGrades(MyCircularDoublyLinkedList<Grade> grades) {
        MyCircularDoublyLinkedList<Grade> sortedGrades = sortGrades(grades);
        double maxGrade = 0;
        for (int i = 0; i < sortedGrades.size(); i++) {
            Grade grade = sortedGrades.get(i);
            double currentGrade = grade.getFinalGrade();
            if (currentGrade >= maxGrade) {
                grade.getStudent().setState(Student.State.SCHOLARSHIP);
                maxGrade = currentGrade;
            }
        }
    }

    public static MyCircularDoublyLinkedList<Grade> sortGrades(MyCircularDoublyLinkedList<Grade> grades) {
        MyArrayList<Grade> sorted = new MyArrayList<>();
        sorted.add(grades.get(0));
        MyCircularDoublyLinkedList<Grade> result = new MyCircularDoublyLinkedList<>();
        for (int i = 1; i < grades.size(); i++) {
            Grade grade = grades.get(i);
            double currentGrade = grade.getFinalGrade();
            boolean isGradeAdded = false;
            for (int j = 0; j < sorted.size(); j++) {
                double sortedGrade = sorted.get(j).getFinalGrade();
                if (currentGrade > sortedGrade) {
                    sorted.add(j, grade);
                    isGradeAdded = true;
                    break;
                }
            }
            if (!isGradeAdded) {
                sorted.add(grade);
            }
        }
        for (int i = 0; i < sorted.size(); i++) {
            result.add(sorted.get(i));
        }
        return result;
    }

}
