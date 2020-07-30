package colinajose.model.people;

import colinajose.model.base.BaseEntity;
import colinajose.model.people.Person;

public class Device extends BaseEntity {
    private Person owner;
    private String phoneNumber;

    public Device(Person owner, String phoneNumber) {
        this.owner = owner;
        this.phoneNumber = phoneNumber;
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

    @Override
    public boolean equals(Object object){
        if(object instanceof Device){
            Device device = (Device) object;
            if (this.phoneNumber.equals(device.getPhoneNumber()) && this.owner.equals(device.getOwner())) {
                return true;
            }
        }
        return false;
    }
}
