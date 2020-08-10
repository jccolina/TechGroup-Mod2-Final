package colinajose.model.people;

public class Employee extends Person {
    protected float salary;
    protected float workingHours;
    protected String jobTitle;

    public Employee(String name, String CI, int age, String gender, Contact contact) {
        super(name, CI, age, gender, contact);
    }
    public Employee(String name, String CI, int age, String gender) {
        super(name, CI, age, gender);
    }
    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(float workingHours) {
        this.workingHours = workingHours;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

}