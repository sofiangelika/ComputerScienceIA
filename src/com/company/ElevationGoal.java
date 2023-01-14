package com.company;

public class ElevationGoal extends Goal{
    //meters
    void setDifficultyEasy() {
        goalValue = 500;
    }
    void setDifficultyIntermediate() {
        goalValue = 1000;
    }
    void setDifficultyChallenging() {
        goalValue = 1500;
    }

    String getGoalString() {
        return "<html>" + "  Reach an elevation gain of " + goalValue + "<br>" + " meters" + "</html>";
    }
}
