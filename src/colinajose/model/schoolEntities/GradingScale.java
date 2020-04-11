package colinajose.model.schoolEntities;

import colinajose.model.base.BaseEntity;

public class GradingScale extends BaseEntity {
    private int expelledGrade;
    private int scholarshipGrade;
    private int notifyGrade;

    public GradingScale(int expelledGrade, int scholarshipGrade, int notifyGrade) {
        this.expelledGrade = expelledGrade;
        this.scholarshipGrade = scholarshipGrade;
        this.notifyGrade = notifyGrade;
    }

    public int getExpelledGrade() {
        return expelledGrade;
    }

    public void setExpelledGrade(int expelledGrade) {
        this.expelledGrade = expelledGrade;
    }

    public int getScholarshipGrade() {
        return scholarshipGrade;
    }

    public void setScholarshipGrade(int scholarshipGrade) {
        this.scholarshipGrade = scholarshipGrade;
    }

    public int getNotifyGrade() {
        return notifyGrade;
    }

    public void setNotifyGrade(int notifyGrade) {
        this.notifyGrade = notifyGrade;
    }
}
