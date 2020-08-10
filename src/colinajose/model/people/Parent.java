package colinajose.model.people;

public class Parent extends Person {
    private Contact workContact;
    private String job;
    public Parent(String name, String CI, int age, String gender, Contact contact) {
        super(name, CI, age, gender, contact);
    }
    public Parent(String name, String CI, int age, String gender) {
        super(name, CI, age, gender);
    }
    public Contact getWorkContact() {
        return workContact;
    }

    public void setWorkContact(Contact workContact) {
        this.workContact = workContact;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
