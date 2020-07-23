package colinajose.service;

import colinajose.model.people.Contact;
import colinajose.model.people.Parent;
import colinajose.model.people.Student;
import colinajose.model.people.Teacher;
import colinajose.model.schoolEntities.Course;
import colinajose.model.schoolEntities.Grade;
import colinajose.model.schoolEntities.Kardex;
import colinajose.model.schoolEntities.School;
import colinajose.model.schoolEntities.Subject;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;

public class SchoolService {
    private School school;
    private GradesService gradesService;
    private ObservableService observable;
    private SearchService searcher;

    public SchoolService(){
        this.gradesService = new GradesService();
        this.observable = new ObservableService();
        this.searcher = new SearchService();
    }

    public void createSchool(String name, String address, String phone, String email){
        Contact contact = new Contact(address, phone, email);
        this.school = new School(name, contact);
    }
    public String enrollStudent(String name, String ci, int age, String gender, String kardexId, String parentId, String address, String phone, String email){
        Contact contact = new Contact(address, phone, email);
        Student student = new Student(name, ci, age, gender, contact);
        Parent parent = SearchService.getParent(this.school, parentId);
        Kardex kardex = SearchService.getKardex(this.school, kardexId);
        student.addParent(parent);
        kardex.addStudent(student);
        return student.getId();
    }
    public String registerKardex(String courseId){
        Course course = SearchService.getCourse(this.school, courseId);
        Kardex kardex = new Kardex(course);
        this.school.addKardex(kardex);
        return kardex.getId();
    }
    public String registerCourse(String grade, String year, String group){
        Course course = new Course(grade, year, group);
        this.school.addCourse(course);
        return course.getId();
    }
    public String registerParent(String name, String ci, int age, String gender, String address, String phone, String email){
        Contact contact = new Contact(address, phone, email);
        Parent parent = new Parent(name, ci, age, gender, contact);
        school.addParent(parent);
        return parent.getId();
    }
    public String registerSubject(String topic, String courseId, String teacherId){
        Subject subject = new Subject(topic);
        Course course = SearchService.getCourse(school, courseId);
        course.addSubject(subject);
        return subject.getId();
    }
    public String registerTeacher(String name, String ci, int age, String gender, String address, String phone, String email){
        Contact contact = new Contact(address, phone, email);
        Teacher teacher = new Teacher(name, ci, age, gender, contact);
        school.addTeacher(teacher);
        return teacher.getId();
    }
    public void sendNotifications(){}
    public void setGradingScale(String courseId, double scholarship, double expelled, double notify){
        Course course = SearchService.getCourse(this.school, courseId);
        gradesService.setGradingScale(course, scholarship, expelled, notify);
    }
    public MyCircularDoublyLinkedList<Student> getExpelledStudents(String kardexId){
        Kardex kardex = SearchService.getKardex(this.school, kardexId);
        return SearchService.getStudentsbyState(kardex.getStudents(), Student.State.EXPELLED);
    }
    public MyCircularDoublyLinkedList<Student> getScholarshipStudents(String kardexId){
        Kardex kardex = SearchService.getKardex(this.school, kardexId);
        return SearchService.getStudentsbyState(kardex.getStudents(), Student.State.EXPELLED);
    }
    public MyCircularDoublyLinkedList<Student> getNotifyStudents(String kardexId){
        Kardex kardex = SearchService.getKardex(this.school, kardexId);
        return SearchService.getStudentsbyState(kardex.getStudents(), Student.State.EXPELLED);
    }
    public MyCircularDoublyLinkedList<Student> getStudents(String kardexId){
        Kardex kardex = SearchService.getKardex(this.school, kardexId);
        return kardex.getStudents();
    }
    public MyCircularDoublyLinkedList<Subject> getSubjects(String courseId){
        Course course = SearchService.getCourse(this.school, courseId);
        return course.getSubjects();
    }
    public MyCircularDoublyLinkedList<Grade> getGrades(String kardexId){
        Kardex kardex = SearchService.getKardex(this.school, kardexId);
        return gradesService.computeGrades(kardex);
    }
    public Teacher getSubjectTeacher(String subjectId, String courseId){
        Course course = SearchService.getCourse(this.school, courseId);
        Subject subject = SearchService.getSubject(course, subjectId);
        return subject.getTeacher();
    }
    public Teacher getTeacher(String teacherId){
        return SearchService.getTeacher(this.school, teacherId);
    }
    public Student getStudent(String studentId){
        return SearchService.getStudent(this.school, studentId);
    }
}
