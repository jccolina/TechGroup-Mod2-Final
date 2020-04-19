package colinajose.model.schoolEntities;

import datastructures.arraylist.MyArrayList;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import colinajose.model.base.BaseEntity;
import colinajose.model.people.Student;
import datastructures.hashmap.MyHashMap;

public class Kardex extends BaseEntity {
    private MyCircularDoublyLinkedList<Student> students;
    private Course course;
    private MyHashMap<Student, Double> averages;
    private MyArrayList<MyArrayList> sortedAverages;

    public Kardex(Course course) {
        this.course = course;
        this.students = new MyCircularDoublyLinkedList<>();
        this.averages = new MyHashMap<>();
        this.sortedAverages = new MyArrayList<MyArrayList>();
    }

    public void computeAverageGrades(){
        this.averages = course.computeAverage(students);
        this.sortedAverages = sortAverages(averages, students);
    }

    public MyArrayList<MyArrayList> getSortedAverages() {
        return sortedAverages;
    }

    private MyArrayList<MyArrayList> sortAverages(MyHashMap<Student, Double> averages, MyCircularDoublyLinkedList<Student> students){
        MyArrayList<MyArrayList> result = new MyArrayList<>();
        if(averages.size()!=0){
            MyArrayList<String> studentsSorted = new MyArrayList<>();
            MyArrayList<Double> averagesSorted = new MyArrayList<>();
            studentsSorted.add(students.get(0).getName());
            averagesSorted.add(averages.get(students.get(0)));
            for (int i = 1; i < averages.size(); i++) {
                double average = averages.get(students.get(i));
                boolean itemAdded = false;
                for (int j = 0; j < averagesSorted.size() && !itemAdded; j++) {
                    if(average > averagesSorted.get(j) ){
                        averagesSorted.add(j, average);
                        studentsSorted.add(j, students.get(i).getName());
                        itemAdded = true;
                    }
                }
                if(!itemAdded){
                    averagesSorted.add(average);
                    studentsSorted.add(students.get(i).getName());
                }
            }
            result.add(studentsSorted);
            result.add(averagesSorted);
        }
        return result;
    }

    public void setGradingScale( double expelled, double notify, double scholarship){
        course.setGradingScale(expelled, notify, scholarship);
    }

    public void computeStudentsState() {
        course.computeStudentsState(averages, students);
    }

    public MyCircularDoublyLinkedList<Student> filterStudents(String state){
        return course.filterStudents(students, state);
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
}
