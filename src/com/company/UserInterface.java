package com.company;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class UserInterface {
    public UserInterface() {
        JFrame frame1 = new JFrame("Main window");
        Container pane = frame1.getContentPane();
        pane.setLayout(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane.setBackground(Color.decode("#72bb53"));
        Color purple = new Color(218,202,251);

        JButton newEntryButton = new JButton("Add new entry");
        newEntryButton.setBackground(purple); //issue
        newEntryButton.setLocation(100,190);
        newEntryButton.setSize(190,60);
        pane.add(newEntryButton);

        JButton editGoalsButton = new JButton("Edit goals");
        editGoalsButton.setBackground(purple); //issue
        editGoalsButton.setLocation(100,290);
        editGoalsButton.setSize(190,60);
        pane.add(editGoalsButton);

        JProgressBar progressBar1 = new JProgressBar(0);
        progressBar1.setSize(200,60);
        progressBar1.setLocation(700, 140);
        pane.add(progressBar1);

        JProgressBar progressBar2 = new JProgressBar(0);
        progressBar2.setSize(200,60);
        progressBar2.setLocation(700, 240);
        pane.add(progressBar2);

        JProgressBar progressBar3 = new JProgressBar(0);
        progressBar3.setSize(200,60);
        progressBar3.setLocation(700, 340);
        pane.add(progressBar3);

        JProgressBar progressBar4 = new JProgressBar(0);
        progressBar4.setSize(200,60);
        progressBar4.setLocation(700, 440);
        pane.add(progressBar4);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE dd. MMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        String dateString = dtf.format(now);

        JLabel dateLabel = new JLabel(dateString);
        Font f1 = new Font(Font.MONOSPACED, Font.PLAIN, 30);
        dateLabel.setFont(f1);
        dateLabel.setBounds(50,50,400,70);
        pane.add(dateLabel);

        JTextArea textArea = new JTextArea(20, 20);
        JScrollPane scrollableTextArea = new JScrollPane(textArea);

        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setBounds(1000,0,300,1300);
        scrollableTextArea.setBackground(purple); //issue

        pane.add(scrollableTextArea);

        frame1.setSize(1300, 700);
        frame1.setVisible(true);
    }

}
