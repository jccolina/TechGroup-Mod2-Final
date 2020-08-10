package colinajose.service;

import colinajose.model.people.Student;
import colinajose.model.schoolEntities.Course;
import colinajose.model.schoolEntities.Grade;
import colinajose.model.schoolEntities.Kardex;
import colinajose.model.schoolEntities.Subject;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import org.junit.Test;

import static org.junit.Assert.*;

public class GradesServiceTest {

    @Test
    public void testSetGradingScale() {
        Course course = new Course("2nd", "2020", "2A");
        double scholarshipExpected = 95;
        double expelledExpected = 20;
        double notifyExpected = 50;
        GradesService.setGradingScale(course, scholarshipExpected, expelledExpected, notifyExpected);
        double actualScholarship = course.getGradingScale().getScholarshipGrade();
        double actualExpelled = course.getGradingScale().getExpelledGrade();
        double actualNotify = course.getGradingScale().getNotifyGrade();
        assertEquals(scholarshipExpected, actualScholarship, 0);
        assertEquals(expelledExpected, actualExpelled, 0);
        assertEquals(notifyExpected, actualNotify, 0);
    }

    @Test
    public void testAddGrade() {
        Subject subject = new Subject("Mathematics");
        Student expectedStudent = new Student("Juan Perez", "5433324CB", 12,"Male");
        double expectedGrade = 78;
        GradesService.addGrade(expectedGrade, expectedStudent, subject);
        double actualGrade = subject.getGrades().getFirst().getValue().getFinalGrade();
        Student actualStudent = subject.getGrades().getFirst().getValue().getStudent();
        assertEquals(expectedGrade, actualGrade, 0);
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    public void testComputeGrades() {
        double grade1 = 65;
        double grade2 = 58;
        Kardex kardex = createKardexWithTwoGrades(grade1, grade2);
        double expectedFinalGrade = (grade1 + grade2)/2;

        GradesService.computeGrades(kardex);
        MyCircularDoublyLinkedList<Grade> grades = GradesService.sortGrades();
        double actualFinalGrade = grades.getFirst().getValue().getFinalGrade();
        assertEquals(expectedFinalGrade, actualFinalGrade, 0);
    }

    @Test
    public void testComputeStudentStates() {
        double finalGrade1 = 10;
        double finalGrade2 = 51;
        double finalGrade3 = 65;
        double finalGrade4 = 95;
        double expelled = 20;
        double notify = 55;
        double scholarship = 90;
        Kardex kardex = createKardexWithFourGrades(finalGrade1, finalGrade2, finalGrade3, finalGrade4);
        GradesService.computeGrades(kardex);
        GradesService.setGradingScale(kardex.getCourse(), scholarship, expelled, notify);
        GradesService.computeStudentStates(kardex);
        MyCircularDoublyLinkedList<Grade> grades = GradesService.sortGrades();
        Student.State actualState1 = grades.get(0).getStudent().getState();
        Student.State actualState2 = grades.get(1).getStudent().getState();
        Student.State actualState3 = grades.get(2).getStudent().getState();
        Student.State actualState4 = grades.get(3).getStudent().getState();

        assertEquals(Student.State.SCHOLARSHIP, actualState1);
        assertEquals(Student.State.AVERAGE, actualState2);
        assertEquals(Student.State.NOTIFY, actualState3);
        assertEquals(Student.State.EXPELLED, actualState4);
    }

    @Test
    public void testSortGrades() {
        double expectedFinalGrade1 = 65;
        double expectedFinalGrade2 = 51;
        double expectedFinalGrade3 = 10;
        Student expectedStudent1 = new Student("Juan Perez", "5433324CB", 12,"Male");
        Student expectedStudent2 = new Student("Ariel Mamani", "73300023LP", 12,"Male");
        Student expectedStudent3 = new Student("Zulma Suarez", "823737437CB", 11,"Female");
        int expectedSize = 3;
        Kardex kardex = createKardexWithThreeGrades(expectedFinalGrade1, expectedFinalGrade2, expectedFinalGrade3,
                expectedStudent1, expectedStudent2, expectedStudent3);
        GradesService.computeGrades(kardex);
        MyCircularDoublyLinkedList<Grade> grades = GradesService.sortGrades();
        int actualSize = grades.size();
        Student actualStudent1 = grades.get(0).getStudent();
        Student actualStudent2 = grades.get(1).getStudent();
        Student actualStudent3 = grades.get(2).getStudent();
        double actualGrade1 = grades.get(0).getFinalGrade();
        double actualGrade2 = grades.get(1).getFinalGrade();
        double actualGrade3 = grades.get(2).getFinalGrade();

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedStudent1, actualStudent1);
        assertEquals(expectedStudent2, actualStudent2);
        assertEquals(expectedStudent3, actualStudent3);
        assertEquals(expectedFinalGrade1, actualGrade1, 0);
        assertEquals(expectedFinalGrade2, actualGrade2, 0);
        assertEquals(expectedFinalGrade3, actualGrade3, 0);
    }

    private Kardex createKardexWithTwoGrades(double grade1, double grade2) {
        Course course = new Course("2nd", "2020", "2A");
        Subject maths = new Subject("Mathematics");
        Subject physics = new Subject("Physics");
        course.addSubject(maths);
        course.addSubject(physics);
        Kardex kardex = new Kardex(course);
        Student student = new Student("Juan Perez", "5433324CB", 12,"Male");
        kardex.addStudent(student);
        Grade gradeStudent1Maths = new Grade(student, grade1);
        Grade gradeStudent1Physics = new Grade(student, grade2);
        maths.addGrade(gradeStudent1Maths);
        physics.addGrade(gradeStudent1Physics);
        return kardex;
    }

    private Kardex createKardexWithFourGrades(double finalGrade1, double finalGrade2, double finalGrade3, double finalGrade4) {
        Course course = new Course("2nd", "2020", "2A");
        Subject maths = new Subject("Mathematics");
        course.addSubject(maths);
        Kardex kardex = new Kardex(course);
        Student student1 = new Student("Juan Perez", "5433324CB", 12,"Male");
        Student student2 = new Student("Ariel Mamani", "73300023LP", 12,"Male");
        Student student3 = new Student("Zulma Suarez", "823737437CB", 11,"Female");
        Student student4 = new Student("Gimena Lopez", "782383933CB", 12,"Female");
        kardex.addStudent(student1);
        kardex.addStudent(student2);
        kardex.addStudent(student3);
        kardex.addStudent(student4);
        Grade grade1 = new Grade(student1, finalGrade1);
        Grade grade2 = new Grade(student2, finalGrade2);
        Grade grade3 = new Grade(student3, finalGrade3);
        Grade grade4 = new Grade(student4, finalGrade4);
        maths.addGrade(grade1);
        maths.addGrade(grade2);
        maths.addGrade(grade3);
        maths.addGrade(grade4);
        return kardex;
    }

    private Kardex createKardexWithThreeGrades(double finalGrade1, double finalGrade2, double finalGrade3, Student student1, Student student2, Student student3) {
        Course course = new Course("2nd", "2020", "2A");
        Subject maths = new Subject("Mathematics");
        course.addSubject(maths);
        Kardex kardex = new Kardex(course);
        kardex.addStudent(student1);
        kardex.addStudent(student2);
        kardex.addStudent(student3);
        Grade grade1 = new Grade(student1, finalGrade1);
        Grade grade2 = new Grade(student2, finalGrade2);
        Grade grade3 = new Grade(student3, finalGrade3);
        maths.addGrade(grade1);
        maths.addGrade(grade2);
        maths.addGrade(grade3);
        return kardex;
    }

}