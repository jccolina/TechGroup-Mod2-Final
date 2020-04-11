package colinajose.model.people;

import colinajose.model.base.BaseEntity;

public abstract class Person extends BaseEntity {
    protected String name;
    protected String ci;
    protected Integer age;
    protected Contact contact;
    protected String gender;
    protected String birthday;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Person(String name, String ci, int age, String gender, Contact contact) {
        this.name = name;
        this.ci = ci;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
    }

    public Person(String name, String ci, int age, String gender) {
        this.name = name;
        this.ci = ci;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object objectToCompare){
        if (objectToCompare instanceof Person){
            Person personToCompare = (Person) objectToCompare;
            if(this.ci.equals(personToCompare.getCi()) &&
            this.name.equals(personToCompare.getName()) &&
                    this.age.equals(personToCompare.getAge()) &&
                    this.gender.equals(personToCompare.getGender())) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
