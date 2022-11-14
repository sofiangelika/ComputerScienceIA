package com.company;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.File;
import java.util.ArrayList;

public class UserInterface {

    public UserInterface() {
        makeMainWindow();
    }

    void makeMainWindow() {
        JFrame frame1 = new JFrame("Cycling tracker");

        Container pane = frame1.getContentPane();
        pane.setLayout(new BorderLayout());
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color purple = new Color(218,202,251);
        Color green = Color.decode("#72bb53");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE dd. MMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        String dateString = dtf.format(now);

        JLabel dateLabel = new JLabel(dateString);
        Font f1 = new Font(Font.MONOSPACED, Font.PLAIN, 30);
        dateLabel.setFont(f1);
        dateLabel.setSize(200,100);

        JButton newEntryButton = new JButton("Add new entry");
        newEntryButton.setBackground(purple);
        newEntryButton.setOpaque(true);
        newEntryButton.setPreferredSize(new Dimension(190,60));
        newEntryButton.setBorderPainted(false);

        newEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser jfc = new JFileChooser();
                    jfc.showDialog(null, "Please Select the File");
                    jfc.setVisible(true);
                    File filename = jfc.getSelectedFile();
                    String str = filename.getPath();
                    EntryManager entryManager = new EntryManager();
                    Entry entryObject = entryManager.makeNewEntry(str);
                    frame1.setVisible(false);
                    makeGraphWindow(entryObject);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
//pass entryObject to Graph? does all ui stuff need to be in the constructor?
                //how to update main window (side panel w/ pass entries), is the constructor only called once?
            }


        });

        JButton editGoalsButton = new JButton("Edit goals");
        editGoalsButton.setBackground(purple);
        editGoalsButton.setOpaque(true);
        editGoalsButton.setPreferredSize(new Dimension(190,60));
        editGoalsButton.setBorderPainted(false);

        JPanel spacePanel = new JPanel();
        spacePanel.setBackground(green);

        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
        firstPanel.setBackground(green);
        firstPanel.add(dateLabel);
        firstPanel.add(spacePanel);
        firstPanel.add(newEntryButton); firstPanel.add(editGoalsButton);

        //add left most panel to main pane
        pane.add(firstPanel, BorderLayout.LINE_START);

        JProgressBar progressBar1 = new JProgressBar(0, 100);
        progressBar1.setMaximumSize(new Dimension(200,60));
        progressBar1.setMinimumSize(new Dimension(200,60));

        JProgressBar progressBar2 = new JProgressBar(0, 100);
        progressBar2.setSize(200,60);

        JProgressBar progressBar3 = new JProgressBar(0, 100);
        progressBar3.setSize(200,60);

        JProgressBar progressBar4 = new JProgressBar(0, 100);
        progressBar4.setSize(200,60);

        JLabel goal1 = new JLabel();
        goal1.setBackground(purple);
        goal1.setOpaque(true);
        JLabel goal2 = new JLabel();
        goal2.setBackground(purple);
        goal2.setOpaque(true);
        JLabel goal3 = new JLabel();
        goal3.setBackground(purple);
        goal3.setOpaque(true);
        JLabel goal4 = new JLabel();
        goal4.setBackground(purple);
        goal4.setOpaque(true);

        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BorderLayout());

        secondPanel.setLayout(new GridLayout(4,2,80,80));

        secondPanel.setBackground(green);
        secondPanel.add(goal1); secondPanel.add(progressBar1);
        secondPanel.add(goal2); secondPanel.add(progressBar2);
        secondPanel.add(goal3); secondPanel.add(progressBar3);
        secondPanel.add(goal4); secondPanel.add(progressBar4);

        pane.add(secondPanel, BorderLayout.CENTER);

        JPanel thirdPanel = new JPanel();
        thirdPanel.setBackground(purple);
        thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.Y_AXIS));

        JScrollPane purple_side_panel_scroll = new JScrollPane(thirdPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //This is where we create past entries side panel
        //I have to make an entryManager object since deserializeHeaders can't be static
        //Since I am saving the arrayList headers to an attribute
        EntryManager entryManager = new EntryManager();
        entryManager.deserializeHeaders();
        ArrayList<String[]> headers = entryManager.getHeaders();
        //if there are no past entries, checking with if
        if(!headers.isEmpty()) {
            for (String[] miniHeadersArray : headers) {
                String date = miniHeadersArray[0];
                String distance = miniHeadersArray[1];
                String time = miniHeadersArray[2];
                JButton past_entry = new JButton(date + "\n" + distance + "\n" + time);
                past_entry.setMinimumSize(new Dimension(200, 100));
                past_entry.setMaximumSize(new Dimension(200, 100));
                Border white_border = BorderFactory.createLineBorder(Color.white, 1);
                past_entry.setBorder(white_border);
                past_entry.setBackground(purple);
                past_entry.setOpaque(true);
                //past entry add action listener?
                thirdPanel.add(past_entry);
            }
        }
        pane.add(purple_side_panel_scroll, BorderLayout.LINE_END);

        frame1.setSize(1300, 700);
        frame1.setVisible(true);
    }

    void makeGraphWindow(Entry entry) {
        JFrame entryFrame = new JFrame("Entry");
        Color purple = new Color(218,202,251);
        entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        entryFrame.setSize(1300, 700);
        mainPanel.setSize(1300, 700);
        mainPanel.setBackground(purple);

        Color green = Color.decode("#72bb53");
        JButton homeButton = new JButton("Home");
        homeButton.setBackground(green);
        homeButton.setOpaque(true);
        homeButton.setPreferredSize(new Dimension(190,60));
        homeButton.setBorderPainted(false);

        //action listener to home button
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entryFrame.setVisible(false);
                makeMainWindow();
            }
        });

        mainPanel.add(homeButton);

        String date = entry.getDate();

        JLabel header = new JLabel(date);
        header.setMaximumSize(new Dimension(900, 200));
        header.setMinimumSize(new Dimension(900, 200));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(header);

        for (int i = 0; i < 6; i++) {
            ChartPanel panel1 = Graph.makeGraph(i, entry);
            panel1.setMinimumSize(new Dimension(900, 500));
            panel1.setMaximumSize(new Dimension(900, 500));
            mainPanel.add(panel1);
        }

        JScrollPane graph_scroll = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        graph_scroll.getVerticalScrollBar().setUnitIncrement(16);

        //Border border = BorderFactory.createLineBorder(purple, 20);

        entryFrame.add(graph_scroll);
        entryFrame.setVisible(true);
    }

}
