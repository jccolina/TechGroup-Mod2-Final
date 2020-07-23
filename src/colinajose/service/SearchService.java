package colinajose.service;

import colinajose.model.base.BaseEntity;
import colinajose.model.people.Parent;
import colinajose.model.people.Student;
import colinajose.model.people.Teacher;
import colinajose.model.schoolEntities.Course;
import colinajose.model.schoolEntities.Kardex;
import colinajose.model.schoolEntities.School;
import colinajose.model.schoolEntities.Subject;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;

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

    public static MyCircularDoublyLinkedList<Student> getStudentsbyState(MyCircularDoublyLinkedList<Student> students, Student.State state) {
        MyCircularDoublyLinkedList<Student> result = new MyCircularDoublyLinkedList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if(student.getState().equals(state)){
                result.add(student);
            }
        }
        return result;
    }

}
