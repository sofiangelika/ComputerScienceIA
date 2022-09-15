package com.company;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class UserInterface extends JFrame {
    public void mainWindow() {
        JFrame frame1 = new JFrame("Main window");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton newEntryButton = new JButton("Add new entry");
        newEntryButton.setLocation(100,190);
        newEntryButton.setSize(190,60);
        frame1.add(newEntryButton);

        JButton editGoalsButton = new JButton("Edit goals");
        editGoalsButton.setLocation(100,290);
        editGoalsButton.setSize(190,60);
        frame1.add(editGoalsButton);

        JProgressBar progressBar1 = new JProgressBar(0);
        progressBar1.setSize(200,60);
        progressBar1.setLocation(700, 140);
        frame1.add(progressBar1);

        JProgressBar progressBar2 = new JProgressBar(0);
        progressBar2.setSize(200,60);
        progressBar2.setLocation(700, 240);
        frame1.add(progressBar2);

        JProgressBar progressBar3 = new JProgressBar(0);
        progressBar3.setSize(200,60);
        progressBar3.setLocation(700, 340);
        frame1.add(progressBar3);

        JProgressBar progressBar4 = new JProgressBar(0);
        progressBar4.setSize(200,60);
        progressBar4.setLocation(700, 440);
        frame1.add(progressBar4);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE" + ", " + "dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String dateString = dtf.format(now);

        JLabel dateLabel = new JLabel(dateString);
        dateLabel.setBounds(20,20,150,50);
        frame1.add(dateLabel);

        frame1.setSize(1300, 700);
        frame1.setLayout(null);
        frame1.setVisible(true);
    }

}
