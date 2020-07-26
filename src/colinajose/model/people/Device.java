package colinajose.model.people;

import colinajose.model.base.BaseEntity;
import colinajose.model.people.Person;

public class Device extends BaseEntity {
    private Person owner;
    private String phoneNumber;

    public Device(Person owner) {
        this.owner = owner;
        this.phoneNumber = owner.getContact().getPhone();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}