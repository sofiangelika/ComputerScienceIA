package com.company;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph extends JFrame {

    public Graph(String title) {
        super(title);
        // Create dataset
        DefaultCategoryDataset dataset = createDataset();
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "New entry", // Chart title
                "Time (s)", // X-Axis Label
                "Values", // Y-Axis Label
                dataset
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Graph example = new Graph("New entry");
            example.setAlwaysOnTop(true);
            example.pack();
            example.setSize(1300, 700);
            example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}