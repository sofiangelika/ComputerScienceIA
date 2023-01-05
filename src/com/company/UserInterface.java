package com.company;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class UserInterface {

    public UserInterface() {
        makeMainWindow();
    }

    void makeMainWindow() {
        JFrame frame1 = new JFrame("Cycling tracker");

        Container pane = frame1.getContentPane();
        pane.setLayout(new BorderLayout());
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color purple = new Color(218, 202, 251);
        Color green = Color.decode("#72bb53");
        Border greenBorder = BorderFactory.createLineBorder(green, 25);
        Color darkPurple = Color.decode("#8b51f5");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE dd. MMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        String dateString = dtf.format(now);

        JLabel dateLabel = new JLabel(dateString);
        Font f1 = new Font(Font.SANS_SERIF, Font.ITALIC, 30);
        dateLabel.setFont(f1);
        dateLabel.setSize(200, 100);
        dateLabel.setBorder(greenBorder);

        JButton newEntryButton = new JButton("Add new entry");
        newEntryButton.setBackground(purple);
        newEntryButton.setOpaque(true);
        newEntryButton.setMinimumSize(new Dimension(230, 100));
        newEntryButton.setMaximumSize(new Dimension(230, 100));

        newEntryButton.setBorder(greenBorder);



        newEntryButton.addActionListener(e -> {
            try {
                JFileChooser jfc = new JFileChooser();
                jfc.showDialog(null, "Please Select the File");
                jfc.setVisible(true);
                File filename = jfc.getSelectedFile();
                String str = filename.getPath();

                //checking if the file is valid
                if (!getFileExtension(str).equals("fit")) {
                    System.out.println("file is checked");
                    JFrame popUpFrame = new JFrame();
                    popUpFrame.setSize(400, 300);
                    popUpFrame.setBackground(purple);

                    JPanel popUpPanel = new JPanel();
                    popUpPanel.setBackground(purple);
                    popUpPanel.setSize(400, 300);

                    popUpFrame.add(popUpPanel);

                    JLabel popUpLabel = new JLabel("<html> The file you've chosen is not a fit file. <br> Please choose another file.</html>");
                    popUpPanel.add(popUpLabel);
                    popUpLabel.setForeground(darkPurple);

                    popUpFrame.setVisible(true);
                    popUpFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                }
                else {
                    EntryManager entryManager = new EntryManager();
                    Entry entryObject = entryManager.makeNewEntry(str);
                    frame1.setVisible(false);
                    makeGraphWindow(entryObject);
                }
            } catch (Exception exception) {
                System.out.println("Got exception: " + exception);
            }
//pass entryObject to Graph? does all ui stuff need to be in the constructor?
            //how to update main window (side panel w/ pass entries), is the constructor only called once?
        });

        JButton editGoalsButton = new JButton("Edit goals");
        editGoalsButton.setBackground(purple);
        editGoalsButton.setOpaque(true);
        editGoalsButton.setMinimumSize(new Dimension(230, 100));
        editGoalsButton.setMaximumSize(new Dimension(230, 100));

        editGoalsButton.setBorder(greenBorder);
        editGoalsButton.addActionListener(e -> {
            frame1.setVisible(false);
            makeEditGoalsWindow();
        });

        JPanel spacePanel = new JPanel();
        spacePanel.setBackground(green);
        //spacePanel.setMinimumSize(new Dimension(200, 200));

        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
        firstPanel.setBackground(green);
        firstPanel.add(dateLabel);
        //firstPanel.add(spacePanel);
        firstPanel.add(newEntryButton);
        firstPanel.add(editGoalsButton);

        //add left most panel to main pane
        pane.add(firstPanel, BorderLayout.LINE_START);

        GoalManager goalManager = new GoalManager();

        goalManager.deSerializeGoals();

        DistanceGoal distanceGoal = (DistanceGoal) goalManager.goals[0];
        JProgressBar progressBar1 = new JProgressBar(0, (int) distanceGoal.getGoalValue());
        progressBar1.setValue((int) distanceGoal.getProgressValue());
        progressBar1.setBorder(greenBorder);

        ElevationGoal elevationGoal = (ElevationGoal) goalManager.goals[1];
        JProgressBar progressBar2 = new JProgressBar(0, (int) elevationGoal.getGoalValue());
        progressBar2.setValue((int) elevationGoal.getProgressValue());
        progressBar2.setBorder(greenBorder);

        TimeGoal timeGoal = (TimeGoal) goalManager.goals[2];
        JProgressBar progressBar3 = new JProgressBar(0, (int) timeGoal.getGoalValue());
        progressBar3.setValue((int) timeGoal.getProgressValue());
        progressBar3.setBorder(greenBorder);

        SetDaysGoal setDaysGoal = (SetDaysGoal) goalManager.goals[3];
        JProgressBar progressBar4 = new JProgressBar(0, (int) setDaysGoal.getGoalValue());
        progressBar4.setValue((int) setDaysGoal.getProgressValue());
        progressBar4.setBorder(greenBorder);

        JLabel goal1 = new JLabel(distanceGoal.getGoalString());
        goal1.setBackground(purple);
        goal1.setBorder(greenBorder);
        goal1.setOpaque(true);

        JLabel goal2 = new JLabel(elevationGoal.getGoalString());
        goal2.setBackground(purple);
        goal2.setBorder(greenBorder);
        goal2.setOpaque(true);

        JLabel goal3 = new JLabel(timeGoal.getGoalString());
        goal3.setBackground(purple);
        goal3.setBorder(greenBorder);
        goal3.setOpaque(true);

        JLabel goal4 = new JLabel(setDaysGoal.getGoalString());
        goal4.setBackground(purple);
        goal4.setBorder(greenBorder);
        goal4.setOpaque(true);

        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BorderLayout());

        secondPanel.setLayout(new GridLayout(4, 2, 80, 80));

        secondPanel.setBackground(green);

        secondPanel.add(goal1);
        secondPanel.add(progressBar1);
        secondPanel.add(goal2);
        secondPanel.add(progressBar2);
        secondPanel.add(goal3);
        secondPanel.add(progressBar3);
        secondPanel.add(goal4);
        secondPanel.add(progressBar4);

        goalManager.serializeGoals();

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
        if (!headers.isEmpty()) {
            for (int i = 0; headers.size() > i; i++) {
                String[] miniHeadersArray = headers.get(i);
                String date = miniHeadersArray[0];
                String distance = miniHeadersArray[1];
                String time = miniHeadersArray[2];
                JButton pastEntryButton = new JButton("<html>" + date + "<br>" + distance + "<br>" + time + "</html>");
                pastEntryButton.setMinimumSize(new Dimension(350, 150));
                pastEntryButton.setMaximumSize(new Dimension(350, 150));

                final int other_i = i; //an action listener for each past entry button
                pastEntryButton.addActionListener(e -> {
                    Entry pastEntry = entryManager.getPastEntry(other_i);
                    makeGraphWindow(pastEntry);
                });


                Border white_border = BorderFactory.createLineBorder(Color.white, 1);
                pastEntryButton.setBorder(white_border);
                pastEntryButton.setBackground(purple);
                pastEntryButton.setOpaque(true);
                //past entry add action listener?
                thirdPanel.add(pastEntryButton);
            }
        }
        pane.add(purple_side_panel_scroll, BorderLayout.LINE_END);

        Date dateStarted = distanceGoal.getDateStarted();

        if (distanceGoal.hasMonthPassed(new Date(), dateStarted)) {
            System.out.println("a month has passed");
            JFrame popUpFrame = new JFrame();
            popUpFrame.setSize(400, 300);
            popUpFrame.setBackground(purple);

            JPanel popUpPanel = new JPanel();
            popUpPanel.setBackground(purple);
            popUpPanel.setSize(400, 300);

            popUpFrame.add(popUpPanel);

            JLabel popUpLabel = new JLabel("<html> A month has passed! <br> Would you like to change the difficulty of your goals? </html>");
            popUpPanel.add(popUpLabel);
            popUpLabel.setForeground(darkPurple);

            JButton popUpButton1 = new JButton();
            JButton popUpButton2 = new JButton();
            JButton popUpButton3 = new JButton();

            popUpButton1.setBackground(green);
            popUpButton1.setOpaque(true);
            popUpButton1.setText("Easy");
            popUpButton1.setBorderPainted(false);
            popUpButton1.setForeground(Color.white);

            popUpButton2.setBackground(green);
            popUpButton2.setOpaque(true);
            popUpButton2.setText("Intermediate");
            popUpButton2.setBorderPainted(false);
            popUpButton2.setForeground(Color.white);

            popUpButton3.setBackground(green);
            popUpButton3.setOpaque(true);
            popUpButton3.setText("Difficult");
            popUpButton3.setBorderPainted(false);
            popUpButton3.setForeground(Color.white);

            popUpButton1.addActionListener(e -> {
                distanceGoal.setDifficultyEasy();
                elevationGoal.setDifficultyEasy();
                timeGoal.setDifficultyEasy();
                setDaysGoal.setDifficultyEasy();
                goalManager.serializeGoals();
            });

            popUpButton2.addActionListener(e -> {
                distanceGoal.setDifficultyIntermediate();
                elevationGoal.setDifficultyIntermediate();
                timeGoal.setDifficultyIntermediate();
                setDaysGoal.setDifficultyIntermediate();
                goalManager.serializeGoals();
            });

            popUpButton3.addActionListener(e -> {
                distanceGoal.setDifficultyChallenging();
                elevationGoal.setDifficultyChallenging();
                timeGoal.setDifficultyChallenging();
                setDaysGoal.setDifficultyChallenging();
                goalManager.serializeGoals();
            });

            popUpPanel.add(popUpButton1);
            popUpPanel.add(popUpButton2);
            popUpPanel.add(popUpButton3);

            popUpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            popUpFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    goalManager.deSerializeGoals();
                }
            });

            popUpFrame.setVisible(true);
            popUpFrame.setAlwaysOnTop(true);
        }

        frame1.setSize(1300, 700);
        frame1.setVisible(true);
    }

    void makeGraphWindow(Entry entry) {
        JFrame entryFrame = new JFrame("Entry");
        Color purple = new Color(218, 202, 251);
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
        homeButton.setPreferredSize(new Dimension(190, 60));
        homeButton.setBorderPainted(false);

        //action listener to home button
        homeButton.addActionListener(e -> {
            entryFrame.setVisible(false);
            makeMainWindow();
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

        entryFrame.add(graph_scroll);
        entryFrame.setVisible(true);
    }

    void makeEditGoalsWindow() {
        JFrame editGoalsFrame = new JFrame("Edit goals");

        JPanel mainPanel = new JPanel();
        GoalManager goalManager = new GoalManager();
        goalManager.deSerializeGoals();

        editGoalsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editGoalsFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                goalManager.serializeGoals();
            }
        });

        mainPanel.setLayout(new GridLayout(6, 4, 10, 10));

        editGoalsFrame.setSize(1300, 700);
        mainPanel.setSize(1300, 700);

        Color purple = new Color(218, 202, 251);
        Color green = Color.decode("#72bb53");
        Border greenBorder = BorderFactory.createLineBorder(green, 20);

        mainPanel.setBackground(green);

        //Distance goal
        DistanceGoal distanceGoal = (DistanceGoal) goalManager.goals[0];
        JLabel label1 = new JLabel(distanceGoal.getGoalString());
        label1.setBackground(purple);
        label1.setBorder(greenBorder);
        label1.setOpaque(true);

        JProgressBar bar1 = new JProgressBar(0, (int) distanceGoal.getGoalValue());
        bar1.setValue((int)distanceGoal.getProgressValue());
        bar1.setBorder(greenBorder);

        JLabel value1 = new JLabel(String.valueOf(distanceGoal.getGoalValue()));
        value1.setBackground(purple);
        value1.setOpaque(true);
        value1.setBorder(greenBorder);

        JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 0, 400, (int) distanceGoal.getGoalValue());
        slider1.addChangeListener(e -> {
            int value = slider1.getValue();
            distanceGoal.setGoalValue(value);
            bar1.setMaximum(value);
            value1.setText(String.valueOf(value));
        });

        //Elevation goal
        ElevationGoal elevationGoal = (ElevationGoal) goalManager.goals[1];
        JLabel label2 = new JLabel(elevationGoal.getGoalString());
        label2.setBackground(purple);
        label2.setBorder(greenBorder);
        label2.setOpaque(true);

        JProgressBar bar2 = new JProgressBar(0, (int) elevationGoal.getGoalValue());
        bar2.setValue((int)elevationGoal.getProgressValue());
        bar2.setBorder(greenBorder);

        JLabel value2 = new JLabel(String.valueOf(elevationGoal.getGoalValue()));
        value2.setBackground(purple);
        value2.setOpaque(true);
        value2.setBorder(greenBorder);

        JSlider slider2 = new JSlider(JSlider.HORIZONTAL, 0, 500, (int) elevationGoal.getGoalValue());
        slider2.addChangeListener(e -> {
            int value = slider2.getValue();
            elevationGoal.setGoalValue(value);
            bar2.setMaximum(value);
            value2.setText(String.valueOf(value));
        });

        //Time goal
        TimeGoal timeGoal = (TimeGoal) goalManager.goals[2];
        JLabel label3 = new JLabel(timeGoal.getGoalString());
        label3.setBackground(purple);
        label3.setBorder(greenBorder);
        label3.setOpaque(true);

        JProgressBar bar3 = new JProgressBar(0, (int) timeGoal.getGoalValue());
        bar3.setValue((int)timeGoal.getProgressValue());
        bar3.setBorder(greenBorder);

        JLabel value3 = new JLabel(String.valueOf(timeGoal.getGoalValue()));
        value3.setBackground(purple);
        value3.setOpaque(true);
        value3.setBorder(greenBorder);

        JSlider slider3 = new JSlider(JSlider.HORIZONTAL, 0, 30, (int) timeGoal.getGoalValue());
        slider3.addChangeListener(e -> {
            int value = slider3.getValue();
            timeGoal.setGoalValue(value);
            bar3.setMaximum(value);
            value3.setText(String.valueOf(value));
        });

        //Set days goal
        SetDaysGoal setDaysGoal = (SetDaysGoal) goalManager.goals[3];
        JLabel label4 = new JLabel(setDaysGoal.getGoalString());
        label4.setBackground(purple);
        label4.setBorder(greenBorder);
        label4.setOpaque(true);

        JProgressBar bar4 = new JProgressBar(0, (int) setDaysGoal.getGoalValue());
        bar4.setValue((int)setDaysGoal.getProgressValue());
        bar4.setBorder(greenBorder);

        JLabel value4 = new JLabel(String.valueOf(setDaysGoal.getGoalValue()));
        value4.setBackground(purple);
        value4.setOpaque(true);
        value4.setBorder(greenBorder);

        JSlider slider4 = new JSlider(JSlider.HORIZONTAL, 0, 30, (int) setDaysGoal.getGoalValue());
        slider4.addChangeListener(e -> {
            int value = slider4.getValue();
            setDaysGoal.setGoalValue(value);
            bar4.setMaximum(value);
            value4.setText(String.valueOf(value));
        });

        JLabel goalsLabel = new JLabel("Goals");
        goalsLabel.setBackground(green);
        goalsLabel.setBorder(greenBorder);
        JLabel adjustLabel = new JLabel("Adjust");
        adjustLabel.setBackground(green);
        adjustLabel.setBorder(greenBorder);
        JLabel progressLabel = new JLabel("Progress");
        progressLabel.setBackground(green);
        progressLabel.setBorder(greenBorder);
        JLabel valueLabel = new JLabel("Value");
        progressLabel.setBackground(green);
        progressLabel.setBorder(greenBorder);

        JButton homeButton = new JButton("Home");
        homeButton.setBackground(purple);
        homeButton.setOpaque(true);
        homeButton.setBorder(greenBorder);

        //action listener to home button
        homeButton.addActionListener(e -> {
            editGoalsFrame.setVisible(false);
            goalManager.serializeGoals();
            makeMainWindow();
        });


        String[] difficultyStrings = {"Easy", "Intermediate", "Challenging"};
        JComboBox difficulties = new JComboBox(difficultyStrings);
        difficulties.setBorder(greenBorder);

        //add actionlistner to listen for change
        ActionListener cbActionListener = e -> {

            String s = (String) difficulties.getSelectedItem();//get the selected item

            switch (s) {//check for a match
                case "Easy" -> {
                    distanceGoal.setDifficultyEasy();
                    elevationGoal.setDifficultyEasy();
                    timeGoal.setDifficultyEasy();
                    setDaysGoal.setDifficultyEasy();
                }
                case "Intermediate" -> {
                    distanceGoal.setDifficultyIntermediate();
                    elevationGoal.setDifficultyIntermediate();
                    timeGoal.setDifficultyIntermediate();
                    setDaysGoal.setDifficultyIntermediate();
                }
                case "Challenging" -> {
                    distanceGoal.setDifficultyChallenging();
                    elevationGoal.setDifficultyChallenging();
                    timeGoal.setDifficultyChallenging();
                    setDaysGoal.setDifficultyChallenging();
                }

            }
        };

        difficulties.addActionListener(cbActionListener);

        JLabel blankLabel1 = new JLabel();
        blankLabel1.setBackground(green);
        blankLabel1.setOpaque(true);

        JLabel blankLabel2 = new JLabel();
        blankLabel2.setBackground(green);
        blankLabel2.setOpaque(true);

        mainPanel.add(difficulties);
        mainPanel.add(homeButton);
        mainPanel.add(blankLabel1);
        mainPanel.add(blankLabel2);

        mainPanel.add(goalsLabel);
        mainPanel.add(adjustLabel);
        mainPanel.add(valueLabel);
        mainPanel.add(progressLabel);

        mainPanel.add(label1);
        mainPanel.add(slider1);
        mainPanel.add(value1);
        mainPanel.add(bar1);

        mainPanel.add(label2);
        mainPanel.add(slider2);
        mainPanel.add(value2);
        mainPanel.add(bar2);

        mainPanel.add(label3);
        mainPanel.add(slider3);
        mainPanel.add(value3);
        mainPanel.add(bar3);

        mainPanel.add(label4);
        mainPanel.add(slider4);
        mainPanel.add(value4);
        mainPanel.add(bar4);

        editGoalsFrame.add(mainPanel);
        editGoalsFrame.setVisible(true);
    }

    public static String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

}
