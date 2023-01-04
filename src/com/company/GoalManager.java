package com.company;

import java.io.*;

public class GoalManager {
    //heart rate 0; cadence 1; distance 2; speed 3; altitude 4; temp 5; time 6
    public Goal[] goals = new Goal[4];

    void serializeGoals() {

        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("data/goals.xml");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(goals);

            out.close();
            file.close();

            System.out.println("Goals have been serialized");

        }

        catch(IOException ex) {
            System.out.println("got exception in serializing goals: " + ex);
        }

    }

    void deSerializeGoals() {
        try {
            FileInputStream file = new FileInputStream("data/goals.xml");
            ObjectInputStream in = new ObjectInputStream(file);

            this.goals = (Goal[]) in.readObject();

            in.close();
            file.close();

            System.out.println("Goals have been deserialized ");
        }

        catch(IOException ex) {
            System.out.println("IOException is caught in deserialization of goals: " + ex);
        }

        catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    void updateGoals(double[][] data) {
        deSerializeGoals();
        //Distance goal
        int colLength = data[0].length;
        double distanceTravelled = data[2][colLength - 1] / 1000; //get distance travelled in km

        DistanceGoal distanceGoal = (DistanceGoal) goals[0];
        distanceGoal.updateProgress(distanceTravelled);

        //Elevation goal
        double maxElevation = 0;
        for (double value : data[4]) {
            if (value > maxElevation)
                maxElevation = value;
        }

        ElevationGoal elevationGoal = (ElevationGoal) goals[1];
        elevationGoal.updateProgress(maxElevation);

        //Time goal
        double startTime = data[6][0];
        double endTime = data[6][colLength - 1];
        double seconds = endTime - startTime;
        double hours = seconds / 3600;

        TimeGoal timeGoal = (TimeGoal) goals[2];
        timeGoal.updateProgress(hours);

        //Set days goal
        SetDaysGoal setDaysGoal = (SetDaysGoal) goals[3];
        setDaysGoal.updateProgress(1);
        serializeGoals();
    }

}
