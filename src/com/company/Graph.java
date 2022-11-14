package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph{

    static public ChartPanel makeGraph(int i, Entry entry) {
        // Create chart
        Entry entryData = entry; //get entry object (entryData) from entry manager
        double[][] data = entryData.getData();

        JFreeChart chart;
            String variable = findString(i);
            DefaultCategoryDataset dataset = createDataset(data, i, variable);

            chart = ChartFactory.createLineChart(
                    variable, // Chart title
                    "Time (s)", // X-Axis Label
                    "Values", // Y-Axis Label
                    dataset
            );


        return new ChartPanel(chart);
    }


    static private DefaultCategoryDataset createDataset(double[][] data, int variable, String type) {
        //heart rate 0; cadence 1; distance 2; speed 3; altitude 4; temp 5; time 6

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < data[0].length; i++) {
            Number number = data[variable][i]; //must make into number or else you get an ambiguous method call error
            dataset.addValue(number, type, data[6][i]); //the parameter of addValue is a Number
        }

        return dataset;
    }

    static String findString(int variable) {
        String type = switch (variable) {
            case 0 -> "heart rate";
            case 1 -> "cadence";
            case 2 -> "distance";
            case 3 -> "speed";
            case 4 -> "altitude";
            case 5 -> "temperature";
            default -> "";
        };
        return type;
    }

}