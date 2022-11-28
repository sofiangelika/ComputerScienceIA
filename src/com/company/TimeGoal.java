package com.company;

public class TimeGoal extends Goal{
    //hours
    void setDifficultyEasy() {
        goalValue = 3;
    }
    void setDifficultyIntermediate() {
        goalValue = 8;
    }
    void setDifficultyChallenging() {
        goalValue = 12;
    }
    String getGoalString() {
        return "  Cycle a total of " + goalValue + " hours";
    }
}
