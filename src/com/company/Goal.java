package com.company;
import java.io.*;
import java.util.Date;

public class Goal implements Serializable {
    double goalValue;
    double progressValue;
    Date dateStarted;
    boolean completed = false;

    void updateProgress(double updateValue) {
        progressValue += updateValue;
        if (progressValue >= goalValue) {
            completed = true;
        }
    }

    public double getGoalValue() {
        return goalValue;
    }

    public void setGoalValue(double goalValue) {
        this.goalValue = goalValue;
    }

    public double getProgressValue() {
        return progressValue;
    }

    boolean hasMonthPassed(Date currentDate) {
        long differenceInTime = currentDate.getTime() - dateStarted.getTime();
        long differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
        return differenceInDays > 30.0;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

}
