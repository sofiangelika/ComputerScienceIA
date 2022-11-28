package com.company;

public class ElevationGoal extends Goal{
    //meters
    void setDifficultyEasy() {
        goalValue = 300;
    }
    void setDifficultyIntermediate() {
        goalValue = 400;
    }
    void setDifficultyChallenging() {
        goalValue = 500;
    }

    String getGoalString() {
        return "<html>" + "  Reach an elevation gain of " + goalValue + "<br>" + " kilometers" + "</html>";
    }
}
