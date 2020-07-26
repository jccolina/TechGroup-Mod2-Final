package colinajose.service;

import colinajose.model.base.BaseEntity;
import colinajose.model.people.Device;
import colinajose.model.people.Parent;
import colinajose.model.people.Person;
import colinajose.model.people.Student;
import colinajose.model.people.Teacher;
import colinajose.model.schoolEntities.Course;
import colinajose.model.schoolEntities.Kardex;
import colinajose.model.schoolEntities.School;
import colinajose.model.schoolEntities.Subject;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import datastructures.linkedlist.MyLinkedList;

public class SearchService {
    public static Course getCourse(School school, String courseId){
        return getElement(school.getCourses(), courseId);
    }

    public static Parent getParent(School school, String parentId){
        return getElement(school.getParents(), parentId);
    }

    public static Kardex getKardex(School school, String kardexId) {
        return getElement(school.getKardexes(), kardexId);
    }

    public static Teacher getTeacher(School school, String teacherId) {
        return getElement(school.getTeachers(), teacherId);
    }

    public static Student getStudent(School school, String studentId) {
        return getElement(school.getStudents(), studentId);
    }

    public static Subject getSubject(Course course, String subjectId) {
        return getElement(course.getSubjects(), subjectId);
    }

    private static <T> T getElement(MyCircularDoublyLinkedList<T> elements, String id){
        T foundElement = null;
        for (int i = 0; i < elements.size(); i++) {
            T element = elements.get(i);
            if(((BaseEntity) element).getId().equals(id)){
                foundElement = element;
            }
        }
        return foundElement;
    }

    public static MyLinkedList<Student> getStudentsbyState(MyCircularDoublyLinkedList<Student> students, Student.State state) {
        MyLinkedList<Student> result = new MyLinkedList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if(student.getState().equals(state)){
                result.add(student);
            }
        }
        return result;
    }

    public static Device getDevice(School school, Person person) {
        Device foundDevice = null;
        MyCircularDoublyLinkedList<Device> devices = school.getDevices();
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            if(device.getOwner().equals(person)){
                foundDevice = device;
            }
        }
        return foundDevice;
    }
}
