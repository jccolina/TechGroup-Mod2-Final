package colinajose.service;

import colinajose.model.people.Device;
import colinajose.model.people.Parent;
import colinajose.model.people.Person;
import colinajose.model.people.Student;
import colinajose.model.people.Teacher;
import colinajose.model.schoolEntities.Course;
import colinajose.model.schoolEntities.Grade;
import colinajose.model.schoolEntities.GradingScale;
import colinajose.model.schoolEntities.Kardex;
import colinajose.model.schoolEntities.School;
import colinajose.model.schoolEntities.Subject;
import datastructures.arraylist.MyArrayList;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import datastructures.hashmap.MyEntry;
import datastructures.hashmap.MyHashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class SchoolServiceTest {

    @After
    public void cleanUp(){
        School school = SchoolService.getSchool();
        school.setContact(null);
        school.setName(null);
        school.setStudents(new MyCircularDoublyLinkedList<Student>());
        school.setCourses(new MyCircularDoublyLinkedList<Course>());
        school.setKardexes(new MyCircularDoublyLinkedList<Kardex>());
        school.setParents(new MyCircularDoublyLinkedList<Parent>());
        school.setTeachers(new MyCircularDoublyLinkedList<Teacher>());
        school.setDevices(new MyCircularDoublyLinkedList<Device>());
    }

    @Test
    public void testEditSchool() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String expectedName = "Los Alamos";
        String expectedAddress = "Av. Blanco Galindo";
        String expectedPhone = "4-4437789";
        String expectedEmail = "alamos@mail.com";
        schoolService.editSchool(expectedName, expectedAddress, expectedPhone, expectedEmail);
        School school = SchoolService.getSchool();
        String actualName = school.getName();
        String actualAddress = school.getContact().getAddress();
        String actualPhone = school.getContact().getPhone();
        String actualEmail = school.getContact().getEmail();

        assertEquals(expectedName, actualName);
        assertEquals(expectedAddress, actualAddress);
        assertEquals(expectedPhone, actualPhone);
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testEnrollStudent() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String expectedName = "Juan Suarez";
        String expectedCI = "3444455CB";
        int expectedAge = 10;
        String expectedGender = "Male";
        String expectedAddress = "Av. Simon Lopez #450";
        String expectedPhone = "4-4437700";
        String expectedEmail = "juan@mail.com";
        String courseId = schoolService.registerCourse("2nd", "2020", "2A");
        String kardexId = schoolService.registerKardex(courseId);
        String expectedStudentId = schoolService.enrollStudent(expectedName, expectedCI, expectedAge, expectedGender, kardexId, expectedAddress, expectedPhone, expectedEmail);
        Student actualStudent = (Student) SearchService.getIndexedElement(expectedStudentId);

        assertEquals(expectedName, actualStudent.getName());
        assertEquals(expectedCI, actualStudent.getCi());
        assertEquals(expectedAge, actualStudent.getAge());
        assertEquals(expectedGender, actualStudent.getGender());
        assertEquals(expectedAddress, actualStudent.getContact().getAddress());
        assertEquals(expectedPhone, actualStudent.getContact().getPhone());
        assertEquals(expectedEmail, actualStudent.getContact().getEmail());
        assertEquals(expectedStudentId, actualStudent.getId());
    }

    @Test
    public void testAssignParent() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String expectedName = "Juan Suarez";
        String expectedCI = "3444455CB";
        int expectedAge = 10;
        String expectedGender = "Male";
        String expectedAddress = "Av. Simon Lopez #450";
        String expectedPhone = "4-4437700";
        String expectedEmail = "juan@mail.com";
        String expectedParentId = schoolService.registerParent(expectedName,expectedCI, expectedAge, expectedGender, expectedAddress, expectedPhone, expectedEmail);
        String studentId = createStudent(schoolService);
        schoolService.assignParent(expectedParentId, studentId);
        Student actualStudent = SchoolService.getSchool().getStudents().getFirst().getValue();
        Parent actualParent = (Parent) actualStudent.getParents().getFirst().getValue();

        assertEquals(expectedName, actualParent.getName());
        assertEquals(expectedCI, actualParent.getCi());
        assertEquals(expectedAge, actualParent.getAge());
        assertEquals(expectedGender, actualParent.getGender());
        assertEquals(expectedAddress, actualParent.getContact().getAddress());
        assertEquals(expectedPhone, actualParent.getContact().getPhone());
        assertEquals(expectedEmail, actualParent.getContact().getEmail());
        assertEquals(expectedParentId, actualParent.getId());
    }

    @Test
    public void testRegisterKardex() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String courseId = schoolService.registerCourse("2nd", "2020", "2A");
        String kardexId = schoolService.registerKardex(courseId);
        Kardex actualKardex = SchoolService.getSchool().getKardexes().getFirst().getValue();

        assertEquals(kardexId, actualKardex.getId());
        assertEquals("2nd", actualKardex.getCourse().getLevel());
        assertEquals("2020", actualKardex.getCourse().getYear());
        assertEquals("2A", actualKardex.getCourse().getGroup());
    }

    @Test
    public void testRegisterCourse() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String expectedGrade = "2nd";
        String expectedYear = "2020";
        String expectedGroup = "2A";
        String courseId = schoolService.registerCourse(expectedGrade, expectedYear, expectedGroup);
        Course actualCourse = (Course) SearchService.getIndexedElement(courseId);

        assertEquals(courseId, actualCourse.getId());
        assertEquals(expectedGrade, actualCourse.getLevel());
        assertEquals(expectedYear, actualCourse.getYear());
        assertEquals(expectedGroup, actualCourse.getGroup());
    }

    @Test
    public void testRegisterParent() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String expectedName = "Juan Suarez";
        String expectedCI = "3444455CB";
        int expectedAge = 10;
        String expectedGender = "Male";
        String expectedAddress = "Av. Simon Lopez #450";
        String expectedPhone = "4-4437700";
        String expectedEmail = "juan@mail.com";
        String expectedParentId = schoolService.registerParent(expectedName,expectedCI, expectedAge, expectedGender, expectedAddress, expectedPhone, expectedEmail);
        Parent actualParent = SchoolService.getSchool().getParents().getFirst().getValue();

        assertEquals(expectedParentId, actualParent.getId());
        assertEquals(expectedName, actualParent.getName());
        assertEquals(expectedCI, actualParent.getCi());
        assertEquals(expectedAge, actualParent.getAge());
        assertEquals(expectedGender, actualParent.getGender());
        assertEquals(expectedAddress, actualParent.getContact().getAddress());
        assertEquals(expectedPhone, actualParent.getContact().getPhone());
        assertEquals(expectedEmail, actualParent.getContact().getEmail());
    }

    @Test
    public void testRegisterSubject() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String courseId = schoolService.registerCourse("2nd", "2020", "2A");
        String teacherId = schoolService.registerTeacher("Juan Perez", "43344444TJ", 37, "Male", "C. San Martin #345", "443333221", "juan@mail.com");
        String expectedTopic = "Mathematics";
        String subjectId = schoolService.registerSubject(expectedTopic, courseId, teacherId);
        Teacher expectedTeacher = (Teacher) SearchService.getIndexedElement(teacherId);
        Subject actualSubject = (Subject) SearchService.getIndexedElement(subjectId);

        assertEquals(expectedTopic, actualSubject.getTopic());
        assertEquals(expectedTeacher, actualSubject.getTeacher());
    }

    @Test
    public void testRegisterTeacher() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String expectedName = "Juan Suarez";
        String expectedCI = "3444455CB";
        int expectedAge = 10;
        String expectedGender = "Male";
        String expectedAddress = "Av. Simon Lopez #450";
        String expectedPhone = "4-4437700";
        String expectedEmail = "juan@mail.com";
        schoolService.registerTeacher(expectedName, expectedCI, expectedAge, expectedGender, expectedAddress, expectedPhone, expectedEmail);
        Teacher actualTeacher = SchoolService.getSchool().getTeachers().getFirst().getValue();

        assertEquals(expectedName, actualTeacher.getName());
        assertEquals(expectedCI, actualTeacher.getCi());
        assertEquals(expectedAge, actualTeacher.getAge());
        assertEquals(expectedGender, actualTeacher.getGender());
        assertEquals(expectedAddress, actualTeacher.getContact().getAddress());
        assertEquals(expectedPhone, actualTeacher.getContact().getPhone());
        assertEquals(expectedEmail, actualTeacher.getContact().getEmail());
    }

    @Test
    public void testRegisterDevice() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String parentId = schoolService.registerParent("Juan Perez", "43344444TJ", 37, "Male", "C. San Martin #345", "443333221", "juan@mail.com");
        String expectedPhone = "79999999";
        String deviceId = schoolService.registerDevice(parentId, expectedPhone);
        Person expectedParent = (Parent) SearchService.getIndexedElement(parentId);
        Device actualDevice = (Device) SearchService.getIndexedElement(deviceId);
        Person actualParent = actualDevice.getOwner();
        String actualPhone = SchoolService.getSchool().getDevices().getFirst().getValue().getPhoneNumber();

        assertEquals(expectedPhone, actualPhone);
        assertEquals(expectedParent, actualParent);
    }

    @Test
    public void testSetGradingScale() {
        SchoolService schoolService = SchoolService.getSchoolService();
        double expectedExpelled = 20;
        double expectedNotify = 50;
        double expectedScholarship = 90;
        String courseId = schoolService.registerCourse("2nd", "2020", "2A");
        String kardexId = schoolService.registerKardex(courseId);
        schoolService.setGradingScale(kardexId, expectedScholarship, expectedExpelled, expectedNotify);
        Course actualCourse = (Course) SearchService.getIndexedElement(courseId);
        GradingScale actualGradingScale = actualCourse.getGradingScale();

        assertEquals(expectedExpelled, actualGradingScale.getExpelledGrade(), 0);
        assertEquals(expectedNotify, actualGradingScale.getNotifyGrade(), 0);
        assertEquals(expectedScholarship, actualGradingScale.getScholarshipGrade(), 0);
    }

    @Test
    public void testComputeGrades() {
        SchoolService schoolService = SchoolService.getSchoolService();
        double grade1 = 90;
        double grade2 = 95;
        double expectedFinalGrade = (grade1 + grade2)/2;
        Student.State expectedState = Student.State.SCHOLARSHIP;
        String kardexId = createKardexWithTwoGrades(grade1, grade2, schoolService);
        schoolService.computeGrades(kardexId);
        MyCircularDoublyLinkedList<Grade> grades = GradesService.sortGrades();
        double actualFinalGrade = grades.get(0).getFinalGrade();
        Student.State actualState =  grades.get(0).getStudent().getState();

        assertEquals(expectedFinalGrade, actualFinalGrade, 0);
        assertEquals(expectedState, actualState);
    }

    @Test
    public void testUpdateNotifyListAdding() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        SchoolService schoolService = SchoolService.getSchoolService();
        double grade1 = 49;
        double grade2 = 45;
        String phone = "44555533";
        String kardexId = createKardexWithTwoGrades(grade1, grade2, schoolService);
        Device device = createDeviceFirstStudent(schoolService, phone);
        schoolService.computeGrades(kardexId);
        schoolService.updateNotifyList(kardexId);
        String expected = "Sending notification to following contact: " + "\n"
                + "Phone: "+ device.getPhoneNumber() + "\n\n"
                + "Dear " + device.getOwner().getName() + ",\n" +
                "You are receiving this notification since the student in charge of you has not been accomplishing the expectations required for our school.\n" +
                "Therefore we would like to get together in order to discuss about it." + "\n" +
                "Best Regards\n";
        schoolService.sendNotifications();
        String actualOutput = output.toString().replace("\r", "");

        assertEquals(expected, actualOutput);
    }

    @Test
    public void testUpdateNotifyListRemoving() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        SchoolService schoolService = SchoolService.getSchoolService();
        double grade1 = 49;
        double grade2 = 45;
        String kardexId = createKardexWithTwoGrades(grade1, grade2, schoolService);
        Device device = createDeviceFirstStudent(schoolService, "44555533");
        schoolService.computeGrades(kardexId);
        schoolService.updateNotifyList(kardexId);

        Student student = SchoolService.getSchool().getStudents().get(0);
        Subject subject = addSubject(schoolService);
        GradesService.addGrade(100, student, subject);
        schoolService.computeGrades(kardexId);
        schoolService.updateNotifyList(kardexId);
        String expected = "No devices to be notified.\n";
        schoolService.sendNotifications();
        String actualOutput = output.toString().replace("\r", "");

        assertEquals(expected, actualOutput);
    }

    @Test
    public void testImportGradesCSV() {
        String path = "test\\colinajose\\resources\\grades.csv";
        String nameField = "Full Name";
        String gradeField = "Grade";
        SchoolService schoolService = SchoolService.getSchoolService();
        createStudent(schoolService);
        String kardexId = SchoolService.getSchool().getKardexes().get(0).getId();
        Subject subject = addSubject(schoolService);
        schoolService.importGrades(kardexId, subject.getId(), path, nameField, gradeField);
        MyCircularDoublyLinkedList<Grade> actualGrades = subject.getGrades();
        Grade actualGrade = subject.getGrades().get(0);

        assertEquals(1, actualGrades.size());
        assertEquals(80, actualGrade.getFinalGrade(), 0);
    }
    @Test
    public void testImportGradesJSON() {
        String path = "test\\colinajose\\resources\\grades.json";
        String nameField = "Full Name";
        String gradeField = "Grade";
        SchoolService schoolService = SchoolService.getSchoolService();
        createStudent(schoolService);
        String kardexId = SchoolService.getSchool().getKardexes().get(0).getId();
        Subject subject = addSubject(schoolService);
        schoolService.importGrades(kardexId, subject.getId(), path, nameField, gradeField);
        MyCircularDoublyLinkedList<Grade> actualGrades = subject.getGrades();
        Grade actualGrade = subject.getGrades().get(0);

        assertEquals(1, actualGrades.size());
        assertEquals(80, actualGrade.getFinalGrade(), 0);
    }

    @Test
    public void testExportGradesCSV() {
        String path = "test\\colinajose\\resources\\export.csv";
        String nameField = "Full Name";
        String gradeField = "Grade";
        String expectedEntry = "\"Grade\",\"Full Name\"\n\"80.0\",\"Saul Miranda\"";
        SchoolService schoolService = SchoolService.getSchoolService();
        createStudent(schoolService);
        String kardexId = SchoolService.getSchool().getKardexes().get(0).getId();
        Subject subject = addSubject(schoolService);
        Student student = SchoolService.getSchool().getStudents().get(0);
        Grade grade = new Grade(student, 80);
        subject.addGrade(grade);
        schoolService.exportGrades(kardexId, path, nameField, gradeField);
        String actualEntry = getConcatLinesFromFile(path);

        assertEquals(expectedEntry, actualEntry);
    }

    @Test
    public void testExportGradesJSON() {
        String path = "test\\colinajose\\resources\\export.json";
        String nameField = "Full Name";
        String gradeField = "Grade";
        String expectedEntry = "[{\"Full Name\":\"Saul Miranda\",\"Grade\":\"80.0\"}]";
        SchoolService schoolService = SchoolService.getSchoolService();
        createStudent(schoolService);
        String kardexId = SchoolService.getSchool().getKardexes().get(0).getId();
        Subject subject = addSubject(schoolService);
        Student student = SchoolService.getSchool().getStudents().get(0);
        Grade grade = new Grade(student, 80);
        subject.addGrade(grade);
        schoolService.exportGrades(kardexId, path, nameField, gradeField);
        String actualEntry = getConcatLinesFromFile(path);

        assertEquals(expectedEntry, actualEntry);
    }

    @Test
    public void getTeachers() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String expectedTeacherId = schoolService.registerTeacher("Juan Perez", "43344444TJ", 37, "Male", "C. San Martin #345", "443333221", "juan@mail.com");
        Teacher expectedTeacher = (Teacher) SearchService.getIndexedElement(expectedTeacherId);
        int expectedSize = 1;
        MyHashMap<String, Teacher> actualTeachers = schoolService.getTeachers();
        MyArrayList<String> actualIds = actualTeachers.getKeys();

        assertEquals(expectedSize, actualTeachers.size());
        assertEquals(expectedTeacherId, actualIds.get(0));
        assertEquals(expectedTeacher, actualTeachers.get(expectedTeacherId));
    }

    @Test
    public void getParents() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String expectedParentId = schoolService.registerParent("Juan Perez", "43344444TJ", 37, "Male", "C. San Martin #345", "443333221", "juan@mail.com");
        Parent expectedParent = (Parent) SearchService.getIndexedElement(expectedParentId);
        int expectedSize = 1;
        MyHashMap<String, Parent> actualParents = schoolService.getParents();
        MyArrayList<String> actualIds = actualParents.getKeys();

        assertEquals(expectedSize, actualParents.size());
        assertEquals(expectedParentId, actualIds.get(0));
        assertEquals(expectedParent, actualParents.get(expectedParentId));
    }

    @Test
    public void getKardexes() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String courseId = schoolService.registerCourse("2nd", "2020", "2A");
        String expectedKardexId = schoolService.registerKardex(courseId);
        Kardex expectedKardex = (Kardex) SearchService.getIndexedElement(expectedKardexId);
        int expectedSize = 1;
        MyHashMap<String, Kardex> actualKardexes = schoolService.getKardexes();
        MyArrayList<String> actualIds = actualKardexes.getKeys();

        assertEquals(expectedSize, actualKardexes.size());
        assertEquals(expectedKardexId, actualIds.get(0));
        assertEquals(expectedKardex, actualKardexes.get(expectedKardexId));
    }

    @Test
    public void getCourses() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String expectedCourseId = schoolService.registerCourse("2nd", "2020", "2A");
        Course expectedCourse = (Course) SearchService.getIndexedElement(expectedCourseId);
        int expectedSize = 1;
        MyHashMap<String, Course> actualCourses = schoolService.getCourses();
        MyArrayList<String> actualIds = actualCourses.getKeys();

        assertEquals(expectedSize, actualCourses.size());
        assertEquals(expectedCourseId, actualIds.get(0));
        assertEquals(expectedCourse, actualCourses.get(expectedCourseId));
    }

    @Test
    public void getSubjects() {
        SchoolService schoolService = SchoolService.getSchoolService();
        String courseId = schoolService.registerCourse("2nd", "2020", "2A");
        String kardexId = schoolService.registerKardex(courseId);
        String teacherId = schoolService.registerTeacher("Juan Perez", "43344444TJ", 37, "Male", "C. San Martin #345", "443333221", "juan@mail.com");
        String expectedSubjectId = schoolService.registerSubject("Mathematics", courseId, teacherId);
        Subject expectedSubject = (Subject) SearchService.getIndexedElement(expectedSubjectId);
        int expectedSize = 1;
        MyHashMap<String, Subject> actualSubjects = schoolService.getSubjects(kardexId);
        MyArrayList<String> actualIds = actualSubjects.getKeys();

        assertEquals(expectedSize, actualSubjects.size());
        assertEquals(expectedSubjectId, actualIds.get(0));
        assertEquals(expectedSubject, actualSubjects.get(expectedSubjectId));
    }

    private String createStudent(SchoolService schoolService) {
        String name = "Saul Miranda";
        String ci = "3444455CB";
        int age = 10;
        String gender = "Male";
        String address = "Av. Simon Lopez #450";
        String phone = "4-4437700";
        String email = "juan@mail.com";
        String courseId = schoolService.registerCourse("2nd", "2020", "2A");
        String kardexId = schoolService.registerKardex(courseId);
        return schoolService.enrollStudent(name, ci, age, gender, kardexId, address, phone, email);
    }

    private String createKardexWithTwoGrades(double grade1, double grade2, SchoolService schoolService) {
        double expectedExpelled = 20;
        double expectedNotify = 50;
        double expectedScholarship = 90;
        String courseId = schoolService.registerCourse("2nd", "2020", "2A");
        String kardexId = schoolService.registerKardex(courseId);
        String teacherId = schoolService.registerTeacher("Juan Perez", "43344444TJ", 37, "Male", "C. San Martin #345", "443333221", "juan@mail.com");
        String studentId = schoolService.enrollStudent("Javier Rocha", "433444432SZ", 37, "Male", kardexId, "C. San Martin #345", "443333221", "juan@mail.com");
        String topic1 = "Mathematics";
        String topic2 = "Literature";
        String subjectId1 = schoolService.registerSubject(topic1, courseId, teacherId);
        String subjectId2 = schoolService.registerSubject(topic2, courseId, teacherId);

        Student student = SchoolService.getSchool().getStudents().get(0);
        Course course = SchoolService.getSchool().getCourses().get(0);
        Subject subject1 = course.getSubjects().get(0);
        Subject subject2 = course.getSubjects().get(1);
        GradesService.addGrade(grade1, student, subject1);
        GradesService.addGrade(grade2, student, subject2);
        schoolService.setGradingScale(kardexId, expectedScholarship, expectedExpelled, expectedNotify);
        return kardexId;
    }

    private Device createDeviceFirstStudent(SchoolService schoolService, String phone) {
        String parentId = schoolService.registerParent("Juan Perez", "43344444TJ", 37, "Male", "C. San Martin #345", "443333221", "juan@mail.com");
        String studentId = SchoolService.getSchool().getStudents().get(0).getId();
        schoolService.assignParent(parentId, studentId);
        schoolService.registerDevice(parentId, phone);
        return SchoolService.getSchool().getDevices().get(0);
    }

    private Subject addSubject(SchoolService schoolService) {
        String topic = "Physics";
        String courseId = SchoolService.getSchool().getCourses().get(0).getId();
        String teacherId = schoolService.registerTeacher("Jonas Mercado", "43344444TJ", 37, "Male", "C. San Martin #345", "443333221", "juan@mail.com");
        String subjectId2 = schoolService.registerSubject(topic, courseId, teacherId);
        return (Subject) SearchService.getIndexedElement(subjectId2);
    }

    private String getConcatLinesFromFile(String path){
        String result = "";
        try{
            File actualFile = new File(path);
            Scanner scannerReader = new Scanner(actualFile);
            while (scannerReader.hasNextLine()){
                result += scannerReader.nextLine() + "\n";
            }
            if(!result.isEmpty()){
                result = result.substring(0, result.length() -1);
            }
            scannerReader.close();
            actualFile.delete();
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());;
        }
        return result;
    }
}