package com.company;

public class DistanceGoal extends Goal{
    //kilometers
    void setDifficultyEasy() {
        goalValue = 50;
    }
    void setDifficultyIntermediate() {
        goalValue = 100;
    }
    void setDifficultyChallenging() {
        goalValue = 200;
    }
    String getGoalString() {
        return "  Cycle a total of " + goalValue + " kilometers";
    }

}
