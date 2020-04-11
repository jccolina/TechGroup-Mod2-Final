package colinajose.model.notification;

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
//        String state = this.studentsState.getStudentState(owner);
//        if(state.equals("NOTIFY")){
//            sendNotification();
//        } else {
//            this.studentsState.removeObserver(this);
//        }
    }

    public void sendNotification(){

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
