package com.company;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Entry implements Serializable {

    String date;
    double[][] data;
    private static final long serialVersionUID = 1L;

    Entry(String date, double[][] data) {
        this.date = date;
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public double[][] getData() {
        return data;
    }

    //return type is string because it's easier to use for labels and stuff
    public String getDistanceTravelled() {
        int colLength = data[0].length;
        double distanceTravelled = data[2][colLength - 1] / 1000;
        DecimalFormat df = new DecimalFormat("###.#");
        String distanceTravelledKm = df.format(distanceTravelled);
        return ("Distance: " + distanceTravelledKm + " km");
    }

    public String getTimeElapsed() {
        int colLength = data[0].length;
        double startTime = data[6][0];
        double endTime = data[6][colLength - 1];
        double seconds = endTime - startTime;
        int hours = (int)seconds / 3600;
        int minutes = (int)(seconds % 3600) / 60;
        return (hours + " h " + minutes + " min");

    }

}
