package colinajose.model.notification;

import colinajose.model.people.Contact;
import colinajose.model.people.Person;

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
        Contact contact = owner.getContact();
        System.out.println("Sending notification to following contact: " + "\n"
                +"Name: " + owner.getName() + "\n"
                +"Phone: "+ contact.getPhone() + "\n"
                +"Email: " + contact.getEmail());
        System.out.println("Dear " + owner.getName() + ",\n" +
                "You are receiving this notification since the student registered with you has not been accomplishing the minimum expectations required for our school.\n" +
                "Therefore we would like to get together in order to discuss about it" + "\n" +
                "Regards");
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
