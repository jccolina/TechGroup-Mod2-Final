package colinajose.model.schoolEntities;

import colinajose.model.base.BaseEntity;

public class GradingScale extends BaseEntity {

    private double scholarshipGrade;
    private double notifyGrade;
    private double expelledGrade;

    public GradingScale(double expelledGrade, double notifyGrade, double scholarshipGrade) {
        this.expelledGrade = expelledGrade;
        this.scholarshipGrade = scholarshipGrade;
        this.notifyGrade = notifyGrade;
    }

    public double getExpelledGrade() {
        return expelledGrade;
    }

    public void setExpelledGrade(double expelledGrade) {
        this.expelledGrade = expelledGrade;
    }

    public double getScholarshipGrade() {
        return scholarshipGrade;
    }

    public void setScholarshipGrade(double scholarshipGrade) {
        this.scholarshipGrade = scholarshipGrade;
    }

    public double getNotifyGrade() {
        return notifyGrade;
    }

    public void setNotifyGrade(double notifyGrade) {
        this.notifyGrade = notifyGrade;
    }
}
