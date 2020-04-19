package colinajose.model.schoolEntities;

import colinajose.data.DataFileIO;
import colinajose.model.notification.Device;
import colinajose.model.people.Person;
import datastructures.arraylist.MyArrayList;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import colinajose.model.base.BaseEntity;
import colinajose.model.notification.Observable;
import colinajose.model.notification.Observer;
import colinajose.model.people.Contact;
import colinajose.model.people.Employee;
import colinajose.model.people.Parent;
import colinajose.model.people.Principal;
import colinajose.model.people.Secretary;
import colinajose.model.people.Student;
import colinajose.model.people.Teacher;
import datastructures.hashmap.MyHashMap;

public class School extends BaseEntity implements Observable {
    private MyCircularDoublyLinkedList<Student> students;
    private MyCircularDoublyLinkedList<Parent> parents;
    private MyCircularDoublyLinkedList<Course> courses;
    private MyCircularDoublyLinkedList<Kardex> kardexes;
    private MyCircularDoublyLinkedList<Teacher> teachers;
    private MyCircularDoublyLinkedList<Observer> devices;
    private MyCircularDoublyLinkedList<Employee> staff;
    private MyArrayList<Contact> contacts;
    private Principal principal;
    private Secretary secretary;
    private DataFileIO dataFileIO;

    private Contact contact;
    private String name;

    public School(String name, String address, String phone, String email) {
        this.name = name;
        this.contact = new Contact(address, phone, email);
        this.students = new MyCircularDoublyLinkedList<>();
        this.parents = new MyCircularDoublyLinkedList<>();
        this.courses = new MyCircularDoublyLinkedList<>();
        this.kardexes = new MyCircularDoublyLinkedList<>();
        this.teachers = new MyCircularDoublyLinkedList<>();
        this.devices = new MyCircularDoublyLinkedList<>();
        this.staff = new MyCircularDoublyLinkedList<>();
        this.contacts = new MyArrayList<>();
        this.dataFileIO = new DataFileIO();
    }

    public MyArrayList<MyHashMap<String, String>> readFile(String fileType, String path){
        MyHashMap<String, String> parameters = new MyHashMap<>();
        parameters.put("path", path);
        return this.dataFileIO.readData(fileType, parameters);
    }

    public void setGradingScale(double expelledGrade, double notifyGrade, double scholarshipGrade, String courseId){
        Course course = searchElementById(courseId, this.courses);
        Kardex kardex = getElement(this.kardexes, new Kardex(course));
        kardex.setGradingScale(expelledGrade, notifyGrade, scholarshipGrade);
    }

    public String[] getStudentsAverage(String courseId){
        Course course = searchElementById(courseId, this.courses);
        Kardex kardex = getElement(this.kardexes, new Kardex(course));
        kardex.computeAverageGrades();
        MyArrayList<MyArrayList> averages = kardex.getSortedAverages();
        String[] sortedAverages = new String[averages.get(0).size()];
        for (int i = 0; i < averages.get(0).size(); i++) {
            sortedAverages[i] = averages.get(0).get(i) + ":" + averages.get(1).get(i);
        }
        return sortedAverages;
    }
    public void updateStudentsState(String courseId){
        Course course = searchElementById(courseId, this.courses);
        Kardex kardex = getElement(this.kardexes, new Kardex(course));
        kardex.computeAverageGrades();
        kardex.computeStudentsState();
    }
    public String[] filterStudentsByState(String courseId, String state){
        Course course = searchElementById(courseId, this.courses);
        Kardex kardex = getElement(this.kardexes, new Kardex(course));
        MyCircularDoublyLinkedList<Student> studentsFiltered = kardex.filterStudents(state);
        String[] result = new String[studentsFiltered.size()];
        for (int i = 0; i < studentsFiltered.size(); i++) {
            result[i] = studentsFiltered.get(i).getName();
        }
        return result;
    }
    public String enrollStudent(String name, String ci, Integer age, String gender, String address, String phone, String email, String courseId) {
        Contact contact = new Contact(address, phone, email);
        Student student = new Student(name, ci, age, gender, contact);
        Course course = searchElementById(courseId, this.courses);
        Kardex kardex = getElement(this.kardexes, new Kardex(course));
        if (kardex == null) {
            return null;
        } else {
            kardex.addStudent(student);
            this.students.add(student);
            Device device = new Device(this, student);
            registerObserver(device);
            return student.getId();
        }
    }

