package colinajose.model.schoolEntities;

import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import colinajose.model.base.BaseEntity;
import colinajose.model.people.Contact;
import colinajose.model.people.Employee;
import colinajose.model.people.Parent;
import colinajose.model.people.Student;
import colinajose.model.people.Teacher;

public class School extends BaseEntity {
    private MyCircularDoublyLinkedList<Student> students;
    private MyCircularDoublyLinkedList<Parent> parents;
    private MyCircularDoublyLinkedList<Course> courses;
    private MyCircularDoublyLinkedList<Kardex> kardexes;
    private MyCircularDoublyLinkedList<Teacher> teachers;
    private MyCircularDoublyLinkedList<Employee> staff;
    private Contact contact;
    private String name;

    public School(String name, Contact contact) {
        this.name = name;
        this.contact = contact;
        this.students = new MyCircularDoublyLinkedList<>();
        this.parents = new MyCircularDoublyLinkedList<>();
        this.courses = new MyCircularDoublyLinkedList<>();
        this.kardexes = new MyCircularDoublyLinkedList<>();
        this.teachers = new MyCircularDoublyLinkedList<>();
        this.staff = new MyCircularDoublyLinkedList<>();
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyCircularDoublyLinkedList getStudents() {
        return students;
    }

    public void setStudents(MyCircularDoublyLinkedList students) {
        this.students = students;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public MyCircularDoublyLinkedList getCourses() {
        return courses;
    }

    public void setCourses(MyCircularDoublyLinkedList courses) {
        this.courses = courses;
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }

    public MyCircularDoublyLinkedList<Kardex> getKardexes() {
        return kardexes;
    }

    public void setKardexes(MyCircularDoublyLinkedList<Kardex> kardexes) {
        this.kardexes = kardexes;
    }

    public void addKardex(Kardex kardex){
        this.kardexes.add(kardex);
    }

    public MyCircularDoublyLinkedList getTeachers() {
        return teachers;
    }

    public void setTeachers(MyCircularDoublyLinkedList teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher){
        this.teachers.add(teacher);
    }

    public MyCircularDoublyLinkedList<Employee> getStaff() {
        return staff;
    }

    public void setStaff(MyCircularDoublyLinkedList<Employee> staff) {
        this.staff = staff;
    }

    public void addStaff(Employee employee){
        this.staff.add(employee);
    }

    public MyCircularDoublyLinkedList<Parent> getParents() {
        return parents;
    }

    public void setParents(MyCircularDoublyLinkedList<Parent> parents) {
        this.parents = parents;
    }

    public void addParent(Parent parent){
        this.parents.add(parent);
    }

}
