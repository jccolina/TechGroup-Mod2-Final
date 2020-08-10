package colinajose.service;

import colinajose.data.DataIO;
import colinajose.model.base.BaseEntity;
import colinajose.model.people.Contact;
import colinajose.model.people.Device;
import colinajose.model.people.Parent;
import colinajose.model.people.Person;
import colinajose.model.people.Student;
import colinajose.model.people.Teacher;
import colinajose.model.schoolEntities.Course;
import colinajose.model.schoolEntities.Grade;
import colinajose.model.schoolEntities.Kardex;
import colinajose.model.schoolEntities.School;
import colinajose.model.schoolEntities.Subject;
import datastructures.arraylist.MyArrayList;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import datastructures.hashmap.MyHashMap;
import datastructures.linkedlist.MyLinkedList;

public class SchoolService {
    private static SchoolService schoolService;
    private School school;
    private ObservableService observable;
    private DataIO dataHandler;

    private SchoolService() {
        this.observable = new ObservableService();
        this.dataHandler = new DataIO();
        this.school = new School();
    }

    public static SchoolService getSchoolService(){
        if(schoolService == null){
            schoolService = new SchoolService();
        }
        return schoolService;
    }

    public void editSchool(String name, String address, String phone, String email) {
        Contact contact = new Contact(address, phone, email);
        this.school.setName(name);
        this.school.setContact(contact);
    }

    public String enrollStudent(String name, String ci, int age, String gender, String kardexId, String address, String phone, String email) {
        Contact contact = new Contact(address, phone, email);
        Student student = new Student(name, ci, age, gender, contact);
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        kardex.addStudent(student);
        this.school.addStudent(student);
        SearchService.indexElement(student.getId(), student);
        return student.getId();
    }

    public void assignParent(String parentId, String studentId) {
        Parent parent = (Parent) SearchService.getIndexedElement(parentId);
        Student student = (Student) SearchService.getIndexedElement(studentId);
        student.addParent(parent);
    }

    public String registerKardex(String courseId) {
        Course course = (Course) SearchService.getIndexedElement(courseId);
        Kardex kardex = new Kardex(course);
        this.school.addKardex(kardex);
        SearchService.indexElement(kardex.getId(), kardex);
        return kardex.getId();
    }

    public String registerCourse(String grade, String year, String group) {
        Course course = new Course(grade, year, group);
        this.school.addCourse(course);
        SearchService.indexElement(course.getId(), course);
        return course.getId();
    }

    public String registerParent(String name, String ci, int age, String gender, String address, String phone, String email) {
        Contact contact = new Contact(address, phone, email);
        Parent parent = new Parent(name, ci, age, gender, contact);
        this.school.addParent(parent);
        SearchService.indexElement(parent.getId(), parent);
        return parent.getId();
    }

    public String registerSubject(String topic, String courseId, String teacherId) {
        Subject subject = new Subject(topic);
        Course course = (Course) SearchService.getIndexedElement(courseId);
        Teacher teacher = getTeacher(teacherId);
        subject.setTeacher(teacher);
        course.addSubject(subject);
        SearchService.indexElement(subject.getId(), subject);
        return subject.getId();
    }

    public String registerTeacher(String name, String ci, int age, String gender, String address, String phone, String email) {
        Contact contact = new Contact(address, phone, email);
        Teacher teacher = new Teacher(name, ci, age, gender, contact);
        this.school.addTeacher(teacher);
        SearchService.indexElement(teacher.getId(), teacher);
        return teacher.getId();
    }

    public String registerDevice(String personId, String phoneNumber) {
        Person person = (Parent) SearchService.getIndexedElement(personId);
        Device device = new Device(person, phoneNumber);
        this.school.addDevice(device);
        SearchService.indexElement(device.getId(), device);
        return device.getId();
    }

    public void setGradingScale(String kardexId, double scholarship, double expelled, double notify) {
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        Course course = kardex.getCourse();
        GradesService.setGradingScale(course, scholarship, expelled, notify);
    }

    public void computeGrades(String kardexId) {
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        GradesService.computeGrades(kardex);
        GradesService.computeStudentStates(kardex);
    }

    public void updateNotifyList(String kardexId) {
        MyLinkedList<Device> devicesToDetach = getDevicesToDetach(kardexId);
        removeObservers(devicesToDetach);
        MyLinkedList<Device> devicesToNotify = getDevicesToNotify(kardexId);
        registerObservers(devicesToNotify);
    }

    public void sendNotifications() {
        this.observable.notifyObservers();
    }

    private MyLinkedList<Device> getDevicesToDetach(String kardexId) {
        MyLinkedList<Student> students = new MyLinkedList<>();
        students.addAll(getAverageStudents(kardexId));
        students.addAll(getScholarshipStudents(kardexId));
        students.addAll(getExpelledStudents(kardexId));
        return getDevices(students);
    }

    private MyLinkedList<Device> getDevicesToNotify(String kardexId) {
        MyLinkedList<Student> students = new MyLinkedList<>();
        students.addAll(getNotifyStudents(kardexId));
        return getDevices(students);
    }

