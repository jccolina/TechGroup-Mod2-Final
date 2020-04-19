package colinajose.model.schoolEntities;

import colinajose.model.base.BaseEntity;
import colinajose.model.people.Student;

public class Grade extends BaseEntity {
    private Student student;
    private double finalGrade;

    public Grade(Student student, double finalGrade) {
        this.student = student;
        this.finalGrade = finalGrade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(float finalGrade) {
        this.finalGrade = finalGrade;
    }
}
