package colinajose.model.notification;

import colinajose.model.people.Contact;
import colinajose.model.people.Person;
import colinajose.model.people.Student;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;

public class Device implements Observer{
    private Observable studentsState;
    private Person owner;

    public Device(Observable studentsState, Person owner) {
        this.studentsState = studentsState;
        this.owner = owner;
    }

    @Override
    public void update() {
        String state = (String) this.studentsState.getState(owner);
        if(state.equals("NOTIFY")){
            sendNotification();
        }
    }

    public void sendNotification(){
        System.out.println("Sending notification for student: " + owner.getName());
        MyCircularDoublyLinkedList parents = ((Student)owner).getParents();
        for (int i = 0; i < parents.size(); i++) {
            Person parent = (Person) parents.get(i);
            Contact contact = parent.getContact();
            System.out.println("Sending notification to following contact: " + "\n"
                    +"Parent: " + parent.getName() + "\n"
                    +"Phone: "+contact.getPhone() + "\n"
                    +"Email: " + contact.getEmail());
            System.out.println("Dear " + parent.getName() + ",\n" +
                    "Your son "+ owner.getName() +", has not been accomplishing the minimum expectations required for our school.\n" +
                    "Therefore we would like to get together in order to discuss about it" );
        }

    }

    public Observable getStudentsState() {
        return studentsState;
    }

    public void setStudentsState(Observable studentsState) {
        this.studentsState = studentsState;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
