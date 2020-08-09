package colinajose.service;

import colinajose.model.base.BaseEntity;
import colinajose.model.people.Device;
import colinajose.model.people.Parent;
import colinajose.model.people.Student;
import colinajose.model.schoolEntities.Course;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import datastructures.linkedlist.MyLinkedList;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchServiceTest {

    @Test
    public void testGetIndexElementWithOneElement() {
        Course expectedCourse = new Course("2nd", "2020", "2A");
        String expectedId = expectedCourse.getId();
        SearchService.indexElement(expectedId, expectedCourse);
        Course actualCourse = (Course) SearchService.getIndexedElement(expectedId);
        String actualId = actualCourse.getId();
        assertEquals(expectedId, actualId);
        assertEquals(expectedCourse, actualCourse);
    }

    @Test
    public void testGetIndexedElementWithTwoElements() {
        Course expectedCourse = new Course("2nd", "2020", "2A");
        String expectedId = expectedCourse.getId();
        Course course2 = new Course("2nd", "2020", "2B");
        String id2 = course2.getId();
        SearchService.indexElement(expectedId, expectedCourse);
        SearchService.indexElement(id2, course2);
        Course actualCourse = (Course) SearchService.getIndexedElement(expectedId);
        String actualId = actualCourse.getId();
        assertEquals(expectedId, actualId);
        assertEquals(expectedCourse, actualCourse);
    }

    @Test
    public void testGetStudentsbyState() {
        MyCircularDoublyLinkedList<Student> students = new MyCircularDoublyLinkedList<>();
        Student expectedStudent = new Student("Maria Lopez", "123123123", 10, "Female");
        expectedStudent.setState(Student.State.EXPELLED);
        Student studentScholarship = new Student("Roberto Gonzales", "123123123", 10, "Male");
        studentScholarship.setState(Student.State.SCHOLARSHIP);
        students.add(expectedStudent);
        students.add(studentScholarship);
        int expectedSize = 1;
        MyLinkedList<Student> actualStudents = SearchService.getStudentsbyState(students, Student.State.EXPELLED);

        assertEquals(actualStudents.size(), expectedSize);
        assertEquals(actualStudents.getFirst().getValue(), expectedStudent);
    }

    @Test
    public void testGetDevices() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String expectedPhone1 = "44555555";
        String expectedPhone2 = "44555556";
        String phone3 = "44555557";
        int expectedSize = 2;
        String parentId1 = schoolService.registerParent("Gabriel Mendez", "23334444TJ", 40, "Male", "Av. Simon Lopez #23", "7999999", "gabriel@mail.com");
        String parentId2 = schoolService.registerParent("Luis Perez", "322423432SZ", 37, "Male", "Av Blanco Galindo", "68999999", "luis@mail.com");
        String deviceId1 = schoolService.registerDevice(parentId1, expectedPhone1);
        String deviceId2 = schoolService.registerDevice(parentId1, expectedPhone2);
        String deviceId3 = schoolService.registerDevice(parentId2, phone3);
        Device expectedDevice1 = (Device) SearchService.getIndexedElement(deviceId1);
        Device expectedDevice2 = (Device) SearchService.getIndexedElement(deviceId2);
        Parent parent1 = (Parent) SearchService.getIndexedElement(parentId1);
        MyLinkedList<Device> actualDevices = SearchService.getDevices(parent1);

        assertEquals(expectedSize, actualDevices.size());
        assertTrue(actualDevices.contains(expectedDevice1));
        assertTrue(actualDevices.contains(expectedDevice2));
    }

    @Test
    public void testGetStudentsbyName() {
        MyCircularDoublyLinkedList<Student> students = new MyCircularDoublyLinkedList<>();
        String studentName = "Maria Lopez";
        Student expectedStudent = new Student(studentName, "123123123", 10, "Female");
        Student student2 = new Student("Roberto Gonzales", "123123123", 10, "Male");
        students.add(expectedStudent);
        students.add(student2);

        Student actualStudent = SearchService.getStudentsbyName(students, studentName);

        assertEquals(expectedStudent, actualStudent);
    }
}