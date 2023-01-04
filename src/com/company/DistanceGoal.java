package com.company;

import java.util.Date;

public class DistanceGoal extends Goal{
    //kilometers
    void setDifficultyEasy() {
        goalValue = 50;
        dateStarted = new Date();
    }
    void setDifficultyIntermediate() {
        goalValue = 100;
        dateStarted = new Date();
    }
    void setDifficultyChallenging() {
        goalValue = 200;
        dateStarted = new Date();
    }

    String getGoalString() {
        return "  Cycle a total of " + goalValue + " kilometers";
    }

}
