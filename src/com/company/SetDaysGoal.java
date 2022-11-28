package com.company;

public class SetDaysGoal extends Goal{
    //days
    void setDifficultyEasy() {
        goalValue = 2;
    }
    void setDifficultyIntermediate() {
        goalValue = 5;
    }
    void setDifficultyChallenging() {
        goalValue = 10;
    }

    String getGoalString() {
        return "  Cycle for " + goalValue + " days consecutively";
    }
}
