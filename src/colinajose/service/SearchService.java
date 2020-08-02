package colinajose.service;

import colinajose.model.base.BaseEntity;
import colinajose.model.people.Device;
import colinajose.model.people.Person;
import colinajose.model.people.Student;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import datastructures.hashmap.MyHashMap;
import datastructures.linkedlist.MyLinkedList;

public class SearchService {
    private static MyHashMap<String, BaseEntity> indexTable = new MyHashMap<>();

    public static <T extends BaseEntity> void indexElement(String id, T element){
        indexTable.put(id, element);
    }
    public static BaseEntity getIndexedElement(String id){
        return indexTable.get(id);
    }

    public static MyLinkedList<Student> getStudentsbyState(MyCircularDoublyLinkedList<Student> students, Student.State state) {
        MyLinkedList<Student> result = new MyLinkedList<>();
        if(students.size() > 0) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (student.getState().equals(state)) {
                    result.add(student);
                }
            }
        }
        return result;
    }

    public static MyLinkedList<Device> getDevices(Person person) {
        MyLinkedList<Device> foundDevices = new MyLinkedList<>();
        MyCircularDoublyLinkedList<Device> devices = SchoolService.getSchool().getDevices();
        if(devices.size() != 0) {
            for (int i = 0; i < devices.size(); i++) {
                Device device = devices.get(i);
                if (device.getOwner().equals(person)) {
                    foundDevices.add(device);
                }
            }
        }
        return foundDevices;
    }

    public static Student getStudentsbyName(MyCircularDoublyLinkedList<Student> students, String studentName) {
        if(students.size() != 0) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (student.getName().equals(studentName)) {
                    return student;
                }
            }
        }
        return null;
    }
}
