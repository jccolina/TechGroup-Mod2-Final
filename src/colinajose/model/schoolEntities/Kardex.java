package colinajose.model.schoolEntities;

import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import colinajose.model.base.BaseEntity;
import colinajose.model.people.Student;

public class Kardex extends BaseEntity {
    private MyCircularDoublyLinkedList<Student> students;
    private Course course;

    public Kardex(Course course) {
        this.course = course;
        this.students = new MyCircularDoublyLinkedList<>();
    }

    public MyCircularDoublyLinkedList<Student> getStudents() {
        return students;
    }

    public void setStudents(MyCircularDoublyLinkedList<Student> students) {
        this.students = students;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Kardex) {
            Kardex kardexToCompare = (Kardex) object;
            if (this.course.equals(kardexToCompare.getCourse())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return getCourse().toString();
    }
}
