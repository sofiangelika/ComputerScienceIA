package com.company;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

public class Graph {

    public Graph() {
        JFrame entry = new JFrame("Entry");
        entry.setLayout(null);
        entry.setSize(1300, 700);
        Color purple = new Color(218,202,251);
        DefaultCategoryDataset dataset1 = createDataset();
        DefaultCategoryDataset dataset2 = createDataset2();
        ChartPanel panel1 = makeGraph(dataset1);
        ChartPanel panel2 = makeGraph(dataset2);

        panel1.setSize(600,400);
        panel2.setSize(600,400);
        panel1.setLocation(5,5);
        panel2.setLocation(5,500);

        JPanel purple_side_panel = new JPanel();
        purple_side_panel.setPreferredSize(new java.awt.Dimension(1300, 3000));
        purple_side_panel.setLayout(null);
        purple_side_panel.setBackground(purple);
        JScrollPane purple_side_panel_scroll = new JScrollPane(purple_side_panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        purple_side_panel_scroll.setSize(1300, 700);

        entry.add(purple_side_panel_scroll);

        purple_side_panel.add(panel1);
        purple_side_panel.add(panel2);

        entry.setVisible(true);
    }

    public ChartPanel makeGraph(DefaultCategoryDataset dataset) {
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "New entry", // Chart title
                "Time (s)", // X-Axis Label
                "Values", // Y-Axis Label
                dataset
        );

        return new ChartPanel(chart);
    }


    private DefaultCategoryDataset createDataset() {

        String series1 = "Heart rate";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(200, series1, "2016-12-19");
        dataset.addValue(150, series1, "2016-12-20");
        dataset.addValue(100, series1, "2016-12-21");
        dataset.addValue(210, series1, "2016-12-22");
        dataset.addValue(240, series1, "2016-12-23");
        dataset.addValue(195, series1, "2016-12-24");
        dataset.addValue(245, series1, "2016-12-25");

        return dataset;
    }

     private DefaultCategoryDataset createDataset2() {

        String series1 = "Carrots";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(100, series1, "2016-12-19");
        dataset.addValue(50, series1, "2016-12-20");
        dataset.addValue(10, series1, "2016-12-21");
        dataset.addValue(10, series1, "2016-12-22");
        dataset.addValue(20, series1, "2016-12-23");
        dataset.addValue(195, series1, "2016-12-24");
        dataset.addValue(205, series1, "2016-12-25");

        return dataset;
    }

}