    private MyLinkedList<Device> getDevices(MyLinkedList<Student> students) {
        MyLinkedList<Device> devices = new MyLinkedList<>();
        if (!students.isEmpty()) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                MyCircularDoublyLinkedList<Person> parents = student.getParents();
                if (parents.size() != 0) {
                    for (int j = 0; j < parents.size(); j++) {
                        devices.addAll(SearchService.getDevices(parents.get(j)));
                    }
                }
            }
        }
        return devices;
    }

    private void removeObservers(MyLinkedList<Device> devices) {
        if (!devices.isEmpty()) {
            for (int i = 0; i < devices.size(); i++) {
                Device device = devices.get(i);
                this.observable.removeObserver(new DeviceService(device));
            }
        }
    }

    private void registerObservers(MyLinkedList<Device> devices) {
        if (!devices.isEmpty()) {
            for (int i = 0; i < devices.size(); i++) {
                Device device = devices.get(i);
                this.observable.registerObserver(new DeviceService(device));
            }
        }
    }

    private MyLinkedList<Student> getExpelledStudents(String kardexId) {
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        return SearchService.getStudentsbyState(kardex.getStudents(), Student.State.EXPELLED);
    }

    private MyLinkedList<Student> getScholarshipStudents(String kardexId) {
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        return SearchService.getStudentsbyState(kardex.getStudents(), Student.State.SCHOLARSHIP);
    }

    private MyLinkedList<Student> getAverageStudents(String kardexId) {
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        return SearchService.getStudentsbyState(kardex.getStudents(), Student.State.AVERAGE);
    }

    private MyLinkedList<Student> getNotifyStudents(String kardexId) {
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        return SearchService.getStudentsbyState(kardex.getStudents(), Student.State.NOTIFY);
    }

    public boolean importGrades(String kardexId, String subjectId, String pathFile, String nameField, String gradeField) {
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        Subject subject = (Subject) SearchService.getIndexedElement(subjectId);
        MyArrayList<MyHashMap<String, String>> entries = this.dataHandler.read(pathFile);
        if (subject == null || entries == null || entries.isEmpty()) {
            return false;
        }
        boolean isImportSucess = true;
        for (int i = 0; i < entries.size(); i++) {
            MyHashMap<String, String> entry = entries.get(i);
            String studentName = entry.get(nameField);
            Student student = SearchService.getStudentsbyName(kardex.getStudents(), studentName);
            if (student == null) {
                isImportSucess = false;
            } else {
                double grade = Double.parseDouble(entry.get(gradeField));
                GradesService.addGrade(grade, student, subject);
            }
        }
        return isImportSucess;
    }

    public boolean exportGrades(String kardexId, String pathFile, String studentField, String gradeField) {
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        GradesService.computeGrades(kardex);
        MyCircularDoublyLinkedList<Grade> sortedGrades = GradesService.sortGrades();
        MyArrayList<MyHashMap<String, String>> entriesToWrite = gradesToEntries(sortedGrades, studentField, gradeField);
        return dataHandler.write(pathFile, entriesToWrite);
    }

    private MyArrayList<MyHashMap<String, String>> gradesToEntries(MyCircularDoublyLinkedList<Grade> grades, String studentField, String gradeField) {
        MyArrayList<MyHashMap<String, String>> entries = new MyArrayList<>();
        if (grades.size() != 0) {
            for (int i = 0; i < grades.size(); i++) {
                MyHashMap<String, String> entry = new MyHashMap<>();
                entry.put(studentField, grades.get(i).getStudent().getName());
                entry.put(gradeField, String.valueOf(grades.get(i).getFinalGrade()));
                entries.add(entry);
            }
        }
        return entries;
    }

    public MyHashMap<String, Student> getStudents(String kardexId) {
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        return elementsToMap(kardex.getStudents());
    }

    public MyCircularDoublyLinkedList<Grade> getGrades(String kardexId) {
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        GradesService.computeGrades(kardex);
        return GradesService.sortGrades();
    }

    public Teacher getSubjectTeacher(String subjectId, String courseId) {
        Subject subject = (Subject) SearchService.getIndexedElement(subjectId);
        return subject.getTeacher();
    }

    private Teacher getTeacher(String teacherId) {
        return (Teacher) SearchService.getIndexedElement(teacherId);
    }

    public Student getStudent(String studentId) {
        return (Student) SearchService.getIndexedElement(studentId);
    }

    private <T extends BaseEntity> MyHashMap<String, T> elementsToMap(MyCircularDoublyLinkedList<T> elements) {
        MyHashMap<String, T> mapResult = new MyHashMap<>();
        if (elements.size() > 0) {
            for (int i = 0; i < elements.size(); i++) {
                T element = elements.get(i);
                mapResult.put(element.getId(), element);
            }
        }
        return mapResult;
    }

    public MyHashMap<String, Teacher> getTeachers() {
        return elementsToMap(this.school.getTeachers());
    }

    public MyHashMap<String, Parent> getParents() {
        return elementsToMap(this.school.getParents());
    }

    public MyHashMap<String, Kardex> getKardexes() {
        return elementsToMap(this.school.getKardexes());
    }

    public MyHashMap<String, Course> getCourses() {
        return elementsToMap(this.school.getCourses());
    }

    public MyHashMap<String, Subject> getSubjects(String kardexId) {
        Kardex kardex = (Kardex) SearchService.getIndexedElement(kardexId);
        Course course = kardex.getCourse();
        return elementsToMap(course.getSubjects());
    }

    public static School getSchool(){
        return schoolService.school;
    }
}