    private <T> T searchElementById(String id, MyCircularDoublyLinkedList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if( ((BaseEntity) list.get(i)).getId().equals(id)){
                return list.get(i);
            }
        }
        return null;
    }

    public String registerCourse(String grade, String year, String group) {
        Course course = new Course(grade, year, group);
        if (this.courses.contains(course)) {
            return null;
        }
        Kardex kardex = new Kardex(course);
        this.kardexes.add(kardex);
        this.courses.add(course);
        return course.getId();
    }

    public String registerSubject(String topic, String courseId, String teacherId) {
        Subject subject = new Subject(topic);
        Course course = searchElementById(courseId, this.courses);
        Teacher teacher = searchElementById(teacherId, this.teachers);
        if (course != null && teacher != null) {
            subject.setTeacher(teacher);
            course.addSubject(subject);
            return subject.getId();
        }
        return null;
    }

    public boolean assignParent(String studentId, String parentId){
        Parent parent = searchElementById(parentId, this.parents);
        Student student = searchElementById(studentId, this.students);
        if(parent !=null && student!=null){
            student.addParent(parent);
            return true;
        }
        return false;
    }
    public String registerContact(String address, String phone, String email){
        String contactId;
        Contact contact = new Contact(address, phone, email);
        this.contacts.add(contact);
        return contact.getId();
    }
    public void sendNotification(){
        notifyObservers();
    }

    public String addTeacher(String name, String CI, Integer age, String gender, String address, String phone, String email) {
        Contact contact = new Contact(address, phone, email);
        Teacher teacher = new Teacher(name, CI, age, gender, contact);
        this.teachers.add(teacher);
        return teacher.getId();
    }

    public String addParent(String name, String ci, Integer age, String gender, String address, String phone, String email, String addressWork, String phoneWork, String emailWork) {
        Contact contact = new Contact(address, phone, email);
        Contact workContact = new Contact(addressWork, phoneWork, emailWork);
        Parent parent = new Parent(name, ci, age, gender, contact);
        parent.setWorkContact(workContact);
        this.parents.add(parent);
        return parent.getId();
    }

    public String addParent(String name, String ci, Integer age, String gender, String address, String phone, String email) {
        Contact contact = new Contact(address, phone, email);
        Parent parent = new Parent(name, ci, age, gender, contact);
        this.parents.add(parent);
        return parent.getId();
    }

    public String addParent(String name, String ci, Integer age, String gender) {
        Parent parent = new Parent(name, ci, age, gender);
        this.parents.add(parent);
        return parent.getId();
    }

    public boolean setGrade(Integer newGrade, String subjectId, String studentId, String courseId) {
        Student student = searchElementById(studentId, this.students);
        Course course = searchElementById(courseId, this.courses);
        if (student != null && course != null) {
            Subject subject = searchElementById(subjectId, course.getSubjects());
            if (subject != null) {
                Grade grade = new Grade(student, newGrade);
                subject.addSingleGrade(grade);
                return true;
            }
        }
        return false;
    }

    public void addStaff(Employee employee) {
        this.staff.add(employee);
    }

    public void setGradingScale(GradingScale gradingScale, Course course) {

    }

    private <T> T getElement(MyCircularDoublyLinkedList<T> list, T element) {
        for (int i = 0; i < list.size(); i++) {
            T currentElement = list.get(i);
            if (currentElement.equals(element)) {
                return currentElement;
            }
        }
        return null;
    }

    @Override
    public void registerObserver(Observer observer) {
        this.devices.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.devices.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < this.devices.size(); i++) {
            this.devices.get(i).update();
        }
    }

    @Override
    public Object getState(Object owner) {
        if(owner instanceof Student){
            Student student = (Student) owner;
            return student.getState();
        }else{
            return "";
        }
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

    public MyCircularDoublyLinkedList getCourses() {
        return courses;
    }

    public void setCourses(MyCircularDoublyLinkedList courses) {
        this.courses = courses;
    }

    public MyCircularDoublyLinkedList<Kardex> getKardexes() {
        return kardexes;
    }

    public void setKardexes(MyCircularDoublyLinkedList<Kardex> kardexes) {
        this.kardexes = kardexes;
    }

    public MyCircularDoublyLinkedList getTeachers() {
        return teachers;
    }

    public void setTeachers(MyCircularDoublyLinkedList teachers) {
        this.teachers = teachers;
    }

    public MyCircularDoublyLinkedList<Observer> getDevices() {
        return devices;
    }

    public void setDevices(MyCircularDoublyLinkedList<Observer> devices) {
        this.devices = devices;
    }

    public MyCircularDoublyLinkedList<Employee> getStaff() {
        return staff;
    }

    public void setStaff(MyCircularDoublyLinkedList<Employee> staff) {
        this.staff = staff;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }

}
