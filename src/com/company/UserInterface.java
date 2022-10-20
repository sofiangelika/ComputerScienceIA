package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        newEntryButton.setBackground(purple);
        newEntryButton.setOpaque(true);
        newEntryButton.setLocation(100,190);
        newEntryButton.setSize(190,60);
        newEntryButton.setBorderPainted(false);
        pane.add(newEntryButton);

        JButton editGoalsButton = new JButton("Edit goals");
        editGoalsButton.setBackground(purple);
        editGoalsButton.setOpaque(true);
        editGoalsButton.setLocation(100,290);
        editGoalsButton.setSize(190,60);
        editGoalsButton.setBorderPainted(false);
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

        JPanel purple_side_panel = new JPanel();
        purple_side_panel.setOpaque(true);
        pane.add(purple_side_panel);
        JScrollPane purple_side_panel_scroll = new JScrollPane(purple_side_panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        purple_side_panel_scroll.setBounds(1000,0,300,1300);
        purple_side_panel_scroll.setBackground(purple); //issue

        pane.add(purple_side_panel_scroll);

        JLabel past_entry = new JLabel();
        past_entry.setBounds(1000, 0, 300, 100);
        Border white_border = BorderFactory.createLineBorder(Color.black, 2);
        past_entry.setBorder(white_border);
        past_entry.setOpaque(true);

        past_entry.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        frame1.setSize(1300, 700);
        frame1.setVisible(true);
    }

}
